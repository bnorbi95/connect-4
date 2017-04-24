package inet;

import java.io.IOException;
import java.net.ServerSocket;

public class Server extends NetworkNode{
	private ServerSocket ssocket;
	
	public Server(NetworkHandler listener){
		this(Network.DEFAULT_PORT, listener);
	}
	
	public Server(int port, NetworkHandler handler){
		super(port, handler);
	}
	
	public void startGameServer(){
		createIOStreams();
		handler.onClientConnected();
	}

	@Override
	protected void initializeSocket() {
		try {
			ssocket = new ServerSocket(port);
			socket = ssocket.accept();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
