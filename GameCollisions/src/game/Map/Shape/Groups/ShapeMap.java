package game.Map.Shape.Groups;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import game.Main;
import game.Entities.Camera;
import game.Entities.Player.Player;
import game.Game.Render.Draw;
import game.Map.MapTile;
import game.Map.Layers.Group;
import game.Map.Shape.Shape;
import game.Maths.Maths;

public class ShapeMap extends ShapeGroup {

	private List<MapTile> tiletorender = new ArrayList<MapTile>();

	public ShapeMap(Shape shape, Group group) {
		super(shape, group);	
	}
	
	public void tick(Camera camera) {
		
		int x = (int) camera.getxOffset();
		int y = (int) camera.getyOffset();
		
		int startx = Math.max(0, shape.toRelativeCoordX(x)-1);
		int starty = Math.max(0, shape.toRelativeCoordY(y)-1);
		
		int maxx = Math.min(shape.width()-1, shape.toRelativeCoordX((int)(x + Main.WIDTH * Main.SCALE)));
		int maxy = Math.min(shape.height()-1, shape.toRelativeCoordY((int)(y + Main.HEIGHT * Main.SCALE)));

		tiletorender.clear();
		
		int index = 0;
		for(Entry<String, BufferedImage[][]> a :  tiles.entrySet()) {
			BufferedImage[][] images = a.getValue();

			for(int i = startx;  i <= maxx; i++) {
				for(int j = starty;  j <= maxy; j++) {
					BufferedImage image = images[i][j];
					if(image != null) {			
						tiletorender.add(new MapTile(image, i-1, j, -(index-entitieslayer)));
					}
				}
			}
			index++;
			
		}
		//System.exit(0);
	
	}
	
	public void render(Draw draw, Player player) {
		tiletorender.forEach(tr -> {
			
			//System.out.println((int)(shape.toMapCoordX(tr.x()) * shape.getTilewidth()/4*4)%4);
			draw.drawImage(tr.image(), 
					shape.toMapCoordX(tr.x()) * shape.getTilewidth(), 
					shape.toMapCoordY(tr.y()) * shape.getTileheight(),
					shape.getTilewidth(), shape.getTileheight(), false, tr.getPriority());
		});
		//System.exit(0);
		
	}
	
	public ShapeMap clone() {
		return new ShapeMap(shape, group);
	}
	
	public Shape getShape() {
		return this.shape;
	}
	
	public BufferedImage[][] getTiles(int index) {
		return (BufferedImage[][]) tiles.values().toArray()[index];
	}
	
}
