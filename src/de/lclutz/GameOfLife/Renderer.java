package de.lclutz.GameOfLife;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.VolatileImage;

public class Renderer {

	private static Frame frame;
	private static Canvas canvas;

	private static int canvasWidth = 0;
	private static int canvasHeight = 0;
	private static int factor = 1;

	private static final int GAME_WIDTH = 400;
	private static final int GAME_HEIGHT = 250;

	public static void init() {
		getBestSize();

		frame = new Frame();
		canvas = new Canvas();

		canvas.setPreferredSize(new Dimension(canvasWidth, canvasHeight));

		frame.add(canvas);
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);

		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				GameOfLife.quit();
			}
		});

		canvas.addKeyListener(new Input());
		canvas.requestFocus();
		canvas.addMouseListener(new Input());
		frame.setVisible(true);
		render();
	}

	private static void getBestSize() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();

		int widthRatio = (int) Math.floor(screenSize.getWidth() / GAME_WIDTH);
		int heightRatio = (int) Math.floor(screenSize.getHeight() / GAME_HEIGHT);

		factor = Math.min(widthRatio, heightRatio);

		canvasWidth = factor * GAME_WIDTH;
		canvasHeight = factor * GAME_HEIGHT;
	}

	public static int[] convertToGameCoordinates(int[] windowCoordinates) {
		return new int[] { (windowCoordinates[0] / factor) / 10, (windowCoordinates[1] / factor) / 10 };
	}

	public static void render() {

		GraphicsConfiguration gc = canvas.getGraphicsConfiguration();
		VolatileImage vImage = gc.createCompatibleVolatileImage(GAME_WIDTH, GAME_HEIGHT);

		if (vImage.validate(gc) == VolatileImage.IMAGE_INCOMPATIBLE) {
			vImage = gc.createCompatibleVolatileImage(GAME_WIDTH, GAME_HEIGHT);
		}

		Graphics g = vImage.getGraphics();

		g.setColor(Color.BLACK);
		g.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);

		// Draw instructions
		g.setColor(Color.LIGHT_GRAY);
		g.drawString("Game of Life", 260, 30);
		g.drawString("[S] to step", 260, 50);
		g.drawString("[C] to clear", 260, 65);
		g.drawString("[R] to set random values", 260, 80);
		g.drawString("Steps: " + GameOfLife.steps, 260, 100);

		// Draw grid
		g.setColor(Color.DARK_GRAY);
		for (int i = 0; i <= 25; i++) {
			g.drawLine(0, i * 10, 250, i * 10);
			g.drawLine(i * 10, 0, i * 10, 250);
		}

		// Draw current game state
		int rowNumber = 0;
		int fieldNumber = 0;
		for (boolean[] row : GameOfLife.grid) {
			for (boolean field : row) {
				if (field)
					g.setColor(Color.GREEN);
				else
					g.setColor(Color.BLACK);
				g.fillRect(fieldNumber * 10 + 1, rowNumber * 10 + 1, 9, 9);
				fieldNumber++;
			}
			rowNumber++;
			fieldNumber = 0;
		}

		g.dispose();

		// Draw to the canvas
		g = canvas.getGraphics();
		g.drawImage(vImage, 0, 0, canvasWidth, canvasHeight, null);
		g.dispose();
	}

}
