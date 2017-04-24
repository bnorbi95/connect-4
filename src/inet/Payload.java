package inet;

public class Payload {
	private int round;
	private int player_id;
	private int column;
	
	public Payload(int round, int player_id, int column) {
		this.round = round;
		this.player_id = player_id;
		this.column = column;
	}
	
	public String toString(){
		return round + " " + player_id + " " + column;
	}
}
