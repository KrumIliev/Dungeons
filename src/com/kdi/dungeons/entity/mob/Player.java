package com.kdi.dungeons.entity.mob;

import java.awt.event.MouseEvent;

import com.kdi.dungeons.Game;
import com.kdi.dungeons.entity.projectile.Projectile;
import com.kdi.dungeons.entity.projectile.WizardProjectile;
import com.kdi.dungeons.graphics.AnimatedSprite;
import com.kdi.dungeons.graphics.Screen;
import com.kdi.dungeons.graphics.Sprite;
import com.kdi.dungeons.graphics.SpriteSheet;
import com.kdi.dungeons.input.KeyInput;
import com.kdi.dungeons.input.Mouse;

public class Player extends Mob {

	private KeyInput input;
	private Sprite sprite;
	private int anim = 0;
	private boolean walking = false;
	AnimatedSprite down = new AnimatedSprite(32, 32, 3, SpriteSheet.playerDown);
	AnimatedSprite up = new AnimatedSprite(32, 32, 3, SpriteSheet.playerUp);
	AnimatedSprite left = new AnimatedSprite(32, 32, 3, SpriteSheet.playerLeft);
	AnimatedSprite right = new AnimatedSprite(32, 32, 3, SpriteSheet.playerRight);

	AnimatedSprite currentAnimaton = down;

	private int fireRate = 0;

	/**
	 * Constructor with player specific start location
	 * 
	 * @param x The x coordinate
	 * @param y The y coordinate
	 */
	public Player(int x, int y, KeyInput input) {
		super(x, y, Sprite.playerDefault);
		this.input = input;
		fireRate = WizardProjectile.FIRE_RATE;
	}

	@Override
	public void update() {
		if (walking)
			currentAnimaton.update();
		else
			currentAnimaton.setFrame(0);

		if (fireRate > 0) fireRate--;

		int xm = 0, ym = 0; // The direction to move 

		// Prevents exception with integer if someone leaves the game running for long period of time /over night or something/
		if (anim < 7500)
			anim++;
		else
			anim = 0;

		if (input.up) {
			ym--; // Sets direction to -1
			currentAnimaton = up;
		} else if (input.down) {
			ym++; // Sets direction to 1
			currentAnimaton = down;
		}

		if (input.left) {
			xm--; // Sets direction to -1
			currentAnimaton = left;
		} else if (input.right) {
			xm++; // Sets direction to 1
			currentAnimaton = right;
		}

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
		sprite = currentAnimaton.getSprite();
		screen.renderMob(x - 16, y - 16, sprite, 0);
	}
}
