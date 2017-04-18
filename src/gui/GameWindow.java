package gui;

import javax.swing.JFrame;

public class GameWindow extends JFrame {
	private GameBoard gameBoard;
	
	public GameWindow(GameBoard gameBoard){
		setTitle("Connect 4 Game");
		
		this.gameBoard = gameBoard;
		add(gameBoard);
		
		setSize(500,700);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
