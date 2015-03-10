package com.kdi.dungeons.graphics;

public class AnimatedSprite extends Sprite {

	private int frame = 0;
	private Sprite sprite;
	private int rate = 5;
	private int lenght = -1;

	protected AnimatedSprite(int width, int height, int lenght, SpriteSheet sheet) {
		super(width, height, sheet);
		this.lenght = lenght;
	}

	public void update() {
		if (frame > lenght)
			frame = 0;
		else
			frame++;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public void setFrameRate(int frames) {
		rate = frames;
	}

}
