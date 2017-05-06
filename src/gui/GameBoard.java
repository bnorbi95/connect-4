package gui;

import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JPanel;

import game.Cell;
import game.GameInfo;
import game.Grid;

public class GameBoard extends JPanel{
	private Grid grid;
	private GameWindow window;
	
	public Grid getGrid(){
		return grid;
	}
	
	public GameBoard(Grid grid, GameWindow window){
		this.grid = grid;
		this.window = window;
	}
	
	public void initGameCells() {
		GameInfo info = getGrid().getGameInfo();
		setLayout(new GridLayout(info.height, info.width, 0, 0));
		for(int y = info.height - 1; y >= 0; y--){
			for(int x = 0; x < info.width; x++){
				add(new GameCell(this.grid.getCell(x, y), this));
			}
		}
	}
	
	public GameWindow getWindow(){
		return window;
	}
	
	public void setWindow(GameWindow window){
		this.window = window;
	}
	
	public void highlightColumn(int column, boolean highlight){
		for(int y = 0; y < getGrid().getGameInfo().height; y++)
		{
			getGrid().getCell(column, y).setHighlighted(highlight);
		}
	}
}
