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
import util.Style;

public class MainWindow extends JFrame{
	private Grid grid;
	private NetworkHandler handler;
	private GameEventHandler listener;
	
	private JTextField TextName=new JTextField();
	private static final String[] colors = ColorConverter.ALL_COLORS;
	private JComboBox<String> colorBox = new JComboBox<String>(colors);
	
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
		title.setFont(Style.GAME_TITLE_FONT);
		title.setBackground(Style.MAIN_TITLE_BACKGROUND);
		
		JLabel setting = new JLabel("Add your name and the color of your stone!");
		setting.setOpaque(true);
		setting.setFont(Style.MAIN_SETTINGS_FONT);
		setting.setBackground(Style.MAIN_SETTINGS_BACKGROUND);
		
	    JPanel SetPanel=new JPanel(new GridLayout(2,2,5,5)); 
		
	    JLabel name = new JLabel("Name:");
		name.setOpaque(true);
		name.setFont(new Font("Times", Font.ITALIC, 20));
		name.setBackground(Style.MAIN_NAME_BACKGROUND);
	    
		JLabel color = new JLabel("Color of your stone:");
		color.setOpaque(true);
		color.setFont(Style.MAIN_COLOR_FONT);
		color.setBackground(Style.MAIN_COLOR_BACKGROUND);
		
		SetPanel.add(name);
	    SetPanel.add(TextName);
	    SetPanel.add(color);
		
	    colorBox.setForeground(Style.MAIN_COLORBOX_FOREGROUND);
		colorBox.setBackground(Style.MAIN_COLORBOX_BACKGROUND);
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
						ColorConverter.getColor(colorBox.getSelectedItem().toString()),
						1
				);
				handler.startServer();
				handler.onWaitinForPlayer();
				setVisible(false);
			}
			
		});
		
		JButton join_game_button = new JButton("Join Game");
		join_game_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				listener.onSetupLocalPlayer(
						TextName.getText(), 
						ColorConverter.getColor(colorBox.getSelectedItem().toString()),
						2
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
