package game.Game.Handlers;

import game.Entities.Entity;
import game.Map.Map;
import game.Maths.Rectangle;
import game.Objects.Object;

public class Collisions {

	public void tick(Map room) {
		for(Entity e : room.getEntities()) 
			e.clearCollideFaces();
		
		for(Entity e : room.getEntities()) {
	
			room.getEntities().stream().filter(x->x.isCollide()).forEach(ee -> {
				if(e != ee) {
					if(e.getBodyfoot().collide(ee.getBodyfoot()) && !e.getVelocity().isZero()) {
						
						int faceA = e.collide(ee.getBodyfoot(), e.getVelocity());
						int faceB = ee.collide(e.getBodyfoot(), ee.getVelocity());
						

						Rectangle recA = e.getBodyfoot().clone();
						Rectangle recB = ee.getBodyfoot().clone();
						
						e.fixDividedPositionCollide(recB, faceA, 2);
						ee.fixDividedPositionCollide(recA, faceB, 2);
						
						
						e.collideWithEntity(ee, faceA);
						ee.collideWithEntity(e, faceB);
						
					}
				}
				
			});
			
			room.getTiles().stream().filter(x->x.isCollide()).forEach(t -> {
				
				if(t.getRectangle().collide(e.getBodyfoot())) {
					
					int face = e.collide(t.getRectangle(), e.getVelocity());
					if(face !=0)
						e.fixPositionCollide(t.getRectangle(), face);
	
					e.collideWithTile(t, face);
				}
				
			});
			
			
		}
		
	}
	
	
	

	
}
