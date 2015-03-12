package com.kdi.dungeons.level;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.kdi.dungeons.entity.mob.Chaser;
import com.kdi.dungeons.entity.mob.Dummy;

public class SpawnLevel extends Level {

	public SpawnLevel(String path) {
		super(path);
	}

	@Override
	protected void loadLevel(String path) {
		try {
			BufferedImage image = ImageIO.read(SpawnLevel.class.getResource(path));
			width = image.getWidth();
			height = image.getHeight();
			tiles = new int[width * height];
			image.getRGB(0, 0, width, height, tiles, 0, width); // Loads the level image pixels in to levelPixels
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Exception! Could not load level file!");
		}
		add(new Chaser(41, 44));
		add(new Dummy(40, 45));
	}

	@Override
	protected void generateLevel() {

	}

}
