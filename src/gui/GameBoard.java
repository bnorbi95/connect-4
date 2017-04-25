package gui;

import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JPanel;

import game.Cell;
import game.GameInfo;
import game.Grid;

public class GameBoard extends JPanel{
	private Grid grid;
	
	public Grid getGrid(){
		return grid;
	}
	
	public GameBoard(Grid grid){
		this.grid = grid;
		GameInfo info = getGrid().getGameInfo();
		setLayout(new GridLayout(info.height, info.width, 0, 0));
		for(int y = 0; y < info.height; y++){
			for(int x = 0; x < info.width; x++){
				add(new GameCell(this.grid.getCell(x, y)));
			}
		}
	}
}
