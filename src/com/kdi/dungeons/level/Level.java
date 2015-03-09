package com.kdi.dungeons.level;

import com.kdi.dungeons.graphics.Screen;
import com.kdi.dungeons.level.tile.Tile;
import com.kdi.dungeons.libs.Reference;

public class Level {

	protected int width, height; // Width and height of the level
	protected int[] tilesInt; // All tiles
	protected int[] tiles;

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

	public void update() {}

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
			for (int x = x0; x < x1; x++) {
				getTile(x, y).render(x, y, screen);
			}
		}
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
