package game.Map.Shape.Groups;

import game.Map.Exits;
import game.Map.Layers.Group;
import game.Map.Shape.Shape;

public class ShapeExit extends ShapeGroup {
	
	private Exits type;
	
	public ShapeExit(Shape shape, Group group) {
		super(shape, group);
		this.type = Exits.byName(group.getName());
	}
	
	public Exits getType() {
		return type;
	}

}
