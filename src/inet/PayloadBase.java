package inet;

public abstract class PayloadBase {
	protected int player_id;
	
	public int getPlayerID() {
		return player_id;
	}
	
	public String toString() {
		return Integer.toString(player_id);
	}
}
