package inet;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import game.Player;


public class Client extends NetworkNode{	
	public Client(NetworkHandler handler){
		super(Network.DEFAULT_PORT, handler);
	}
	
	public Client(int port, NetworkHandler handler){
		super(port, handler);
	}
	
	public void connect(Player p) throws IOException {
		createIOStreams();
		//after connection, send own player data
		PayloadConfig cfg = new PayloadConfig(p.getRole(), p.getName(), p.getStoneColor());
		outstream.write(cfg.toString());
		outstream.flush();
		//receive opponent's data
		handler.onRecvPlayerData(new PayloadConfig(instream.readLine()));
		doMainLoop();
	}

	@Override
	protected void initializeSocket() throws UnknownHostException, IOException {
		socket = new Socket(Network.LOCAL, port);
	}
	
}
