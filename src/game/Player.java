package game;

import java.awt.Color;

public class Player {
	private String name;
	private Color stone;
	private int role;
	
	public Player(){
		this("Player", new Color(0,0,0));
	}
	
	public Player(String name, Color stone){
		this.name = name;
		this.stone = stone;
	}
	
	public String getName(){
		return name;
	}
	
	public Color getStoneColor(){
		return stone;
	}
	
	public void setRole(int role){
		this.role = role;
	}
}
