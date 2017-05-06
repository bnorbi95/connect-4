package game;

import java.awt.Color;

import javax.swing.JOptionPane;

import exceptions.ColumnIsFullException;
import exceptions.InvalidColumnException;
import exceptions.InvalidPlayerIdException;
import gui.GameBoard;
import gui.GameWindow;
import gui.MainWindow;
import inet.Client;
import inet.Network;
import inet.NetworkHandler;
import inet.NetworkNode;
import inet.Payload;
import inet.Server;

public class GameHandler implements NetworkHandler, GameEventHandler{
	private NetworkNode network_node;
	
	private MainWindow mw;
	private GameWindow gw;
	
	private GameInfo info;
	private Grid grid;
	
	private Player me;
	private Player opp;
	
	private int round;
	
	private boolean disabled = false;
	
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
		
	}

	/**
	 * Update game window
	 */
	@Override
	public void onRecvPayload(Payload pl) {
		round++;
		setDisabled(false);
		try {
			Cell lastCell = grid.placeToColumn(pl.getColumn(), pl.getPlayerID());
			if(grid.checkForWin(lastCell))
			{
				JOptionPane.showMessageDialog(null, "You lost");
			}
		} catch (InvalidColumnException | ColumnIsFullException | InvalidPlayerIdException e) {
			//other player gets the error message
			e.printStackTrace();
		}
		gw.repaint();
	}

	@Override
	public void onGameSetup(String playerName, Color stoneColor) {
		round = 0;
		me = new Player(playerName, stoneColor, 1); //local setup
		opp = new Player("Player 2", Color.YELLOW, 2); //onClientConnect
		grid.setHandler(this);
		GameBoard gb = new GameBoard(grid, null);
		gw = new GameWindow(gb, me, opp);
		gb.setWindow(gw);
	}
	
	public int getRound(){
		return round;
	}
	
	public void increaseRound(){
		round++;
	}
	
	public boolean getDisabled(){
		return disabled;
	}
	
	public void setDisabled(boolean disabled){
		this.disabled = disabled;
	}
}
