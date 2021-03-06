package com.kdi.dungeons;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import com.kdi.dungeons.entity.mob.Player;
import com.kdi.dungeons.graphics.Screen;
import com.kdi.dungeons.input.KeyInput;
import com.kdi.dungeons.input.Mouse;
import com.kdi.dungeons.level.Level;
import com.kdi.dungeons.level.SpawnLevel;
import com.kdi.dungeons.level.TileCoordinate;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	private static int width = 256;
	private static int height = width / 4 * 3;
	private static int scale = 2;

	private Thread gameThread;
	private JFrame frame;
	private KeyInput keyInput;
	private Level level;
	private Player player;
	private boolean running = false;
	private Screen screen;

	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

	public Game() {
		Dimension size = new Dimension(width * scale, height * scale);
		setPreferredSize(size);

		screen = new Screen(width, height);
		frame = new JFrame();
		level = new SpawnLevel("/textures/level.png");
		keyInput = new KeyInput();
		TileCoordinate playerSpawn = new TileCoordinate(38, 47);
		player = new Player(playerSpawn.getX(), playerSpawn.getY(), keyInput);
		player.setLevel(level);
		level.add(player);

		Mouse mouse = new Mouse();
		addKeyListener(keyInput);
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
	}

	public static int getWindowWidth() {
		return width * scale;
	}

	public static int getWindowHeight() {
		return height * scale;
	}

	public synchronized void start() {
		running = true;
		gameThread = new Thread(this);
		gameThread.start();
	}

	public synchronized void stop() {
		running = false;
		try {
			gameThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double nanoSeconds = 1000000000.0 / 60.0;
		double delta = 0;
		int frames = 0;
		int updates = 0;

		requestFocus();
		while (running) {

			long currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / nanoSeconds;
			lastTime = currentTime;

			while (delta >= 1) {
				update();
				updates++;
				delta--;
			}

			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println(updates + " upd/sec, " + frames + " FPS");
				updates = 0;
				frames = 0;
			}
		}
		stop();
	}

	public void update() {
		keyInput.update();
		level.update();
	}

	public void render() {
		BufferStrategy bufferStrategy = getBufferStrategy();
		if (bufferStrategy == null) {
			createBufferStrategy(3);
			return;
		}

		screen.clear();

		int xScroll = player.getX() - screen.width / 2;
		int yScroll = player.getY() - screen.height / 2;
		level.render(xScroll, yScroll, screen);

		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}

		Graphics graphics = bufferStrategy.getDrawGraphics();

		// Draw all graphics between creating the graphics and disposing
		// -------------------------------------------------------------------------->

		graphics.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		graphics.setColor(Color.WHITE);
		graphics.setFont(new Font("Verdana", 0, 50));
		graphics.fillRect(Mouse.getX() - 10, Mouse.getY() - 10, 20, 20);
		//graphics.drawString("Button: " + Mouse.getB(), 80, 80);

		// <--------------------------------------------------------------------------

		graphics.dispose();
		bufferStrategy.show();

	}

	public static void main(String[] args) {
		Game game = new Game();
		game.frame.setResizable(false);
		game.frame.setTitle("Dungeons");
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);

		game.start();
	}

}
