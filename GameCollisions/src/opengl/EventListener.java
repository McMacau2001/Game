package opengl;


import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;

import game.Game.Render.Render;
import opengl.world.World;

public class EventListener implements GLEventListener {

	public static GL2 gl = null;
	
	public void init(GLAutoDrawable drawable) {
		gl = drawable.getGL().getGL2();
		gl.glClearColor(0.37f, 0.59f, 0.46f, 1);

		gl.glEnable(GL2.GL_TEXTURE_2D);
		gl.glEnable(GL2.GL_BLEND);
		
		gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);
		gl.setSwapInterval(0);

	}
	
	public void dispose(GLAutoDrawable drawable) {

	}
	
	//Render display
	public void display(GLAutoDrawable drawable) {
		gl = drawable.getGL().getGL2();
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
		
		
		gl.glTranslatef(-Renderer.cameraX, -Renderer.cameraY, 0);
		World.render();
		gl.glTranslatef(Renderer.cameraX, Renderer.cameraY, 0);
		
		Render.render(Renderer.screenWidth, Renderer.screenHeight);
		
		//FPSAnimator animator = (FPSAnimator) drawable.getAnimator();
		//Renderer.getWindow().setTitle("FPS: "+animator.getFPS());
		
	}

	//Resize
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {		
		gl = drawable.getGL().getGL2();
		
		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glLoadIdentity();
		
		Renderer.unitsTall = Renderer.getScreenHeight() / (Renderer.getScreenWidth() / Renderer.getUnitsWide());
		
		gl.glOrtho(-Renderer.getUnitsWide()/2, Renderer.getUnitsWide()/2, -Renderer.unitsTall/2, Renderer.unitsTall/2, -1, 0);
		gl.glMatrixMode(GL2.GL_MODELVIEW);


		
	}

}
