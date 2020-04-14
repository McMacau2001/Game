package game.Objects;

import java.awt.image.BufferedImage;

import game.Game.Render.Draw;
import game.Images.ImageManager;
import game.Maths.Maths;
import game.Maths.Rectangle;

public class Cacto extends Object {

	private BufferedImage image;

	public Cacto() {
		this.image = ImageManager.loadImage("cacto.png");
		
	}
	
	@Override
	public void init() {
		this.width = 100;
		this.height = 100;
	}

	@Override
	public void render(Draw draw) {
		draw.drawImage(this.image, x() - image.getWidth()/2 + 25, y() - image.getHeight() + 40, true, priority());
	}

}
