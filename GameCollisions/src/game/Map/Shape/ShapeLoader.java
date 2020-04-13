package game.Map.Shape;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileReader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import game.Images.ImageManager;
import game.Map.Layers.Layer;
import game.Map.Layers.Objectgroup;
import game.Objects.Object;
import game.Objects.Tree;
import game.Others.InterfaceLayerAdapter;
import game.Others.InterfaceObjectGroupAdapter;

public class ShapeLoader {

	public static Shape loadTiledMap(String path, BufferedImage image) {
		path = (System.getProperty("user.dir")+"/resources/maps/"+path).replace("/", "\\");
		
		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(Layer.class, new InterfaceLayerAdapter<Layer>());
		builder.registerTypeAdapter(Object.class, new InterfaceObjectGroupAdapter<Object>());
		
		builder.setPrettyPrinting();
		
		Gson gson = builder.create();
		Shape shape = new Shape();
		
		try {
			shape = gson.fromJson(new FileReader(path), Shape.class);
			shape.init(image);
 		} catch (JsonSyntaxException | JsonIOException | FileNotFoundException e) {e.printStackTrace();}
		
		return shape;
	}
	
}
