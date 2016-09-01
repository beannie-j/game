package bounce;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

public class KeyBoard implements KeyListener {
	
	private static Set<Integer> pressedKeys = new HashSet<Integer>();
	public static Set<Integer> releasedKeys = new HashSet<Integer>();

	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	 //This doesnt seem right, needs refactoring and also need to make it faster how???
	
	public static boolean isKeyPressed(int keycode) {
		return pressedKeys.contains(keycode);
	}
	
	public static boolean isKeyReleased(int keycode) {
		return releasedKeys.contains(keycode);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		pressedKeys.add(key);
		
	/*	
		if(key == e.VK_UP) {
			Main.player.py--;
			Main.player.update();
		} else if (key == e.VK_DOWN) {
			Main.player.py++;
			Main.player.update(); 
		} else if (key == e.VK_LEFT) {
			Main.player.px--;
		} else if (key == e.VK_RIGHT) {
			Main.player.px++;
		} else if(key == e.VK_A) { 
			/* how to move the map oh gosh**/
		/*} else if(key == e.VK_SPACE) {
//			space = true;
		}*/
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		pressedKeys.remove(key);
		releasedKeys.add(key);
		
		/*
		if(key == e.VK_UP) {
			Main.player.py--;
			Main.player.update(); 
		} else if (key == e.VK_DOWN) {
			Main.player.py++;
			Main.player.update(); 
		} else if (key == e.VK_LEFT) {
			Main.player.px--;
		} else if (key == e.VK_RIGHT) {
			Main.player.px++;
		} else if (key == e.VK_SPACE) {
			space = true;
		}*/
	}
	
	
	
	

}
