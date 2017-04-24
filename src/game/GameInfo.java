package game;

public class GameInfo {
	public int width, height, streak;
	public GameInfo(int width, int height, int streak){
		this.width = width;
		this.height = height;
		this.streak = streak;
	}
	public GameInfo() {
		this(7,6,4);
	}
}
