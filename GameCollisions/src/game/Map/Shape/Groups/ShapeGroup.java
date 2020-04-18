package game.Map.Shape.Groups;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import game.Images.SpriteSheet;
import game.Map.Layers.Group;
import game.Map.Layers.Layer;
import game.Map.Layers.Objectgroup;
import game.Map.Layers.Tilelayer;
import game.Map.Shape.Shape;
import game.Objects.Object;

public abstract class ShapeGroup {

	protected LinkedHashMap<String, BufferedImage[][]> tiles = new LinkedHashMap<String, BufferedImage[][]>();
	protected List<Object> objects = new ArrayList<Object>();
	
	protected Shape shape;
	protected SpriteSheet sheet;
	protected Group group;
	
	protected int entitieslayer = 0;
	
	public ShapeGroup(Shape shape, Group group) {
		this.shape = shape;
		this.sheet = shape.getSheet();
		this.group = group;
		
		bindGroup(this);
	}
	
	public void bindGroup(ShapeGroup group) {
		int index = 0;
		for(Layer l : group.group.getLayers()) {
			if(l instanceof Objectgroup) {
				Objectgroup og = (Objectgroup)l;
				
				if(l.getName().equalsIgnoreCase("entities"))
					entitieslayer = index;
				
				for(Object o : og.getObjects())
					objects.add(o);	

			}
			else if(l instanceof Tilelayer) {
				Tilelayer tl = (Tilelayer)l;
				
				BufferedImage[][] images = tiles.get(l.getName());
				if(images == null)
					images = new BufferedImage[shape.width()][shape.height()];

				for(int i = 1 ; i <= tl.getData().length; i++) {
					int val = tl.getData()[i-1];
					
					if(val != 0) {
						int row = ((i) % shape.width());
						int col = (i) / shape.width();
						
						images[row][col] = sheet.grabImage(val);
			
					}

				}
	
				tiles.put(l.getName(), images);
				
			}
			index++;
		}
	
	}
	
	public List<Object> getObjects() {
		return objects;
	}
	
	public HashMap<String, BufferedImage[][]> getTiles() {
		return tiles;
	}
	
}
