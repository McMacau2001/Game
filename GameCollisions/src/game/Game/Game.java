package game.Game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.util.HashMap;

import javax.swing.JFrame;

import game.Main;
import game.Entities.Camera;
import game.Entities.Mobs.Scorpion;
import game.Entities.Player.Player;
import game.Game.Handlers.Collisions;
import game.Game.Handlers.KeyInput;
import game.Game.Handlers.MouseInput;
import game.Game.Render.Draw;
import game.Game.Render.Render;
import game.Images.Font;
import game.Images.ImageManager;
import game.Map.Map;
import game.Map.Biomes;
import game.Map.Maps.MapA;
import game.Map.Shape.Shape;
import game.Map.Shape.ShapeLoader;

public class Game extends Canvas implements Runnable {

	public static KeyInput input = new KeyInput();
	public static MouseInput mouse = new MouseInput();
	public static Collisions collisions = new Collisions();
	
	public boolean debugMode = false;
	
	private static final long serialVersionUID = 1L;

	private JFrame frame;
	
	private boolean running = false;
	private Thread thread;
	
	private Player player;
	private Camera camera;
	
	protected Draw draw;
	protected Render render;
	protected Font font;
	
	private HashMap<String,Map> rooms = new HashMap<>();
	private Map activeRoom;
	
	private int fps_cap;
	
	public Game(int fps_cap) {
		this.fps_cap = fps_cap;
		
		Dimension d = new Dimension((int)(Main.WIDTH * Main.SCALE), (int)(Main.HEIGHT * Main.SCALE));
		setPreferredSize(d);
		setMaximumSize(d);
		setMinimumSize(d);
		setBackground(Color.BLACK);
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Cursor c = toolkit.createCustomCursor(ImageManager.loadImage("cursor.png") , new Point(this.getX(), this.getY()), "Cursor");
		setCursor (c);
		
		frame = new JFrame(Main.TITLE);
		frame.add(this);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
			
	}
	
	public void run() {
		init();
		long lastTime = System.nanoTime();
		final double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double nsfps = 1000000000 / fps_cap;
		double delta = 0;
		double deltafps = 0;
		
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();
		
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			deltafps += (now - lastTime) / nsfps;
			lastTime = now;
			
			while(delta >=1) {
				tick();
				delta--;
				updates++;
			}
			
			if(deltafps >= 1) {
				render();
				frames++;
				deltafps--;
			}

			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				frame.setTitle(Main.TITLE + " TICKS: "+updates + " FPS: "+frames + " X: "+((int)player.x()) + " Y: "+((int) + player.y()));
				updates = 0;
				frames = 0;
			}
			
		}
		stop();
	}
		
	public synchronized void start() {
		if(running)
			return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop() {
		if(!running)
			return;
		
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.exit(1);
	}

	private void init() {
		addKeyListener(input = new KeyInput());
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
		
		render = new Render(Main.WIDTH, Main.HEIGHT, this);

		camera = new Camera(this, 0, 0);
		font = new Font();
		draw = new Draw(this, font);
		
		player = new Player(this, 0, 0, 13, 20);
		
		//TODO a shape nao precisa de bioma....
		Shape shape = ShapeLoader.loadTiledMap("map2.json", Biomes.FOREST.getTile());
		rooms.put("A",activeRoom = new MapA(this, shape, Biomes.FOREST));
	
		activeRoom.roomload(player);
		
		requestFocus();
	}
	
	private void tick() {
		
		camera.centerOnLocation(player, activeRoom);
		
		if(activeRoom != null)
			activeRoom.roomtick(collisions);
		
		if(activeRoom != null)
			activeRoom.roompredraw(draw, player);
		
		render.prerender(activeRoom, draw, player);
		
		mouse.clearCache();
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();		
		if(bs == null) {
			createBufferStrategy(2);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		
		draw.drawString("Vem Monstro", 5*5, 70, 10, -100, false);
		draw.drawString("DEVE SER DEVE", 10, 150, 5*5 + 10, -100, false);
		draw.drawString("IRINEU", 7, player.x()-15, player.y()-12, -100, true);
		
		render.render(g, draw, this, player);
		
		g.dispose();
		bs.show();
		
	}
	
	public void setActiveRoom(String roomName) {
		Map room = rooms.get(roomName);
		if(room != null) {
			this.activeRoom = room;
			activeRoom.roomload(player);
		}
		
	}
	
	public static Collisions getCollisions() {
		return collisions;
	}
	
	public Camera getCamera() {
		return camera;
	}
	
	public boolean isDebugMode() {
		return debugMode;
	}
	
	public Player getPlayer() {
		return player;
	}

}
