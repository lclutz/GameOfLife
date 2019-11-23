package de.lclutz.GameOfLife;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Input implements KeyListener {

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_S) {
			GameOfLife.update();
		}
		if (e.getKeyCode() == KeyEvent.VK_C) {
			GameOfLife.clear();
		}
		if (e.getKeyCode() == KeyEvent.VK_R) {
			GameOfLife.randomize();
		}
	}

}
