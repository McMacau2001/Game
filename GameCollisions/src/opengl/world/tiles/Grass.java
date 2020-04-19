package opengl.world.tiles;

import game.Images.ImageManager;
import game.Images.SpriteSheet;
import opengl.Animation;
import opengl.resource.ImageResource;
import opengl.world.Tile;

public class Grass extends Tile {
	
	public Grass() {
		SpriteSheet sp = new SpriteSheet(ImageManager.loadImage("forest.png"), 15, 10);
		
		animations = new Animation[1];
		animations[0] = new Animation();
		animations[0].frames = new ImageResource[1];
		animations[0].frames[0] = new ImageResource(sp.grabImage(1));
		animations[0].fps = 10;
	}

}
