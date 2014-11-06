/*
 * Simple object with primitive instance variable
 * Object with references
 * Object with array of primitives
 * Object with array of references
 * Object that uses an instance of one of Java's collection classes
 * 
 */
import java.util.Scanner;

public class ClassSelector {
	public ClassSelector(){}

	public Object getObject()
	{
		boolean objectBuilding = true;
		Scanner in = new Scanner(System.in);
		String input;
		
		while(objectBuilding)
		{
			PrintFunctions.displayClasses();
			input = in.next();
			
			if(input.charAt(0) == '1' && input.length() == 1)
			{
				Object obj = buildSimpleObject(in, true);
				if(obj != null)
					return obj; 
			}
			else if (input.charAt(0) == '2' && input.length() == 1)
			{
				Object obj = buildReferenceObject(in);
				if(obj != null)
					return obj;
			}
			else if (input.charAt(0) == '3' && input.length() == 1)
			{
				Object obj = buildPrimArrayObject(in);
				if(obj != null)
						return obj;
			}
			else if (input.charAt(0) == '4' && input.length() == 1)
			{
				Object obj = buildRefArrayObject(in);
				if(obj != null)
					return obj;
			}
			else if (input.charAt(0) == '5' && input.length() == 1)
			{
				//return buildCollectionObject(); 
			}
			else if ((input.charAt(0) == 'q' && input.length() == 1) ||
					input.equalsIgnoreCase("quit"))
			{
				objectBuilding = false;
			}
			else
				PrintFunctions.displayIncorrectInput("Number or Quit");	
			
		}
		
		return null;
	}

	private Object buildSimpleObject(Scanner in, boolean level)
	{
		boolean objectBuilding = true;
		String input;
		
		SimpleObject SO = new SimpleObject();
				
		while(objectBuilding)
		{
			PrintFunctions.displaySOOptions();
			if(level)
				PrintFunctions.displayGeneralOptions();
			else 
				PrintFunctions.displaySecondLevel();
			
			while(! in.hasNext()){}
			input = in.next();
			
			if(input.charAt(0) == '1' && input.length() == 1){
				PrintFunctions.displayInput("Boolean");
				SO.setBoolVar(handleBoolean(in));			 
			}
			else if (input.charAt(0) == '2' && input.length() == 1){
				PrintFunctions.displayInput("Character");
				SO.setCharVar(handleChar(in));
			}
			else if (input.charAt(0) == '3' && input.length() == 1){
				PrintFunctions.displayInput("Integer");
				SO.setIntVar(handleInt(in));
			}
			else if (input.charAt(0) == '4' && input.length() == 1){
				PrintFunctions.displayInput("Double");
				SO.setDoubVar(handleDouble(in));
			}
			else if (input.charAt(0) == 's' && input.length() == 1)
				return SO;
			else if (input.charAt(0) == 'r' && input.length() == 1)
				objectBuilding = false;			
			else
				PrintFunctions.displayIncorrectInput();
		}
		
		return null;
	}
	
