package exceptions;

public class ColumnIsFullException extends Exception {
	public ColumnIsFullException(int column){
		super("Column "+column+" is full.");
	}
}
