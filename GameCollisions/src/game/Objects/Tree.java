package game.Objects;

import java.awt.image.BufferedImage;

import game.Game.Render.Draw;
import game.Images.ImageManager;
import game.Maths.Maths;
import game.Maths.Rectangle;

public class Tree extends Object {

	private BufferedImage image;

	public Tree() {
		boolean a = Maths.randomBool();
		
		if(a)this.image = ImageManager.loadImage("tree.png");
		else this.image = ImageManager.loadImage("tree1.png");
		
		boolean b = Maths.randomBool();
		if(b)
			this.image = ImageManager.flip(this.image);
	}
	
	@Override
	public void init() {
		this.width = 0;
		this.height = 0;
		this.collide = false;
		
	}

	public void render(Draw draw) {
		draw.drawImage(this.image, x() - image.getWidth()/2 + 25, y() - image.getHeight() + 40, true, priority());
	}

}
