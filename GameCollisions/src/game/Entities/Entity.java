package game.Entities;

import java.awt.geom.Point2D;

import game.Game.Handlers.Collisions;
import game.Game.Render.Draw;
import game.Map.Map;
import game.Maths.Rectangle;
import game.Maths.Vector2D;
import game.Objects.Object;

public abstract class Entity extends EntityBody {
	
	private boolean collide;
	
	public abstract void init();
	public abstract void update();
	public abstract void draw(Draw draw);
	
	public abstract void collideWithEntity(Entity e, int position);
	public abstract void collideWithTile(Object t, int position);
	
	public Entity(Rectangle body, boolean collide) {
		super(body);
		this.collide = collide;
		this.velocity = new Vector2D(0, 0);
		
		init();
	}
	
	public void tick(Collisions collisions, Map room) {
		update();
		moveEntity(velocity);
	}
	
	public void render(Draw draw) {
		draw(draw);
	}
	
	public void move(float x, float y) {
		velocity = new Vector2D(x, y);
	}
	
	public void move(Vector2D velocity) {
		this.velocity = velocity;
	}
	
	public boolean isMoving() {
		return !velocity.isZero();
	}
	
	public boolean isCollide() {
		return collide;
	}
		
}