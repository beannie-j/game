package bounce;



import java.awt.Graphics;

public class Entity {

	protected int x, y, width, height;
	protected boolean removed = false;
	protected Level level;
	
	public Level getLevel() {
		return level;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void remove() {
		removed = true;
	}
	
	public boolean isRemoved() {
		return removed;
	}
	
	public void update() {
	}

	public void render(Graphics g) {
	}
	
	public void render() {
		
	}
	
	public void setLevel(Level level) {
		this.level = level;
	}

	
}
