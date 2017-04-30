package game;

import java.awt.Color;

import gui.GameBoard;
import gui.GameWindow;
import gui.MainWindow;
import inet.Client;
import inet.Network;
import inet.NetworkHandler;
import inet.NetworkNode;
import inet.Payload;
import inet.Server;

public class GameHandler implements NetworkHandler, GameListener{
	private NetworkNode network_node;
	
	private MainWindow mw;
	private GameWindow gw;
	
	private GameInfo info;
	private Grid grid;
	
	private Player me;
	private Player opp;
	
	public GameHandler() {
		info = new GameInfo();
		grid = new Grid(info);
	}
	
	public void run() {
		mw = new MainWindow(this, this, grid);
	}

	@Override
	public void startServer() {
		network_node = new Server((NetworkHandler) this);
		new Thread(new Runnable() {

			@Override
			public void run() {
				((Server) network_node).startGameServer();
			}
			
		}).start();
	}

	@Override
	public void startClient() {
		network_node = new Client((NetworkHandler) this);
		new Thread(new Runnable() {

			@Override
			public void run() {
				((Client) network_node).connect();
			}
			
		}).start();
	}

	@Override
	public void onClientConnected() {
		// TODO Auto-generated method stub
		System.out.println("Client connected");
		
	}

	/**
	 * Update game window
	 */
	@Override
	public void onRecvPayload(Payload pl) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onGameSetup() {
		// TODO Auto-generated method stub
		me = new Player("Player 1", Color.RED, 1); //local setup
		opp = new Player("Player 2", Color.YELLOW, 2); //onClientConnect
		GameBoard gb = new GameBoard(grid, null);
		gw = new GameWindow(gb, me, opp);
		gb.setWindow(gw);
	}
}
