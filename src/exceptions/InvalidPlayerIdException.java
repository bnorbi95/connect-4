package exceptions;

public class InvalidPlayerIdException extends Error {
	public InvalidPlayerIdException(){
		super("Invalid player id provided");
	}
	
	public InvalidPlayerIdException(int id){
		super("Invalid player id provided: "+id);
	}
}
