package com.kdi.dungeons.entity.spawner;

import com.kdi.dungeons.entity.Entity;
import com.kdi.dungeons.level.Level;

public class Spawner extends Entity {

	public enum Type {
		MOB, PARTICLE
	}

	private Type type;

	public Spawner(int x, int y, Type type, int amount, Level level) {
		super(x, y, null);
		setLevel(level);
		this.type = type;
	}
}
