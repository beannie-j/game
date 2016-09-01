package bounce;


import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Level {
	
	public List<Entity> entities = new ArrayList<Entity>();
	public int number;
	public Player p;
	private int difficulty = 2;
	public Random random = new Random();

	public Level() {
		init();
	}
	
	public Level(Player p) {
		this.p = p;
		p.setLevel(this);
		entities.add(p);
		init();
	}
	
	private void init() {
		add(new Obstacle("", 400, 270, 50, 50));
		add(new Obstacle("", 500, 320, 50, 50));
		add(new Obstacle("", 450, 400, 50, 50));
		
		for(int i = 0; i < difficulty; i++) {
			int X = random.nextInt((460 -1) + 1) + 1;
			int Y = random.nextInt((480 -1) + 1) + 1;
			add(new Balloon(X, Y, 100));
		}
	}
	
	public void add(Entity entity) {
		entity.setLevel(this);
		entities.add(entity);
	}
	
	public <T> List<T> findEntities(Class<T> cls) {  // takes a parameter which is type of class.
		// T for type.
		List<T> results = new ArrayList<T>();
		for (Entity entity : entities) {
			if (cls.isInstance(entity))
				results.add((T)entity);
		}
		return results;
	}
	
	private boolean isLevelOver() {
		List<Balloon> balloons = findEntities(Balloon.class);
		return balloons.isEmpty();
	}
	
	public void update() {
		for (int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.update();		//updating for player
			
			if (e.isRemoved())
				entities.remove(i--); 
			
		}
		
		if (isLevelOver())
			Game.getInstance().nextLevel();
	}
	
	public void render(Graphics g) {
		for (Entity e : entities) {
			e.render(g);
		}
	}
	

	

}
