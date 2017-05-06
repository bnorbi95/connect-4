package game;

import java.awt.Color;
import java.io.IOException;

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
import inet.PayloadConfig;
import inet.Server;
import util.ColorConverter;

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
				try {
					((Server) network_node).startGameServer(me);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}).start();
	}

	@Override
	public void startClient() {
		network_node = new Client((NetworkHandler) this);
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					((Client) network_node).connect(me);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}).start();
	}
	
	@Override
	public void onRecvPlayerData(PayloadConfig pl) {
		opp = new Player(pl.getName(), ColorConverter.getColor(pl.getColor()), pl.getPlayerID());
		onGameSetup();
	}

	/**
	 * Update game window
	 */
	@Override
	public void onRecvPayload(Payload pl) {
		round++;
		setDisabled(false);
		gw.getStatusbar().setText("Current player: "+ me.getName());
		
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
	public void onGameSetup() {
		round = 0;
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

	@Override
	public void onSetupLocalPlayer(String playerName, Color stoneColor, int role) {
		me = new Player(playerName, stoneColor, role);
	}
}
