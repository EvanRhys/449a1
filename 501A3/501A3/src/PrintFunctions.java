
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
	public static void displayGeneralOptions(){
		System.out.println("	Enter (s) to submit object to serializer");	
		System.out.println("	Enter (r) to restart");		
	}
	public static void displaySecondLevel(){
		System.out.println("	Enter (s) to return");
	}
	public static void displaySOOptions(){
		System.out.println("These are the options for the Simple Object");
		System.out.println("	Enter Boolean Value (1)");
		System.out.println("	Enter Character Value (2)");
		System.out.println("	Enter Integer Value (3)");
		System.out.println("	Enter Double Value (4)");
	}
	public static void displayROOptions(){
		System.out.println("These are the options for the Reference Object");
		System.out.println("	Build Object ref1 (1)");
		System.out.println("	Build Object ref2 (2)");
	}
	public static void displayPAOOptions(){
		System.out.println("These are the options for the Primitive Array Object");
		System.out.println("	Use Premade Int Array (1)");
		System.out.println("	Use Int Array Of Given Size (2)");
		System.out.println("	Set Array Element (3)");
	}
	public static void displayRAOOptions(){
		System.out.println("These are the options for the Reference Array Object");
		System.out.println("	Use Premade Reference Array (1)");
		System.out.println("	Use Reference Array Of Given Size (2)");
		System.out.println("	Set Array Element (3)");
	}
	public static void displayIncorrectInput(String input){
		System.out.println("Incorrect Input. Please Enter " + input);
	}
	public static void displayIncorrectInput(){
		System.out.println("Incorrect Input");
	}
	public static void displayInput(String input){
		System.out.println("Enter " + input + ": ");
	}
	public static void displayArrayOutOfBounds(int point){
		System.out.println("Poition " + point + " is out of bounds for given Array");
	}
	public static void displayNullError(){
		System.out.println("Please Initialize Array First");
	}
	public static void notImplemented(String function){
		System.out.println("The Function " + function + " has not been implemented");
	}
}
