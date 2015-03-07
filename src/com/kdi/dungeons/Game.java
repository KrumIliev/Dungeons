package com.kdi.dungeons;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import com.kdi.dungeons.graphics.Screen;
import com.kdi.dungeons.input.KeyInput;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	public static int width = 300;
	public static int height = width / 16 * 9;
	public static int scale = 3;

	private Thread gameThread;
	private JFrame frame;
	private KeyInput keyInput;
	private boolean running = false;
	private Screen screen;

	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

	public Game() {
		Dimension size = new Dimension(width * scale, height * scale);
		setPreferredSize(size);

		screen = new Screen(width, height);
		frame = new JFrame();

		keyInput = new KeyInput();
		frame.addKeyListener(keyInput);
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

	int x = 0, y = 0;

	public void update() {
		keyInput.update();
		if (keyInput.up) y--;
		if (keyInput.down) y++;
		if (keyInput.left) x--;
		if (keyInput.right) x++;
	}

	public void render() {
		BufferStrategy bufferStrategy = getBufferStrategy();
		if (bufferStrategy == null) {
			createBufferStrategy(3);
			return;
		}

		screen.clear();
		screen.render(x, y);

		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}

		Graphics graphics = bufferStrategy.getDrawGraphics();

		// Draw all graphics between creating the graphics and disposing
		// -------------------------------------------------------------------------->

		graphics.drawImage(image, 0, 0, getWidth(), getHeight(), null);

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
