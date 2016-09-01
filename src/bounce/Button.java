package bounce;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;


public class Button {
	
	private String label;
	private Rectangle box;
	private Boolean inside;
	public Boolean colorChange = false;
	
	public Button(String label, int x, int y, int width, int height) {
		this.label = label;
		this.box = new Rectangle(x, y, width, height);
	}
	
	public Rectangle getBox() {
		return box;
	}

	public void setBox(Rectangle box) {
		this.box = box;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	public boolean getInside() {
		inside = box.contains(Mouse.getX(), Mouse.getY());
		return inside;
	}

	
	public void update() {
		if(getInside() && Mouse.getClicked()) {
			System.out.println("Button Clicked");
			colorChange = true;	
 		}
		inside = false;
	}
	
	public void change(Graphics g, Rectangle r) {
		g.setColor(Color.YELLOW);
		g.fillRect((int)r.getX(), (int)r.getY(), (int)r.getWidth(), (int)r.getHeight());	
	}
	
	public void render(Graphics g, Color c) {
		g.setColor(c);
		g.fillRect(getBox().x, getBox().y, getBox().width, getBox().height);
		g.setColor(Color.WHITE);
		g.drawString(label, getBox().x+ 10, getBox().y + 17);
	}

	
	public void render(Graphics g) {
		g.setColor(Color.GRAY);
		g.fillRect(getBox().x, getBox().y, getBox().width, getBox().height);
		g.setColor(Color.BLUE);
		g.drawString(label, getBox().x+ 10, getBox().y + 10);
	}

}
