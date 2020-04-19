package opengl.resource;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.awt.AWTTextureIO;

import opengl.Renderer;

public class ImageResource {

	private Texture texture = null;
	private BufferedImage image = null;
	
	public ImageResource (String path) {
		path = (System.getProperty("user.dir")+"/resources/images/"+path).replace("/", "\\");
		
		try {
			image = ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(image != null)
			image.flush();
		
	}
	
	public ImageResource (BufferedImage image) {
		this.image = image;	
	}
	
	public Texture getTexture() {
		if(image == null)
			return null;
		
		if(texture == null)
			texture =  AWTTextureIO.newTexture(Renderer.getProfile(), image, true);
		
		return texture;	
	}
	
}
