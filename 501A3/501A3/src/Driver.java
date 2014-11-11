import java.io.IOException;


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
		//*
		ClassSelector CS = new ClassSelector();
		Object selected = CS.getObject();
		try{
			Serializer S = new Serializer();
			if(!S.serialize(selected))
				System.out.println("Serialization Failed");
		}
		catch (IOException IOE){
			IOE.printStackTrace();
		}//*/
		
		Visualizer v = new Visualizer();
		//v.inspect(selected, false);//*/
		System.out.println("======================================================================================");
		//*
		Deserializer D = new Deserializer();
	
		try
		{
			Object output = D.deserialize();
			System.out.println("======================================================================================");
			/*
			if(output != null)
				v.inspect(output, true);
			else
				System.out.println("Error");//*/
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}//*/
		
	}

}
