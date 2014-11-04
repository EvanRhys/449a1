/*
 * Simple object with primitive instance variable
 * Object with references
 * Object with array of primitives
 * Object with array of references
 * Object that uses an instance of one of Java's collection classes
 * 
 */
import java.io.*;
import java.text.NumberFormat;
import java.util.Scanner;
import java.lang.reflect.*;

public class ClassSelector {
	public ClassSelector(){}

	public static Object getObject() {
		boolean objectBuilding = true;
		Scanner in = new Scanner(System.in);
		String input;
		
		while(objectBuilding){
			PrintFunctions.displayClasses();
			input = in.next();
			
			if(input.charAt(0) == '1' && input.length() == 1)
			{
				Object obj = buildSimpleObject(in);
				if(obj != null)
					return obj; 
			}
			else if (input.charAt(0) == '2' && input.length() == 1)
			{
				//return buildReferenceObject();
			}
			else if (input.charAt(0) == '3' && input.length() == 1)
			{
				//return buildPrimArrayObject();
			}
			else if (input.charAt(0) == '4' && input.length() == 1)
			{
				//return buildRefArrayObject(); 
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
				System.out.println("Incorrect input");		
			
		}
		
		return null;
	}

	public static Object buildSimpleObject(Scanner in){
		ClassSelector cs = new ClassSelector();
		boolean objectBuilding = true;
		String input;
		
		Object obj = new SimpleObject();
		Class objClass = obj.getClass();
				
		while(objectBuilding){
			PrintFunctions.displaySOOptions();
			while(! in.hasNext()){}
			input = in.next();
			
			if(input.charAt(0) == '1' && input.length() == 1)
			{
				boolean b = cs.handleBoolean(in);
				try{
					Field f = objClass.getDeclaredField("boolVar");
					f.setAccessible(true);
					f.set(obj, b);
				}
				catch (Exception e){
					e.printStackTrace();					
				}
			} 
			else if (input.charAt(0) == '2' && input.length() == 1)
			{
				char c = cs.handleChar(in);
				try{
					Field f = objClass.getDeclaredField("charVar");
					f.setAccessible(true);
					f.set(obj, c);
				}
				catch (Exception e){
					e.printStackTrace();					
				}
			}
			else if (input.charAt(0) == '3' && input.length() == 1)
			{
				int i = cs.handleInt(in);
				try{
					Field f = objClass.getDeclaredField("intVar");
					f.setAccessible(true);
					f.set(obj, i);
				}
				catch (Exception e){
					e.printStackTrace();					
				}
			}
			else if (input.charAt(0) == '4' && input.length() == 1)
			{
				double d = cs.handleDouble(in);
				try{
					Field f = objClass.getDeclaredField("doubVar");
					f.setAccessible(true);
					f.set(obj, d);
				}
				catch (Exception e){
					e.printStackTrace();					
				}
			}
			else if (input.charAt(0) == 's' && input.length() == 1){
				return obj;
			}
			else if (input.charAt(0) == 'r' && input.length() == 1)
				objectBuilding = false;			
			else
				System.out.println("Incorrect input");		
		}
		
		return null;
	}
	private boolean handleBoolean(Scanner in)
	{
		String input;
				
		while(true){
			System.out.print("Enter Boolean Value: ");
			input = in.next();
			
			if(input.equalsIgnoreCase("true"))
				return true;			
			else if(input.equalsIgnoreCase("false"))
				return false;
			else
				System.out.println("Invalid Input");
		}
	}
	private char handleChar(Scanner in)
	{
		String input;
		
		while(true){
			System.out.print("Enter Character: ");
			input = in.next();
			
			if(input.length() == 1)
				return input.charAt(0);			
			else
				System.out.println("Invalid Input");
		}
	}
	private int handleInt(Scanner in)
	{
		String input;

		while(true){
			System.out.print("Enter Integer: ");
			input = in.next();
			
			try{
				return Integer.parseInt(input);			
			}
			catch (NumberFormatException e){
				System.out.println("Invalid Input");
			}
		}		
	}
	private double handleDouble(Scanner in)
	{	 	
		String input;

		while(true){
			System.out.print("Enter Integer: ");
			input = in.next();
			
			try{
				return Double.parseDouble(input);				
			}
			catch (NumberFormatException e){
				System.out.println("Invalid Input");
			}
		}		
	}
}
