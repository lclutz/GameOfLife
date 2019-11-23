package de.lclutz.GameOfLife;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Input implements KeyListener, MouseListener {

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

	@Override
	public void mouseClicked(MouseEvent e) {
		GameOfLife.handleMouseClick(e.getX(), e.getY());
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

}
