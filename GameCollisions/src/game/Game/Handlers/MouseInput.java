package game.Game.Handlers;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.event.MouseInputAdapter;

import game.Main;

public class MouseInput extends MouseInputAdapter {
	
	private int mouseX;
	private int mouseY;
	
	ArrayList<Integer> keys = new ArrayList<>();

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
