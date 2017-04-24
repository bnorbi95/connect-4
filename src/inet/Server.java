package inet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private int port;
	private ServerSocket ssocket;
	private Socket csocket;
	private BufferedReader instream;
	private PrintWriter outstream;
	
	public Server(){
		this(Network.DEFAULT_PORT);
	}
	
	public Server(int port){
		this.port = port;
	}
	
	public void startGameServer(){
		try {
			ssocket = new ServerSocket(port);
			csocket = ssocket.accept();
			outstream = new PrintWriter(csocket.getOutputStream());
			instream = new BufferedReader(new InputStreamReader(csocket.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
