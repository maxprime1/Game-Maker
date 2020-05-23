package com.gamemaker.behavior;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.swing.JPanel;

import com.gamemaker.model.Component;
import com.gamemaker.model.ComponentGroup;

public class StickReaction implements Reaction, KeyListener {
	
	private static final long serialVersionUID = 1L;
	private boolean moveLeft;
	private boolean moveRight;
	private boolean moveUp;
	private boolean moveDown;
	private int leftMovement;
	private int rightMovement;
	private int upMovement;
	private int downMovement;
	
	@Override
	public void react(Component activeComponent, ComponentGroup group) throws InterruptedException {
		
		//System.out.println("React Method oF component called");

		Iterator<Entry<String, Component>> componentIterator = group.getComponentMap().entrySet().iterator();
		PlaySound playSound = new PlaySound("bounce.wav");
	
		while(componentIterator.hasNext()) {
			Component passiveComponent = componentIterator.next().getValue();
			
			// Checking if try to apply this reaction for same component in the iterator
			if (activeComponent == passiveComponent)
				continue; // skip the component itself

			Rectangle2D activeBounds = activeComponent.getShape().getBounds2D();
			Rectangle2D passiveBounds = passiveComponent.getShape().getBounds2D();
			Rectangle2D insect = passiveBounds.createIntersection(activeBounds);
			
			Double activeX = activeBounds.getX();
			Double activeY = activeBounds.getY();
			Double activeMaxX = activeBounds.getMaxX();
			Double activeMaxY = activeBounds.getMaxY();
			Double activeWidth = activeBounds.getWidth();
			Double activeHeight = activeBounds.getHeight();
			
			Double passiveX = passiveBounds.getX();
			Double passiveY = passiveBounds.getY();
			Double passiveMaxX = passiveBounds.getMaxX();
			Double passiveMaxY = passiveBounds.getMaxY();
			
			
			if (activeBounds.intersects(passiveBounds))
			{
				//System.out.println("Active and Passive intersection");
				activeComponent.setStatus("sticked");
				passiveComponent.setPositionX(activeComponent.getPositionX());
				//passiveComponent.setPositionY(activeComponent.getPositionY());
				//VerticalMovement autoMove = (VerticalMovement)passiveComponent.getMovement();
				//AutomaticMovement activeAutoMove = (AutomaticMovement)activeComponent.getMovement();		
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
		// Not used
		
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
	public void reactNew(Component activeComponent, Component passiveComponent, JPanel panel) throws InterruptedException {
		// TODO Auto-generated method stub
		Rectangle2D activeBounds = activeComponent.getShape().getBounds2D();
		Rectangle2D passiveBounds = passiveComponent.getShape().getBounds2D();
		Rectangle2D insect = passiveBounds.createIntersection(activeBounds);
		
		Double activeX = activeBounds.getX();
		Double activeY = activeBounds.getY();
		Double activeMaxX = activeBounds.getMaxX();
		Double activeMaxY = activeBounds.getMaxY();
		Double activeWidth = activeBounds.getWidth();
		Double activeHeight = activeBounds.getHeight();
		
		Double passiveX = passiveBounds.getX();
		Double passiveY = passiveBounds.getY();
		Double passiveMaxX = passiveBounds.getMaxX();
		Double passiveMaxY = passiveBounds.getMaxY();
		
		
		if (activeBounds.intersects(passiveBounds))
		{
			//System.out.println("Active and Passive intersection");
			activeComponent.setStatus("sticked");
			passiveComponent.setPositionX(activeComponent.getPositionX());
			//passiveComponent.setPositionY(activeComponent.getPositionY());
			//VerticalMovement autoMove = (VerticalMovement)passiveComponent.getMovement();
			//AutomaticMovement activeAutoMove = (AutomaticMovement)activeComponent.getMovement();		
		}
		
	}
}
