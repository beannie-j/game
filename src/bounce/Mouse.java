package bounce;

import java.util.List;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class Mouse implements MouseListener {
	
	private static int mx, my;
	private static Point p = MouseInfo.getPointerInfo().getLocation();
	public static boolean clicked = false;
	
	public static boolean released = false;
	
	public static Set<Integer> pressedMouse = new HashSet<Integer>();
	public static Set<Integer> releasedMouse = new HashSet<Integer>();
	
	public static boolean isMousePressed(int mouse) {
		mouse = mx + (my * 320);
		return pressedMouse.contains(mouse);
	}
	
	public static boolean isMouseReleased(int mouse) {
		mouse = mx + (my * 320);
		return releasedMouse.contains(mouse);
	}
	
	public static boolean getClicked() {
		return clicked;
	}
	
	public static int getX() {
		return mx;
	}
	
	public static int getY() {
		return my;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
		
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("Mouse Pressed");
		mx = e.getX();
		my = e.getY();
		System.out.println(mx + " ," + my + " = " + (mx + (my * 320)));
		clicked = true;
		int m = mx + (my * 320);
		pressedMouse.add(m);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		mx = e.getX();
		my = e.getY();
		
		System.out.println("Mouse Released");
		clicked = false;
	    released = true;
		
		WindowCanvas.up = false;
		WindowCanvas.down = false;
		WindowCanvas.left = false;
		WindowCanvas.right = false;
		
		int m = mx + (my * 320);
		pressedMouse.remove(m);
		releasedMouse.add(m);
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}


}
