package game;

import game.Game.Game;
import opengl.Renderer;
import opengl.engine.GameLoop;

public class Main {
	
	public static int STARTWIDTH = 530;
	public static int STARTHEIGHT = STARTWIDTH / 16 * 9;

	public static int WIDTH = STARTWIDTH;
	public static int HEIGHT = STARTHEIGHT;
	public static float SCALE = 3.5f;
	
	public static final String TITLE = "Game";
	
	public static void main(String args[]) {
		
		//System.setProperty("sun.java2d.opengl", "true");
		
		Renderer.init();
		Game game = new Game(12000);
		game.start();
		
		
		//GameLoop.start();
		
	}
	
}
