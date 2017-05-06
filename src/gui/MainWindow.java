package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import game.GameEventHandler;
import game.Grid;
import inet.NetworkHandler;
import util.ColorConverter;

public class MainWindow extends JFrame{
	private Grid grid;
	private NetworkHandler handler;
	private GameEventHandler listener;
	
	private JButton StartButton=new JButton("START");
	private JButton LoginButton=new JButton("LOGIN");
	private JTextField TextName=new JTextField();
	private static final String[] colors = {"MAGENTA","BLUE","RED","PINK","YELLOW","ORANGE"};
	private JComboBox colorBox = new JComboBox(colors);
	
	public MainWindow(NetworkHandler handler, GameEventHandler listener, Grid grid){
		this.handler = handler;
		this.listener = listener;
		this.grid = grid;
		//GUI
		setTitle("Connect 4");
		
		generateLayout();
		
		setSize(400,300);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	private void generateLayout(){
		setLayout(new BorderLayout(15, 20));
		
		JLabel title = new JLabel("Connect Four");
		title.setOpaque(true);
		title.setFont(new Font("Times", Font.ITALIC, 20));
		title.setBackground(Color.RED);
		
		JLabel setting = new JLabel("Add your name and the color of your stone!");
		setting.setOpaque(true);
		setting.setFont(new Font("Times", Font.ITALIC, 12));
		setting.setBackground(Color.GRAY);
		
	    JPanel SetPanel=new JPanel(new GridLayout(2,2,5,5)); 
		
	    JLabel name = new JLabel("Name:");
		name.setOpaque(true);
		name.setFont(new Font("Times", Font.ITALIC, 20));
		name.setBackground(Color.BLUE);
	    
		JLabel color = new JLabel("Color of your stone:");
		color.setOpaque(true);
		color.setFont(new Font("Times", Font.ITALIC, 20));
		color.setBackground(Color.BLUE);
		
		SetPanel.add(name);
	    SetPanel.add(TextName);
	    SetPanel.add(color);
		
	    colorBox.setForeground(Color.red);
		colorBox.setBackground(Color.white);
		colorBox.setSelectedItem("RED");
		SetPanel.add(colorBox);
		
		JPanel SETPanel=new JPanel(new BorderLayout());
	    SETPanel.add(setting,BorderLayout.NORTH);
	    SETPanel.add(SetPanel, BorderLayout.CENTER);
	    
		JButton new_game_button = new JButton("New Game");
		new_game_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				listener.onSetupLocalPlayer(
						TextName.getText(), 
						ColorConverter.getColor(colorBox.getSelectedItem().toString())
				);
				handler.startServer();
				setVisible(false);
			}
			
		});
		
		JButton join_game_button = new JButton("Join Game");
		join_game_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				listener.onSetupLocalPlayer(
						TextName.getText(), 
						ColorConverter.getColor(colorBox.getSelectedItem().toString())
				);
				handler.startClient();
				setVisible(false);
			}
			
		});
		
		JPanel ButtonPanel=new JPanel(new GridLayout(1,2,5,5));
		ButtonPanel.add(new_game_button);
		ButtonPanel.add(join_game_button);
		
		add(title, BorderLayout.NORTH);
	    add(SETPanel, BorderLayout.CENTER);
	    add(ButtonPanel, BorderLayout.SOUTH);
	}
}
