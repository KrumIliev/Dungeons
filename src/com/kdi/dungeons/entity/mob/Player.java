package com.kdi.dungeons.entity.mob;

import java.awt.event.MouseEvent;

import com.kdi.dungeons.Game;
import com.kdi.dungeons.entity.projectile.Projectile;
import com.kdi.dungeons.entity.projectile.WizardProjectile;
import com.kdi.dungeons.graphics.Screen;
import com.kdi.dungeons.graphics.Sprite;
import com.kdi.dungeons.input.KeyInput;
import com.kdi.dungeons.input.Mouse;

public class Player extends Mob {

	private KeyInput input;
	private Sprite sprite;
	private int anim = 0;
	private boolean walking = false;

	private int fireRate = 0;

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
		fireRate = WizardProjectile.FIRE_RATE;
	}

	@Override
	public void update() {
		if (fireRate > 0) fireRate--;

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

		clear();
		shooting();
	}

	private void clear() {
		for (int i = 0; i < level.getProjectiles().size(); i++) {
			Projectile p = level.getProjectiles().get(i);
			if (p.isRemoved()) level.getProjectiles().remove(i);
		}
	}

	private void shooting() {
		if (Mouse.getB() == MouseEvent.BUTTON1 && fireRate <= 0) {
			// Calculating shooting direction 
			double dx = Mouse.getX() - Game.getWindowWidth() / 2;
			double dy = Mouse.getY() - Game.getWindowHeight() / 2;
			double dir = Math.atan2(dy, dx);

			shoot(x, y, dir);
			fireRate = WizardProjectile.FIRE_RATE;
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
