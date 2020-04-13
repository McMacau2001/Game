package game.Objects;

import java.awt.image.BufferedImage;

import game.Game.Render.Draw;
import game.Images.ImageManager;
import game.Maths.Rectangle;

public class Rock extends Object {

	private BufferedImage image;
	
	public Rock() {
		this.image = ImageManager.loadImage("rock.png");
	}
	
	@Override
	public void init() {
		this.width = 0;
		this.height = 0;
		
	}

	public void render(Draw draw) {
		draw.drawImage(this.image, x(), y(), true, 1);
	}

}
