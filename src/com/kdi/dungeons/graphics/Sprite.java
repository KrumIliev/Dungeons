package com.kdi.dungeons.graphics;

public class Sprite {

	private int x, y;
	protected SpriteSheet sheet;

	private int width, height;
	public int[] pixels;

	public static Sprite voidSprite = new Sprite(16, 16, 0x007ae1);

	/**
	 * Level sprites
	 */
	public static Sprite grass = new Sprite(16, 0, 0, SpriteSheet.tiles);

	public static Sprite flowerGreen = new Sprite(16, 0, 4, SpriteSheet.tiles);
	public static Sprite flowerRed = new Sprite(16, 1, 4, SpriteSheet.tiles);
	public static Sprite flowerYellow = new Sprite(16, 2, 4, SpriteSheet.tiles);
	public static Sprite flowerMush = new Sprite(16, 3, 4, SpriteSheet.tiles);

	public static Sprite blockBlue = new Sprite(16, 0, 1, SpriteSheet.tiles);
	public static Sprite blockGold = new Sprite(16, 1, 1, SpriteSheet.tiles);
	public static Sprite blockGray = new Sprite(16, 2, 1, SpriteSheet.tiles);

	public static Sprite groundRocksBlue = new Sprite(16, 1, 0, SpriteSheet.tiles);
	public static Sprite groundRocksYellow = new Sprite(16, 2, 0, SpriteSheet.tiles);
	public static Sprite groundRocksPink = new Sprite(16, 3, 0, SpriteSheet.tiles);

	public static Sprite bricks = new Sprite(16, 5, 0, SpriteSheet.tiles);

	/**
	 * Player sprites
	 */
	public static Sprite playerUp = new Sprite(32, 0, 7, SpriteSheet.tiles);
	public static Sprite playerUp1 = new Sprite(32, 1, 7, SpriteSheet.tiles);
	public static Sprite playerUp2 = new Sprite(32, 2, 7, SpriteSheet.tiles);

	public static Sprite playerDown = new Sprite(32, 0, 5, SpriteSheet.tiles);
	public static Sprite playerDown1 = new Sprite(32, 1, 5, SpriteSheet.tiles);
	public static Sprite playerDown2 = new Sprite(32, 2, 5, SpriteSheet.tiles);

	public static Sprite playerSide = new Sprite(32, 0, 6, SpriteSheet.tiles);
	public static Sprite playerSide1 = new Sprite(32, 1, 6, SpriteSheet.tiles);
	public static Sprite playerSide2 = new Sprite(32, 2, 6, SpriteSheet.tiles);

	/**
	 * Projectile sprites
	 */
	public static Sprite projectileWizard = new Sprite(16, 0, 6, SpriteSheet.tiles);

	/**
	 * Particle sprites
	 */
	public static Sprite particleNormal = new Sprite(3, 3, 0xAAAAAA); //

	protected Sprite(int width, int height, SpriteSheet sheet) {
		this.width = width;
		this.height = height;
		this.sheet = sheet;
	}

	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		this.width = size;
		this.height = size;
		pixels = new int[size * size];
		this.x = x * size;
		this.y = y * size;
		this.sheet = sheet;
		load();
	}

	public Sprite(int width, int height, int color) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
		setColor(color);
	}

	public Sprite(int[] pixels, int width, int height) {
		this.width = width;
		this.height = height;
		this.pixels = pixels;
	}

	private void setColor(int color) {
		for (int i = 0; i < width * height; i++) {
			pixels[i] = color;
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	private void load() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				pixels[x + y * width] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.getWidth()];
			}
		}
	}
}
