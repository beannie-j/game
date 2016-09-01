package bounce;


import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class WindowCanvas extends Canvas {

	public int width, height;
	public Thread thread;
	public Rectangle rectangle = new Rectangle(50, 50, 200, 200);
	public Rectangle pinkOval = new Rectangle(10, 10, 20, 20);
	public boolean collision = false;
	public boolean running = false;
	public boolean inBorder = true;

	public static boolean up, down, left, right = false;

	public Graphics g;
	private BufferStrategy bs;

	public Button startButton, upButton, downButton, leftButton, rightButton;

	public long time;

	public JFrame frame;
	private Random random = new Random();

	/**
	 * An image with an accessable buffer, what does this mean tho..? created an
	 * image to draw things accessing the image with int[] array
	 */
	private BufferedImage image;
	public int[] pixels;
	private int[] tiles = new int[64 * 64];

	public WindowCanvas(int width, int height, JFrame frame) {
		setPreferredSize(new Dimension(width, height));
		this.frame = frame; // is this ok??
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
		renderTiles();

	}

	public void renderTiles() {
		for (int i = 0; i < 64 * 64; i++) {
			if (i % 3 == 1) {
				tiles[i] = 0x000000; // black
			} else if (i % 2 == 1) {
				tiles[i] = 0xFFFFCC; // yellow
			}
			// tiles[i] = random.nextInt(0xffffff);
		}
	}

	// public void renderImage() {
	// URL input = this.getClass().getResource("spritesheet.png");
	// try {
	// grass = ImageIO.read(input);
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }

	public void renderPlayer() {
		
//		for (int y1 = 0; y1 < getHeight(); y1++) {
//			for (int x1 = 0; x1 < getWidth(); x1++) {
//				pixels[x1 + y1 * getWidth()] = Sprite.stickMan.pixels[x1 + y1 * 16];
//			}
//		}
//		
	}

	public void renderPixels() {
		/**
		 * Filling the pixel array
		 */
		for (int y1 = 0; y1 < getHeight(); y1++) {
			for (int x1 = 0; x1 < getWidth(); x1++) {
				// pixels[x1 + y1 * getWidth()] = 0xCC66FF;
				/**
				 * making tiles 16 * 16 size
				 */
				int tileIndex = (x1 / 16) + (y1 / 16) * 64;
				// int tileIndex = (x1 >> 4) + (y1 >> 4) * 64;
				// bitwise operators??

				/**
				 * populating tile arrays here with grass
				 **/
				// pixels[x1 + y1 * getWidth()] = tiles[tileIndex];
				pixels[x1 + y1 * getWidth()] = Sprite.grass.pixels[(x1 & 15) + (y1 & 15) * Sprite.grass.SIZE];
				
				
				// pixels[x1 + y1 * getWidth()] = random.nextInt(0xffffff);
			}
		}
	}

	private void moveZigzag(Rectangle r) {
		for (int i = 0; i < getWidth(); i++) {
			r.x++;
			r.y++;
		}
	}

	private void move(Rectangle r) {
		/*
		 * Moves the pink Oval
		 */
		/*
		 * while (r.x + r.width > getWidth() || r.x == 0) { inBorder = true; }
		 * if(inBorder) { r.x = Main.player.getX() + 30; // why is it not moving
		 * faster? r.y = Main.player.getY() + 30; } else
		 * System.out.println("Hit Border!");
		 */
	}

	private void controlRight(Rectangle r) {
		r.x++;
	}

	private void controlLeft(Rectangle r) {
		r.x--;
	}

	private void controlUp(Rectangle r) {
		r.y--;
	}

	private void controlUp(Bullet bullet) {
		bullet.rectangle.y--;
	}

	private void controlDown(Rectangle r) {
		r.y++;
	}

	private void control(Rectangle r) {

		/**
		 * Not working why? seems like a more reasonable condition
		 **/
		// while(bounds) {
		// r.x--;
		// r.y--;
		// if(r.x + r.width > getWidth() || r.x == 0) {
		// System.out.println(".");
		// bounds = false;
		// }
		// }

		while (r.x + r.width > getWidth() || r.x == 0) { // if it hits border
			System.out.println("Hit Border");
			inBorder = false;
		}
		if (inBorder) {
			// add 4 cases of up down left right - how to change this to switch?
			if (up) {
				System.out.println("Up");
				r.y--;
			} else if (down) {
				r.y++;

			} else if (left) {
				// it is moving still nothing...?
				System.out.println("left");
				r.x--;

			} else if (right)
				System.out.println("right");
			r.x++;
		} else {
			// do nothing
		}

		// if(r.x + r.width > getWidth() || r.x == 0) {
		// running = false;
		// }

		// if(r.x + r.width > getWidth() || r.x == 0) {
		// in = false;
		// }
	}

	private boolean collision(Rectangle r1, Rectangle r2) {

		if (r1.contains(r2.getX(), r2.getY())
				|| r1.contains(r2.getX() + r2.getHeight(),
						r2.getY() + r2.getWidth())) {
			collision = true;

			g.setColor(Color.decode("#B03B3D")); // can we not separate
													// graphics?? only all needs
													// to be in render()?
			g.drawString("Game Over", 350, 400);

			System.out.println("Collision");
			System.out.println(r1.x);
			System.out.println(r2.x);
		}

		return collision;
	}

	private void stopMove(Rectangle r) {
		if (r.x + r.width > getWidth())
			running = false;
	}

	public void update() {
		/*
		 * if(collision(pinkOval, yellowOval) == true || collision(yellowOval,
		 * block1.getBox()) == true) { // render on screen - game over
		 * System.out.println("COllision!!!!!!!!!!!!!"); }
		 */

		move(pinkOval);
		stopMove(pinkOval);
	}

	public void hitBounds(Rectangle r) {
		if (r.x + r.width > getWidth() || r.x == 0) {
			running = false;
		}
	}

	// add click here to play button

	public void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void beginRendering() {
		// Graphics gets initialised here.
		bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			bs = getBufferStrategy();
		}
		g = bs.getDrawGraphics();
	}

	public void endRendering() {
		g.dispose();
		bs.show();
	}
	
	public void renderGameOver() {
		g.setColor(Color.red);
		g.fillRect(200, 200, 400, 400);
		g.setColor(Color.white);
		g.drawString("Game Over", 210, 210);
	}

	public void render() {

		renderPixels();

		g.drawImage(image, 0, 0, getWidth(), getHeight(), null); 
		// g.drawImage(grass, 250, 250, getWidth(), getHeight(), null);

		g.setColor(Color.WHITE);
		g.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);

		if (collision) {
			g.setColor(Color.white);
			g.drawString("Game Over", 260, 300);
		}

		startButton = new Button("Start", 300, 300, 100, 100);
		startButton.render(g);

		upButton = new Button("Up", 100, 100, 50, 50);
		upButton.render(g, Color.pink);

		downButton = new Button("Down", 100, 150, 50, 50);
		downButton.render(g, Color.decode("#79D2D2"));

		leftButton = new Button("Left", 50, 150, 50, 50);
		leftButton.render(g, Color.decode("#5940BF"));

		rightButton = new Button("Right", 150, 150, 50, 50);
		rightButton.render(g, Color.decode("#5940BF"));

		g.setColor(Color.PINK);
		g.fillOval(pinkOval.x, pinkOval.y, pinkOval.width, pinkOval.height);

		startButton.update();

		upButton.update();
		downButton.update();
		leftButton.update();
		rightButton.update();

		if (Mouse.clicked && startButton.colorChange) {
			startButton.change(g, startButton.getBox()); // Fix the change color
															// bug
			g.drawString("Start Game", 180, 180);
		}

		/**
		 * Buttons color changes and movements
		 **/

