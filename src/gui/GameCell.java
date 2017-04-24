package gui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import game.Cell;

public class GameCell extends JPanel{
	private Cell cell;
	
	public GameCell(Cell cell){
		this.cell = cell;
	}
	
	public Cell getCell(){
		return cell;
	}
	
	@Override 
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      g.setColor(new Color(0,0,255));
      g.fillRect(0, 0, this.getWidth(), this.getHeight());
      
      float size = 0.8f;
      g.setColor(new Color(120,0,0));
      g.fillArc((int)((1.0f-size)*this.getWidth()/2),
    		  (int)((1.0f-size)*this.getHeight()/2), 
    		  (int)(size*this.getWidth()),
    		  (int)(size*this.getHeight()),
    		  0, 360);
    }
}
