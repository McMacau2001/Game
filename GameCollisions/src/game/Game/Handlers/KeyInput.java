package game.Game.Handlers;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class KeyInput extends KeyAdapter {

	private ArrayList<Integer> keys = new ArrayList<Integer>();
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(!isPressedKey(key))
			keys.add(key);
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if(isPressedKey(key))
			keys.remove((Integer)key);
		
	}
	
	public boolean isPressedKey(int key) {
		return keys.contains(key);
	}
}
