package com.gamemaker.behavior;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import com.gamemaker.model.Component;
import com.gamemaker.model.ComponentGroup;

public class NoReaction implements Reaction, KeyListener {

	private static final long serialVersionUID = 1L;

	@Override
	public void react(Component activeComponent, ComponentGroup group) {
		// Intentionally leaving this empty for use of creating a reaction object that does nothing
	}

	@Override
	public void reactNew(Component activeComponent, Component passiveComponent, JPanel panel) throws InterruptedException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
