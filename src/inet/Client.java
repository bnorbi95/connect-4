package inet;

import java.io.IOException;
import java.net.Socket;


public class Client extends NetworkNode{	
	public Client(NetworkHandler handler){
		super(Network.DEFAULT_PORT, handler);
	}
	
	public Client(int port, NetworkHandler handler){
		super(port, handler);
	}
	
	public void connect() {
		createIOStreams();
	}

	@Override
	protected void initializeSocket() {
		try {
			socket = new Socket(Network.LOCAL, port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
