package com.kdi.dungeons.entity.projectile;

import com.kdi.dungeons.entity.spawner.ParticleSpawner;
import com.kdi.dungeons.graphics.Screen;
import com.kdi.dungeons.graphics.Sprite;

public class WizardProjectile extends Projectile {

	public static final int FIRE_RATE = 10; // Higher is slower

	public WizardProjectile(int x, int y, double direction) {
		super(x, y, direction);
		range = random.nextInt(100) + 150;
		speed = 2;
		damage = 20;
		sprite = Sprite.projectileWizard;

		// Calculates the x and y steps so the projectile can fly in the desired direction 
		xNew = speed * Math.cos(angle);
		yNew = speed * Math.sin(angle);
	}

	@Override
	public void update() {
		if (level.tileCollision((int) (x + xNew), (int) (y + yNew), 7, 10, 10)) {
			level.add(new ParticleSpawner((int) x + 10, (int) y + 10, 50, 50, level));
			remove();
		}
		move();
	}

	@Override
	protected void move() {
		x += xNew;
		y += yNew;

		if (distance() > range) remove();
	}

	/**
	 * Calculates the distance between player and the projectile
	 */
	private double distance() {
		double distance = 0;
		distance = Math.sqrt(Math.abs((xOrigin - x) * (xOrigin - x) + (yOrigin - y) * (yOrigin - y)));
		return distance;
	}

	@Override
	public void render(Screen screen) {
		screen.renderTile((int) x, (int) y, sprite);
	}

}
