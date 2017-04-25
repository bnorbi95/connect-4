package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import game.GameInfo;
import game.GameListener;
import game.Grid;
import inet.Client;
import inet.NetworkHandler;
import inet.Server;

public class MainWindow extends JFrame{
	private JMenuBar menuBar;
	private Grid grid;
	private NetworkHandler handler;
	private GameListener listener;
	
	public MainWindow(NetworkHandler handler, GameListener listener, Grid grid){
		this.handler = handler;
		this.listener = listener;
		this.grid = grid;
		//GUI
		setTitle("Connect 4");
		
		generateLayout();
		
		menuBar = generateMenuBar();
		setJMenuBar(menuBar);
		
		setSize(400,250);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	private void generateLayout(){
		setLayout(new GridLayout(1,2,5,5));
		JButton new_game_button = new JButton("New Game");
		new_game_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				handler.startServer();
				listener.onGameSetup();
			}
			
		});
		
		JButton join_game_button = new JButton("Join Game");
		join_game_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				handler.startClient();
			}
			
		});
		add(new_game_button);
		add(join_game_button);
	}
	
	private JMenuBar generateMenuBar(){
		//Settings menu
		JMenu settings = new JMenu("Settings");
		JMenuItem playerSettings = new JMenuItem("Player settings");
		settings.add(playerSettings);
		settings.add(new JMenuItem("Game settings"));
		
		
		//MenuBar
		JMenuBar rtv = new JMenuBar();
		rtv.add(settings);
		
		return rtv;
	}
}
