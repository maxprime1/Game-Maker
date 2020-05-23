package com.gamemaker.behavior;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.gamemaker.model.Component;

public class AutomaticMovement implements Movement, KeyListener{
	
	private static final long serialVersionUID = 1L;
	private int speedX;
	private int speedY;
	
	public AutomaticMovement(int speedX,int speedY) {
		this.speedX = speedX;
		this.speedY = speedY;
	}
	
	public int getSpeedX() {
		return speedX;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public int getSpeedY() {
		return speedY;
	}

	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}

	@Override
	public void move(Component component) {
		component.setPositionX(component.getPositionX() + speedX);
		component.setPositionY(component.getPositionY() + speedY);
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// Intentionally left blank	
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// Intentionally left blank		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// Intentionally left blank		
	}
}
