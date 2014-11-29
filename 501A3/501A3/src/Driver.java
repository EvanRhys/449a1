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
		valiadateArgs(args);
	}
	private static void valiadateArgs(String[] args)
	{
		if(args.length == 4)
		{
			if(args[0].equals("Client"))
				runClient(args);
			else if(args[0].equals("Host")) 
				runHost(args);	
		}
		else
			PrintFunctions.displayIncorrectInput();
	}
	//Host ip port filename
	private static void runHost(String[] args)
	{
		ClassSelector CS = new ClassSelector();
		Object selected = CS.getObject();
		try{
			Serializer S = new Serializer(args[3]);
			if(!S.serialize(selected))
				System.out.println("Serialization Failed");
		}
		catch (IOException IOE){
			IOE.printStackTrace();
		}
		try
		{
			Host H = new Host(args[1], args[2], args[3]);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	//Client ip port full
	private static void runClient(String[] args)
	{
		Client C = new Client();
		Visualizer V = new Visualizer();
		String filename = "";
		try
		{
			 filename = C.getFile(args[1], args[2]);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		Deserializer D = new Deserializer(filename);	
		try
		{
			Object output = D.deserialize();
			if(output != null)
				V.inspect(output, true, Boolean.parseBoolean(args[3]));
			else
				System.out.println("Error");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
