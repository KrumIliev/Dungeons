package com.kdi.dungeons.entity.mob;

import com.kdi.dungeons.entity.Entity;
import com.kdi.dungeons.entity.projectile.Projectile;
import com.kdi.dungeons.entity.projectile.WizardProjectile;
import com.kdi.dungeons.graphics.Sprite;

public abstract class Mob extends Entity {

	protected Sprite sprite;

	protected final int DIRECTION_UP = 0;
	protected final int DIRECTION_RIGHT = 1;
	protected final int DIRECTION_LEFT = 2;
	protected final int DIRECTION_DOWN = 3;
	protected int direction = DIRECTION_UP;

	protected boolean moving = false; // Is the mob moving at that moment

	/**
	 * Moves the mob on the screen
	 * 
	 * @param xm -1 move left, 0 do nothing, 1 move right
	 * @param ym -1 move up, 0 do nothing, 1 move down
	 */
	public void move(int xm, int ym) {
		/**
		 * Separates the x and y movements so the player can slide on the walls
		 */
		if (xm != 0 && ym != 0) {
			move(xm, 0);
			move(0, ym);
			return;
		}

		/**
		 * Sets the direction
		 */
		if (xm > 0) direction = DIRECTION_RIGHT;
		if (xm < 0) direction = DIRECTION_LEFT;
		if (ym > 0) direction = DIRECTION_DOWN;
		if (ym < 0) direction = DIRECTION_UP;

		/**
		 * Checks for collision and there is no collision move
		 */
		if (!collision(xm, ym)) {
			x += xm;
			y += ym;
		}
	}

	/**
	 * Updates the mob information /location etc./
	 */
	public void update() {}

	/**
	 * Renders the mob on the screen
	 */
	public void render() {}

	/**
	 * Checks for collision
	 */
	private boolean collision(int xm, int ym) {
		boolean collision = false;
		for (int c = 0; c < 4; c++) {
			int xt = ((x + xm) + c % 2 * 12 - 7) / 16;
			int yt = ((y + ym) + c / 2 * 15 - 1) / 16;
			if (level.getTile(xt, yt).solid()) collision = true;
		}

		return collision;
	}

	protected void shoot(int x, int y, double direction) {
		Projectile projectile = new WizardProjectile(x, y, direction);
		level.add(projectile);
	}
}
