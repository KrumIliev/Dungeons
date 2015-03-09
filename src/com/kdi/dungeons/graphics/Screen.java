package com.kdi.dungeons.graphics;

import java.util.Random;

import com.kdi.dungeons.libs.Reference;

public class Screen {

	public int width, height; // The screen size
	public int[] pixels; // The screen pixels
	public final int MAP_SIZE = 64; // The maps size
	public final int MAP_SIZE_MASK = MAP_SIZE - 1;
	public int xOffset, yOffset;
	public int[] tiles = new int[MAP_SIZE * MAP_SIZE];

	private Random random = new Random();

	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];

		for (int i = 0; i < MAP_SIZE * MAP_SIZE; i++) {
			tiles[i] = random.nextInt(0xffffff);
		}
	}

	public void clear() {
		for (int i = 0; i < pixels.length; i++)
			pixels[i] = 0;
	}

	public void renderTile(int xPixel, int yPixel, Sprite sprite) {
		xPixel -= xOffset; // Adjusting the x position with the offset
		yPixel -= yOffset; // Adjusting the y position with the offset
		for (int y = 0; y < sprite.SIZE; y++) {
			int yAbsolute = y + yPixel;
			for (int x = 0; x < sprite.SIZE; x++) {
				int xAbsolute = x + xPixel;
				if (xAbsolute < -sprite.SIZE || xAbsolute >= width || yAbsolute < 0 || yAbsolute >= height) break;
				if (xAbsolute < 0) xAbsolute = 0;

				// Renders all colors without 0xFFFF00FF /pink/ 
				int color = sprite.pixels[x + y * sprite.SIZE];
				if (color != Reference.COLOR_TRANSPARENCY) pixels[xAbsolute + yAbsolute * width] = color;
			}
		}
	}

	public void renderPlayer(int xPixel, int yPixel, Sprite sprite, int flip) {
		xPixel -= xOffset; // Adjusting the x position with the offset
		yPixel -= yOffset; // Adjusting the y position with the offset
		for (int y = 0; y < 32; y++) {
			int yAbsolute = y + yPixel;
			int ys = y;
			// Checks if the image needs to be flipped vertically
			if (flip == 2 || flip == 3) {
				ys = 31 - y;
			}

			for (int x = 0; x < 32; x++) {
				int xAbsolute = x + xPixel;
				int xs = x;
				// Checks if the image needs to be flipped horizontally
				if (flip == 1 || flip == 3) {
					xs = 31 - x;
				}

				if (xAbsolute < -32 || xAbsolute >= width || yAbsolute < 0 || yAbsolute >= height) break;
				if (xAbsolute < 0) xAbsolute = 0;

				// Renders all colors without 0xFFFF00FF /pink/ 
				int color = sprite.pixels[xs + ys * 32];
				if (color != Reference.COLOR_TRANSPARENCY) pixels[xAbsolute + yAbsolute * width] = color;

			}
		}
	}

	public void setOffset(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
}
