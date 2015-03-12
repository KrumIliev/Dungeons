package com.kdi.dungeons.entity.mob;

import com.kdi.dungeons.entity.Entity;
import com.kdi.dungeons.entity.projectile.Projectile;
import com.kdi.dungeons.entity.projectile.WizardProjectile;
import com.kdi.dungeons.graphics.Sprite;

public abstract class Mob extends Entity {

	public Mob(int x, int y, Sprite sprite) {
		super(x, y, sprite);
	}

	protected enum Direction {
		UP, DOWN, LEFT, RIGHT;
	}

	protected Direction direction = Direction.UP;
	protected boolean moving = false; // Is the mob moving at that moment

	/**
	 * Moves the mob on the screen
	 * 
	 * @param xm -1 move left, 0 do nothing, 1 move right
	 * @param ym -1 move up, 0 do nothing, 1 move down
	 */
	public void move(double xm, double ym) {
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
		if (xm > 0) direction = Direction.RIGHT;
		if (xm < 0) direction = Direction.LEFT;
		if (ym > 0) direction = Direction.DOWN;
		if (ym < 0) direction = Direction.UP;

		/**
		 * Checks for collision and there is no collision move
		 */

		for (int x = 0; x < Math.abs(xm); x++) {
			if (!collision(abs(xm), ym)) this.x += abs(xm);
		}
		for (int y = 0; y < Math.abs(ym); y++) {
			if (!collision(xm, abs(ym))) this.y += abs(ym);
		}
	}

	private int abs(double value) {
		if (value < 0) return -1;
		return 1;
	}

	/**
	 * Updates the mob information /location etc./
	 */
	public abstract void update();

	/**
	 * Checks for collision
	 */
	private boolean collision(double xm, double ym) {
		boolean collision = false;
		for (int c = 0; c < 4; c++) {
			double xt = ((x + xm) - c % 2 * 16) / 16;
			double yt = ((y + ym) - c / 2 * 16) / 16;
			int ix = (int) Math.ceil(xt); // checking the right side
			int iy = (int) Math.ceil(yt); // checking the top side
			if (c % 2 == 0) ix = (int) Math.floor(xt); // checking the left side
			if (c / 2 == 0) iy = (int) Math.floor(yt); // checking down side
			if (level.getTile(ix, iy).solid()) collision = true;
		}

		return collision;
	}

	protected void shoot(int x, int y, double direction) {
		Projectile projectile = new WizardProjectile(x, y, direction);
		level.add(projectile);
	}
}
