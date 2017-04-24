package inet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public abstract class NetworkNode {
	protected int port;
	protected Socket socket;
	protected BufferedReader instream;
	protected PrintWriter outstream;
	
	protected NetworkHandler handler;
	
	protected String payload;
	
	public NetworkNode(NetworkHandler handler){
		this(Network.DEFAULT_PORT, handler);
	}
	
	public NetworkNode(int port, NetworkHandler handler){
		this.port = port;
		this.handler = handler;
	}
	
	protected abstract void initializeSocket();
	
	protected void createIOStreams() {
		try {
			initializeSocket();
			outstream = new PrintWriter(socket.getOutputStream());
			instream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendPayload(Payload pl) {
		outstream.write(pl.toString());
		outstream.flush();
	}
	
	public void recvPayload() throws IOException {
		payload = instream.readLine();
		handler.onRecvPayload();
	}
}
