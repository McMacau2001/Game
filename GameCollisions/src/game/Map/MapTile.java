package game.Map;

import java.awt.image.BufferedImage;

public class MapTile {
	
	private BufferedImage image;
	private int x;
	private int y;
	private int priority;
	
	public MapTile(BufferedImage image, int x,  int y, int priority) {
		this.image = image;
		this.x = x;
		this.y = y;
		this.priority = priority;
	}
	
	public int getPriority() {
		return priority;
	}
	
	public BufferedImage image() {
		return image;
	}
	
	public int x() {
		return x;
	}
	
	public int y() {
		return y;
	}
	

}
