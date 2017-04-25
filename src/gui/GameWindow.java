package gui;

import javax.swing.JFrame;

import game.GameInfo;
import game.Player;

public class GameWindow extends JFrame {
	private final static int CELLWIDTH = 60; 
	
	private GameBoard gameBoard;
	private Player me, opp;
	
	public GameWindow(GameBoard gameBoard, Player me, Player opp){
		this.me = me;
		this.opp = opp;
		
		setTitle("Connect 4 Game");
		
		this.gameBoard = gameBoard;
		add(gameBoard);
		
		GameInfo info = gameBoard.getGrid().getGameInfo();
		
		setSize(info.width*CELLWIDTH, info.height*CELLWIDTH);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public Player getMe(){
		return me;
	}
}
