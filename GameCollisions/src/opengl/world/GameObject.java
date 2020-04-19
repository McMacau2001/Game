package opengl.world;


import opengl.Animation;
import opengl.Graphics;

public class GameObject {

	public float x = 0;
	public float y = 0;
	
	public float width = 1f;
	public float height = 1f;
	
	public float rotation = 0;
	
	public Animation[] animations;
	public int currentAnimation = 0;
	
	public void update() {
		
	}
	
	public void render() {
		animations[currentAnimation].play();
		
		Graphics.setRotation(rotation);
		Graphics.setColor(1, 1, 1, 1);
		Graphics.drawImage(animations[currentAnimation].getImage(), x, y, width, height);
		Graphics.setRotation(0);

		
	}
	
}
