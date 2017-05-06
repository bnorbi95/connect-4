package inet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public abstract class NetworkNode {
	protected int port;
	protected Socket socket;
	protected BufferedReader instream;
	protected PrintWriter outstream;
	
	protected NetworkHandler handler;
	
	public NetworkNode(NetworkHandler handler){
		this(Network.DEFAULT_PORT, handler);
	}
	
	public NetworkNode(int port, NetworkHandler handler){
		this.port = port;
		this.handler = handler;
	}
	
	protected abstract void initializeSocket() throws UnknownHostException, IOException;
	
	protected void createIOStreams() throws UnknownHostException, IOException {
		initializeSocket();
		outstream = new PrintWriter(socket.getOutputStream());
		instream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}
	
	public void sendPayload(Payload pl) {
		outstream.write(pl.toString());
		outstream.flush();
	}
	
	public void recvPayload() throws IOException {
		Payload payload = new Payload(instream.readLine());
		handler.onRecvPayload(payload);
	}
	
	protected void doMainLoop(){
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					while(true) {
						recvPayload();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			
			}
			
		}).start();	
	}
}
