package com.kdi.dungeons.level.tile;

import com.kdi.dungeons.graphics.Screen;
import com.kdi.dungeons.graphics.Sprite;

public class Tile {

	public int x, y; // Tile coordinates on the screen
	public Sprite sprite; // Tile sprite
	private boolean solid = false;

	public static Tile grass = new Tile(Sprite.grass, false);

	public static Tile flowerGreen = new Tile(Sprite.flowerGreen, false);
	public static Tile flowerRed = new Tile(Sprite.flowerRed, false);
	public static Tile flowerYellow = new Tile(Sprite.flowerYellow, false);
	public static Tile flowerMush = new Tile(Sprite.flowerMush, false);

	public static Tile blockBlue = new Tile(Sprite.blockBlue, true);
	public static Tile blockGold = new Tile(Sprite.blockGold, true);
	public static Tile blockGray = new Tile(Sprite.blockGray, true);

	public static Tile groundRocksBlue = new Tile(Sprite.groundRocksBlue, false);
	public static Tile groundRocksYellow = new Tile(Sprite.groundRocksYellow, false);
	public static Tile groundRocksPink = new Tile(Sprite.groundRocksPink, false);

	public static Tile bricks = new Tile(Sprite.bricks, true);

	public static Tile voidTile = new Tile(Sprite.voidSprite, false); // Tile to render if there is an error 

	/**
	 * @param sprite The sprite to render
	 */
	public Tile(Sprite sprite, boolean solid) {
		this.sprite = sprite;
		this.solid = solid;
	}

	/**
	 * Renders the tile to the screen
	 * 
	 * @param x The x position on the screen
	 * @param y The y position on the screen
	 * @param screen The game {@link Screen}
	 */
	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}

	/**
	 * Indicator for solid tiles. Tiles the hero cannot pass thru /rocks, walls,
	 * etc./
	 */
	public boolean solid() {
		return solid;
	}
}
