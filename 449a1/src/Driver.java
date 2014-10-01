import java.io.*;
import java.util.StringTokenizer;

public class Driver {
	public static int [][] linePen;
	public static Node [] tooNearPen;
	public static Node [] forcedPart;
	public static Node [] forbiddenM;
	public static Node [] tooNearTask;
	
	public static void main(String[] args){
		String inputFile = args[0];
		String line;
		linePen = new int[8][8];
		tooNearPen = new Node[8];
		forcedPart = new Node[8];
		forbiddenM = new Node[8];
		tooNearTask = new Node[8];
		
		FileReader fr;
		BufferedReader br;
		try {
			fr = new FileReader(inputFile);
			br = new BufferedReader(fr);
			while((line = br.readLine()) != null){
				if (line.equalsIgnoreCase("forced partial assignment:")){
					forcedPartialAssign(br);
				}
				else if (line.equalsIgnoreCase("forbidden machine:")){
					forbiddenMachine(br);
				}
				else if (line.equalsIgnoreCase("too-near tasks:")){
					//tooNearTask(br);
					tNT(br);
				}
				else if (line.equalsIgnoreCase("machine penalties:")){
					machinePenalties(br);
				}
				else if (line.equalsIgnoreCase("too-near penalities")){
					tooNearPen(br);
				}
			
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e){
			System.out.println("End of File");
		}
		int i;
		Node current;
		/*System.out.println("mach pen:");
		for (i=0; i < 8; i++){
			for(j=0; j< 8; j++){
				System.out.println("i="+i+"; j="+j+"; A[i][j]="+ linePen[i][j]);
			}
		}*/
		System.out.println("forced part:");
		for(i=0; i < 8; i++){
			if (forcedPart[i] != null)
				System.out.println("Computer:" + forcedPart[i].getComputer() + "; Task:" + forcedPart[i].getTask());
		}
		System.out.println("forbidden:");
		for(i=0; i < 8; i++)
			if (forbiddenM[i] != null){
				current = forbiddenM[i];
				System.out.println("Computer:" + current.getComputer() + "; Task:" + current.getTask());
				while (current.getNext() != null){
					current = current.getNext();
					System.out.println("Computer:" + current.getComputer() + "; Task:" + current.getTask());
				}				
			}
		System.out.println("Too Near Task:");
		for(i=0; i < 8; i++)
			if (tooNearTask[i] != null){
				current = tooNearTask[i];
				System.out.println("Task1:" + current.getTask() + "; Task2:" + current.getSecondTask());
				while (current.getNext() != null){
					current = current.getNext();
					System.out.println("Task1:" + current.getTask() + "; Task2:" + current.getSecondTask());
				}				
			}
		System.out.println("Too near Pen");
		for(i=0; i < 8; i++)
			if (tooNearPen[i] != null){
				current = tooNearPen[i];
				System.out.println("Task1:" + current.getTask() + "; Task2:" + current.getSecondTask() + "; Pen:" + current.getPenality());
				while (current.getNext() != null){
					current = current.getNext();
					System.out.println("Task1:" + current.getTask() + "; Task2:" + current.getSecondTask() + "; Pen:" + current.getPenality());
				}				
			}						
	}
	public static void forcedPartialAssign(BufferedReader br) throws IOException{
		String line;
		
		while((line = br.readLine()).length() > 0){			
			switch(line.charAt(1)){
			case '1': 
				if (forcedPart[0] == null)
					forcedPart[0] = new forcedNode(line.charAt(1), line.charAt(3));
				else
					System.out.println("partial assignment error");
				break;
			case '2': 
				if (forcedPart[1] == null)
					forcedPart[1] = new forcedNode(line.charAt(1), line.charAt(3));
				else
					System.out.println("partial assignment error");
				break;
			case '3':
				if (forcedPart[2] == null)
					forcedPart[2] = new forcedNode(line.charAt(1), line.charAt(3));
				else
					System.out.println("partial assignment error");
				break;
			case '4':
				if (forcedPart[3] == null)
					forcedPart[3] = new forcedNode(line.charAt(1), line.charAt(3));
				else
					System.out.println("partial assignment error");
				break;
			case '5':
				if (forcedPart[4] == null)
					forcedPart[4] = new forcedNode(line.charAt(1), line.charAt(3));
				else
					System.out.println("partial assignment error");
				break;
			case '6': 
				if (forcedPart[5] == null)
					forcedPart[5] = new forcedNode(line.charAt(1), line.charAt(3));
				else
					System.out.println("partial assignment error");
				break;
			case '7':
				if (forcedPart[6] == null)
					forcedPart[6] = new forcedNode(line.charAt(1), line.charAt(3));
				else
					System.out.println("partial assignment error");
				break;
			case '8': 
				if (forcedPart[7] == null)
					forcedPart[7] = new forcedNode(line.charAt(1), line.charAt(3));
				else
					System.out.println("partial assignment error");
				break;
			}
		}
	}
	public static void machinePenalties(BufferedReader br) throws IOException{
		int i = 0;
		int j = 0;
		StringTokenizer st;
		String line;
		String numb;
		
		while(i < 8){
			line = br.readLine();
			st = new StringTokenizer(line);
			j = 0;
			while (st.hasMoreTokens()){
				numb = st.nextToken();
				linePen[i][j] = Integer.parseInt(numb);
				j++;	
			}
			i++;
		}
	}
	public static void forbiddenMachine(BufferedReader br) throws IOException{
		String line;
		Node current;
		
		while((line = br.readLine()).length() > 0){
			switch(line.charAt(1)){
			case '1': 
				if (forbiddenM[0] == null)
					forbiddenM[0] = new forbiddenNode(line.charAt(1), line.charAt(3));
				else{
					current = forbiddenM[0];
					while (current.getNext() != null){
						current = current.getNext();
					}						
					current.setNext(new forbiddenNode(line.charAt(1), line.charAt(3)));
				}
				break;
			case '2': 
				if (forbiddenM[1] == null)
					forbiddenM[1] = new forbiddenNode(line.charAt(1), line.charAt(3));
				else{
					current = forbiddenM[1];
					while (current.getNext() != null){
						current = current.getNext();
					}						
					current.setNext(new forbiddenNode(line.charAt(1), line.charAt(3)));
				}
				break;
			case '3': 
				if (forbiddenM[2] == null)
					forbiddenM[2] = new forbiddenNode(line.charAt(1), line.charAt(3));
				else{
					current = forbiddenM[2];
					while (current.getNext() != null){
						current = current.getNext();
					}						
					current.setNext(new forbiddenNode(line.charAt(1), line.charAt(3)));
				}
				break;
			case '4': 
				if (forbiddenM[3] == null)
					forbiddenM[3] = new forbiddenNode(line.charAt(1), line.charAt(3));
				else{
					current = forbiddenM[3];
					while (current.getNext() != null){
						current = current.getNext();
					}						
					current.setNext(new forbiddenNode(line.charAt(1), line.charAt(3)));
				}
				break;
			case '5': 
				if (forbiddenM[4] == null)
					forbiddenM[4] = new forbiddenNode(line.charAt(1), line.charAt(3));
				else{
					current = forbiddenM[4];
					while (current.getNext() != null){
						current = current.getNext();
					}						
					current.setNext(new forbiddenNode(line.charAt(1), line.charAt(3)));
				}
				break;
			case '6': 
				if (forbiddenM[5] == null)
					forbiddenM[5] = new forbiddenNode(line.charAt(1), line.charAt(3));
				else{
					current = forbiddenM[5];
					while (current.getNext() != null){
						current = current.getNext();
					}						
					current.setNext(new forbiddenNode(line.charAt(1), line.charAt(3)));
				}
				break;
			case '7': 
				if (forbiddenM[6] == null)
					forbiddenM[6] = new forbiddenNode(line.charAt(1), line.charAt(3));
				else{
					current = forbiddenM[6];
					while (current.getNext() != null){
						current = current.getNext();
					}						
					current.setNext(new forbiddenNode(line.charAt(1), line.charAt(3)));
				}
				break;
			case '8': 
				if (forbiddenM[7] == null)
					forbiddenM[7] = new forbiddenNode(line.charAt(1), line.charAt(3));
				else{
					current = forbiddenM[7];
					while (current.getNext() != null){
						current = current.getNext();
					}						
					current.setNext(new forbiddenNode(line.charAt(1), line.charAt(3)));
				}
				break;				
			}
		}		
	}
	private static void tNT(BufferedReader br) throws IOException{
		String line;
		int i;
		Node current;
		
		while((line = br.readLine()).length() > 0){
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
		}
	}
	public static void tooNearTask(BufferedReader br) throws IOException{
		String line;
		Node current;
		
		while((line = br.readLine()).length() > 0){
			switch(line.charAt(1)){
			case 'A': 
				if (tooNearTask[0] == null)
					tooNearTask[0] = new nearTaskNode(line.charAt(1), line.charAt(3));
				else{
					current = tooNearTask[0];
					while (current.getNext() != null){
						current = current.getNext();
					}						
					current.setNext(new nearTaskNode(line.charAt(1), line.charAt(3)));
				}
				break;
			case 'B': 
				if (tooNearTask[1] == null)
					tooNearTask[1] = new nearTaskNode(line.charAt(1), line.charAt(3));
				else{
					current = tooNearTask[1];
					while (current.getNext() != null){
						current = current.getNext();
					}						
					current.setNext(new nearTaskNode(line.charAt(1), line.charAt(3)));
				}
				break;
			case 'C': 
				if (tooNearTask[2] == null)
					tooNearTask[2] = new nearTaskNode(line.charAt(1), line.charAt(3));
				else{
					current = tooNearTask[2];
					while (current.getNext() != null){
						current = current.getNext();
					}						
					current.setNext(new nearTaskNode(line.charAt(1), line.charAt(3)));
				}
				break;
			case 'D': 
				if (tooNearTask[3] == null)
					tooNearTask[3] = new nearTaskNode(line.charAt(1), line.charAt(3));
				else{
					current = tooNearTask[3];
					while (current.getNext() != null){
						current = current.getNext();
					}						
					current.setNext(new nearTaskNode(line.charAt(1), line.charAt(3)));
				}
				break;
			case 'E': 
				if (tooNearTask[4] == null)
					tooNearTask[4] = new nearTaskNode(line.charAt(1), line.charAt(3));
				else{
					current = tooNearTask[4];
					while (current.getNext() != null){
						current = current.getNext();
					}						
					current.setNext(new nearTaskNode(line.charAt(1), line.charAt(3)));
				}
				break;
			case 'F': 
				if (tooNearTask[5] == null)
					tooNearTask[5] = new nearTaskNode(line.charAt(1), line.charAt(3));
				else{
					current = tooNearTask[5];
					while (current.getNext() != null){
						current = current.getNext();
					}						
					current.setNext(new nearTaskNode(line.charAt(1), line.charAt(3)));
				}
				break;
			case 'G': 
				if (tooNearTask[6] == null)
					tooNearTask[6] = new nearTaskNode(line.charAt(1), line.charAt(3));
				else{
					current = tooNearTask[6];
					while (current.getNext() != null){
						current = current.getNext();
					}						
					current.setNext(new nearTaskNode(line.charAt(1), line.charAt(3)));
				}
				break;
			case 'H': 
				if (tooNearTask[7] == null)
					tooNearTask[7] = new nearTaskNode(line.charAt(1), line.charAt(3));
				else{
					current = tooNearTask[7];
					while (current.getNext() != null){
						current = current.getNext();
					}						
					current.setNext(new nearTaskNode(line.charAt(1), line.charAt(3)));
				}
				break;				
			}
		}		
	}
	public static void tooNearPen(BufferedReader br) throws IOException{
		String line;
		String [] parts;
		String penValue;
		int value;
		Node current;
		
		while((line = br.readLine()).length() > 0){
			parts = line.split(",");
			penValue = parts[2].substring(0, (parts[2].length() - 1));
			value = Integer.parseInt(penValue);
			switch((parts[0]).charAt(1)){
			case 'A': 
				if (tooNearPen[0] == null)
					tooNearPen[0] = new nearPenNode((parts[0]).charAt(1), (parts[1]).charAt(0), value);
				else{
					current = tooNearPen[0];
					while (current.getNext() != null){
						current = current.getNext();
					}						
					current.setNext(new nearPenNode((parts[0]).charAt(1), (parts[1]).charAt(0), value));
				}
				break;
			case 'B': 
				if (tooNearPen[1] == null)
					tooNearPen[1] = new nearPenNode((parts[0]).charAt(1), (parts[1]).charAt(0), value);
				else{
					current = tooNearPen[1];
					while (current.getNext() != null){
						current = current.getNext();
					}						
					current.setNext(new nearPenNode((parts[0]).charAt(1), (parts[1]).charAt(0), value));
				}
				break;
			case 'C': 
				if (tooNearPen[2] == null)
					tooNearPen[2] = new nearPenNode((parts[0]).charAt(1), (parts[1]).charAt(0), value);
				else{
					current = tooNearPen[2];
					while (current.getNext() != null){
						current = current.getNext();
					}						
					current.setNext(new nearPenNode((parts[0]).charAt(1), (parts[1]).charAt(0), value));
				}
				break;
			case 'D': 
				if (tooNearPen[3] == null)
					tooNearPen[3] = new nearPenNode((parts[0]).charAt(1), (parts[1]).charAt(0), value);
				else{
					current = tooNearPen[3];
					while (current.getNext() != null){
						current = current.getNext();
					}						
					current.setNext(new nearPenNode((parts[0]).charAt(1), (parts[1]).charAt(0), value));
				}
				break;
			case 'E': 
				if (tooNearPen[4] == null)
					tooNearPen[4] = new nearPenNode((parts[0]).charAt(1), (parts[1]).charAt(0), value);
				else{
					current = tooNearPen[4];
					while (current.getNext() != null){
						current = current.getNext();
					}						
					current.setNext(new nearPenNode((parts[0]).charAt(1), (parts[1]).charAt(0), value));
				}
				break;
			case 'F': 
				if (tooNearPen[5] == null)
					tooNearPen[5] = new nearPenNode((parts[0]).charAt(1), (parts[1]).charAt(0), value);
				else{
					current = tooNearPen[5];
					while (current.getNext() != null){
						current = current.getNext();
					}						
					current.setNext(new nearPenNode((parts[0]).charAt(1), (parts[1]).charAt(0), value));
				}
				break;
			case 'G': 
				if (tooNearPen[6] == null)
					tooNearPen[6] = new nearPenNode((parts[0]).charAt(1), (parts[1]).charAt(0), value);
				else{
					current = tooNearPen[6];
					while (current.getNext() != null){
						current = current.getNext();
					}						
					current.setNext(new nearPenNode((parts[0]).charAt(1), (parts[1]).charAt(0), value));
				}
				break;
			case 'H': 
				if (tooNearPen[7] == null)
					tooNearPen[7] = new nearPenNode((parts[0]).charAt(1), (parts[1]).charAt(0), value);
				else{
					current = tooNearPen[7];
					while (current.getNext() != null){
						current = current.getNext();
					}						
					current.setNext(new nearPenNode((parts[0]).charAt(1), (parts[1]).charAt(0), value));
				}
				break;				
			}
		}
	}
}
