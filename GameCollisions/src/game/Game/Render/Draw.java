package game.Game.Render;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import game.Entities.Camera;
import game.Game.Game;
import game.Images.Font;
import game.Maths.Rectangle;

public class Draw {
	
	private Camera camera;
	private Font font;
	private Game game;
	
	private List<Drawer> images = new ArrayList<>();
	private List<Rectangle> rectangles = new ArrayList<>();
	private List<Rectangle> fillrectangles = new ArrayList<>();
	private List<Line2D> line = new ArrayList<>();
	
	public Draw(Game game, Font font) {
		this.game = game;
		this.font = font;
		this.camera = game.getCamera();
	}
	
	public void drawRect(float x, float y, float width, float height) {
		rectangles.add(new Rectangle(this.camera.toXLocation(x), this.camera.toYLocation(y), (int)width, (int)height));
	}
	
	public void fillRect(float x, float y, float width, float height) {
		fillrectangles.add(new Rectangle(this.camera.toXLocation(x), this.camera.toYLocation(y), (int)width, (int)height));
	}
	
	public void drawLine(float x1, float y1, float x2, float y2) {
		line.add(new Line2D.Float(this.camera.toXLocation(x1), this.camera.toYLocation(y1), this.camera.toXLocation(x2), this.camera.toYLocation(y2)));
	}
	
	public void drawString(String string, int size, float x, float y, int priority, boolean move) {
		BufferedImage img = font.buildString(string, size);
		
		if(move)
			images.add(new Drawer(img, this.camera.toXLocation(x), this.camera.toYLocation(y), img.getWidth(), img.getHeight(), false, priority));
		else
			images.add(new Drawer(img, (int)x, (int)y, img.getWidth(), img.getHeight(), false, priority));
	}
	
	//IMAGES
	
	public void drawImage(Drawer drawer) {
		if(!game.isDebugMode() && drawer.isInsideScreen(camera)) 
			images.add(drawer);
	}
	
	public void drawImage(BufferedImage image, float x, float y, boolean opacity, int priority) {
		drawImage(new Drawer(image, this.camera.toXLocation(x), this.camera.toYLocation(y), (int)image.getWidth(), (int)image.getHeight(), opacity, priority));
	}
	
	public void drawImage(BufferedImage image, float x, float y, float width, float height, boolean opacity, int priority) {;
		drawImage(new Drawer(image, this.camera.toXLocation(x), this.camera.toYLocation(y), (int)width, (int)height, opacity, priority));
	}
	
	public void drawImage(BufferedImage image, float x, float y, float width, float height, boolean opacity, boolean fixed, int priority) {;
		if(!fixed)
			drawImage(new Drawer(image, this.camera.toXLocation(x), this.camera.toYLocation(y), (int)width, (int)height, opacity, priority));
		else
			drawImage(new Drawer(image, (int)x, (int)y, (int)width, (int)height, opacity, priority));
	}
	
	
	//

	public List<Drawer> getImages() {
		return images;
	}
	
	public List<Rectangle> getFillrectangles() {
		return fillrectangles;
	}
	
	public List<Rectangle> getRectangles() {
		return rectangles;
	}
	
	public List<Line2D> getLine() {
		return line;
	}
	
	public Camera getCamera() {
		return camera;
	}
	
}
