package com.gamemaker.behavior;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.gamemaker.model.Component;

public class HorizontalMovement implements Movement, KeyListener {

	private static final long serialVersionUID = 1L;
	private int speedX;
	private int updateSpeed;
	private boolean isLeft;
	private boolean isRight;
	private String control;
	private int leftMovement;
	private int rightMovement;

	public HorizontalMovement(int speedX, String control) {
		this.speedX = speedX;
		this.control = control;
		this.updateSpeed = speedX;
		if(control.equals("arrows"))
		{
			this.leftMovement=KeyEvent.VK_LEFT;
			this.rightMovement=KeyEvent.VK_RIGHT;
		}
		if(control.equals("keys"))
		{
			this.leftMovement=KeyEvent.VK_A;
			this.rightMovement=KeyEvent.VK_D;
		}
	}

	public String getControl() {
		return control;
	}

	public void setControl(String control) {
		this.control = control;
	}

	@Override
	public void move(Component component) {
		if (isLeft) {
			component.setPositionX(component.getPositionX() - speedX);
			speedX = 0;
		}
		if (isRight) {
			component.setPositionX(component.getPositionX() + speedX);
			speedX = 0;
		}
	}

	public int getSpeedX() {
		return speedX;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public boolean isLeft() {
		return isLeft;
	}

	public void setLeft(boolean isLeft) {
		this.isLeft = isLeft;
	}

	public boolean isRight() {
		return isRight;
	}

	public void setRight(boolean isRight) {
		this.isRight = isRight;
	}
	
	public int getLeftMovement() {
		return leftMovement;
	}

	public int getRightMovement() {
		return rightMovement;
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		keyEventHelper(arg0.getKeyCode(), true);
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		keyEventHelper(arg0.getKeyCode(), false);
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// Not used
	}

	@Override
	public int getSpeedY() {
		return 0;
	}
	
	private void keyEventHelper(int keyCode, boolean flag) {
		if (keyCode == leftMovement) {
			this.isLeft = flag;
		}
		if (keyCode == rightMovement) {
			this.isRight = flag;
		}
		speedX = updateSpeed;
	}

}
