package game.Entities.Mobs;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.Random;
import java.util.Vector;

import game.Entities.Entity;
import game.Game.Render.Draw;
import game.Images.ImageManager;
import game.Images.Sprite;
import game.Images.SpriteAnimation;
import game.Images.SpriteSheet;
import game.Maths.Maths;
import game.Maths.Rectangle;
import game.Maths.Vector2D;
import game.Objects.Object;

public class Bat extends Entity {
	
	private SpriteAnimation animation;
	
	private SpriteSheet sheet;
	private Sprite walking;
	
	private float speed = 1.4f;
	public Point2D positionToGo;
	
	public Bat(int x, int y) {
		super(new Rectangle(0, 0, 14, 12), false);
		teleport(x, y);
		
		sheet = new SpriteSheet(ImageManager.loadImage("bat.png"), 5, 6);
		animation = new SpriteAnimation();
		
		walking = new Sprite(sheet, 1, 5, false);

		animation.runSprite(walking, (int)speed+2, true, Maths.randomInt(walking.getImages().length-1, 0));

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		if(positionToGo == null) {
			positionToGo = new Point((int)(Maths.randomInt(150, -150)+x()), (int)(Maths.randomInt(150, -150)+y()));
		}
		
		positionToGo = moveEntityTo(positionToGo, speed);
		
		animation.runAnimation();
		
	}

	@Override
	public void draw(Draw draw) {
		animation.drawAnimation(draw, x(), y(), 0,0, false, 0);	
		//draw.drawRect(x(), y(), width(), height());
	}

	@Override
	public void collideWithEntity(Entity e, int position) {
		
	}

	@Override
	public void collideWithTile(Object t, int position) {
		if(positionToGo != null)
			positionToGo = new Point((int)-(positionToGo.getX()+Maths.randomInt(150, -150)), (int)-(positionToGo.getY()+Maths.randomInt(150, -150)));
	}



}
