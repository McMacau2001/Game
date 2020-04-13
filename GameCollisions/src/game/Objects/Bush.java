package game.Objects;

import java.awt.image.BufferedImage;

import game.Game.Render.Draw;
import game.Images.ImageManager;
import game.Maths.Rectangle;

public class Bush extends Object {

	private BufferedImage image;

	public Bush() {
		this.image = ImageManager.loadImage("bush.png");
	}

	@Override
	public void init() {
		this.width = 0;
		this.height = 0;
		
	}
	
	public void render(Draw draw) {
		draw.drawImage(this.image, x() - image.getWidth()/2 + 30, y() - image.getHeight() + 60, true, priority());
	}

}
