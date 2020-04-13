package game.Images;

import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.MappedByteBuffer;

import javax.imageio.ImageIO;

public class ImageManager {
	
	private static final GraphicsConfiguration GFX_CONFIG = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();

	public static BufferedImage toCompatibleImage(final BufferedImage image) {
	    if (image.getColorModel().equals(GFX_CONFIG.getColorModel())) {
	        return image;
	    }

	    final BufferedImage new_image = GFX_CONFIG.createCompatibleImage(image.getWidth(), image.getHeight(), image.getTransparency());
	    final Graphics2D g2d = (Graphics2D) new_image.getGraphics();

	    g2d.drawImage(image, 0, 0, null);
	    g2d.dispose();

	    return new_image;
	}
	
	public static BufferedImage loadImage(String path) {
		BufferedImage image = null;
		path = (System.getProperty("user.dir")+"/resources/images/"+path).replace("/", "\\");
		
		try {image = toCompatibleImage(ImageIO.read(new File(path)));
		} catch (IOException e) {e.printStackTrace();}
		
		return image;
	}
	
	public static BufferedImage grabImage(BufferedImage image, int x, int y, int width, int height) {
		BufferedImage img = image.getSubimage(x, y, width, height);	
		return img;
	}
	
    public static BufferedImage flip(BufferedImage img){
        BufferedImage image = new BufferedImage(img.getWidth(),img.getHeight(),BufferedImage.TYPE_INT_ARGB);
        for(int xx = img.getWidth()-1;xx>0;xx--){
            for(int yy = 0;yy < img.getHeight();yy++){
            	image.setRGB(img.getWidth()-xx, yy, img.getRGB(xx, yy));
            }
        }
        return image;
    }

}
