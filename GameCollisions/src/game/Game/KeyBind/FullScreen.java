package game.Game.KeyBind;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;

public class FullScreen extends AbstractAction {

	private static final long serialVersionUID = 1L;
	
	private JFrame frame;
	private GraphicsDevice fullscreenDevice;

	public FullScreen (JFrame frame) {
		this(frame, GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice());
	}

	public FullScreen (JFrame frame, GraphicsDevice fullscreenDevice) {
	    this.frame = frame;
	    this.fullscreenDevice = fullscreenDevice;
	}
	  
	@Override
	public void actionPerformed(ActionEvent e) {
		frame.dispose();

		if (frame.isUndecorated()) {
			fullscreenDevice.setFullScreenWindow(null);
			frame.setUndecorated(false);
		} else {
		    frame.setUndecorated(true);
		    fullscreenDevice.setFullScreenWindow(frame);
		}

		frame.setVisible(true);
		frame.repaint();
	}

}
