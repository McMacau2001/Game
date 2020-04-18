package game;

import game.Game.Game;

public class Main {
	
	public static int STARTWIDTH = 320;
	public static int STARTHEIGHT = STARTWIDTH / 16 * 9;

	public static int WIDTH = STARTWIDTH;
	public static int HEIGHT = STARTHEIGHT;
	public static float SCALE = 3f;
	
	public static final String TITLE = "Game";
	
	public static void main(String args[]) {
		
		System.setProperty("sun.java2d.opengl", "true");
		
		Game game = new Game(12000);
		game.start();
		
	}
	
}
