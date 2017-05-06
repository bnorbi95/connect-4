package game;

import exceptions.InvalidPlayerIdException;

public class Cell {
	public static final int EMPTY = 0;
	public static final int PLAYER_1 = 1;
	public static final int PLAYER_2 = 2;
	
	private int status;
	
	private boolean highlight;
	
	private int column;
	private int row;
	
	public Cell(int status, int column, int row){
		this.status = status;
		this.column = column;
		this.row = row;
	}
	
	public Cell(int column, int row){
		this(Cell.EMPTY, column, row);
	}
	
	boolean isEmpty(){
		return status == 0;
	}
	
	void setEmpty(){
		status = Cell.EMPTY;
	}
	
	public int getColumn(){
		return column;
	}
	
	public int getRow(){
		return row;
	}

	public int getStatus(){
		return status;
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
			highlight = setHighlight;
	}
	
	public boolean isHighlighted(){
		return highlight;	
	}
	
	public String toString(){
		return status == Cell.PLAYER_1 ? "1" : status == Cell.PLAYER_2 ? "2" : ".";
	}
}
