package bounce;


public class Main {
		
	public static void main(String[] args) throws InterruptedException {
		String title = "";
		int width = 640;
		int height = 600;
				
		Game game = new Game(title, width, height);
		game.start();
	}

}
