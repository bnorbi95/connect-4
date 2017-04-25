package inet;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Payload {
	private static Pattern format = Pattern.compile("[0-9]+ [12] [0-9]+"); 
	
	private int round;
	private int player_id;
	private int column;
	
	public Payload(int round, int player_id, int column) {
		this.round = round;
		this.player_id = player_id;
		this.column = column;
	}
	
	public Payload(String pl) {
		if(format.matcher(pl).matches()){
			Scanner scanner = new Scanner(pl);
			round = scanner.nextInt();
			player_id = scanner.nextInt();
			column = scanner.nextInt();
		}
		else {
			//TODO add exception here
		}
	}
	
	public String toString(){
		return round + " " + player_id + " " + column;
	}
}