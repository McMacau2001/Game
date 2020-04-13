package game.Images;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class Font {
	
	public char[] chars  = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'
			,'?','!','1','2','3','4','5','6','7','8','9','0',' '};
	
	private  HashMap<Character, BufferedImage> font = new HashMap<>();

	public Font() {
		SpriteSheet sheet = new SpriteSheet(ImageManager.loadImage("font.png"), 10, 4);

		for(int i = 0; i<chars.length; i++) 
			font.put(chars[i], sheet.grabImage(i+1));
		
	}
	
	public BufferedImage getChar(char c) {
		BufferedImage img = font.get(c);

		if(img == null)
			img = font.get(' ');
		
		return img;
	}
	
	public BufferedImage buildString(String s, int size) {
		int width = size * s.length();
		
		BufferedImage image = new BufferedImage(width, size, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = image.createGraphics();
		
		int x = 0;
		for(Character c : s.toLowerCase().toCharArray()) {
			g2d.drawImage(getChar(c), x * size, 0, size, size, null);
			x++;
			
		}
		
		g2d.dispose();
		return image;
	}

}
