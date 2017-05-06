package inet;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Payload extends PayloadBase {
	private static Pattern format = Pattern.compile("[0-9]+ [12] [0-9]+"); 
	
	private int player_id;
	private int round;
	private int column;
	
	public Payload(int round, int player_id, int column) {
		super(TYPE_ROUND);
		this.round = round;
		this.player_id = player_id;
		this.column = column;
	}
	
	public Payload(String pl) {
		super(TYPE_ROUND);
		if(format.matcher(pl).matches()){
			Scanner scanner = new Scanner(pl);
			round = scanner.nextInt();
			player_id = scanner.nextInt();
			column = scanner.nextInt();
			scanner.close();
		}
		else {
			//TODO add exception here
		}
	}
	
	@Override
	public String toString(){
		return round + " " + player_id + " " + column + "\n";
	}
	
	public int getRound(){
		return round;
	}
	
	public int getPlayerID() {
		return player_id;
	}
	
	public int getColumn(){
		return column;
	}
}
