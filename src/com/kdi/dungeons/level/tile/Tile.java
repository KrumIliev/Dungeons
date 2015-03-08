package com.kdi.dungeons.level.tile;

import com.kdi.dungeons.graphics.Screen;
import com.kdi.dungeons.graphics.Sprite;

public class Tile {

	public int x, y; // Tile coordinates on the screen
	public Sprite sprite; // Tile sprite

	public static Tile grass = new GrassTile(Sprite.grass);
	public static Tile voidTile = new VoidTile(Sprite.voidSprite); // Tile to render if there is an error 

	/**
	 * @param sprite The sprite to render
	 */
	public Tile(Sprite sprite) {
		this.sprite = sprite;
	}

	/**
	 * Renders the tile to the screen
	 * 
	 * @param x The x position on the screen
	 * @param y The y position on the screen
	 * @param screen The game {@link Screen}
	 */
	public void render(int x, int y, Screen screen) {}

	/**
	 * Indicator for solid tiles. Tiles the hero cannot pass thru /rocks, walls, etc./
	 */
	public boolean solid() {
		return false;
	}
}
