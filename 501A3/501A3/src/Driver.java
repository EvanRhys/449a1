
public class Driver {
	
	/*
	 * Client Side:
	 *  Creates Class Selector
	 * 	 - Allows user to select from pre made classes
	 *   - Allows user to input data
	 *   - Allows user to send to serializer
	 * Creates Serializer
	 *   - 
	 * Creates Socket
	 * 	 - listens
	 * 
	 * 
	 * Receiver side:
	 *  Creates Socket
	 *   -Connects to Client
	 *   -Gets data
	 *  Creates Deserializer
	 *   -Deserializes
	 *  Creates Visualizer
	 *   -Visualizes
	 *  
	 */
	public static void main(String[] args) {
		ClassSelector CS = new ClassSelector();
		Object selected = CS.getObject();
		System.out.println("======================================================================================");
		Visualizer v = new Visualizer();
		v.inspect(selected, true);
	}

}
