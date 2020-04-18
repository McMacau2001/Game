package game.Entities.Player;

import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;

import game.Entities.Entity;
import game.Game.Game;
import game.Game.Render.Draw;
import game.Images.ImageManager;
import game.Images.Sprite;
import game.Images.SpriteAnimation;
import game.Images.SpriteSheet;
import game.Maths.Maths;
import game.Maths.Rectangle;
import game.Objects.Object;

public class Player extends Entity {
	
	private float speed = 2.2f;
	public Point2D positionToGo;
	
	private SpriteAnimation animation;
	
	private SpriteSheet sheet;
	private Sprite walking_right;
	private Sprite walking_left;
	private Sprite attack_right;
	private Sprite attack_left;

	public Player(Game game, float x, float y, float width, float height) {
		super(new Rectangle(x, y, width, height), true);
	}
	
	@Override
	public void init() {
		
		sheet = new SpriteSheet(ImageManager.loadImage("player.png"), 10, 10);
		animation = new SpriteAnimation();
		
		//11 18 
		walking_right = new Sprite(sheet, 11, 18, false);
		walking_left = new Sprite(sheet, 11, 18, true);
		
		attack_right = new Sprite(sheet, 24, 27, false);
		attack_left = new Sprite(sheet, 24, 27, true);
		
		animation.runSprite(walking_right, 3, true);
		
		
	}

	@Override
	public void update() {
		//Key Input

		float velX = 0;
		float velY = 0;
			
		if(Game.input.isPressedKey(KeyEvent.VK_D)) {
			velX = speed;//movePlayer(speed , 0);
			positionToGo = null;
		}
		if(Game.input.isPressedKey(KeyEvent.VK_A)) {
			velX = -speed; //movePlayer(-speed , 0);		
			positionToGo = null;
		}
		if(Game.input.isPressedKey(KeyEvent.VK_S))  {
			velY = speed; //movePlayer(0 , speed);
			positionToGo = null;
		}	
		if(Game.input.isPressedKey(KeyEvent.VK_W)) {
			velY = -speed; //movePlayer(0 , -speed);
			positionToGo = null;
		}	
		
		//Fix diagonal move
		if(velX != 0 && velX == velY || velX+velY == 0) {
			velX = (float) (velX/ Math.sqrt(2));
			velY = (float) (velY/ Math.sqrt(2));
		}
				
		movePlayer(velX, velY);
		
		if(Game.mouse.isCliked(1)) { 
			if(animation.getSprite() == walking_right || animation.getSprite() == attack_right)
				animation.runSprite(attack_right, 4, false);
			else if(animation.getSprite() == walking_left || animation.getSprite() == attack_left)
				animation.runSprite(attack_left, 4, false);
			
		}

		animation.runAnimation();
	}



	private void movePlayer(float x, float y) {
		move(x, y);
		if(animation.getSprite() == attack_left || animation.getSprite() == attack_right)
			return;
		
		if(x>0) 
			animation.runSprite(walking_right, 3, true);
		else if(x<0)
			animation.runSprite(walking_left, 3, true);
		
	}
	
	@Override
	public void draw(Draw draw) {

		//System.out.println(x()+" "+y()+" " +width());
		animation.drawAnimation(draw, x()-11, y()-13, 0,0, false, 0);	
		//draw.drawRect(x(), y(), width(), height());
		//getBodycollide().render(draw);
	}
	
	@Override
	public void collideWithEntity(Entity e, int position) {
		
	}

	@Override
	public void collideWithTile(Object t, int position) {

	}

	
}
