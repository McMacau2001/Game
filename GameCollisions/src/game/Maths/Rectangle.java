package game.Maths;

import game.Game.Render.Draw;

public class Rectangle {
	
	private float x;
	private float y;
	
	private float width;
	private float height;
	
	public Rectangle(float x, float y, float width, float height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public boolean collide(Rectangle rectangle) {
		boolean collide = false;
		
		if(x < (rectangle.x + rectangle.width) && 
		  (x + width) > rectangle.x &&
		  y < (rectangle.y + rectangle.height) && 
		  (y + height) > rectangle.y)
			collide = true;
		
		return collide;
	}
	
	public void move(Vector2D velocity) {
		x(x + velocity.x());
		y(y + velocity.y());
	}
	
	public void render(Draw draw) {
		draw.drawRect(x ,y ,width , height);
	}
	
	public Rectangle clone() {
		return new Rectangle(x, y, width, height);
	}
	
	public void x(float x) {
		this.x = x;
	}
	
	public void y(float y) {
		this.y = y;
	}
	
	public float x() {
		return this.x;
	}
	
	public float y() {
		return this.y;
	}
	
	public float width() {
		return width;
	}
	
	public float height() {
		return height;
	}
	

}