//		if (KeyBoard.isKeyPressed(KeyEvent.VK_SPACE)) {
//			bullet.render(g, Color.white); // rendering only once.
//			// there is a time gap in between.
//			bullet.update();
//			if (bullet.getRectangle().getY() < 0.0) {
//				// stop rendering - how..?
//				// shoot bullets again
//
//			}
//
//			// controlUp(bullet);
//
//			// g.setColor(Color.WHITE);
//			// g.fillOval(yellowOval.x, yellowOval.y, bullet.width,
//			// bullet.height);
//			// for(int i = 0; i < 10; i++) {
//			// g.setColor(Color.BLACK);
//			// g.fillOval(yellowOval.x + i, yellowOval.y + i, bullet.width,
//			// bullet.height);
//			// // how to shoot bullets.
//			// }
//			//
//		}

		if (Mouse.clicked && rightButton.colorChange) {
			right = true;
			rightButton.change(g, rightButton.getBox()); // Fix the change color
															// bug
			// controlRight(yellowOval);
		}

		if (Mouse.clicked && leftButton.colorChange) {
			left = true;
			leftButton.change(g, leftButton.getBox()); // Fix the change color
														// bug
			// controlLeft(yellowOval);

		}

		if (Mouse.clicked && upButton.colorChange) {
			up = true;
			upButton.change(g, upButton.getBox()); // Fix the change color bug
			// controlUp(yellowOval);
		}

		if (Mouse.clicked && downButton.colorChange) {
			down = true;
			downButton.change(g, downButton.getBox()); // Fix the change color
														// bug
			// controlDown(yellowOval);
		}

	}

}
