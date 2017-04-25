package gui;

import javax.swing.JFrame;

import game.GameInfo;

public class GameWindow extends JFrame {
	private final static int CELLWIDTH = 60; 
	
	private GameBoard gameBoard;
	
	public GameWindow(GameBoard gameBoard){
		setTitle("Connect 4 Game");
		
		this.gameBoard = gameBoard;
		add(gameBoard);
		
		GameInfo info = gameBoard.getGrid().getGameInfo();
		
		setSize(info.width*CELLWIDTH, info.height*CELLWIDTH);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
