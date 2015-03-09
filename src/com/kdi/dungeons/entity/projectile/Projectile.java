package com.kdi.dungeons.entity.projectile;

import java.util.Random;

import com.kdi.dungeons.entity.Entity;
import com.kdi.dungeons.graphics.Sprite;

public abstract class Projectile extends Entity {

	protected final int xOrigin, yOrigin;
	protected double angle;
	protected Sprite sprite;
	protected double x, y;
	protected double xNew, yNew;
	protected double distance;
	protected double speed, range, damage;

	protected final Random random = new Random();

	public Projectile(int x, int y, double direction) {
		xOrigin = x;
		yOrigin = y;
		angle = direction;
		this.x = x;
		this.y = y;
	}

	protected void move() {}

}
