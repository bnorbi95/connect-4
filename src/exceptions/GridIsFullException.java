package exceptions;

public class GridIsFullException extends Exception{
	public GridIsFullException(){
		super("Grid is full. It's Draw!");
		System.exit(0);
	}
}
