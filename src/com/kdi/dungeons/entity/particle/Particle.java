package com.kdi.dungeons.entity.particle;

import com.kdi.dungeons.entity.Entity;
import com.kdi.dungeons.graphics.Screen;
import com.kdi.dungeons.graphics.Sprite;

public class Particle extends Entity {

	private int lifeSpan;
	private int time = 0;

	// The amount of pixels to move
	protected double xx, yy, zz;
	protected double xa, ya, za;

	public Particle(int x, int y, int lifeSpan) {
		super(x, y, Sprite.particleNormal);
		this.xx = x;
		this.yy = y;
		this.lifeSpan = lifeSpan + (random.nextInt(20) - 10);

		this.xa = random.nextGaussian();
		this.ya = random.nextGaussian();
		this.zz = random.nextFloat() + 2.0;
	}

	@Override
	public void update() {
		time++;
		if (time >= 7400) time = 0; // Just to be save
		if (time > lifeSpan) remove();

		za -= 0.1; // Simulates gravity

		if (zz < 0) {
			zz = 0;
			za *= -0.55;
			xa *= 0.4;
			ya *= 0.4;
		}

		move(xx + xa, (yy + ya) + (zz + za));
	}

	private void move(double x, double y) {
		if (collision(x, y)) {
			xa *= -0.5;
			ya *= -0.5;
			za *= -0.5;
		}
		xx += xa;
		yy += ya;
		zz += za;
	}

	public boolean collision(double x, double y) {
		boolean collision = false;
		for (int c = 0; c < 4; c++) {
			double xt = (x - c % 2 * 16) / 16;
			double yt = (y - c / 2 * 16) / 16;
			int ix = (int) Math.ceil(xt); // checking the right side
			int iy = (int) Math.ceil(yt); // checking the top side
			if (c % 2 == 0) ix = (int) Math.floor(xt); // checking the left side
			if (c / 2 == 0) iy = (int) Math.floor(yt); // checking down side
			if (level.getTile(ix, iy).solid()) collision = true;
		}

		return collision;
	}

	@Override
	public void render(Screen screen) {
		screen.renderSprite((int) xx - 1, (int) yy - (int) zz - 1, sprite, true);
	}

}
