package game.Map;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import game.Entities.Entity;
import game.Entities.Player.Player;
import game.Game.Game;
import game.Game.Handlers.Collisions;
import game.Game.Render.Draw;
import game.Map.Shape.Shape;
import game.Map.Shape.Groups.ShapeMap;
import game.Objects.Object;

public abstract class Map {

	protected ShapeMap mapshape;
	protected Biomes biome;
	protected Minimap minimap;
	
	protected Point2D spawn;
	protected Game game;
	
	private List<Entity> entities = new ArrayList<Entity>();
	private List<Object> tiles = new ArrayList<Object>();
	
	protected abstract void render();
	protected abstract void tick();
	protected abstract void init();
	
	protected abstract void load(Player player);
	
	public Map(Game game, Point2D spawn, Biomes biome, ShapeMap shape) {
		this.game = game;
		this.spawn = spawn;
		this.biome = biome;
		this.mapshape = shape;
		
		this.minimap = new Minimap(shape, 70, 70,0.05f);
		
		shape.getObjects().forEach(t -> {
			t.init(game.getCamera(), mapshape.getShape());
			tiles.add(t);
		});

		init();
	}
	
	public void roomload(Player player) {
		player.teleport(spawn);
		addEntity(player);
		load(player);
		
	}
	
	public void roomtick(Collisions collisions) {
		entities.forEach(x->x.tick(collisions, this));
		collisions.tick(this);
		
		mapshape.tick(game.getCamera());
		tick();
	}
	
	public void roompredraw(Draw render, Player player) {
		entities.forEach(x->x.render(render));
		tiles.forEach(x->x.render(render));	
		
		mapshape.render(render, player);
		minimap.render(render, player);
		
		render();
	}
	
	public List<Entity> getEntities() {
		return entities;
	}
	
	public List<Object> getTiles() {
		return tiles;
	}
	
	protected void addEntity(Entity e) {
		if(!entities.contains(e))
			entities.add(e);
	}
	
	protected void addTile(Object t) {
		if(!tiles.contains(t))
			tiles.add(t);
	}
	
	public Biomes getBiome() {
		return biome;
	}
	
	public ShapeMap getMapshape() {
		return mapshape;
	}
	
}
