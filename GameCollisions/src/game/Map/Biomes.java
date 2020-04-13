package game.Map;

import java.awt.Color;
import java.awt.image.BufferedImage;

import game.Images.ImageManager;

public enum Biomes {
	
	//BIOMES
	FOREST("Forest", "forest.png", new Color(37, 59, 46)),
	DESERT("Desert", "desert.png", new Color(108, 63, 0));
	
	private String name;
	private BufferedImage tile;
	
	private Color background;
	
	Biomes(String name, String tile, Color background) {
		this.name = name;
		this.tile = ImageManager.loadImage(tile);
		this.background = background;
		
	}
	
	public String getName() {
		return name;
	}
	
	public BufferedImage getTile() {
		return tile;
	}

	public Color getBackground() {
		return background;
	}

}
