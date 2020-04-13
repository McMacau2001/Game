package game.Maths;

public class Point2D {
	
	private float x;
	private float y;
	
	public Point2D(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public float distance(Point2D point) {
		return (float) Math.sqrt((point.y - y) * (point.y - y) + (point.x - x) * (point.x - x));
	}
	
	public float x() {
		return x;
	}
	
	public float y() {
		return y;
	}
	
	public void x(float x) {
		this.x = x;
	}
	
	public void y(float y) {
		this.y = y;
	}
	
	

}
