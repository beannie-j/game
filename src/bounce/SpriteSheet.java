package bounce;


import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class SpriteSheet {
	
	private String path;
	public final int SIZE;
	public int[] pixels;
	public URL input;
	
	public static SpriteSheet tiles = new SpriteSheet(SpriteSheet.class.getResource("spritesheet.png"), 256);
	
	public SpriteSheet(String path, int size) {
		this.path = path;
		this.SIZE = size;
		pixels = new int[SIZE * SIZE];
		load();
	}
	
	public SpriteSheet(URL input, int size) {
		this.input = input;
		this.SIZE = size;
		pixels = new int[SIZE * SIZE];
		load();
		
	}


	private void load() {
		try {
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource("spritesheet.png"));
			int w = image.getWidth();
			int h = image.getHeight();
			image.getRGB(0, 0, w, h, pixels, 0, w);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
