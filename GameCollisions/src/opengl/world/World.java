package opengl.world;

import java.util.ArrayList;

import opengl.Graphics;

public class World {
	
	private static ArrayList<Tile> tiles = new ArrayList<Tile>();
	private static ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
	
	public static void update() {

		for(Tile t : tiles)
			t.update();
		
		for(GameObject go : gameObjects)
			go.update();
	}
	
	public static void render() {		

		for(Tile t : tiles)
			t.render();
	
		for(GameObject go : gameObjects)
			go.render();
	}
	
	public static void addGameObject(GameObject go) {
		gameObjects.add(go);
	}
	
	public static void addTile(Tile t) {
		tiles.add(t);
	}

}
