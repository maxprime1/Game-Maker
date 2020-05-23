package com.gamemaker.behavior;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.gamemaker.model.Component;

public class HorizontalVerticalKeyMovements implements Movement, KeyListener{
	
	private static final long serialVersionUID = 1L;
	private int speedX;
	private int updateSpeedX;
	private int updateSpeedY;
	private int speedY;
	
	private int leftMovement;
	private int rightMovement;
	private int upMovement;
	private int downMovement;
	
	boolean isLeft;
	boolean isRight;
	boolean isUp;
	boolean isDown;

	//private int updateSpeed;
	
	public HorizontalVerticalKeyMovements(int speedX, int speedY,String control ) {
		this.speedX = speedX;
		this.speedY = speedY;
		this.updateSpeedX = speedX;
		this.updateSpeedY = speedY;
		if(control.equals("arrows"))
		{
			this.leftMovement=KeyEvent.VK_LEFT;
			this.rightMovement=KeyEvent.VK_RIGHT;
			this.upMovement=KeyEvent.VK_UP;
			this.downMovement=KeyEvent.VK_DOWN;
		}
		if(control.equals("keys"))
		{
			this.leftMovement=KeyEvent.VK_A;
			this.rightMovement=KeyEvent.VK_D;
			this.upMovement=KeyEvent.VK_W;
			this.downMovement=KeyEvent.VK_S;
		}
	}
	
	public boolean isLeft() {
		return isLeft;
	}
	
	public boolean isRight() {
		return isRight;
	}
	public boolean isUp() {
		return isUp;
	}
	public boolean isDown() {
		return isDown;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// not used
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keyEventHelper(e.getKeyCode(), true);
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keyEventHelper(e.getKeyCode(), false);
		
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
		if (isUp) {
			component.setPositionY(component.getPositionY() - speedY);
			speedY = 0;
		}
		if (isDown) {
			component.setPositionY(component.getPositionY() + speedY);
			speedY = 0;
		}
		
	}
	private void keyEventHelper(int keyCode, boolean flag) {
		if (keyCode == leftMovement || keyCode == rightMovement) {
			if (keyCode == leftMovement) {
				this.isLeft = flag;
			}
			if (keyCode == rightMovement) {
				this.isRight = flag;
			}
			speedX = updateSpeedX;
		}
		else if (keyCode == upMovement || keyCode == downMovement) {
			if (keyCode == upMovement) {
				this.isUp = flag;
			}
			if (keyCode == downMovement) {
				this.isDown = flag;
			}
			speedY = updateSpeedY;
		}
		
				
	}

	@Override
	public int getSpeedX() {
		// TODO Auto-generated method stub
		return speedX;
	}

	@Override
	public int getSpeedY() {
		// TODO Auto-generated method stub
		return speedY;
	}

	public int getUpMovement() {
		// TODO Auto-generated method stub
		return upMovement;
	}

	public int getDownMovement() {
		// TODO Auto-generated method stub
		return downMovement;
	}

	public int getLeftMovement() {
		// TODO Auto-generated method stub
		return leftMovement;
	}

	public int getRightMovement() {
		// TODO Auto-generated method stub
		return rightMovement;
	}

	

}
