package opengl;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.texture.Texture;

import opengl.resource.ImageResource;

public class Graphics {
	
	// Color values
	private static float red = 0;
	private static float green = 0;
	private static float blue = 0;
	private static float alpha = 1;
	
	// Rotation in degrees
	private static float rotation = 0;
	
	public static void drawImage(ImageResource image, float x, float y, float width, float height) {
		GL2 gl = EventListener.gl;
		
		Texture tex = image.getTexture();
		
		/*
		if(x - width / 2 - Renderer.cameraX > Renderer.unitsWide / 2 ||
				x + width / 2 - Renderer.cameraX < -Renderer.unitsWide / 2 ||
				y - height / 2 - Renderer.cameraY > Renderer.unitsTall / 2 ||
				y + height / 2 - Renderer.cameraY < -Renderer.unitsTall / 2) {
			return;
		}*/
		
		if(tex != null)
			gl.glBindTexture(GL2.GL_TEXTURE_2D, tex.getTextureObject());
		
		gl.glTexParameterf(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MAG_FILTER,GL2.GL_NEAREST);
		
		gl.glTranslatef(x, y, 0);
		gl.glRotatef(-rotation, 0, 0, 1);
		
		gl.glColor4f(red, green, blue, alpha);
		gl.glBegin(GL2.GL_QUADS);
			
			//Textura coords use to flip 
			gl.glTexCoord2f(0, 1);
			gl.glVertex2f(-width/2, -height/2);
			
			gl.glTexCoord2f(1, 1);
			gl.glVertex2f(width/2, -height/2);
			
			gl.glTexCoord2f(1, 0);
			gl.glVertex2f(width/2, height/2);
			
			gl.glTexCoord2f(0, 0);
			gl.glVertex2f(-width/2, height/2);
			
		gl.glEnd();
		gl.glFlush();
		
		gl.glBindTexture(GL2.GL_TEXTURE_2D, 0);
		
		gl.glRotatef(rotation, 0, 0, 1);
		gl.glTranslatef(-x, -y, 0);
		
	}
	
	public static void fillRect(float x, float y, float width, float height) {
		GL2 gl = EventListener.gl;
		
		gl.glTranslatef(x, y, 0);
		gl.glRotatef(-rotation, 0, 0, 1);
		
		gl.glColor4f(red, green, blue, alpha);
		gl.glBegin(GL2.GL_QUADS);
			gl.glVertex2f(-width/2, -height/2);
			gl.glVertex2f(width/2, -height/2);
			gl.glVertex2f(width/2, height/2);
			gl.glVertex2f(-width/2, height/2);
		gl.glEnd();
		gl.glFlush();
		
		gl.glRotatef(rotation, 0, 0, 1);
		gl.glTranslatef(-x, -y, 0);
		
	}
	
	public static void setColor(float r, float g, float b, float a) {
		red = Math.max(0, Math.min(1, r));
		green = Math.max(0, Math.min(1, g));
		blue = Math.max(0, Math.min(1, b));
		alpha = Math.max(0, Math.min(1, a));

	}
	
	public static void setRotation(float r) {
		rotation = r;
	}

}
