package util;

import java.awt.Color;

public class ColorConverter {
	public final static String[] ALL_COLORS = {"MAGENTA","RED","PINK","YELLOW","ORANGE"};

	public static Color getColor(String color) {
		switch (color) {
			case "MAGENTA":
				return Color.MAGENTA;
			case "RED":
				return Color.RED;
			case "PINK":
				return Color.PINK;
			case "YELLOW":
				return Color.YELLOW;
			case "ORANGE":
				return Color.ORANGE;
		}
		return Color.BLACK;
	}
	
	public static String getColor(Color color) {
		if (color == Color.MAGENTA) return "MAGENTA";
		else if (color == Color.RED) return "RED";
		else if (color == Color.PINK) return "PINK";
		else if (color == Color.YELLOW) return "YELLOW";
		else if (color == Color.ORANGE) return "ORANGE";
		else return "BLACK";
	}
	
}
