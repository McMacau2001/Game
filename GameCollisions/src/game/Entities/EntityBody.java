package game.Entities;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import game.Main;
import game.Maths.Rectangle;
import game.Maths.Vector2D;

public class EntityBody{

	protected Vector2D velocity;
	protected Vector2D direction;
	
	private Rectangle bodycollide;
	private Rectangle bodyfoot;
	
	private Rectangle head;
	private Rectangle foot;
	private Rectangle right;
	private Rectangle left;
	
	private ArrayList<Integer> collidingFaces = new ArrayList<Integer>();
	
	public EntityBody(Rectangle body) {
		this.bodycollide = body;
		this.direction = new Vector2D(0, 0);
		
		this.bodyfoot = new Rectangle(0, height()/4*3, width(), height()/4);
		
		this.head = new Rectangle(bodyfoot.x() + bodyfoot.width()/8, bodyfoot.y() -2, bodyfoot.width() - bodyfoot.width()/4, bodyfoot.height()/2);
		this.foot = new Rectangle(bodyfoot.x() + bodyfoot.width()/8, bodyfoot.y()+bodyfoot.height() - bodyfoot.height()/2 + 2, bodyfoot.width() - bodyfoot.width()/4, bodyfoot.height()/2);

		this.left = new Rectangle(bodyfoot.x() - 2, bodyfoot.y() + bodyfoot.height()/30, bodyfoot.width()/2, bodyfoot.height() - bodyfoot.height()/14);
		this.right = new Rectangle(bodyfoot.x() + bodyfoot.width() - bodyfoot.width()/2 + 2, bodyfoot.y() + bodyfoot.height()/30, bodyfoot.width()/2 , bodyfoot.height()- bodyfoot.height()/14);

	}
	
	public boolean moveEntity(Vector2D v) {
		direction = v;
		
		if(v.isZero()) 
			return false;
		
		//Update 
		bodycollide.move(v);
		bodyfoot.move(v);
	
		head.move(v);
		foot.move(v);
		right.move(v);
		left.move(v);
		
		return true;
		
	}	
	
	public Point2D moveEntityTo(Point2D position, float speed) {
		if(position != null) {
			Point2D entity = new Point((int)(x() + width()/ 2), (int)(y() + height()/2));
			
			float hypotenuse = (float) entity.distance(position);
			
			if(hypotenuse >= speed) {
				float cattetx = (float) (position.getX() - entity.getX());
				float cattety = (float) (position.getY() - entity.getY());
				
				float xv = (float) (speed * cattetx / hypotenuse);
				float yv = (float) (speed * cattety / hypotenuse);
	
				moveEntity(new Vector2D(xv, yv));
				
				return position;
				
			}
				
		}
		return null;
	}
	
	public void teleport(int x, int y) {
		float velx = x - x();
		float vely = y - y();
		this.moveEntity(new Vector2D(velx, vely));
	}
	
	public void teleport(Point2D point) {
		float velx = (float) (point.getX() - x());
		float vely = (float) (point.getY() - y());
		this.moveEntity(new Vector2D(velx, vely));
	}
	
	//******************
	// COLLISIONS
	
	public void fixPositionCollide(Rectangle r, int position) {
		if(position == 1) //HEAD
			moveEntity(new Vector2D(0, (r.y() - bodyfoot.y()) + r.height()));
		else if(position == 3) //FOOT
			moveEntity(new Vector2D(0, r.y() - bodyfoot.y() - bodyfoot.height()));
		else if(position == 4) //LEFT
			moveEntity(new Vector2D((r.x() - bodyfoot.x()) + r.width(), 0));
		else if(position == 2) ///RIGHT
			moveEntity(new Vector2D((r.x() - bodyfoot.x()) - bodyfoot.width(), 0));
			
	}
	
	
	public void fixDividedPositionCollide(Rectangle r, int position, float percentage) {
		if(position == 1) //HEAD
			moveEntity(new Vector2D(0, ((r.y() - bodyfoot.y()) + r.height()) / percentage));
		else if(position == 3) //FOOT
			moveEntity(new Vector2D(0, (r.y() - bodyfoot.y() - bodyfoot.height()) / percentage));
		else if(position == 4) //LEFT
			moveEntity(new Vector2D(((r.x() - bodyfoot.x()) + r.width()) / percentage, 0));
		else if(position == 2) ///RIGHT
			moveEntity(new Vector2D(((r.x() - bodyfoot.x()) - bodyfoot.width()) / percentage, 0));

	}
	
	
	public int collide(Rectangle rec, Vector2D velocity) {
		int position = 0;
		
		//Priority check

		boolean h = head.collide(rec); //1
		boolean f = foot.collide(rec); //3
		boolean l = left.collide(rec); //4
		boolean r = right.collide(rec); //2
		
		if(r && l && h)
			position = 1;
		else if(r && l && f)
			position = 3;
		
		else if(h && f && r)
			position = 2;
		else if(h && f && l)
			position = 4;
		
		if(h) addCollideFace(1);
		if(f) addCollideFace(3);
		if(r) addCollideFace(2);
		if(l) addCollideFace(4);
		
		return position;
	}
	
	private void addCollideFace(int face) {
		if(face != 0 && !collidingFaces.contains((Integer)face))
			collidingFaces.add(face);
	}
	
	public boolean isCollidingFace(int face) {
		return collidingFaces.contains((Integer)face);
	}
	
	public void clearCollideFaces() {
		collidingFaces.clear();
	}
	
	// COLLISIONS
	//******************
	
	public Rectangle getBodycollide() {
		return bodycollide;
	}
	
	public Rectangle getBodyfoot() {
		return bodyfoot;
	}

	public float width() {
		return bodycollide.width();
	}
	
	public float height() {
		return bodycollide.height();
	}
	
	public Rectangle getFoot() {
		return foot;
	}
	
	public Rectangle getHead() {
		return head;
	}
	
	public Rectangle getLeft() {
		return left;
	}
	
	public Rectangle getRight() {
		return right;
	}
	
	public float x() {
		return this.bodycollide.x();
	}
	
	public float y() {
		return this.bodycollide.y();
	}
	
	public Vector2D getVelocity() {
		return velocity;
	}
	
	public Vector2D getDirection() {
		return direction;
	}
	
}
