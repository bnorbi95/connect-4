package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MainWindow extends JFrame{
	private JMenuBar menuBar;
	
	public MainWindow(){
		setTitle("Connect 4");
		
		generateLayout();
		
		menuBar = generateMenuBar();
		setJMenuBar(menuBar);
		
		setSize(400,250);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private void generateLayout(){
		setLayout(new GridLayout(1,2,5,5));
		add(new JButton("New Game"));
		add(new JButton("Join Game"));
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
