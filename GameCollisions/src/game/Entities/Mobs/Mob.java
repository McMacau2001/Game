package game.Entities.Mobs;

import game.Entities.Entity;
import game.Game.Render.Draw;
import game.Maths.Rectangle;
import game.Objects.Object;

public class Mob extends Entity {

	private float speed = 0;

	public Mob(float x, float y, float width, float height, float speed) {
		super(new Rectangle(x, y, width, height), false);
		this.speed = speed;
		
		move(speed, 0);
	}

	@Override
	public void init() {

		
	}

	@Override
	public void update() {
		
		/*if(size == 60*8) {
			speed = -speed;
			size = 0;
		}*/
		
		//size++;
		
	}

	@Override
	public void draw(Draw draw) {
	}

	@Override
	public void collideWithEntity(Entity e, int position) {

		move(speed = -speed, 0);
		
	}

	@Override
	public void collideWithTile(Object t, int position) {
		
		move(speed = -speed, 0);
		
	}

}
