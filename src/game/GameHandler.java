package game;

import java.awt.Color;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.net.SocketException;

import javax.swing.JDialog;
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
	private JOptionPane waitingWindow;
	private JDialog dialog;
	
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
				} catch (SocketException e) {
					//Server stopped by closing waiting window
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "Network error, unable to connect");
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
					JOptionPane.showMessageDialog(null, "Network error, unable to connect");
				}
			}
			
		}).start();
	}
	
	@Override
	public void onRecvPlayerData(PayloadConfig pl) {
		if(network_node instanceof Server) dialog.dispose();
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
				onGameEnd(opp);
			}
		} catch (InvalidColumnException | ColumnIsFullException | InvalidPlayerIdException e) {
			//other player gets the error message
			e.printStackTrace();
		}
		gw.repaint();
	}
	
	@Override
	public void onSendPayload(Payload pl) {
		network_node.sendPayload(pl);	
	}

	@Override
	public void onGameSetup() {
		round = 0;
		grid.setHandler(this);
		GameBoard gb = new GameBoard(grid, null);
		gw = new GameWindow(gb, me, opp);
		gb.setWindow(gw);
		gb.initGameCells();

		if(gw.getMe().getRole() == 1)
			setDisabled(false);
		if(gw.getMe().getRole() == 2)
			setDisabled(true);
			
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
  
	@Override
	public void onGameEnd(Player winner) {
		if(winner == me){
			JOptionPane.showMessageDialog(null, "You Won!");
		}
		else{
			JOptionPane.showMessageDialog(null, "You Lost!");
		}
		System.exit(0);
	}

	@Override
	public void onWaitinForPlayer() {
		waitingWindow = new JOptionPane("Waiting for other player...", JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
		dialog = new JDialog();
		dialog.addWindowListener(new WindowListener() {

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				((Server)network_node).close();
				
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		dialog.setTitle("Message");
		dialog.setModal(true);
		dialog.setContentPane(waitingWindow);
		dialog.pack();
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
	}
}
