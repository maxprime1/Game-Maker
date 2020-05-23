package com.gamemaker.behavior;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

import javax.swing.JPanel;

import com.gamemaker.model.Component;

public class BounceBoundaryInteraction implements BoundaryInteraction, KeyListener, Serializable {

	private static final long serialVersionUID = 1L;
	private boolean moveLeft;
	private boolean moveRight;
	private boolean moveUp;
	private boolean moveDown;
	private int leftMovement;
	private int rightMovement;
	private int upMovement;
	private int downMovement;

	public BounceBoundaryInteraction() {
		leftMovement = 0;
		rightMovement = 0;
		upMovement = 0;
		downMovement = 0;
	}

	@Override
	public void execute(Component activeComponent, JPanel gamePanel) throws InterruptedException {
		//System.out.println("Bounce Execute Method Called");
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

		if (move instanceof AutomaticMovement) {
			AutomaticMovement autoMove = (AutomaticMovement) activeComponent.getMovement();
			if(activeX + autoMove.getSpeedX() <= leftBound || activeMaxX + autoMove.getSpeedX() >= rightBound) {
				autoMove.setSpeedX(autoMove.getSpeedX() * -1);
				playSound.play();
			}

			if(activeY + autoMove.getSpeedY() <= upperBound || activeMaxY + autoMove.getSpeedY() >= lowerBound) {
				autoMove.setSpeedY(autoMove.getSpeedY() * -1);
				playSound.play();
			}
		} else if (move instanceof HorizontalMovement) {
			HorizontalMovement horiMove = (HorizontalMovement) activeComponent.getMovement();
			leftMovement = horiMove.getLeftMovement();
			rightMovement = horiMove.getRightMovement();

			if (activeX + horiMove.getSpeedX() <= leftBound && moveLeft) {
				// Stop the object instead of bouncing it back
				activeComponent.setPositionX(leftBound);
				playSound.play();
			}
			if (activeMaxX + horiMove.getSpeedX() >= rightBound && moveRight) {
				// Stop the object instead of bouncing it back
				activeComponent.setPositionX(rightBound - activeWidth);
				playSound.play();
			}

		} else if (move instanceof VerticalMovement) {
			VerticalMovement verMove = (VerticalMovement) activeComponent.getMovement();
			upMovement = verMove.getUpMovement();
			downMovement = verMove.getDownMovement();

			if (activeY + verMove.getSpeedY() <= upperBound && moveUp) {
				// Stop the object instead of bouncing it back
				activeComponent.setPositionY(upperBound);
				playSound.play();
			}
			if (activeMaxY + verMove.getSpeedY() >= lowerBound && moveDown) {
				// Stop the object instead of bouncing it back
				activeComponent.setPositionY(lowerBound - activeHeight);
				playSound.play();
			}
		}
		else if(move instanceof HorizontalVerticalKeyMovements) {
			
			HorizontalVerticalKeyMovements horiVerMove = (HorizontalVerticalKeyMovements) activeComponent.getMovement();
			upMovement = horiVerMove.getUpMovement();
			downMovement = horiVerMove.getDownMovement();
			leftMovement = horiVerMove.getLeftMovement();
			rightMovement = horiVerMove.getRightMovement();
			
			
			
			if (activeX + horiVerMove.getSpeedX() <= leftBound && moveLeft) {
				
				// Stop the object at the rightBoundary of the passive component
				activeComponent.setPositionX(leftBound);
			}
			if (activeMaxX + horiVerMove.getSpeedX() >= rightBound && moveRight) {
				// Stop the object instead of bouncing it back
				
				activeComponent.setPositionX(rightBound - activeWidth);
			}
			if (activeY + horiVerMove.getSpeedY() <= upperBound && moveUp) {
				// Stop the object instead of bouncing it back
				
				activeComponent.setPositionY(upperBound);
			}
			if (activeMaxY + horiVerMove.getSpeedY() >= lowerBound && moveDown) {
				// Stop the object instead of bouncing it back
				
				activeComponent.setPositionY(lowerBound - activeHeight);
			}
		}
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
}
