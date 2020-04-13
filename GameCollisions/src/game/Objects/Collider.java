package game.Objects;
import game.Game.Render.Draw;

public class Collider extends Object {

	
	@Override
	public void init() {
		this.collide = true;
		
	}
	
	@Override
	public void render(Draw draw) {	
		//draw.drawRect(x(), y(), width(), height());
	}

	
}
