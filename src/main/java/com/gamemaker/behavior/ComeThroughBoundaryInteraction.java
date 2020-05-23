package com.gamemaker.behavior;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

import javax.swing.JPanel;

import com.gamemaker.model.Component;

public class ComeThroughBoundaryInteraction implements BoundaryInteraction,KeyListener, Serializable 
{
	public ComeThroughBoundaryInteraction() 
	{
		System.out.println("ComeThroughBoundaryInteractionCalled");
	}

	@Override
	public void execute(Component activeComponent, JPanel gamePanel) throws InterruptedException 
	{
		//System.out.println("Execute of ComeThrough Called");
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


		if (move instanceof AutomaticMovement) 
		{
			AutomaticMovement autoMove = (AutomaticMovement) activeComponent.getMovement();
			if (activeX + autoMove.getSpeedX() <= leftBound)
			{
				//if (activeX + autoMove.getSpeedY() == 0)
				if (autoMove.getSpeedY() == 0)
				{
					activeComponent.setPositionX(rightBound);
				}
			}
			if (activeX + autoMove.getSpeedX() >= rightBound)
			{
				if (autoMove.getSpeedY() == 0)
				{
					//System.out.println("inside right bound condition");
					//autoMove.setSpeedX(0);
					activeComponent.setPositionX(0);
				}
			}
			
			if (activeY + autoMove.getSpeedY() >= lowerBound) {
				if (autoMove.getSpeedX() == 0) {
					
					activeComponent.setPositionY(upperBound);
				}
			}
			
			if (activeY + autoMove.getSpeedY() <= upperBound) {
				if (autoMove.getSpeedX() == 0) {
					
					activeComponent.setPositionY(lowerBound);
				}
			}

		}

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {

		// TODO Auto-generated method stub

	}

}
