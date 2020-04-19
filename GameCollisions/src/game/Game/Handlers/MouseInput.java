package game.Game.Handlers;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;

import javax.swing.event.MouseInputAdapter;

import game.Main;

public class MouseInput extends MouseInputAdapter {
	
	private int mouseX;
	private int mouseY;
	
	ArrayList<Integer> keys = new ArrayList<Integer>();
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		int position = (int) e.getPreciseWheelRotation();
		float oldscale = Main.SCALE;
	
		Main.SCALE += position;
		
		if(Main.SCALE <2)
			Main.SCALE = 2;
		if(Main.SCALE >8)
			Main.SCALE = 8;
		
		Main.WIDTH = (int) (Main.WIDTH*Main.SCALE/oldscale);
		Main.HEIGHT = Main.WIDTH / 16 * 9;
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		updateCoords(e);
	
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		updateCoords(e);
		int key = e.getButton();
		if(!isCliked(key))
			keys.add((Integer)key);
	}

	
	private void updateCoords(MouseEvent e) {
		mouseX = e.getX()+12;
		mouseY = e.getY()+12;
	}
	
	public void clearCache( ) {
		keys.clear();
	}
	
	public boolean isCliked(int key) {
		return keys.contains(key);
	}
	
	public int mouseX() {
		return (int) (mouseX / Main.SCALE);
	}
	
	public int mouseY() {
		return (int) (mouseY / Main.SCALE);
	}
	

	
}
