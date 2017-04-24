package game;

import java.io.IOException;

import gui.MainWindow;
import inet.Client;
import inet.Network;
import inet.NetworkHandler;
import inet.Payload;
import inet.Server;

public class GameHandler implements NetworkHandler{
	private Server srv;
	private Client cnt;
	private int network_role;
	
	public GameHandler() {
		srv = new Server();
		cnt = new Client();
	}
	
	public void run() {
		MainWindow mw = new MainWindow(this);
	}

	@Override
	public void startServer() {
		srv = new Server();
		new Thread(new Runnable() {

			@Override
			public void run() {
				srv = new Server();
				srv.startGameServer();
			}
			
		}).start();
		network_role = Network.ROLE_SERVER;
		
	}

	@Override
	public void startClient() {
		cnt = new Client();
		try {
			cnt.connect("");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		network_role = Network.ROLE_CLIENT;
	}

	@Override
	public void sendPayload(Payload pl) {
		if(network_role == Network.ROLE_SERVER) {
			srv.sendPayload(pl);
		} else if (network_role == Network.ROLE_CLIENT) {
			cnt.sendPayload(pl);
		}else {
			// ROLE NOT INITIALIZED
		}
		
	}
	
}
