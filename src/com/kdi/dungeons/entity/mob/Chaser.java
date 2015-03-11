package com.kdi.dungeons.entity.mob;

import com.kdi.dungeons.graphics.AnimatedSprite;
import com.kdi.dungeons.graphics.Screen;
import com.kdi.dungeons.graphics.Sprite;
import com.kdi.dungeons.graphics.SpriteSheet;

public class Chaser extends Mob {

	AnimatedSprite down = new AnimatedSprite(32, 32, 3, SpriteSheet.dummyDown);
	AnimatedSprite up = new AnimatedSprite(32, 32, 3, SpriteSheet.dummyUp);
	AnimatedSprite left = new AnimatedSprite(32, 32, 3, SpriteSheet.dummyLeft);
	AnimatedSprite right = new AnimatedSprite(32, 32, 3, SpriteSheet.dummyRight);
	AnimatedSprite currentAnimaton = down;
	private boolean walking = false;

	private int time = 0;
	private int xm = 0; // The x direction to move 
	private int ym = 0; // The y direction to move 

	public Chaser(int x, int y) {
		super(x << 4, y << 4, Sprite.dummyDefault);
	}

	@Override
	public void update() {

		time++;

		if (walking)
			currentAnimaton.update();
		else
			currentAnimaton.setFrame(0);

		if (ym < 0) {
			direction = Direction.UP;
			currentAnimaton = up;
		} else if (ym > 0) {
			direction = Direction.DOWN;
			currentAnimaton = down;
		}

		if (xm < 0) {
			direction = Direction.LEFT;
			currentAnimaton = left;
		} else if (xm > 0) {
			direction = Direction.RIGHT;
			currentAnimaton = right;
		}

		if (xm != 0 || ym != 0) {
			move(xm, ym);
			walking = true;
		} else {
			walking = false;
		}

	}

	@Override
	public void render(Screen screen) {
		screen.renderMob(x, y, sprite, 0);
	}

}
