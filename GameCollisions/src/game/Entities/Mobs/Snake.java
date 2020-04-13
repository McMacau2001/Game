package game.Entities.Mobs;

import java.awt.Point;
import java.awt.geom.Point2D;

import game.Entities.Entity;
import game.Game.Render.Draw;
import game.Images.ImageManager;
import game.Images.Sprite;
import game.Images.SpriteAnimation;
import game.Images.SpriteSheet;
import game.Maths.Maths;
import game.Maths.Rectangle;
import game.Objects.Object;

public class Snake extends Entity {
	
	private SpriteAnimation animation;
	
	private SpriteSheet sheet;
	private Sprite walking_right;
	private Sprite walking_left;
	
	private float speed = 0.8f;
	public Point2D positionToGo;
	
	public Snake(int x, int y) {
		super(new Rectangle(0, 0, 16, 14), false);
		teleport(x, y);
		
		sheet = new SpriteSheet(ImageManager.loadImage("snake.png"), 8, 5);
		animation = new SpriteAnimation();
		
		walking_right = new Sprite(sheet, 9, 16, false);
		walking_left = new Sprite(sheet, 9, 16, true);

		animation.runSprite(walking_right, (int)speed+2, true);

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		if(positionToGo == null) {
			positionToGo = new Point((int)(Maths.randomInt(350, -350)+x()), (int)(Maths.randomInt(350, -350)+y()));
		}
		
		positionToGo = moveEntityTo(positionToGo, speed);
		
		if(getDirection().x()>0) 
			animation.runSprite(walking_right, (int)speed+2, true);
		else 
			animation.runSprite(walking_left, (int)speed+2, true);
		
		animation.runAnimation();
		
	}

	@Override
	public void draw(Draw draw) {
		animation.drawAnimation(draw, x()-12, y()-18, 0,0, false, 0);	
		//draw.drawRect(x(), y(), width(), height());
	}

	@Override
	public void collideWithEntity(Entity e, int position) {

	}

	@Override
	public void collideWithTile(Object t, int position) {
		if(positionToGo != null)
			positionToGo = new Point((int)-(positionToGo.getX()+Maths.randomInt(350, -350)), (int)-(positionToGo.getY()+Maths.randomInt(350, -350)));
	}



}


