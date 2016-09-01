package bounce;


import java.awt.Rectangle;

import javax.swing.JFrame;

public class Game implements Runnable {

	private static Game game;
	
	private Window window;
	private WindowCanvas windowCanvas;
	private Level level;
	
	private Level currentLevel = null;

	private boolean running = false;
	
	public Player player = new Player("Jeannie", 0, 0, 100); 
	public Obstacle quitBlock = new Obstacle("Quit", 10, 10, 100, 100);
	public Obstacle replayBlock = new Obstacle("Replay", 120, 10, 100, 100);
	
	public Mouse mouse = new Mouse();
	public KeyBoard keyBoard = new KeyBoard();

	private String curr;
	
	private Thread thread;

	
	public Game(String title, int width, int height) {
		game = this;
		init(title, width, height);
	}
	
	public void init(String title, int width, int height) {
		window = new Window(title, width, height);
		windowCanvas = new WindowCanvas(width, height, window.frame);
		
		window.addCanvas(windowCanvas);		//adding windowCanvas to window
		windowCanvas.addMouseListener(mouse);
		windowCanvas.addKeyListener(keyBoard);
		
		level = new Level(player);
		currentLevel = level;	

		window.show();
	}
	
	public void start() {
		running = true;
		thread = new Thread(this); // the this keyword why?
		thread.start(); // invokes the run() method.
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		double delta = 0.0;
		double ns = 1000000000.0 / 60.0;	// 60.0 means that you are updating 60 frames per second. 
		int updates = 0, frames = 0;
		long timer = System.currentTimeMillis();
		while(running) {
			/**
			 * EVERYTHING HAPPENS HERE*/
			
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;		// calculating the difference and dividing it by ns.
												// when we divide it by 1,000,000,000 we convert the nanoSecond to Seconds, 
												// but why multiply by 60? - I dont understand how this ensures that 60 frames are ran per second! :( 
			
			lastTime = now;
			
			
			if (delta >= 1.0) {		// if delta is greater than 1 second do you mean?
				update();
				updates++;
				delta--;
			}			
			
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) { // prints every 1 second
				timer += 1000;
				curr = updates + " ups, " + frames + " fps";
				updates = 0;
				frames = 0;
			}		
		}
	}
	
	
	@SuppressWarnings("deprecation")
	public void update() {
		window.frame.setTitle(curr);	
		currentLevel.update();
		// where the players get updated, after updating, gets removed.
		
	}	
	
	public void render() {	
		windowCanvas.beginRendering();
		windowCanvas.render();
		currentLevel.render(windowCanvas.g);
		windowCanvas.endRendering();
	}
	
	public static Game getInstance() { 
		return game;
	}

	public void nextLevel() {
		currentLevel = new Level(player);
	}

}
	

