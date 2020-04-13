package game.Entities;

import java.awt.Rectangle;

import game.Main;
import game.Game.Game;
import game.Map.Map;
import game.Map.Shape.Shape;
import game.Map.Shape.Groups.ShapeMap;

public class Camera {
	
	//private Game game;
	
	private int xOffset;
	private int yOffset;
	
	public Camera(Game game, int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
	
	public void move(float x, float y) {
		this.xOffset += x;
		this.yOffset += y;
	}
	
	public void centerOnLocation(Entity entity, Map map) {
		Shape shape = map.getMapshape().getShape();

		int x = (int)(entity.x()+entity.width()/2 * 0.2);
		int y = (int) (entity.y()+entity.height()/2* 0.2);

		xOffset = (int)x - (Main.WIDTH / 2);
		yOffset = (int)y - (Main.HEIGHT / 2);
		
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
