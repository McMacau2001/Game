package game.Images;

import java.awt.image.BufferedImage;

public class Sprite {
	
	private BufferedImage[] images;
	private SpriteSheet sprite;
	
	private boolean isFlipped;
	
	public Sprite(SpriteSheet sprite, int imageStart, int imageEnd, boolean isFlipped) {
		this.sprite = sprite;
		this.images = new BufferedImage[imageEnd-imageStart+1];
		this.isFlipped = isFlipped;
		
		fillSprites(imageStart, imageEnd);
	}
	
	private void fillSprites(int imageStart, int imageEnd) {
		
		for (int i = 0 , k = imageStart; k <= imageEnd; k++, i++) {
			if(isFlipped)images[i] =  ImageManager.flip(sprite.grabImage(k));
			else images[i] =  sprite.grabImage(k);
		}	
	}
	
	public BufferedImage[] getImages() {
		return images;
	}
	
	
}
