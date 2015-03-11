package com.kdi.dungeons.entity;

import java.util.Random;

import com.kdi.dungeons.graphics.Screen;
import com.kdi.dungeons.graphics.Sprite;
import com.kdi.dungeons.level.Level;

public abstract class Entity {

	protected int x, y; // Position
	private boolean removed = false; // If the entity is removed from the screen 
	protected Sprite sprite;

	protected Level level; // The level the entity resides in
	protected Random random = new Random(); // TODO Used for AI 

	public Entity(int x, int y, Sprite sprite) {
		this.x = x;
		this.y = y;
		this.sprite = sprite;
	}

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

	public Sprite getSprite() {
		return sprite;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}
