package inet;

import java.awt.Color;
import java.util.Scanner;
import java.util.regex.Pattern;

import util.ColorConverter;

public class PayloadConfig extends PayloadBase {
	private static Pattern format = Pattern.compile("[12] `[^`]*` [A-Z]+"); 

	private int player_id;
	private String name;
	private String color;
	
	public PayloadConfig(int player_id, String name, Color color) {
		super(TYPE_CONFIG);
		this.player_id = player_id;
		this.name = name;
		this.color = ColorConverter.getColor(color);
	}
	
	public PayloadConfig(int player_id, String name, String color) {
		super(TYPE_CONFIG);
		this.player_id = player_id;
		this.name = name;
		this.color = color;
	}
	
	public PayloadConfig(String pl) {
		super(TYPE_CONFIG);
		if(format.matcher(pl).matches()) {
			Scanner scanner = new Scanner(pl);
			player_id = scanner.nextInt();
			name = scanner.next();
			name = name.substring(1, name.length() - 1);
			color = scanner.next();
			scanner.close();
		}
	}
	
	public int getPlayerID() {
		return player_id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getColor() {
		return color;
	}
	
	@Override
	public String toString() {
		return player_id + " `" + name + "` " + color + "\n";
	}
}
