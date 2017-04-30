package game;

import exceptions.InvalidPlayerIdException;

public class Cell {
	public static final int EMPTY = 0;
	public static final int PLAYER_1 = 1;
	public static final int PLAYER_2 = 2;
	
	private int status;
	
	private boolean highlight;
	
	public int column;
	
	public Cell(int status, int column){
		this.status = status;
		this.column = column;
	}
	
	public Cell(int column){
		this(Cell.EMPTY, column);
	}
	
	boolean isEmpty(){
		return status == 0;
	}
	
	void setEmpty(){
		status = Cell.EMPTY;
	}
	
	void setPlayer(int id) throws InvalidPlayerIdException{
		switch(id){
			case 1:
				status = Cell.PLAYER_1;
				break;
			case 2:
				status = Cell.PLAYER_2;
				break;
			default:
				throw new InvalidPlayerIdException(id);
		}
	}
	
	public void setHighlighted(boolean setHighlight){
		if(setHighlight) 
			highlight = true;
		if(!setHighlight)
			highlight = false;
	}
	
	public boolean isHighlighted(){
		return highlight;	
	}
	
	public String toString(){
		return status == Cell.PLAYER_1 ? "1" : status == Cell.PLAYER_2 ? "2" : ".";
	}
}
