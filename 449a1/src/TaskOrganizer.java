import java.io.*;
import java.util.StringTokenizer;

public class TaskOrganizer {
	private final String input = "ABCDEFGH";
	private final char empty = 'X';
	
	private int [][] linePen;
	private Node [] tooNearPen;
	private Node [] forcedPart;
	private Node [] forbiddenM;
	private Node [] tooNearTask;
	private HardConstraints HC;
	private SoftConstraints SC;
	private Tree sTree;
	private String inputFile;
	private String outputFile;
	
	public TaskOrganizer(){}
	public TaskOrganizer(String inputFile, String outputFile){
		linePen = new int[8][8];
		tooNearPen = new Node[8];
		forcedPart = new Node[8];
		forbiddenM = new Node[8];
		tooNearTask = new Node[8];
		this.inputFile = inputFile;
		this.outputFile = outputFile;		
		HC = null;
		SC = null;
	}
	
	public void readFile() throws IOException{
		String line;
		int keyWordCount = 0;
		FileReader fr;
		BufferedReader br;
		try {
			fr = new FileReader(inputFile);
			br = new BufferedReader(fr);
			while((line = br.readLine()) != null){
				if (line.equalsIgnoreCase("forced partial assignment:")){
					forcedPartialAssign(br);
					keyWordCount++;					
				}
				else if (line.equalsIgnoreCase("forbidden machine:")){
					forbiddenMachine(br);
					keyWordCount++;					
				}
				else if (line.equalsIgnoreCase("too-near tasks:")){
					tooNearTask(br);
					keyWordCount++;					
				}
				else if (line.equalsIgnoreCase("machine penalties:")){
					machinePenalties(br);
					keyWordCount++;					
				}
				else if (line.equalsIgnoreCase("too-near penalities")){
					tooNearPen(br);
					keyWordCount++;
				}
				else if(line.equalsIgnoreCase("Name:")){//added to handle when name title is read, garbage
					line = br.readLine();//added to test name, garbage
				}
				else if(line.startsWith(" "));//added to handle line with spaces, garbage
				else if(line.length() == 0);//added to handle blank line, garbage
				else
					throw new ParsingInputException();//added to throw error when garbage is read, garbage
			}
			fr.close();
			if (keyWordCount != 5)
				throw new ParsingInputException();
			HC = new HardConstraints(forcedPart, forbiddenM , tooNearTask);
			SC = new SoftConstraints(tooNearPen, linePen);
		} catch (FileNotFoundException e){
			e.printStackTrace();
		} catch (PartialAssignmentException e){
			writeOutput("partial assignment error", outputFile);
		} catch (ParsingInputException e){
			writeOutput("Error while parsing input file", outputFile);
		} catch (InvalidInputException e){
			writeOutput("invalid machine/task", outputFile);
		} catch (MachinePenException e){
			writeOutput("machine penalty error", outputFile);
		} catch (InvalidPenException e){
			writeOutput("invalid penalty", outputFile);
		} catch (InvalidTaskException e){
			writeOutput("invalid task", outputFile);
		}		
	}
	public void runSearch()throws FileNotReadException, IOException{
		if (HC != null){
			String output;
			int score;
			sTree = new Tree(HC, SC);

			sTree.searchR(input, "", 1, 0, empty);
			output = sTree.getBestString();
			score = sTree.getBestScore();
			if (!output.equalsIgnoreCase("No valid solution possible!"))
				output = "Solution " + output + "; Quality:" + score;
			writeOutput(output, outputFile);
		}
		else{
			throw new FileNotReadException(); 
		}
			
	}
	/*******
	 * @param br
	 * @throws IOException
	 * @throws PartialAssignmentException
	 */
	private void forcedPartialAssign(BufferedReader br) throws IOException, PartialAssignmentException, InvalidInputException{
		String compare = input;
		String line;
		String [] temp;
		CharSequence seq;
		char c;
		int i;
		while(((line = br.readLine()).length() > 0) && ((line.charAt(0)) != ' ')){
			c = line.charAt(3);
			i = ((line.charAt(1) - 49)); //Changed to calculate number differently, wrongmachine
			if(i >= 8)	//added to check if number if greater then 8
				throw new InvalidInputException();
			seq = Character.toString((c));
			if ((forcedPart[i] == null) && (compare.contains(seq)))
				forcedPart[i] = new forcedNode(line.charAt(1), line.charAt(3));
			else{
				if ((c >= 65) && (c <= 72))
					throw new PartialAssignmentException();
				else 
					throw new InvalidInputException();				
			}
			temp = compare.split(Character.toString(c));
			if (temp.length > 1){
				compare = temp[0] + temp[1];
			}
			else if (temp.length == 1) {
				compare = temp[0];
			}
			else
				compare = "";
			if (line.charAt(4) != ')')
				throw new InvalidInputException();	
		}
	}
	/*******
	 * 
	 * @param br
	 * @throws IOException
	 * @throws MachinePenException
	 */
	private void machinePenalties(BufferedReader br) throws IOException, MachinePenException{
		int i = 0;
		int j = 0;
		StringTokenizer st;
		String line;
		String numb;
		try{
			while(((line = br.readLine()).length() > 0)&&((line.charAt(0)) != ' ')){
				st = new StringTokenizer(line);
				j = 0;
				while (st.hasMoreTokens()){
					numb = st.nextToken();
					linePen[i][j] = Integer.parseInt(numb);
					j++;
				}
				if (j <= 7){
					throw new MachinePenException();
				}
				i++;
			}
			if (i <= 7){
				throw new MachinePenException();
			}
		}
		catch (ArrayIndexOutOfBoundsException e){
			throw new MachinePenException();
		}
		catch (NumberFormatException e){
			throw new InvalidPenException();
		}
	}
	/*******
	 * 
	 * @param br
	 * @throws IOException
	 */
	private void forbiddenMachine(BufferedReader br) throws IOException{
		String line;
		Node current;
		int i;
		while (((line = br.readLine()).length() > 0)&&((line.charAt(0)) != ' ')){
			i = ((line.charAt(1) - 1) %8);
			if (forbiddenM[i] == null)
				forbiddenM[i] = new forbiddenNode(line.charAt(1), line.charAt(3));
			else{
				current = forbiddenM[i];
				while (current.getNext() != null){
					current = current.getNext();
				}						
				current.setNext(new forbiddenNode(line.charAt(1), line.charAt(3)));
			}
			if (line.charAt(4) != ')')
				throw new InvalidInputException();	
		}	
	}
	/*******
	 * 
	 * @param br
	 * @throws IOException
	 */
	private void tooNearTask(BufferedReader br) throws IOException{
		String line;
		int i;
		Node current;
		
		while(((line = br.readLine()).length() > 0)&&((line.charAt(0)) != ' ')){
			i = (line.charAt(1) - 1)%8;
			if (tooNearTask[i] == null)
				tooNearTask[i] = new nearTaskNode(line.charAt(1), line.charAt(3));
			else{
				current = tooNearTask[i];
				while (current.getNext() != null){
					current = current.getNext();
				}						
				current.setNext(new nearTaskNode(line.charAt(1), line.charAt(3)));
			}
			if (line.charAt(4) != ')')
				throw new InvalidInputException();	
		}
	}
	/*******
	 * 
	 * @param br
	 * @throws IOException
	 */
	private void tooNearPen(BufferedReader br) throws IOException{
		String line;
		String [] parts;
		String penValue;
		int value;
		int i;
		Node current;
		try{
			while(((line = br.readLine()).length() > 0)&&((line.charAt(0)) != ' ')){
				i = (line.charAt(1) - 1)%8;
				parts = line.split(",");
				penValue = parts[2].substring(0, (parts[2].length() - 1));
				value = Integer.parseInt(penValue);
				if (tooNearPen[i] == null)
					tooNearPen[i] = new nearPenNode((parts[0]).charAt(1), (parts[1]).charAt(0), value);
				else{
					current = tooNearPen[i];
					while (current.getNext() != null){
						current = current.getNext();
					}						
					current.setNext(new nearPenNode((parts[0]).charAt(1), (parts[1]).charAt(0), value));
				}
			}
		}catch(NullPointerException e){
		}catch(NumberFormatException e){
			throw new InvalidPenException();//Changed from wrong exception, wrongnumbertoonear
		}		
	}
	
	/*******
	 * Creates output file
	 * write output to file and prints to screen
	 * closes file
	 * @param output String that is to be written to the output file
	 * @throws IOException
	 */
	private void writeOutput(String output, String outputFile) throws IOException{
		FileWriter fw = new FileWriter(outputFile);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(output, 0, output.length());
		System.out.println(output);
		bw.close();
		fw.close();
	}
}
