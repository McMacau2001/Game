package game.Entities;

import java.awt.Rectangle;

import game.Main;
import game.Game.Game;
import game.Map.Map;
import game.Map.Shape.Shape;
import game.Map.Shape.Groups.ShapeMap;
import game.Maths.Maths;

public class Camera {
	
	//private Game game;
	
	private float xOffset;
	private float yOffset;
	
	public Camera(Game game) {
		this.xOffset = 0;
		this.yOffset = 0;
	}
	
	public void move(float x, float y) {
		this.xOffset += x;
		this.yOffset += y;
	}
	
	public void centerOnLocation(Entity entity) {

		float x = (entity.x()+entity.width()/2);
		float y = (entity.y()+entity.height()/2);

		xOffset = x - (Main.WIDTH / 2);
		yOffset = y - (Main.HEIGHT / 2);

		
		//TODO isto vai sair
		//FIX X
		/*
		if(xOffset<-shape.getWidth()/2) 
			xOffset = -shape.getWidth()/2;
		else if(xOffset>shape.getWidth()/2 - Main.WIDTH + shape.getTilewidth()) 
			xOffset = shape.getWidth()/2 - Main.WIDTH + shape.getTilewidth();
		
		
		//FIX Y
		if(yOffset<-shape.getHeight()/2) 
			yOffset = -shape.getHeight()/2;
		else if(yOffset>shape.getHeight()/2 - Main.HEIGHT + shape.getTileheight())
			yOffset = shape.getHeight()/2 - Main.HEIGHT + shape.getTileheight();
		*/
	
	}
	
	public float getxOffset() {
		return xOffset;
	}
	
	public float getyOffset() {
		return yOffset;
	}
	
	public Rectangle getRectangle() {
		return new Rectangle((int)xOffset, (int)yOffset, Main.WIDTH, Main.HEIGHT);
	}
	
	public int toXLocation(float value) {
		return (int) (value - xOffset);
	}
	
	public int toYLocation(float value) {
		return (int) (value - yOffset);
	}

}
