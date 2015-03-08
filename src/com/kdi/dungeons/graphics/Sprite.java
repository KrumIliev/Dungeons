package com.kdi.dungeons.graphics;

public class Sprite {

	private int x, y;
	private SpriteSheet sheet;

	public final int SIZE;
	public int[] pixels;

	public static Sprite grass = new Sprite(16, 0, 0, SpriteSheet.tiles);
	public static Sprite voidSprite = new Sprite(16, 0x007ae1);

	public static Sprite playerUp = new Sprite(32, 0, 7, SpriteSheet.tiles);
	public static Sprite playerDown = new Sprite(32, 0, 5, SpriteSheet.tiles);
	public static Sprite playerSide = new Sprite(32, 0, 6, SpriteSheet.tiles);

	public static Sprite playerUp1 = new Sprite(32, 1, 7, SpriteSheet.tiles);
	public static Sprite playerUp2 = new Sprite(32, 2, 7, SpriteSheet.tiles);

	public static Sprite playerDown1 = new Sprite(32, 1, 5, SpriteSheet.tiles);
	public static Sprite playerDown2 = new Sprite(32, 2, 5, SpriteSheet.tiles);

	public static Sprite playerSide1 = new Sprite(32, 1, 6, SpriteSheet.tiles);
	public static Sprite playerSide2 = new Sprite(32, 2, 6, SpriteSheet.tiles);

	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		SIZE = size;
		pixels = new int[SIZE * SIZE];
		this.x = x * SIZE;
		this.y = y * SIZE;
		this.sheet = sheet;
		load();
	}

	public Sprite(int size, int color) {
		SIZE = size;
		pixels = new int[SIZE * SIZE];
		setColor(color);
	}

	private void setColor(int color) {
		for (int i = 0; i < SIZE * SIZE; i++) {
			pixels[i] = color;
		}
	}

	private void load() {
		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {
				pixels[x + y * SIZE] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.SIZE];
			}
		}
	}
}
