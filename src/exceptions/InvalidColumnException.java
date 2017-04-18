package exceptions;

public class InvalidColumnException extends Exception{
	public InvalidColumnException(int column, int width){
		super("Column number must be in interval [0;"+width+"). Got "+column);
	}
}
