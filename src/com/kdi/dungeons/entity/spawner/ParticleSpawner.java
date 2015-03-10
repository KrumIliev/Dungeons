package com.kdi.dungeons.entity.spawner;

import com.kdi.dungeons.entity.particle.Particle;
import com.kdi.dungeons.level.Level;

public class ParticleSpawner extends Spawner {

	public ParticleSpawner(int x, int y, int lifeSpan, int amount, Level level) {
		super(x, y, Type.PARTICLE, amount, level);

		for (int i = 0; i < amount; i++) {
			level.add(new Particle(x, y, lifeSpan));
		}
	}
}
