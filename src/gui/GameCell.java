package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import exceptions.ColumnIsFullException;
import exceptions.InvalidColumnException;
import exceptions.InvalidPlayerIdException;
import game.Cell;
import inet.Payload;

public class GameCell extends JPanel implements MouseListener{
	private final static Color DEFAULT_BACKGROUND = Color.blue;
	private final static Color HIGHLIGHTED_BACKGROUND = Color.cyan;
	
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
      g.setColor(DEFAULT_BACKGROUND);
      g.fillRect(0, 0, this.getWidth(), this.getHeight());
      }
      else
      {
      g.setColor(HIGHLIGHTED_BACKGROUND);
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
		try {
			board.getGrid().placeToColumn(getCell().column, board.getWindow().getMe().getRole());
			board.getGrid().getGameHandler().increaseRound();
			Payload pl = new Payload(board.getGrid().getGameHandler().getRound(), board.getWindow().getMe().getRole(), getCell().column);
		} catch (InvalidColumnException e) {
			JOptionPane.showMessageDialog(null, "Invalid column");
			e.printStackTrace();
		} catch (ColumnIsFullException e) {
			JOptionPane.showMessageDialog(null, "That column is already full!");
			e.printStackTrace();
		} catch (InvalidPlayerIdException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
