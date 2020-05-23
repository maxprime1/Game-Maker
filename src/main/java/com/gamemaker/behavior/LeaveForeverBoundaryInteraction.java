package com.gamemaker.behavior;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

import com.gamemaker.model.Component;

public class LeaveForeverBoundaryInteraction implements BoundaryInteraction, KeyListener {

	private static final long serialVersionUID = 1L;

	@Override
	public void execute(Component activeComponent, JPanel gamePanel) {
		Double leftBound = 0.0;
		Double upperBound = 0.0;
		Double rightBound = (double) gamePanel.getWidth();
		Double lowerBound = (double) gamePanel.getHeight();

		Rectangle2D activeBounds = activeComponent.getShape().getBounds2D();		
		
		Double activeX = activeBounds.getX();
		Double activeY = activeBounds.getY();
		Double activeMaxX = activeBounds.getMaxX();
		Double activeMaxY = activeBounds.getMaxY();
		
		if(activeMaxX < leftBound || activeX > rightBound || activeMaxY < upperBound || activeY > lowerBound) {
			activeComponent.setStatus("dead");
		}		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// Intentionally left blank
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// Intentionally left blank		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// Intentionally left blank		
	}

}
