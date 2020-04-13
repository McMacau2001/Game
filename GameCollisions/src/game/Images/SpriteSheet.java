package game.Images;

import java.awt.image.BufferedImage;

public class SpriteSheet {

	private BufferedImage image;
	
	private int spriteWidth;
	private int spriteHeight;
	
	public SpriteSheet(BufferedImage image, int spriteWidth, int spriteHeight) {
		this.image = image;
		this.spriteWidth = spriteWidth;
		this.spriteHeight = spriteHeight;
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
	public BufferedImage grabImage(int col, int row) {
		int width = image.getWidth() / spriteWidth;
		int height = image.getHeight() / spriteHeight;

		BufferedImage img = ImageManager.grabImage(image, (col) * width,(row) * height, width, height);	
		return img;
	}
	
	public BufferedImage grabImage(int image) {
		int col = (image-1) % spriteWidth;
		int row = (image-1) / spriteWidth;
		
		return grabImage( col, row);
	}

    
}
