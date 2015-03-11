package com.kdi.dungeons.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {

	private String path;
	private int width, height; // Size of the spritesheet not the sprite

	public int[] pixels;

	public static SpriteSheet tiles = new SpriteSheet("/textures/spritesheet.png", 256, 256);

	public static SpriteSheet player = new SpriteSheet("/textures/hero.png", 128, 96);
	public static SpriteSheet playerDown = new SpriteSheet(player, 0, 0, 1, 3, 32);
	public static SpriteSheet playerUp = new SpriteSheet(player, 1, 0, 1, 3, 32);
	public static SpriteSheet playerLeft = new SpriteSheet(player, 3, 0, 1, 3, 32);
	public static SpriteSheet playerRight = new SpriteSheet(player, 2, 0, 1, 3, 32);

	public static SpriteSheet dummy = new SpriteSheet("/textures/dummy.png", 128, 96);
	public static SpriteSheet dummyDown = new SpriteSheet(dummy, 2, 0, 1, 3, 32);
	public static SpriteSheet dummyUp = new SpriteSheet(dummy, 0, 0, 1, 3, 32);
	public static SpriteSheet dummyLeft = new SpriteSheet(dummy, 3, 0, 1, 3, 32);
	public static SpriteSheet dummyRight = new SpriteSheet(dummy, 1, 0, 1, 3, 32);

	private Sprite[] sprites;

	/**
	 * This constructor extracts part of an spritesheet. /sub spritesheet/
	 */
	public SpriteSheet(SpriteSheet sheet, int x, int y, int width, int height, int spriteSize) {
		int xx = x * spriteSize;
		int yy = y * spriteSize;
		int w = width * spriteSize;
		int h = height * spriteSize;

		this.width = w;
		this.height = h;
		pixels = new int[w * h];

		for (int y0 = 0; y0 < h; y0++) {
			int yPsition = yy + y0;
			for (int x0 = 0; x0 < w; x0++) {
				int xPsition = xx + x0;
				pixels[x0 + y0 * w] = sheet.pixels[xPsition + yPsition * sheet.getWidth()];
			}
		}

		int frame = 0;
		sprites = new Sprite[width * height];
		// First go through all tiles 
		for (int ya = 0; ya < height; ya++) {
			for (int xa = 0; xa < width; xa++) {
				int spritePixels[] = new int[spriteSize * spriteSize];
				// On every tile go through its pixels 
				for (int y0 = 0; y0 < spriteSize; y0++) {
					for (int x0 = 0; x0 < spriteSize; x0++) {
						// Extract current pixel information to spritePixels[]
						spritePixels[x0 + y0 * spriteSize] = pixels[(x0 + xa * spriteSize) + (y0 + ya * spriteSize) * spriteSize];

					}
				}
				Sprite sprite = new Sprite(spritePixels, spriteSize, spriteSize);
				sprites[frame++] = sprite;
			}
		}

	}

	public SpriteSheet(String path, int width, int height) {
		this.path = path;
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
		load();
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Sprite[] getSprites() {
		return sprites;
	}

	private void load() {
		try {
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
			int width = image.getWidth();
			int height = image.getHeight();
			image.getRGB(0, 0, width, height, pixels, 0, width);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
