package bounce;

import java.awt.Canvas;

import javax.swing.JFrame;


public class Window {
	
	private String name = "";
	private int width, height;
	public JFrame frame;


	public Window(String name, int width, int height) {
		this.name = name;
		this.width = width;
		this.height = height;
		create();
	}
	

	private void create() {
		frame = new JFrame(name);
		frame.setResizable(false);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);	
	}
	
	
	public void addMouse(Mouse m) {
		frame.addMouseListener(m);
	}
	
	public void addKeyboard(KeyBoard key) {
		frame.addKeyListener(key);
	}
	
	public void show() {
		frame.setVisible(true);
	
	}
	
	public void close() {
		frame.setVisible(false);
	}
	
	public void addTitle(String title) {
		frame.setTitle(title);
	}
	
	public void removeCanvas(WindowCanvas canvas) {
		frame.remove(canvas);
	}
	
	public void addCanvas(WindowCanvas canvas) {
		// adding canvas to the frame.
		frame.add(canvas);
	}

}
