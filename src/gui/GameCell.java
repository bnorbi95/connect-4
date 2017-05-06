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
import util.Style;

public class GameCell extends JPanel implements MouseListener{
	private Cell cell;
	private GameBoard board;
	private Color stone_player_1;
	private Color stone_player_2;
	
	public GameCell(Cell cell, GameBoard board){
		this.addMouseListener(this);
		this.board = board;
		this.cell = cell;
		if (board.getWindow().getMe().getRole() == 1) {
			stone_player_1 = board.getWindow().getMe().getStoneColor();
			stone_player_2 = board.getWindow().getOpp().getStoneColor();
		}
		else {
			stone_player_2 = board.getWindow().getMe().getStoneColor();
			stone_player_1 = board.getWindow().getOpp().getStoneColor();
		}
	}
	
	public Cell getCell(){
		return cell;
	}
	
	
	@Override 
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      if(!cell.isHighlighted())
	      g.setColor(Style.CELL_DEFAULT_BACKGROUND);
      else
	      g.setColor(Style.CELL_HIGHLIGHTED_BACKGROUND);     
      g.fillRect(0, 0, this.getWidth(), this.getHeight());
      
      float size = 0.8f;
      if(getCell().getStatus() == Cell.EMPTY)
    	  g.setColor(Style.STONE_EMPTY);
      else if(getCell().getStatus() == Cell.PLAYER_1)
    	  g.setColor(stone_player_1);
      else if(getCell().getStatus() == Cell.PLAYER_2)
    	  g.setColor(stone_player_2);
      g.fillArc((int)((1.0f-size)*this.getWidth()/2),
    		  (int)((1.0f-size)*this.getHeight()/2), 
    		  (int)(size*this.getWidth()),
    		  (int)(size*this.getHeight()),
    		  0, 360);
    }

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if(!board.getGrid().getGameHandler().getDisabled()){
			try {
				Cell lastCell = board.getGrid().placeToColumn(
						getCell().getColumn(), 
						board.getWindow().getMe().getRole());
				board.getGrid().getGameHandler().increaseRound();
				
				Payload pl = new Payload(
					board.getGrid().getGameHandler().getRound(), 
					board.getWindow().getMe().getRole(),
					getCell().getColumn());
				
				board.getGrid().getGameHandler().setDisabled(true);
				board.highlightColumn(getCell().getColumn(), false);
				board.repaint();
				
				if(board.getGrid().checkForWin(lastCell))
				{
					JOptionPane.showMessageDialog(null, "Congratulations, you won!");
				}
			} catch (InvalidColumnException e) {
				JOptionPane.showMessageDialog(null, "Invalid column");
			} catch (ColumnIsFullException e) {
				JOptionPane.showMessageDialog(null, "That column is already full!");
			} catch (InvalidPlayerIdException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if(!board.getGrid().getGameHandler().getDisabled()){
		board.highlightColumn(getCell().getColumn(), true);
		board.repaint();
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if(!board.getGrid().getGameHandler().getDisabled()){
		board.highlightColumn(getCell().getColumn(), false);
		board.repaint();
		}
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
