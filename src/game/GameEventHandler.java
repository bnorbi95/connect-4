package game;

import java.awt.Color;

public interface GameEventHandler {
	public void onGameSetup();
	public void onSetupLocalPlayer(String playerName, Color stoneColor, int role);
}
