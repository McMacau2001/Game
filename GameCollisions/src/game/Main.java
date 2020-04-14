package game;

import game.Game.Game;

public class Main {

	public static final int WIDTH = 680;
	public static final int HEIGHT = WIDTH / 16 * 9;
	public static final float SCALE = 1.5f;
	
	public static final String TITLE = "Game";
	
	public static void main(String args[]) {
		
		System.setProperty("sun.java2d.opengl", "true");
			
		Game game = new Game(120);
		game.start();
		
	}
	
}
