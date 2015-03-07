package com.kdi.dungeons.graphics;

public class Screen {

	private int width, height;
	public int[] pixels;

	int xTime = 0, yTime; // For testing
	int counter = 0; // For testing

	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
	}

	public void clear() {
		for (int i = 0; i < pixels.length; i++)
			pixels[i] = 0;
	}

	public void render() {
		counter++;
		if (counter % 100 == 0) xTime++;
		if (counter % 100 == 0) yTime++;

		for (int y = 0; y < height; y++) {
			if (yTime < 0 || yTime >= height) break;
			for (int x = 0; x < width; x++) {
				if (xTime < 0 || xTime >= width) break;
				pixels[xTime + yTime * width] = 0xff00ff;
			}
		}
	}
}
