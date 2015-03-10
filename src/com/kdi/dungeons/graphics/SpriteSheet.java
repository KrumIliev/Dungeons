package com.kdi.dungeons.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {

	private String path;
	private int width, height; // Size of the spritesheet not the sprite

	public int[] pixels;

	public static SpriteSheet tiles = new SpriteSheet("/textures/spritesheet.png", 256, 256);

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
