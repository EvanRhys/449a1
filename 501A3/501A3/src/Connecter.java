import java.io.IOException;
import java.net.Socket;
import java.net.SocketAddress;


public class Connecter {
	private SocketAddress ipAdd;
	private Socket connection;
	
	public Connecter(){}
	public Connecter(String port){}
	public Connecter(String ipAdd, String port){}
	
	public void close() throws IOException{
		connection.close();
	}

}
