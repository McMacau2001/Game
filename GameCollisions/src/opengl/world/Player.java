package opengl.world;

import java.awt.image.BufferedImage;

import com.jogamp.newt.event.KeyEvent;
import game.Images.ImageManager;
import game.Images.Sprite;
import game.Images.SpriteSheet;
import opengl.Animation;
import opengl.Renderer;
import opengl.engine.GameLoop;
import opengl.input.KeyInput;
import opengl.input.MouseInput;
import opengl.resource.ImageResource;

public class Player extends GameObject {
	
	private float speed = 2f;
	
	public Player() {
		SpriteSheet sheet = new SpriteSheet(ImageManager.loadImage("player.png"), 10, 10);
		Sprite sprite = new Sprite(sheet, 11, 18, false);
		
		animations = new Animation[1];
		animations[0] = new Animation();
		animations[0].frames = new ImageResource[sprite.getImages().length];
		animations[0].fps = 10;
		
		
		int img = 0;
		for(BufferedImage image : sprite.getImages()) {
			animations[0].frames[img] = new ImageResource(image);
			img++;
	
		}
	}

	public void update() {
		float xInput = 0;
		float yInput = 0;
		
		if(KeyInput.getKey(KeyEvent.VK_W))
			yInput ++;
		if(KeyInput.getKey(KeyEvent.VK_S))
			yInput --;
		if(KeyInput.getKey(KeyEvent.VK_D))
			xInput ++;
		if(KeyInput.getKey(KeyEvent.VK_A))
			xInput --;
		
		x += xInput * speed * GameLoop.updateDelta();
		y += yInput * speed * GameLoop.updateDelta();
	
		
		//rotation = (float)Math.toDegrees(Math.atan2(MouseInput.getWorldX() - x, MouseInput.getWorldY()) - y);
		//Renderer.cameraX += 1.0f * GameLoop.updateDelta();
		rotation += 0.2f;
		
		Renderer.cameraX = x;
		Renderer.cameraY = y;

	}
	

	
}
