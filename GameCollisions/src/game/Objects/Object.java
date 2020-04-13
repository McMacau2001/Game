package game.Objects;

import game.Entities.Camera;
import game.Game.Render.Draw;
import game.Map.Shape.Shape;
import game.Maths.Rectangle;

public abstract class Object implements ObjectDrawer {

	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected int priority = 0;
	
	protected Rectangle rectangle;
	protected boolean collide = false;
	
	public abstract void init();
	
	public void init(Camera camera, Shape map) {
		init();
		x = (int) x - map.getWidth() / 2 +10;
		y = (int) y - map.getHeight() / 2;

		rectangle = new Rectangle(x, y, width, height);

	}
	
	public void fixPriority(int priority) {
		this.priority = priority;
	}
	
	@Override
	public void render(Draw draw) {
	}
	
	public boolean isCollide() {
		return collide;
	}
	
	public Rectangle getRectangle() {
		return rectangle;
	}
	
	public float x() {
		return x;
	}
	
	public float y() {
		return y;
	}
	
	public float width() {
		return width;
	}
	
	public float height() {
		return height;
	}
	
	public int priority() {
		return priority;
	}

	
}
