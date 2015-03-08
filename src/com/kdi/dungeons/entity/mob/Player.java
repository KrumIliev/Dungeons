package com.kdi.dungeons.entity.mob;

import com.kdi.dungeons.graphics.Screen;
import com.kdi.dungeons.graphics.Sprite;
import com.kdi.dungeons.input.KeyInput;

public class Player extends Mob {

	private KeyInput input;
	private Sprite sprite;
	private int anim = 0;
	private boolean walking = false;

	public Player(KeyInput input) {
		this.input = input;
		sprite = Sprite.playerUp; // This is just to be save it is not needed 
	}

	/**
	 * Constructor with player specific start location
	 * 
	 * @param x The x coordinate
	 * @param y The y coordinate
	 */
	public Player(int x, int y, KeyInput input) {
		this.x = x;
		this.y = y;
		this.input = input;
		sprite = Sprite.playerUp; // This is just to be save it is not needed
	}

	@Override
	public void update() {
		int xm = 0, ym = 0; // The direction to move 

		// Prevents exception with integer if someone leaves the game running for long period of time /over night or something/
		if (anim < 7500)
			anim++;
		else
			anim = 0;

		if (input.up) ym--; // Sets direction to -1
		if (input.down) ym++; // Sets direction to 1
		if (input.left) xm--; // Sets direction to -1
		if (input.right) xm++; // Sets direction to 1

		if (xm != 0 || ym != 0) {
			move(xm, ym);
			walking = true;
		} else {
			walking = false;
		}
	}

	@Override
	public void render(Screen screen) {
		// Sets the player sprite direction
		int flip = 0;
		if (direction == DIRECTION_UP) {
			sprite = Sprite.playerUp;
			if (walking) {
				if (anim % 20 > 10) {
					sprite = Sprite.playerUp1;
				} else {
					sprite = Sprite.playerUp2;
				}
			}
		}
		if (direction == DIRECTION_DOWN) {
			sprite = Sprite.playerDown;
			if (walking) {
				if (anim % 20 > 10) {
					sprite = Sprite.playerDown1;
				} else {
					sprite = Sprite.playerDown2;
				}
			}
		}
		if (direction == DIRECTION_LEFT) {
			sprite = Sprite.playerSide;
			if (walking) {
				if (anim % 20 > 10) {
					sprite = Sprite.playerSide1;
				} else {
					sprite = Sprite.playerSide2;
				}
			}
		}
		if (direction == DIRECTION_RIGHT) {
			flip = 1;
			sprite = Sprite.playerSide;
			if (walking) {
				if (anim % 20 > 10) {
					sprite = Sprite.playerSide1;
				} else {
					sprite = Sprite.playerSide2;
				}
			}
		}

		screen.renderPlayer(x - 16, y - 16, sprite, flip);
	}
}
