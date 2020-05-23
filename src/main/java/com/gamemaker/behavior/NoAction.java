package com.gamemaker.behavior;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.gamemaker.GamePanel;
import com.gamemaker.model.Component;

public class NoAction implements Action, KeyListener {

	private static final long serialVersionUID = 1L;

	@Override
	public void keyTyped(KeyEvent e) {
		// Intentionally left empty for no action
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// Intentionally left empty for no action		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// Intentionally left empty for no action		
	}

	@Override
	public void act(Component component, GamePanel gamePanel) {
		// Intentionally left empty for no action	
		
	}

}
