package game.Map.Maps;

import java.awt.Point;
import java.util.Random;

import game.Entities.Mobs.Bat;
import game.Entities.Mobs.Scorpion;
import game.Entities.Mobs.Snake;
import game.Entities.Player.Player;
import game.Game.Game;
import game.Map.Map;
import game.Map.Biomes;
import game.Map.Shape.Shape;

public class MapA extends Map {

	public MapA(Game game, Shape shape, Biomes biome) {
		super(game, new Point(0,0), biome, shape.createRandom(new Random()));
	}

	@Override
	protected void init() {
		

	}
	
	@Override
	protected void render() {
	
	}

	@Override
	protected void tick() {
		if(getEntities().size()<12) {
			addEntity(new Bat(-300,-200));
			addEntity(new Snake(-200,-100));
			addEntity(new Scorpion(-300,-200));
		}
	}

	@Override
	protected void load(Player player) {

	}

}
