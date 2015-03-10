package com.kdi.dungeons.entity;

import java.util.Random;

import com.kdi.dungeons.graphics.Screen;
import com.kdi.dungeons.level.Level;

public abstract class Entity {

	public int x, y; // Position
	private boolean removed = false; // If the entity is removed from the screen 

	protected Level level; // The level the entity resides in
	protected Random random = new Random(); // TODO Used for AI 

	public void update() {}

	/**
	 * Renders the entity to the screen
	 * 
	 * @param screen The game {@link Screen}
	 */
	public void render(Screen screen) {}

	/**
	 * Removes the entity from the level
	 */
	public void remove() {
		removed = true;
	}

	/**
	 * @return Is the entity removed from the level
	 */
	public boolean isRemoved() {
		return removed;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

}
