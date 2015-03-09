package com.kdi.dungeons.level;

import com.kdi.dungeons.libs.Reference;

public class TileCoordinate {
	private int x, y;

	public TileCoordinate(int x, int y) {
		this.x = x * Reference.TILE_SIZE;
		this.y = y * Reference.TILE_SIZE;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}
