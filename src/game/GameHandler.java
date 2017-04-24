package game;

import java.io.IOException;

import gui.MainWindow;
import inet.Client;
import inet.NetworkHandler;
import inet.Server;

public class GameHandler implements NetworkHandler{
	private Server srv;
	private Client cnt;
	
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
		
	}
	
}
