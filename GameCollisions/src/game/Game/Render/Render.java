package game.Game.Render;

import java.awt.AlphaComposite;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferInt;
import java.util.Comparator;

import javax.swing.JFrame;

import game.Main;
import game.Entities.Camera;
import game.Entities.Player.Player;
import game.Game.Game;
import game.Map.Map;
import game.Map.Shape.Shape;
import game.Maths.Maths;
import game.Maths.Rectangle;

public class Render {
	
	private BufferedImage image;
	private int width;
	private int height;


	public Render(int width, int height, Game game) {
		this.width = width;
		this.height = height;
	}
	
	public void prerender(Map map, Draw draw, Player player) {
		this.image = new BufferedImage(Main.WIDTH, Main.HEIGHT, BufferedImage.TYPE_INT_RGB);
		
		Graphics2D g2d = image.createGraphics();
		
		Camera camera = draw.getCamera();
		Rectangle p = new Rectangle(camera.toXLocation(player.getBodycollide().x()), camera.toYLocation(player.getBodycollide().y()), player.getBodycollide().width(), player.getBodycollide().height());
		
		g2d.setColor(map.getBiome().getBackground());
		g2d.fillRect(0, 0, this.image.getWidth(), this.image.getHeight());
		
		draw.getImages().stream()
.filter(x->x.isInsideScreen(camera))
		.sorted((i1, i2) -> i1.getHeightRelative().compareTo(i2.getHeightRelative()))
		.sorted(Comparator.comparing(Drawer::getPriority).reversed())
		.forEach(img-> {
			
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,(float) 1));
			if(img.isOpcaity() && img.getRectangle().collide(p))
				g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,(float) 0.50));

			//System.out.print(Maths.roundNumberMultiple(img.getRectangle().x(), Main.SCALE)+"\n");
			g2d.drawImage(img.getImage(),
					(int)img.getRectangle().x(),
					(int)img.getRectangle().y(), null);
			
			/*
			g2d.setColor(Color.RED);
			g2d.drawRect((int)(img.getRectangle().x()),
					(int)(img.getRectangle().y()), (int)img.getRectangle().width(), (int)img.getRectangle().height());
			*/
	
		});
		
		//Render Debug
		g2d.setColor(Color.RED);
		
		draw.getRectangles().forEach(x-> g2d.drawRect((int)x.x(), (int)x.y(), (int)x.width(), (int)x.height()));
		draw.getFillrectangles().forEach(x-> g2d.fillRect((int)x.x(), (int)x.y(), (int)x.width(), (int)x.height()));
		draw.getLine().forEach(x-> g2d.draw(x));
		
		draw.getRectangles().clear();
		draw.getFillrectangles().clear();
		draw.getLine().clear();
		
		g2d.dispose();
		draw.getImages().clear();
	
		draw.getRectangles().clear();
		draw.getFillrectangles().clear();
		draw.getLine().clear();
		draw.getImages().clear();
		
	}
	
	public void render(Graphics  g, Draw draw, Canvas canvas, Player player) {
		//prerender(draw, player);
	
		g.drawImage(image, 0, 0, canvas.getWidth(), canvas.getHeight(), null);
	
	}
	
	
	
}
