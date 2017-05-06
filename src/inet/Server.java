package inet;

import java.io.IOException;
import java.net.ServerSocket;

import game.Player;

public class Server extends NetworkNode{
	private ServerSocket ssocket;
	
	public Server(NetworkHandler listener){
		this(Network.DEFAULT_PORT, listener);
	}
	
	public Server(int port, NetworkHandler handler){
		super(port, handler);
	}
	
	public void startGameServer(Player p) throws IOException{
		createIOStreams();
		//receive opponenet's data
		handler.onRecvPlayerData(new PayloadConfig(instream.readLine()));
		//send own player data
		PayloadConfig cfg = new PayloadConfig(p.getRole(), p.getName(), p.getStoneColor());
		outstream.write(cfg.toString());
		outstream.flush();
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
