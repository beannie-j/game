package bounce;



import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import java.util.List;

import bounce.Player.Direction;

public class Bullet extends Entity {
	// bullet is entity
	

	public Rectangle rectangle;
	private Entity owner;
	private BufferedImage image;
	private int[] pixels = new int[64 * 64];


	public boolean collision = false;
	public Direction direction;
	
	public Bullet(int bx, int by, int width, int height, Entity owner) {
		this.x = bx;
		this.y = by;
		this.width = width;
		this.height = height;
		this.rectangle = new Rectangle(bx, by, width, height);
		this.owner = owner;
		image = new BufferedImage(30, 30, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
		render();
	}
	
	public Bullet(int bx, int by, int width, int height, Entity owner, Direction dir) {
		this.x = bx;
		this.y = by;
		this.width = width;
		this.height = height;
		this.rectangle = new Rectangle(bx, by, width, height);
		this.owner = owner;
		this.direction = dir;
		image = new BufferedImage(30, 30, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
		render();
	}
	
	public void moveInDirection() {
		//get the last pressed button
	}

	public void update() {
		int xa = 0;
		int ya = 0;	
		
		if(!collision(xa, ya)) {
			x += xa;
			y += ya;
		} 
		
		if(this.direction == Direction.UP) {
			x += xa;
			y += -5;
		} else if(this.direction == Direction.LEFT) {
			x -= 5;
			y += ya;
		} else if(this.direction == Direction.RIGHT) {
			x += 5;
			y += ya;
		} else if(this.direction == Direction.DOWN) {
			x -= xa;
			y += 5;
		}
	}
	
	public void render(Graphics g) {
		g.drawImage(image, x, y, 20, 20, null);
	}
	
	public void render() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				pixels[x + y * width] = Sprite.fire.pixels[(x & 15) + (y & 15) * Sprite.fire.SIZE];
			}
		}
	
	}

	public boolean collision(int xa, int ya) {
		List<Balloon> balloons = level.findEntities(Balloon.class);
		for (Balloon b : balloons) {
			if (x + xa < b.getX() + b.getWidth() && x + xa + width > b.getX()) {
				if (y + ya < b.getY() + b.getHeight() && y + ya + height > b.getY()) {
					collision = true;
					b.damage(10);
					remove();
				}
			}

			else
				collision = false;
		}

		return collision;
	}
	
	public Entity getOwner() {
		return owner;
	}

}
