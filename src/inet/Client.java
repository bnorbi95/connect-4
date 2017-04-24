package inet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class Client {	
	private int port;
	private Socket socket;
	private BufferedReader instream;
	private PrintWriter outstream;
	
	public Client(){
		this(Network.DEFAULT_PORT);
	}
	
	public Client(int port){
		this.port = port;
	}
	
	public void connect(String hostIp) throws UnknownHostException, IOException{
		if(hostIp.equals("")) {
			hostIp = Network.LOCAL;
		}
		socket = new Socket(hostIp, port);
		outstream = new PrintWriter(socket.getOutputStream());
		instream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}
	
	public void sendPayload(Payload pl) {
		outstream.write(pl.toString());
		outstream.flush();
	}
	
}
