package com.kdi.dungeons.entity.spawner;

import com.kdi.dungeons.entity.Entity;
import com.kdi.dungeons.level.Level;

public class Spawner extends Entity {

	public enum Type {
		MOB, PARTICLE
	}

	private Type type;

	public Spawner(int x, int y, Type type, int amount, Level level) {
		setLevel(level);
		this.x = x;
		this.y = y;
		this.type = type;
	}
}
