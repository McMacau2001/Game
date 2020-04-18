package game.Map.Shape;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import game.Images.SpriteSheet;
import game.Map.Layers.Group;
import game.Map.Layers.Layer;
import game.Map.Shape.Groups.ShapeExit;
import game.Map.Shape.Groups.ShapeMap;
import game.Map.Shape.Groups.ShapeRandom;
import game.Maths.Maths;

public class Shape {
	
	//Mapa tem que ter dimencoes pares tipo 60 / 60

	private List<Layer> layers;
	
	private int tilewidth;
	private int tileheight;
	
	private int width;
	private int height;
	
	private ShapeMap map;
	private List<ShapeExit> exits = new ArrayList<ShapeExit>();
	private List<ShapeRandom> randoms = new ArrayList<ShapeRandom>();
	
	//Exits

	private SpriteSheet sheet;

	public void init(BufferedImage tiled) {
		this.sheet = new SpriteSheet(tiled, tiled.getWidth() / tilewidth, tiled.getHeight() / tileheight);
		
		for(Layer layer : layers) {
			if(layer instanceof Group) {
				Group group = (Group)layer;
				
				//LOAD GROUPS
				if(group.getName().equalsIgnoreCase("exits")){
					for(Layer rexit : group.getLayers()) {
						if(rexit instanceof Group) {
							Group grouprexit = (Group) rexit;
							exits.add(new ShapeExit(this, grouprexit));
						}
					}
				}
				
				
				else if(group.getName().equalsIgnoreCase("randoms")) {
					for(Layer rgroup : group.getLayers()) {
						if(rgroup instanceof Group) {
							Group grouprgroup = (Group) rgroup;
							randoms.add(new ShapeRandom(this, grouprgroup));
						}
					}
				}
				
				else if(group.getName().equalsIgnoreCase("map")) 
					map = new ShapeMap(this, group);
					
			}
		}

	}
	
	public ShapeMap createRandom(Random rand) {
		ShapeMap map = this.map.clone();
		
		//Add exit
		map.bindGroup(exits.get(Maths.randomInt(rand, 3, 0)));
		map.bindGroup(randoms.get(Maths.randomInt(rand, randoms.size()-1, 0)));
		
		/*
		for(int x = 0; x<=15; x++) {
			map.getObjects().remove(Maths.randomInt(rand, map.getObjects().size()-1,0));
		}
		*/

		
		return map;
	}
	
	
	public int toMapCoordX(int value) {
		return (value - width / 2);
	}
	
	public int toMapCoordY(int value) {
		return (value - height / 2);
	}
	
	public int toRelativeCoordX(int value) {
		int t = value / tilewidth + width / 2;

		if(t<0) return 0;
		else if(t>width) return width;
		return t;
	}
	
	public int toRelativeCoordY(int value) {
		int t = value / tileheight + height / 2;
		
		if(t<0) return 0;
		else if(t>height) return height;
		return t;
	}
	
	public int width() {
		return width;
	}
	
	public int height() {
		return height;
	}
	
	public int getWidth() {
		return width * tilewidth;
	}
	
	public int getHeight() {
		return height * tileheight;
	}
	
	public int getTilewidth() {
		return tilewidth;
	}
	
	public int getTileheight() {
		return tileheight;
	}
	
	public SpriteSheet getSheet() {
		return sheet;
	}
	
	
}