	private Object buildReferenceObject(Scanner in)
	{
		boolean objectBuilding = true;
		String input;
		
		ReferenceObject RO = new ReferenceObject();		
		
		while(objectBuilding)
		{
			PrintFunctions.displayROOptions();
			PrintFunctions.displayGeneralOptions();
			
			while(! in.hasNext()){}
			input = in.next();
			
			if(input.charAt(0) == '1' && input.length() == 1)
			{				
				try
				{
					RO.setRef1((SimpleObject) buildSimpleObject(in, false));
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			else if (input.charAt(0) == '2' && input.length() == 1)
			{
				try
				{
					RO.setRef2((SimpleObject) buildSimpleObject(in, false));
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			else if (input.charAt(0) == 's' && input.length() == 1)
				return RO;
			else if  (input.charAt(0) == 'r' && input.length() == 1)
				objectBuilding = false;
			else
				PrintFunctions.displayIncorrectInput();
		}
		return null;
	}
	
	private Object buildPrimArrayObject(Scanner in)
	{
		boolean objectBuilding = true;
		String input;
		
		PrimArrayObject PAO = null;
		
		while(objectBuilding)
		{
			PrintFunctions.displayPAOOptions();
			PrintFunctions.displayGeneralOptions();
			
			while(! in.hasNext()){}
			input = in.next();
			
			if(input.charAt(0) == '1' && input.length() == 1)
			{				
				PAO = new PrimArrayObject();
			}
			else if (input.charAt(0) == '2' && input.length() == 1)
			{
				PrintFunctions.displayInput("Array Size");;
				PAO = new PrimArrayObject(handleInt(in));
			}
			else if (input.charAt(0) == '3' && input.length() == 1)
			{
				PrintFunctions.displayInput("Array Position");
				int point = handleInt(in);
				PrintFunctions.displayInput("New Value");
				int value = handleInt(in);
				try{
					PAO.set(point, value);
				}
				catch(ArrayIndexOutOfBoundsException AIO)
				{
					PrintFunctions.displayArrayOutOfBounds(point);
				}
				catch(NullPointerException NPE)
				{
					PrintFunctions.displayNullError();
				}
			}
			else if (input.charAt(0) == 's' && input.length() == 1)
				return PAO;
			else if  (input.charAt(0) == 'r' && input.length() == 1)
				objectBuilding = false;
			else
				PrintFunctions.displayIncorrectInput();
		}
		
		return null;
	}
	private Object buildRefArrayObject(Scanner in)
	{	
		boolean objectBuilding = true;
		String input;
		
		RefArrayObject RAO = null;
		
		while(objectBuilding)
		{
			PrintFunctions.displayRAOOptions();
			PrintFunctions.displayGeneralOptions();
			
			while(! in.hasNext()){}
			input = in.next();
			
			if(input.charAt(0) == '1' && input.length() == 1)
			{				
				RAO = new RefArrayObject();
			}
			else if (input.charAt(0) == '2' && input.length() == 1)
			{
				PrintFunctions.displayInput("Array Size");
				RAO = new RefArrayObject(handleInt(in));
			}
			else if (input.charAt(0) == '3' && input.length() == 1)
			{
				PrintFunctions.displayInput("Array Position");
				int point = handleInt(in);
				PrintFunctions.displayInput("New Simple Object");
				try{
					SimpleObject value = (SimpleObject) buildSimpleObject(in, false);
					RAO.set(point, value);
				}
				catch(ArrayIndexOutOfBoundsException AIO)
				{
					PrintFunctions.displayArrayOutOfBounds(point);
				}
				catch(NullPointerException NPE)
				{
					PrintFunctions.displayNullError();
				}
			}
			else if (input.charAt(0) == 's' && input.length() == 1)
				return RAO;
			else if  (input.charAt(0) == 'r' && input.length() == 1)
				objectBuilding = false;
			else
				PrintFunctions.displayIncorrectInput();
		}
		
		return null;
	}
	private boolean handleBoolean(Scanner in)
	{
		String input;
				
		while(true){
			while(! in.hasNext()){}
			input = in.next();
			
			if(input.equalsIgnoreCase("true"))
				return true;			
			else if(input.equalsIgnoreCase("false"))
				return false;
			else
				PrintFunctions.displayIncorrectInput();
		}
	}
	private char handleChar(Scanner in)
	{
		String input;
		
		while(true){
			while(! in.hasNext()){}
			input = in.next();
			
			if(input.length() == 1)
				return input.charAt(0);			
			else
				PrintFunctions.displayIncorrectInput();
		}
	}
	private int handleInt(Scanner in)
	{
		String input;

		while(true){
			while(! in.hasNext()){}
			input = in.next();
			
			try{
				return Integer.parseInt(input);			
			}
			catch (NumberFormatException e){
				PrintFunctions.displayIncorrectInput();
			}
		}		
	}
	private double handleDouble(Scanner in)
	{	 	
		String input;

		while(true){
			while(! in.hasNext()){}
			input = in.next();
			
			try{
				return Double.parseDouble(input);				
			}
			catch (NumberFormatException e){
				PrintFunctions.displayIncorrectInput();
			}
		}		
	}
}
