package com.kdi.dungeons.graphics;

public class AnimatedSprite extends Sprite {

	private int frame = 0;
	private Sprite sprite;
	private int rate = 5;
	private int time = 0;
	private int lenght = -1;

	public AnimatedSprite(int width, int height, int lenght, SpriteSheet sheet) {
		super(width, height, sheet);
		this.lenght = lenght;
		sprite = sheet.getSprites()[0];
		if (lenght > sheet.getSprites().length) System.err.println("Error! Length of animation is too long");
	}

	public void update() {
		time++;
		if (time % rate == 0) {
			if (frame >= lenght - 1)
				frame = 0;
			else
				frame++;

			sprite = sheet.getSprites()[frame];
		}
	}

	public Sprite getSprite() {
		return sprite;
	}

	public void setFrameRate(int frames) {
		rate = frames;
	}

	public void setFrame(int index) {
		if (index > sheet.getSprites().length - 1) {
			System.err.println("Error! Index out of bound in " + this);
			return;
		}
		sprite = sheet.getSprites()[index];
	}

}
