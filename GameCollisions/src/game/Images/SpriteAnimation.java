package game.Images;

import java.awt.image.BufferedImage;

import game.Main;
import game.Game.Render.Draw;
import game.Maths.Maths;

public class SpriteAnimation {

	private int speed;
	private int frames;
	
	private int index = 0;
	private int count = 0;

	private BufferedImage[] images;
	private BufferedImage currentImage;
	
	private boolean isInfinit;
	
	private Sprite sprite;
	
	private int oldspeed;
	private Sprite oldsprite;
	
	private boolean active;

	public void nextFrame() {
		currentImage = images[count];
		
		if(!active && count == 0) 
			return;
		
		count++;

		if (count >= frames - 1)  {
			if(!isInfinit)
				runSprite(oldsprite, oldspeed, true);
			count = 0;
			

		}	
		
	}

	public void runAnimation() {
		index++;
		
		if (index > speed) {
			index = 0;
			nextFrame();
		}

	}

	public void drawAnimation(Draw draw, double x, double y, float xOffSet, float yOffSet, boolean opacity, int priority) {
		int vx = Maths.roundNumberMultiple((float) (x + xOffSet), 1);
		int vy = Maths.roundNumberMultiple((float) (y + yOffSet), 1);
		
		draw.drawImage(currentImage, vx, vy, opacity, priority);
	}
	
	public void runSprite(Sprite sprite, int speed, boolean isInfinit) {
		runSprite(sprite, speed, isInfinit, 0);
	}
	
	public void runSprite(Sprite sprite, int speed, boolean isInfinit, int startimage) {
		if(this.sprite == sprite) return;
		
		this.oldspeed = this.speed;
		this.oldsprite = this.sprite;
		
		this.speed = speed;
		this.sprite = sprite;
		this.isInfinit = isInfinit;
		
		images = sprite.getImages();
		frames = images.length;
		
		if(startimage >= images.length || startimage <0) 
			count = 0;
		else 
			count = startimage;
		
		currentImage = images[count];
		
		active = true;
	}
	
	public boolean isActive() {
		return active;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}

	public BufferedImage getCurrentImage() {
		return currentImage;
	}
	
	public Sprite getSprite() {
		return sprite;
	}
	
}
