package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import game.GameInfo;
import game.Player;
import util.Style;

public class GameWindow extends JFrame{
	private final static int CELLWIDTH = 60; 
	private final static int SHOW_PANEL_WIDTH = 100;
	
	private GameBoard gameBoard;
	private Player me, opp;
	private JLabel statusbar;
	
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
		showPanel.add(colorBox(me.getStoneColor()));
		showPanel.add(new JLabel(opp.getName()));
		showPanel.add(colorBox(opp.getStoneColor()));
		
		JLabel title = new JLabel("Connect Four");
		title.setOpaque(true);
		title.setBackground(Style.GAME_TITLE_BACKGROUND);
		title.setFont(Style.GAME_TITLE_FONT);
		
		JPanel showPanelContainer = new JPanel();
		showPanelContainer.setLayout(new BoxLayout(showPanelContainer, BoxLayout.X_AXIS));
		showPanelContainer.add(showPanel);
		showPanelContainer.setPreferredSize(new Dimension(SHOW_PANEL_WIDTH, 0));
		
		statusbar = new JLabel("Current player: ");
		
		add(showPanelContainer, BorderLayout.EAST);
		add(title, BorderLayout.NORTH);
		add(gameBoard, BorderLayout.CENTER);
		add(statusbar, BorderLayout.SOUTH);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(info.width*CELLWIDTH + SHOW_PANEL_WIDTH, info.height*CELLWIDTH + 40);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}
	
	public JLabel getStatusbar(){
		return statusbar;
	}
	
	public Player getMe(){
		return me;
	}
	
	public Player getOpp(){
		return opp;
	}
	
	private JPanel colorBox(Color bg) {
		JPanel p = new JPanel();
		p.setBackground(bg);
		return p;
	}
}
