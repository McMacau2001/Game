package game.Game.KeyBind;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;

import game.Game.Game;

public class Pause extends AbstractAction {

	private Game game;

	public Pause (Game game) {
		this.game = game;
	}
	  
	@Override
	public void actionPerformed(ActionEvent e) {
		this.game.setPaused(!this.game.isPaused());
	}

}
