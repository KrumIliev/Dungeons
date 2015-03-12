package com.kdi.dungeons.level;

import java.util.ArrayList;
import java.util.List;

import com.kdi.dungeons.entity.Entity;
import com.kdi.dungeons.entity.mob.Player;
import com.kdi.dungeons.entity.particle.Particle;
import com.kdi.dungeons.entity.projectile.Projectile;
import com.kdi.dungeons.graphics.Screen;
import com.kdi.dungeons.level.tile.Tile;
import com.kdi.dungeons.libs.Reference;

public class Level {

	protected int width, height; // Width and height of the level
	protected int[] tilesInt; // All tiles
	protected int[] tiles;

	private List<Entity> entities = new ArrayList<Entity>();
	private List<Projectile> projectiles = new ArrayList<Projectile>();
	private List<Particle> particles = new ArrayList<Particle>();
	private List<Player> players = new ArrayList<Player>();

	/**
	 * Random level constructor
	 * 
	 * @param width Width of the level
	 * @param height Height of the level
	 */
	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		tilesInt = new int[width * height];
		generateLevel();
	}

	/**
	 * Level from file constructor
	 * 
	 * @param path Level file path
	 */
	public Level(String path) {
		loadLevel(path);
		generateLevel();
	}

	protected void generateLevel() {}

	/**
	 * Loads the level from specific file
	 * 
	 * @param path Level file path
	 */
	protected void loadLevel(String path) {}

	public void update() {
		for (int i = 0; i < entities.size(); i++)
			entities.get(i).update();
		for (int i = 0; i < projectiles.size(); i++)
			projectiles.get(i).update();
		for (int i = 0; i < particles.size(); i++)
			particles.get(i).update();
		for (int i = 0; i < players.size(); i++)
			players.get(i).update();
		remove();
	}

	private void remove() {
		for (int i = 0; i < entities.size(); i++)
			if (entities.get(i).isRemoved()) entities.remove(i);
		for (int i = 0; i < projectiles.size(); i++)
			if (projectiles.get(i).isRemoved()) projectiles.remove(i);
		for (int i = 0; i < particles.size(); i++)
			if (particles.get(i).isRemoved()) particles.remove(i);
		for (int i = 0; i < players.size(); i++)
			if (players.get(i).isRemoved()) players.remove(i);
	}

	/**
	 * Renders the level
	 * 
	 * @param xScroll The x position to move the map
	 * @param yScroll The y position to move the map
	 * @param screen The game {@link Screen}
	 */
	public void render(int xScroll, int yScroll, Screen screen) {
		screen.setOffset(xScroll, yScroll);

		// Corner pins
		int x0 = xScroll >> 4; // Top x corner 
		int x1 = (xScroll + screen.width + 16) >> 4; // Bottom x corner
		int y0 = yScroll >> 4; // Top y corner
		int y1 = (yScroll + screen.height + 16) >> 4; // Bottom y corner

		// Render only pixel between the corner pins
		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++)
				getTile(x, y).render(x, y, screen);
		}

		for (int i = 0; i < entities.size(); i++)
			entities.get(i).render(screen);
		for (int i = 0; i < projectiles.size(); i++)
			projectiles.get(i).render(screen);
		for (int i = 0; i < particles.size(); i++)
			particles.get(i).render(screen);
		for (int i = 0; i < players.size(); i++)
			players.get(i).render(screen);
	}

	public void add(Entity entity) {
		entity.setLevel(this);

		if (entity instanceof Particle) {
			particles.add((Particle) entity);

		} else if (entity instanceof Projectile) {
			projectiles.add((Projectile) entity);

		} else if (entity instanceof Player) {
			players.add((Player) entity);

		} else {
			entities.add(entity);
		}
	}

	public List<Projectile> getProjectiles() {
		return projectiles;
	}

	/**
	 * Checks for collision
	 */
	public boolean tileCollision(int x, int y, int size, int xOffset, int yOffset) {
		boolean collision = false;
		for (int c = 0; c < 4; c++) {
			int xt = (x - c % 2 * size + xOffset) >> 4;
			int yt = (y - c / 2 * size + yOffset) >> 4;
			if (getTile(xt, yt).solid()) collision = true;
		}

		return collision;
	}

	public List<Player> getPlayers() {
		return players;
	}

	/**
	 * TODO for multiplayer
	 * 
	 * @return player at specific index in the players list
	 */
	public Player getPlayerAt(int index) {
		return players.get(index);
	}

	/**
	 * @return The player that you are currently playing with
	 */
	public Player getClientPlayer() {
		return players.get(0);
	}

	/**
	 * @param entity to search around
	 * @param search distance from entity
	 * 
	 * @return List of entities in specific radius around specific entity
	 */
	public List<Entity> getEnitiesInRadius(Entity entity, int radius) {
		List<Entity> result = new ArrayList<Entity>();

		for (Entity e : entities) {
			int distanceX = Math.abs(e.getX() - entity.getX());
			int distanceY = Math.abs(e.getY() - entity.getY());

			double distance = Math.sqrt((distanceX * distanceX) + (distanceY * distanceY));
			if (distance <= radius) {
				result.add(e);
			}
		}

		return result;
	}

	public List<Player> getPlayersInRadius(Entity entity, int radius) {
		List<Player> result = new ArrayList<Player>();

		for (Player p : players) {
			int distanceX = Math.abs(p.getX() - entity.getX());
			int distanceY = Math.abs(p.getY() - entity.getY());

			double distance = Math.sqrt((distanceX * distanceX) + (distanceY * distanceY));
			if (distance <= radius) {
				result.add(p);
			}
		}

		return result;
	}

	/**
	 * Retrieves {@link Tile} for specific position
	 */
	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height) return Tile.voidTile; // Fixes ArrayIndexOutOfBoundsExeption
		if (tiles[x + y * width] == Reference.COLOR_GRASS) return Tile.grass;
		if (tiles[x + y * width] == Reference.COLOR_FLOWER_GREEN) return Tile.flowerGreen;
		if (tiles[x + y * width] == Reference.COLOR_FLOWER_MUSH) return Tile.flowerMush;
		if (tiles[x + y * width] == Reference.COLOR_FLOWER_RED) return Tile.flowerRed;
		if (tiles[x + y * width] == Reference.COLOR_FLOWER_YELLOW) return Tile.flowerYellow;
		if (tiles[x + y * width] == Reference.COLOR_BRICKS) return Tile.bricks;
		if (tiles[x + y * width] == Reference.COLOR_GROUND_BLUE) return Tile.groundRocksBlue;
		if (tiles[x + y * width] == Reference.COLOR_GROUND_PINK) return Tile.groundRocksPink;
		if (tiles[x + y * width] == Reference.COLOR_GROUND_YELLOW) return Tile.groundRocksYellow;
		if (tiles[x + y * width] == Reference.COLOR_BLOCK_BLUE) return Tile.blockBlue;
		if (tiles[x + y * width] == Reference.COLOR_BLOCK_GRAY) return Tile.blockGray;
		if (tiles[x + y * width] == Reference.COLOR_BLOCK_YELLOW) return Tile.blockGold;
		return Tile.voidTile;
	}

}
