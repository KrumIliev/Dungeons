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

	/**
	 * Used for debugging
	 */
	public void renderSpriteSheet(int xPosition, int yPosition, SpriteSheet sheet, boolean fixed) {
		if (fixed) {
			xPosition -= xOffset; // Adjusting the x position with the offset
			yPosition -= yOffset; // Adjusting the y position with the offset
		}

		for (int y = 0; y < sheet.getHeight(); y++) {
			int yAbsolute = y + yPosition;
			for (int x = 0; x < sheet.getWidth(); x++) {
				int xAbsolute = x + xPosition;
				if (xAbsolute < 0 || xAbsolute >= width || yAbsolute < 0 || yAbsolute >= height) continue; // If exceeds the bounds of the screen don't render it
				pixels[xAbsolute + yAbsolute * width] = sheet.pixels[x + y * sheet.getWidth()];
			}
		}
	}

	public void renderSprite(int xPosition, int yPosition, Sprite sprite, boolean fixed) {
		if (fixed) {
			xPosition -= xOffset; // Adjusting the x position with the offset
			yPosition -= yOffset; // Adjusting the y position with the offset
		}

		for (int y = 0; y < sprite.getHeight(); y++) {
			int yAbsolute = y + yPosition;
			for (int x = 0; x < sprite.getWidth(); x++) {
				int xAbsolute = x + xPosition;
				if (xAbsolute < 0 || xAbsolute >= width || yAbsolute < 0 || yAbsolute >= height) continue; // If exceeds the bounds of the screen don't render it
				pixels[xAbsolute + yAbsolute * width] = sprite.pixels[x + y * sprite.getWidth()];
			}
		}
	}

	public void renderTile(int xPixel, int yPixel, Sprite sprite) {
		xPixel -= xOffset; // Adjusting the x position with the offset
		yPixel -= yOffset; // Adjusting the y position with the offset
		for (int y = 0; y < sprite.getHeight(); y++) {
			int yAbsolute = y + yPixel;
			for (int x = 0; x < sprite.getWidth(); x++) {
				int xAbsolute = x + xPixel;
				if (xAbsolute < -sprite.getWidth() || xAbsolute >= width || yAbsolute < 0 || yAbsolute >= height) break;
				if (xAbsolute < 0) xAbsolute = 0;

				// Renders all colors without 0xFFFF00FF /pink/ 
				int color = sprite.pixels[x + y * sprite.getWidth()];
				if (color != Reference.COLOR_TRANSPARENCY) pixels[xAbsolute + yAbsolute * width] = color;
			}
		}
	}

	// TODO remove flip
	public void renderMob(int xPixel, int yPixel, Sprite sprite, int flip) {
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
