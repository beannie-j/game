package bounce;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class Player extends Entity { // now player is type entity

	private String name;
	public int health;
	private Rectangle yellowOval = new Rectangle(200, 400, 20, 20);
	private boolean boundsRight = false;
	private boolean boundsLeft = false;
	private boolean boundsUp = false;
	private boolean boundsDown = false;
	private boolean collision = false;
	private boolean restart = false;
	public static boolean space, A = false;
	private int delay = 10;
	private int delayTimer = 0;
	
	public enum Direction {
		LEFT, RIGHT, UP, DOWN
	}


	private JFrame frame;

	public boolean dead = false;
	private int speed = 6;

	private BufferedImage image;
	private int[] pixels = new int[64 * 64];


	public Player(String name, int px, int py, int health) {
		this.name = name;
		this.x = px;
		this.y = py;
		this.health = health;
		width = 30;
		height = 30;

		image = new BufferedImage(30, 30, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
		render();
	}
	

	public void render() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				pixels[x + y * width] = Sprite.smiley.pixels[(x & 15) + (y & 15) * Sprite.smiley.SIZE];

			}
		}
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public Rectangle getOval() {
		return yellowOval;
	}

	public int getHealth() {
		return health;
	}


	public boolean getBounds() {
		boundsLeft = true;
		boundsRight = true;
		boundsUp = true;
		boundsDown = true;
		if (x > 620) {
			boundsRight = false;
		} else if (x < 0) {
			boundsLeft = false;
		} else if (y > 558) {
			boundsDown = false;
		} else if (y < 0) {
			boundsUp = false;
		}
		return boundsRight;
	}

	private boolean collision(int xa, int ya) {
		collision = false;
		List<Entity> entities = level.entities;
		for (Entity entity : entities) {
			if (entity == this || entity instanceof Bullet) // because player is type entity as well.
				continue;

			if (x + xa < entity.x + entity.width && x + xa + width > entity.x) {
				if (y + ya < entity.y + entity.height && y + ya + height > entity.y) {
					collision = true;
					health--;
					if(health < 0) {
						health = 0;
						dead = true;
					}
				}
			}
		}
		return collision;
	}
	public void update() {

		/*
		 * Too many if statements?
		 **/
		// moving the player - the yellowOval
		// makes sense because the player should move itself!

		getBounds();
		
		if (!boundsRight) {
			x = 620;
		} else if (!boundsLeft) {
			x = 0;
		} else if (!boundsUp) {
			y = 0;
		} else if (!boundsDown) {
			y = 558;
		}

		int xa = 0;
		int ya = 0;
		if (KeyBoard.isKeyPressed(KeyEvent.VK_UP)) {
			ya -= speed;
		} else if (KeyBoard.isKeyPressed(KeyEvent.VK_DOWN)) {
			ya += speed;
		}

		if (KeyBoard.isKeyPressed(KeyEvent.VK_LEFT)) {
			xa -= speed;
		} else if (KeyBoard.isKeyPressed(KeyEvent.VK_RIGHT)) {
			xa += speed;
		}
		
		if (!collision(xa, ya)) {
			x += xa;
			y += ya;
		}
		
		if(KeyBoard.isKeyPressed(KeyEvent.VK_W)) {
			
		}

//		if (WindowCanvas.up) {
//			y -= speed;
//		} else if (WindowCanvas.down) {
//			y += speed;
//		} else if (WindowCanvas.left) {
//			x -= speed;
//		} else if (WindowCanvas.right) {
//			x += speed;
//		}
		
		delayTimer--;

		if (KeyBoard.isKeyPressed(KeyEvent.VK_SPACE)) {
			space = true;
			shoot(Direction.UP);

		} else if (KeyBoard.isKeyReleased(KeyEvent.VK_SPACE)) {
			space = false;
			// if false stop drawing
		}
		
		if (KeyBoard.isKeyPressed(KeyEvent.VK_A)) {
			A = true;
			shoot(Direction.LEFT);
		} else if (KeyBoard.isKeyReleased(KeyEvent.VK_A)) {
			A = false;
		}
		
		if (KeyBoard.isKeyPressed(KeyEvent.VK_D)) {
			shoot(Direction.RIGHT);
		} else if (KeyBoard.isKeyReleased(KeyEvent.VK_D)) {
		}
		
		if (KeyBoard.isKeyPressed(KeyEvent.VK_W)) {
			shoot(Direction.UP);
		} else if (KeyBoard.isKeyReleased(KeyEvent.VK_W)) {
		}
		
		if (KeyBoard.isKeyPressed(KeyEvent.VK_S)) {
			shoot(Direction.DOWN);
		} else if (KeyBoard.isKeyReleased(KeyEvent.VK_S)) {
		}

	}

	public void render(Graphics g) {
		String s = Integer.toString(getHealth());
		g.setColor(Color.WHITE);
		g.drawString("Health : " + s, 450, 100);
		if (collision) {
			g.setColor(Color.RED);
			g.fillRect(x, y, width, height);
		} else
			g.drawImage(image, x, y, width, height, null);
		g.setColor(Color.BLACK);
		g.drawString(name, x, y);
		
		if(health <= 0) {
			g.setColor(Color.red);
			g.fillRect(200, 200, 200, 200);
			g.setColor(Color.white);
			g.drawString("GAME OVER, replay?", 210, 230);

			int X = Mouse.getX();
			int Y = Mouse.getY();
			
			if(X > 200 && X < 400 && Y > 200 && Y < 400 && Mouse.clicked) {
				restart = true;
				Game.getInstance().nextLevel();
				health = 100;
			} else {
				restart = false;
			}			
		} 
	}

	
	public void shoot(Direction dir) {
		if (delayTimer > 0)
			return;	
		level.add(new Bullet(x, y, 20, 20, this, dir));
		delayTimer = delay;
	}

}
