package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
		GameInfo info = gameBoard.getGrid().getGameInfo();
		
		setLayout(new BorderLayout());
		add(gameBoard);
		
		JPanel showPanel = new JPanel(new GridLayout(4, 1, 10, 10));
		showPanel.add(new JLabel(me.getName()));
		showPanel.add(new JLabel(me.getStoneColor().toString()));
		showPanel.add(new JLabel(opp.getName()));
		showPanel.add(new JLabel(opp.getStoneColor().toString()));
		
		JLabel title = new JLabel("Connect Four");
		title.setOpaque(true);
		title.setBackground(Color.RED);
		title.setFont(new Font("Times", Font.ITALIC, 20));
		
		add(showPanel, BorderLayout.EAST);
		add(title, BorderLayout.NORTH);
		add(gameBoard, BorderLayout.CENTER);
		
		setSize(info.width*CELLWIDTH, info.height*CELLWIDTH);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public Player getMe(){
		return me;
	}
}
