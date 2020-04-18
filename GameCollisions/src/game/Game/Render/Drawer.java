package game.Game.Render;

import java.awt.image.BufferedImage;

import game.Main;
import game.Entities.Camera;
import game.Maths.Rectangle;

public class Drawer {

	private Rectangle rectangle;
	private boolean hasOpcaity;
	private int priority;
	
	private BufferedImage image;
	
	public Drawer(BufferedImage image, float x, float y, float width, float height, boolean hasOpacity, int priority) {
		this.image = image;
		this.rectangle = new Rectangle(x, y, width, height);
		this.hasOpcaity = hasOpacity;
		this.priority = priority;
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
	public boolean isOpcaity() {
		return hasOpcaity;
	}
	
	public Rectangle getRectangle() {
		return rectangle;
	}
	
	public Integer getHeightRelative() {
		return (int) (rectangle.y() + rectangle.height());
	}
	
	public int getPriority() {
		return priority;
	}
	
	public void setOpcaity(boolean hasOpcaity) {
		this.hasOpcaity = hasOpcaity;
	}
	
	
	public boolean isInsideScreen(Camera camera) {
		float x = camera.getxOffset();
		float y = camera.getyOffset();
		
		float width = Main.WIDTH;
		float height = Main.HEIGHT;
		
		Rectangle rec = new Rectangle(camera.toXLocation(x), camera.toYLocation(y), width, height);
		return rec.collide(rectangle);
	}
	
	
}
