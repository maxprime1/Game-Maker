package com.gamemaker.behavior;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

import javax.swing.JPanel;

import com.gamemaker.model.Component;

public class MoveDownBoundaryInteraction implements BoundaryInteraction, KeyListener, Serializable {
	
	private static final long serialVersionUID = 1L;
	private boolean moveLeft;
	private boolean moveRight;
	private boolean moveUp;
	private boolean moveDown;
	private int leftMovement;
	private int rightMovement;
	private int upMovement;
	private int downMovement;

	public MoveDownBoundaryInteraction() {
		leftMovement = 0;
		rightMovement = 0;
		upMovement = 0;
		downMovement = 0;
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
		// Intentionally left blank since no need to implement it
	}

	private void keyEventHelper(int keyCode, boolean flag) {
		if (keyCode == leftMovement) {
			this.moveLeft = flag;
		}
		if (keyCode == rightMovement) {
			this.moveRight = flag;
		}
		if (keyCode == upMovement) {
			this.moveUp = flag;
		}
		if (keyCode == downMovement) {
			this.moveDown = flag;
		}
	}

	@Override
	public void execute(Component activeComponent, JPanel gamePanel) throws InterruptedException {
		// TODO Auto-generated method stub
		
	
		Double leftBound = 0.0;
		Double upperBound = 0.0;
		Double rightBound = (double) gamePanel.getWidth();
		Double lowerBound = (double) gamePanel.getHeight();

		Rectangle2D activeBounds = activeComponent.getShape().getBounds2D();
		Movement move = activeComponent.getMovement();

		Double activeX = activeBounds.getX();
		Double activeY = activeBounds.getY();
		Double activeMaxX = activeBounds.getMaxX();
		Double activeMaxY = activeBounds.getMaxY();
		Double activeWidth = activeBounds.getWidth();
		Double activeHeight = activeBounds.getHeight();

		PlaySound playSound = new PlaySound("bounceBoundary.wav");
		AutomaticMovement autoMove = (AutomaticMovement) activeComponent.getMovement();
		
		if(activeX + autoMove.getSpeedX() <= leftBound || activeMaxX + autoMove.getSpeedX() >= rightBound) {
			activeComponent.setPositionY(activeComponent.getPositionY()+30);
			autoMove.setSpeedX(autoMove.getSpeedX() * -1);
			
			playSound.play();
		}

		if(activeY + autoMove.getSpeedY() <= upperBound || activeMaxY + autoMove.getSpeedY() >= lowerBound) {
			autoMove.setSpeedY(autoMove.getSpeedY() * 0);
			playSound.play();
		}
		
	}

}
