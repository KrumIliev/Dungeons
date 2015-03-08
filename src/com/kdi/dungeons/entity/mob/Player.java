package com.kdi.dungeons.entity.mob;

import com.kdi.dungeons.graphics.Screen;
import com.kdi.dungeons.graphics.Sprite;
import com.kdi.dungeons.input.KeyInput;

public class Player extends Mob {

	private KeyInput input;
	private Sprite sprite;

	public Player(KeyInput input) {
		this.input = input;
		sprite = Sprite.playerUp; // This is just to be save it is not needed 
	}

	/**
	 * Constructor with player specific start location
	 * 
	 * @param x The x coordinate
	 * @param y The y coordinate
	 */
	public Player(int x, int y, KeyInput input) {
		this.x = x;
		this.y = y;
		this.input = input;
		sprite = Sprite.playerUp; // This is just to be save it is not needed
	}

	@Override
	public void update() {
		int xm = 0, ym = 0; // The direction to move 

		if (input.up) ym--; // Sets direction to -1
		if (input.down) ym++; // Sets direction to 1
		if (input.left) xm--; // Sets direction to -1
		if (input.right) xm++; // Sets direction to 1

		if (xm != 0 || ym != 0) move(xm, ym);
	}

	@Override
	public void render(Screen screen) {
		// Sets the player sprite direction
		if (direction == DIRECTION_UP) sprite = Sprite.playerUp;
		if (direction == DIRECTION_DOWN) sprite = Sprite.playerDown;
		if (direction == DIRECTION_LEFT) sprite = Sprite.playerLeft;
		if (direction == DIRECTION_RIGHT) sprite = Sprite.playerRight;

		screen.renderPlayer(x - 16, y - 16, sprite);
	}
}
