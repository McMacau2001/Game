package game.Map;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import game.Main;
import game.Entities.Player.Player;
import game.Game.Render.Draw;
import game.Map.Shape.Shape;
import game.Map.Shape.Groups.ShapeMap;

public class Minimap {
	
	private ShapeMap shape;
	
	private int width;
	private int height;
	private float scale;
	
	private BufferedImage minimap;
	
	public Minimap(ShapeMap shape, int width, int height, float scale) {
		this.shape = shape;
		this.width = width;
		this.height = height;
		this.scale = scale;

		BufferedImage[][] images = shape.getTiles(0);
		
		minimap = new BufferedImage((int)(shape.getShape().getWidth() * scale), (int)(shape.getShape().getHeight() * scale), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = minimap.createGraphics();
		
		int tilesize = (int)(shape.getShape().getTilewidth() * scale);
		for(int x = 0; x < images.length; x++) 
			for(int y = 0; y < images.length; y++) 
				g2d.drawImage(images[x][y], x * tilesize, y*tilesize, tilesize, tilesize, null);
	
		g2d.dispose();
		
	}

	public void render(Draw draw, Player player) {	
		int tilesize = (int)(shape.getShape().getTilewidth() * scale);
		
		int xp = -(int)(player.getBodyfoot().x() + (player.getBodyfoot().width())/2) * tilesize / tilesize;
		int yp = -(int)(player.getBodyfoot().y() + (player.getBodyfoot().height())/2) * tilesize / tilesize;
		
		int x = (int)((xp * scale) / tilesize*tilesize );
		int y = (int)(yp * scale) / tilesize*tilesize;
		
		BufferedImage background = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = background.createGraphics();
		
		//BORDER
		g2d.setColor(Color.BLACK);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,(float) 0.6));
		g2d.fillRect(0, 0, width, height);
	
		//MAP
		g2d.drawImage(minimap, x, y, minimap.getWidth(), minimap.getHeight(), null);
		
		//PLAYER
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,(float) 1));
		g2d.drawRect(0, 0, width-1, height-1);
		g2d.setColor(Color.WHITE);
		g2d.drawRect((int)width/2, (int)height/2, tilesize, tilesize);
		
		g2d.dispose();

		draw.drawString("map", 8, 23, Main.HEIGHT-height-15, -102, false);
		draw.drawImage(background, 10, Main.HEIGHT-height-10, width, height, true, true, -101);
		
	}

}
