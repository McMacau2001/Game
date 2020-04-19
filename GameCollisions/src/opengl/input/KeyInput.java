package opengl.input;

import com.jogamp.newt.event.InputEvent;
import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.KeyListener;
import com.jogamp.newt.opengl.GLWindow;

public class KeyInput implements KeyListener {

	private GLWindow window;
	private static boolean[] keys = new boolean[256];
	
	public KeyInput(GLWindow window) {
		this.window = window;
	}
	
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_F11)
			this.window.setFullscreen(!this.window.isFullscreen());
		
		if(0 == (InputEvent.AUTOREPEAT_MASK & e.getModifiers()))
			keys[e.getKeyCode()] = true;
	
	}

	public void keyReleased(KeyEvent e) {
		if(0 == (InputEvent.AUTOREPEAT_MASK & e.getModifiers()))
			keys[e.getKeyCode()] = false;
	}
	
	public static boolean getKey(int keyCode) {
		return keys[keyCode];
	}
	

}
