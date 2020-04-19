package opengl.input;

import com.jogamp.newt.event.MouseEvent;
import com.jogamp.newt.event.MouseListener;

import opengl.Renderer;

public class MouseInput implements MouseListener {

	private static int x = 0;
	private static int y = 0;
	
	public static int getX() {
		return x;
	}
	
	public static int getY() {
		return y;
	}
	
	public static float getWorldX() {
		return (Renderer.unitsWide / Renderer.getScreenWidth() * x - Renderer.unitsWide / 2) + Renderer.cameraX;
	}
	
	public static float getWorldY() {
		return -(Renderer.unitsTall / Renderer.getScreenHeight() * y - Renderer.unitsTall  / 2) + Renderer.cameraY;
	}

	public void mouseClicked(MouseEvent e) {
	
	}


	public void mouseDragged(MouseEvent e) {
	
	}

	
	public void mouseEntered(MouseEvent e) {

	}

	
	public void mouseExited(MouseEvent e) {
	
	}

	
	public void mouseMoved(MouseEvent e) {
		x = e.getX();
		y = e.getY();
	}


	public void mousePressed(MouseEvent e) {

	}


	public void mouseReleased(MouseEvent e) {

	}


	public void mouseWheelMoved(MouseEvent e) {
	
	}

}
