package com.gamemaker.behavior;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.gamemaker.model.Component;

public class VerticalMovement implements Movement, KeyListener{

	private static final long serialVersionUID = 1L;
	private int speedY;
	private int upMovement;
	private int downMovement;
	private int updateSpeed;
	private String control;


	public VerticalMovement(int speedY,String control)
	{
		this.control=control;
		this.speedY=speedY;
		this.updateSpeed=speedY;

		if(control.equals("arrows"))
		{
			this.upMovement=KeyEvent.VK_UP;
			this.downMovement=KeyEvent.VK_DOWN;
		}
		if(control.equals("keys"))
		{
			this.upMovement=KeyEvent.VK_W;
			this.downMovement=KeyEvent.VK_S;
		}
	}

	public String getControl() {
		return control;
	}

	public void setControl(String control) {
		this.control = control;
	}

	public int getSpeedY() {
		return speedY;
	}

	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}

	public boolean isUp() {
		return isUp;
	}

	public void setUp(boolean isUp) {
		this.isUp = isUp;
	}

	public boolean isDown() {
		return isDown;
	}

	public void setDown(boolean isDown) {
		this.isDown = isDown;
	}
	
	public int getUpMovement() {
		return upMovement;
	}

	public int getDownMovement() {
		return downMovement;
	}

	boolean isUp;
	boolean isDown;

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
	public void move(Component component) {
		if (isUp) {
			component.setPositionY(component.getPositionY() - speedY);
			speedY = 0;
		}
		if (isDown) {
			component.setPositionY(component.getPositionY() + speedY);
			speedY = 0;
		}
	}

	@Override
	public int getSpeedX() {
		return 0;
	}
	
	private void keyEventHelper(int keyCode, boolean flag) {
		if (keyCode == upMovement) {
			this.isUp = flag;
		}
		if (keyCode == downMovement) {
			this.isDown = flag;
		}
		speedY = updateSpeed;
	}
}
