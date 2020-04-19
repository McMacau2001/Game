package opengl;

import com.jogamp.nativewindow.WindowClosingProtocol.WindowClosingMode;
import com.jogamp.newt.event.WindowAdapter;
import com.jogamp.newt.event.WindowEvent;
import com.jogamp.newt.event.WindowListener;
import com.jogamp.newt.event.WindowUpdateEvent;
import com.jogamp.newt.opengl.GLWindow;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.util.FPSAnimator;

import opengl.input.KeyInput;
import opengl.input.MouseInput;
public class Renderer {
	
	private static GLWindow window = null;
	private static GLProfile profile = null;
	
	public static int screenWidth = 640 * 2;
	public static int screenHeight = 360 * 2;
	
	public static float unitsWide = 4;
	public static float unitsTall = 0;
	
	public static float cameraX = 0;
	public static float cameraY = 0;
	
	public static void init() {
		GLProfile.initSingleton();
		profile = GLProfile.get(GLProfile.GL2);
		GLCapabilities caps = new GLCapabilities(profile);
		
		caps.setHardwareAccelerated(true);
		caps.setDoubleBuffered(true);
		
		window = GLWindow.create(caps);
		window.setSize(screenWidth, screenHeight);
		window.setRealized(false);
		window.addGLEventListener(new EventListener());
		window.addMouseListener(new MouseInput());
		window.addKeyListener(new KeyInput(window));
		window.setDefaultCloseOperation(WindowClosingMode.DISPOSE_ON_CLOSE);
		
		window.addWindowListener(new WindowAdapter() {
			public void windowDestroyNotify(WindowEvent arg0) {
				System.exit(0);
			}
		});
		
		//FPSAnimator animator = new FPSAnimator(window, 1000);
		//animator.start();

		window.setFullscreen(false);
		window.setVisible(true);
		
		window.requestFocus();
	
		
	}
	
	public static int getScreenWidth() {
		return window.getWidth();
	}
	
	public static int getScreenHeight() {
		return window.getHeight();
	}
	
	public static float getUnitsWide() {
		return unitsWide;
	}
	
	public static GLWindow getWindow() {
		return window;
	}
	
	public static GLProfile getProfile() {
		return profile;
	}
	
	public static void render() {
		if(window == null)
			return;

		window.display();
	}
	
}
