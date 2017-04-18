package gui;

import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JPanel;

import game.Cell;
import game.GameInfo;

public class GameBoard extends JPanel{
	private GameInfo info;
	
	public GameBoard(GameInfo info){
		this.info = info;
		setLayout(new GridLayout(this.info.width, this.info.height, 0, 0));
		for(int y = 0; y < info.height; y++){
			for(int x = 0; x < info.width; x++){
				add(new GameCell(new Cell()));
			}
		}
	}
}
