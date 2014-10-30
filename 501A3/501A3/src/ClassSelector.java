/*
 * Simple object with primitive instance variable
 * Object with references
 * Object with array of primitives
 * Object with array of references
 * Object that uses an instance of one of Java's collection classes
 * 
 */
import java.io.*;

public class ClassSelector {
	public ClassSelector(){}

	public static void displayClasses() {
		System.out.println("These are the objects you can select (select with number):");
		System.out.println("	SimpleObject (1)");
		System.out.println("	ReferenceObject (2)");
		System.out.println("	PrimArrayObject (3)");
		System.out.println("	RefArrayObject (4)");
		System.out.println("	Type q to quit");
	}
	public static Object getObject() {
		
		return null;
	}
}
