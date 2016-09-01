package bounce;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Balloon extends Entity {
	
	public Rectangle rectangle;
	private int health;
	public boolean collision = false;
	
	private float r = width /2;
	private float dx = 3;
	private float dy = 2;
	private int frameWidth = 560;
	private int frameHeight = 480;
	
	
	public Balloon(int bx, int by, int size) {
		this.x = bx;
		this.y = by;
		this.width = size;
		this.height = size;
		this.health = (int) (size * 1.5);
		this.rectangle = new Rectangle(bx, by, size, size);	
	}
	
	public void update() {
		bounce();
	}
	
	private void bounce() {
		this.x += dx;
		this.y += dy;
		
		if(this.x - r < 0) {
			dx = -dx; // reflect
			this.x = (int) r;
		} else if(this.x > frameWidth) {
			dx = -dx;
			this.x = (int) (frameWidth - r);
		}	
		
		if(this.y - r < 0) {
			dy = -dy; // reflect
			this.y = (int) r; // reset
		} else if(this.y + r > frameHeight) {
			dy = -dy;
			this.y = (int) (frameHeight - r);
		}
		
		
	}
	
	public void render(Graphics g) {
		if (health > 0) {
			g.setColor(Color.MAGENTA);
			g.fillOval(x, y, width, height);
		} else {
			g.setColor(Color.BLACK);
			g.setColor(new Color(0, 0, 0, 100));
			g.fillOval(x, y, width, height);
			// how to set clear?
		}
		g.setColor(Color.white);
		g.drawString(Integer.toString(health), x + 30, y + 50);
	}
	
	public void damage(int damage) {
		setHealth(health - damage);
	}
	
	public void setHealth(int health) {
		this.health = health;
		if (health == 0)
			remove();
	}
	
	

}
