package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import game.Cell;
import inet.Payload;

public class GameCell extends JPanel implements MouseListener{
	private Cell cell;
	private GameBoard board;
	
	public GameCell(Cell cell, GameBoard board){
		this.addMouseListener(this);
		this.board = board;
		this.cell = cell;
	}
	
	public Cell getCell(){
		return cell;
	}
	
	
	@Override 
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      if(!cell.isHighlighted())
      {
      g.setColor(new Color(0,0,255));
      g.fillRect(0, 0, this.getWidth(), this.getHeight());
      }
      else
      {
      g.setColor(new Color(0,0,100));
      g.fillRect(0, 0, this.getWidth(), this.getHeight());     
      }
      
      float size = 0.8f;
      g.setColor(new Color(120,0,0));
      g.fillArc((int)((1.0f-size)*this.getWidth()/2),
    		  (int)((1.0f-size)*this.getHeight()/2), 
    		  (int)(size*this.getWidth()),
    		  (int)(size*this.getHeight()),
    		  0, 360);
    }

	@Override
	public void mouseClicked(MouseEvent arg0) {
		//System.out.print(board.getWindow().getMe().getRole());
		//System.out.print(getCell().column);
		Payload pl = new Payload(0, board.getWindow().getMe().getRole(), getCell().column);
		System.out.print("(0, " + board.getWindow().getMe().getRole() + ", " + getCell().column + ")");
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		board.highlightColumn(getCell().column, true);
		board.repaint();
	}

	@Override
	public void mouseExited(MouseEvent e) {
		board.highlightColumn(getCell().column, false);
		board.repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
