package opengl.engine;


import opengl.Renderer;
import opengl.world.Player;
import opengl.world.World;
import opengl.world.tiles.Grass;

public class GameLoop {
	
	public static boolean running = false;
	
	private static int updates = 0;
	private static final int max_updates = 5;
	
	private static long lastUpdateTime = 0;
	
	private static int targetFPS = 1200;
	private static int targetTime = 1000000000 / targetFPS;
	
	public static void start() {
		
		for(int x = 0; x< 1; x++) {
			for(int y = 0; y < 1; y++) {
				Player g =new Player();
				g.x = g.width * x+30;
				g.y = g.height * y+30;
				World.addGameObject(g);//World.addTile(g);
			}
		}
		
		
		
		Thread thread = new Thread() {
			public void run() {
				
				running = true;
				lastUpdateTime = System.nanoTime();
				
				int fps = 0;
				long lastFpsCheck = System.nanoTime();

				while(running) {
					long currentTime = System.nanoTime();
					
					updates = 0;
					
					while(currentTime - lastUpdateTime > targetTime) {
						World.update();
						lastUpdateTime += targetTime;
						updates++;
						
						if(updates > max_updates)
							break;
						
					}
					
					Renderer.render();
					
					
					fps++;
					if(System.nanoTime() >= lastFpsCheck + 1000000000) {
						Renderer.getWindow().setTitle("FDS: "+fps);
						fps = 0;
						lastFpsCheck = System.nanoTime();
					}
					
					
					long timeTaken = System.nanoTime() - currentTime;
					if(targetTime > timeTaken) {
						try {
							Thread.sleep((targetTime - timeTaken) / 1000000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					
					
				}
				
				Renderer.getWindow().destroy();
				
				
			}
		};
		thread.setName("GameLoop");
		thread.start();
	}
	
	public static float updateDelta() {
		return 1.0f / 1000000000 * targetTime;
	}

}
