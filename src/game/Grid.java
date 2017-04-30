package game;

import exceptions.ColumnIsFullException;
import exceptions.InvalidColumnException;
import exceptions.InvalidPlayerIdException;

/**
 * Class for stroing game board. Coordinate system: left to right (from 0), bottom to top
 * @author Norbert Balogh
 *
 */
public class Grid {
	private GameInfo info;
	private Cell[][] data;
	private GameHandler handler;

	public Grid(GameInfo info){
		this.info = info;
		data = new Cell[this.info.height][this.info.width];
		for(int y = 0; y < this.info.height; y++){
			for(int x = 0; x < this.info.width; x++){
				data[y][x] = new Cell(x);
			}
		}
	}
	
	public Grid(){
		this(new GameInfo(7,6,4));
	}
	
	public void setHandler(GameHandler handler){
		this.handler = handler;
	}
	
	public GameInfo getGameInfo(){
		return info;
	}
	
	public GameHandler getGameHandler(){
		return handler;
	}
	
	public Cell getCell(int x, int y){
		return data[y][x];
		//TODO parameter check
	}
	
	private boolean isValidColumn(int column){
		return (column >= 0 && column < info.width);
	}
	
	/**
	 * Checks if column is full or not.
	 * @param column number of column (starting from 0, counting from left ot right)
	 * @return true if column is full, false otherwise
	 */
	private boolean isColumnFull(int column){
		return !data[info.height-1][column].isEmpty();
	}
	
	/**
	 * Alters data field, places stone to column.
	 * @param column number of column (starting from 0, counting from left ot right)
	 * @param playerId 1 or 2
	 * @return Modified cell
	 * @throws InvalidColumnException if column number is out of bounds
	 * @throws ColumnIsFullException if column is full
	 */
	public Cell placeToColumn(int column, int playerId) throws InvalidColumnException, ColumnIsFullException, InvalidPlayerIdException{
		if(isValidColumn(column)){
			if(!isColumnFull(column)){
				int i = 0;
				while(!data[i][column].isEmpty()){
					i++;
				}
				data[i][column].setPlayer(playerId);
				return data[i][column];
			}else{
				throw new ColumnIsFullException(column);
			}
		}else{
			throw new InvalidColumnException(column, info.width);
		}
	}
	
	/**
	 * Checks if last placed stone won the game. If yes, the winner is the owner of the last placed stone.
	 * @param last Cell of the last placed stone. Can be aquired using return value of placeToColumn(int column)
	 * @return true if last placed stone won the game, false otherwise.
	 */
	public boolean checkForWinner(Cell last){
		return false;
		
	}
	
	public void printGrid(){
		for(int x = 0; x < info.width; x++){
			System.out.print("--");
		}
		System.out.println();
		for(int y = info.height-1; y >=0; y--){
			for(int x = 0; x < info.width; x++){
				System.out.print(data[y][x]+" ");
			}
			System.out.println();
		}
	}
}
