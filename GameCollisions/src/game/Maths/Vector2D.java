package game.Maths;

public class Vector2D {
	
	private float x;
	private float y;
	
	public Vector2D(float x2, float y2) {
		this.x = x2;
		this.y = y2;
	}
	
	public void x(float x) {
		this.x = x;
	}
	
	public void y(float y) {
		this.y = y;
	}
	public float x() {
		return x;
	}
	
	public float y() {
		return y;
	}
	
	public boolean isZero() {
		return x == 0 && y == 0;
	}
	
	public Vector2D subtract(Vector2D vector) {
		return new Vector2D(x+vector.x(), y+vector.y());
	}
	
	public Vector2D inverse() {
		return new Vector2D(-x, -y);
	}
	
	public boolean equals(Vector2D vector) {
		if(vector == null) return false;
		return x == vector.x() && y == vector.y();
	}


}
