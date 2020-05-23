package com.gamemaker.behavior;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.gamemaker.model.Component;

public class NoMovement implements Movement, KeyListener {

	private static final long serialVersionUID = 1L;

	@Override
	public void move(Component component) {
		// Intentionally left empty for no movement
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// Intentionally left empty for no movement		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// Intentionally left empty for no movement			
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// Intentionally left empty for no movement			
	}

	@Override
	public int getSpeedX() {
		return 0;
	}

	@Override
	public int getSpeedY() {
		return 0;
	}

}
