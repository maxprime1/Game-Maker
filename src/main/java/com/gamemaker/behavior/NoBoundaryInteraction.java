package com.gamemaker.behavior;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import com.gamemaker.model.Component;
import com.gamemaker.model.ComponentGroup;

public class NoBoundaryInteraction implements BoundaryInteraction, KeyListener {

	private static final long serialVersionUID = 1L;

	public NoBoundaryInteraction(JPanel gamePanel, ComponentGroup group) {
		// Intentionally left blank for no interaction
	}
	
	public  NoBoundaryInteraction() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void execute(Component activeComponent, JPanel gamePanel) {
		// Intentionally left blank for no interaction
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// Intentionally left blank for no interaction

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// Intentionally left blank for no interaction

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// Intentionally left blank for no interaction

	}


}
