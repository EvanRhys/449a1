/***************
 * CPSC 449 Assignment 1 
 * Parsing Input Exception
 * @author team 1-1 
 * thrown If any key word is missing, one of the place holders is assigned an invalid value or the file contains something not specified above
 * 
 * Version 3
 * forcedPartialAssign has been changed, wrongmachine
 * tooNearPen has been changed, wrongnumbertoonear
 * main has been changed, garbage
 */
import java.io.IOException;

public class Driver {
	
	/*******
	 * @param args arguments passed, args[0] should be input file name, args[1] should be output file name
	 * @throws IOException 
	 * reads file and builds data structure
	 * Initializes Hard and Soft Constraints
	 * Initializes tree
	 * runs searchR
	 * prints output, and writes to file
	 * catches any error and writes proper error to file
	 */
	public static void main(String[] args){
		try{
			TaskOrganizer TO = new TaskOrganizer(args[0], args[1]);
			if(TO.readFile())
				TO.runSearch();
		}catch(IOException e){
			e.printStackTrace();
		}catch(FileNotReadException e){
			System.out.printf("File has not been read");
			System.out.printf("TEST");
		}
	}
}
