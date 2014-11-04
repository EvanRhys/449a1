
public class PrintFunctions {
	public static void displayClasses() {
		System.out.println("These are the objects you can select (select with number):");
		System.out.println("	SimpleObject (1)");
		System.out.println("	ReferenceObject (2)");
		System.out.println("	PrimArrayObject (3)");
		System.out.println("	RefArrayObject (4)");
		System.out.println("	CollectionObject (5)");
		System.out.println("	Type q to quit");
	}
	
	public static void displaySOOptions(){
		System.out.println("These are the options for the Simple Object");
		System.out.println("	Enter Boolean Value (1)");
		System.out.println("	Enter Character Value (2)");
		System.out.println("	Enter Integer Value (3)");
		System.out.println("	Enter Double Value (4)");
		System.out.println("	Enter (s) to submit object to serializer");	
		System.out.println("	Enter (r) to restart");		
	}
}
