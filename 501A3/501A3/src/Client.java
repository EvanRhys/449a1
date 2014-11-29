import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
/*
* Code from this source https://docs.oracle.com/javase/tutorial/networking/sockets/clientServer.html
*  was used to help set up the sockets 
*/
public class Client {
	public Client(){}
	
	public String getFile(String ip, String port)throws Exception
	{
		int portNumber = Integer.parseInt(port);
		Socket sock = new Socket();
		sock.connect(new InetSocketAddress(ip, portNumber));r
		String input, output, filename = "";
		
		if(sock.isConnected())
		{	
			System.out.println("Connected");
			PrintWriter out = new PrintWriter(sock.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			FileWriter fw;
			BufferedWriter bw;
			System.out.println("Starting");
			out.print("Start\n");
			out.flush();
			input = in.readLine();
			System.out.println(input);
			if(input.startsWith("Start:"))
			{
				String [] tokens = input.split(" ");
				filename = tokens[1];
				fw = new FileWriter(filename+"Client");
				bw = new BufferedWriter(fw);
				while((input = in.readLine()) != null)
				{
					if(input.equals("End"))
						break;
					bw.write(input);
				}
				System.out.println("Finished");
				out.write("Finished\n");
				out.flush();
				bw.close();
				fw.close();
			}
		}
		sock.close();
		return filename;
	}
	
}
