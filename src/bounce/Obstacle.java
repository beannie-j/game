package bounce;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JFrame;


public class Obstacle extends Entity {
	
	private String label;
	private boolean collision = false;
	
	private Level level;
	
	private int dx = 1;
	private int MAX = 560;
	private int MIN = 300;

	public Obstacle(String label, int x, int y, int width, int height) {
		this.label = label;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void update() {
		move();
		int X = Mouse.getX();
		int Y = Mouse.getY();
		
		if(X > 120 && X < 220 && Y > 10 && Y < 110) {
			// restart
			// Game.restart = true;
			// System.out.println("Game Over");
		}
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(x, y, width, height);	
		g.setColor(Color.white);
		g.drawString(label, x+5, y+15);
	}
	
	private void move() {
		this.x += dx;
		
		if(this.x > MAX - this.width) {
			dx = -dx;
		}
		if(this.x < MIN) {
			dx = -dx;
		}
	}

}
