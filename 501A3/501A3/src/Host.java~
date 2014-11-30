import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.ServerSocket;

public class Host {
	
	public Host (String port, String fileName) throws Exception
	{
		int portNumber = Integer.parseInt(port);
		ServerSocket server = new ServerSocket(portNumber);
		System.out.println("Waiting for Connection...");
		Socket client = server.accept();
		System.out.println("Connected");
		PrintWriter out = new PrintWriter(client.getOutputStream(), true);
		BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
		String input;
		while((input = in.readLine()) != null)
		{
			if(input.equals("Start"))
				sendFile(out, fileName);
			else if(input.equals("Finished"))
				break;			
		}
		System.out.println("Finished");
		client.close();
		server.close();
	}
	private void sendFile(PrintWriter out, String fileName) throws Exception
	{
		FileReader fr = new FileReader(fileName);
		BufferedReader br = new BufferedReader(fr);
		String fromFile;
		
		out.write("Start: " + fileName +"\n");
		out.flush();
		while((fromFile = br.readLine()) != null)
		{
			out.write(fromFile + "\n");
			out.flush();
		}
		out.write("End\n");
		out.flush();
	}
}
