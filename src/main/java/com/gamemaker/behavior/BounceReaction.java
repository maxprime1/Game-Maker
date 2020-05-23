package com.gamemaker.behavior;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.swing.JPanel;

import com.gamemaker.model.Component;
import com.gamemaker.model.ComponentGroup;

public class BounceReaction implements Reaction, KeyListener {
	
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
			
			if (activeBounds.intersects(passiveBounds)){ 
				// Play sound effect
				
				playSound.play();
				//setting the status to dead to check winning condition for bounce
				activeComponent.setStatus("bounced");

				/* Source for below piece of code: 
				 https://stackoverflow.com/questions/13261767/java-ball-object-doesnt-bounce-off-of-drawn-rectangles-like-its-supposed-to
				*/
				// Flags...
				boolean vertical = false;
				boolean horizontal = false;
				boolean isLeft = false;
				boolean isTop = false;
				// Left side...
				if (insect.getX() == passiveX) {
					horizontal = true;
					isLeft = true;
					// Right side
				} else if (insect.getX() + insect.getWidth() == passiveX + passiveBounds.getWidth()) {
					horizontal = true;
				}
				// Top
				if (insect.getY() == passiveY) {
					vertical = true;
					isTop = true;
					// Bottom
				} else if (insect.getY() + insect.getHeight() == passiveY + passiveBounds.getHeight()) {
					vertical = true;
				}

				// Technically, we can really only collide with a single edge...more or less
				if (horizontal && vertical) {
					// Basically, we try and give precedence to the longer edge...
					if (insect.getWidth() > insect.getHeight()) {
						horizontal = false;
					} else {
						vertical = false;
					}
				}
				
				Movement move = activeComponent.getMovement();
				if (move instanceof AutomaticMovement) {
					AutomaticMovement autoMove = (AutomaticMovement)activeComponent.getMovement();
					// We collided with a horizontal side...
					if (horizontal) {
						autoMove.setSpeedX(-autoMove.getSpeedX());
						// Move the ball to the appropriate edge so we don't get caught...
						if (isLeft) {
							activeComponent.setPositionX(passiveX - activeBounds.getWidth());
						} else {
							activeComponent.setPositionX(passiveX + passiveBounds.getWidth());
						}
						// We collided with a vertical side...
					} else if (vertical) {
						autoMove.setSpeedY(-autoMove.getSpeedY());
						// Move the ball to the appropriate edge so we don't get caught...
						if (isTop) {
							activeComponent.setPositionY(passiveY - activeBounds.getHeight());
						} else {
							activeComponent.setPositionY(passiveY + passiveBounds.getHeight());
						}
					}
				} else if (move instanceof HorizontalMovement) {
					HorizontalMovement horiMove = (HorizontalMovement) activeComponent.getMovement();
					leftMovement = horiMove.getLeftMovement();
					rightMovement = horiMove.getRightMovement();
					
					if (activeX + horiMove.getSpeedX() <= passiveMaxX && moveLeft) {
						// Stop the object at the rightBoundary of the passive component
						activeComponent.setPositionX(passiveMaxX);
					}
					if (activeMaxX + horiMove.getSpeedX() >= passiveX && moveRight) {
						// Stop the object instead of bouncing it back
						activeComponent.setPositionX(passiveX - activeWidth);
					}
				} else if (move instanceof VerticalMovement) {
					VerticalMovement verMove = (VerticalMovement) activeComponent.getMovement();
					upMovement = verMove.getUpMovement();
					downMovement = verMove.getDownMovement();
					if (activeY + verMove.getSpeedY() <= passiveMaxY && moveUp) {
						// Stop the object instead of bouncing it back
						activeComponent.setPositionY(passiveMaxY);
					}
					if (activeMaxY + verMove.getSpeedY() >= passiveY && moveDown) {
						// Stop the object instead of bouncing it back
						activeComponent.setPositionY(passiveY - activeHeight);
					}
				}
			}
		}
	}
	
	

	@Override
	public void reactNew(Component activeComponent, Component passiveComponent, JPanel panel) throws InterruptedException {

		//Iterator<Entry<String, Component>> componentIterator = group.getComponentMap().entrySet().iterator();
		PlaySound playSound = new PlaySound("bounce.wav");
	
		//while(componentIterator.hasNext()) {
			//Component passiveComponent = componentIterator.next().getValue();
			
			// Checking if try to apply this reaction for same component in the iterator
			//if (activeComponent == passiveComponent)
			//	continue; // skip the component itself
		

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
			
			if (activeBounds.intersects(passiveBounds)){ 
				// Play sound effect
				//System.out.println("bounce");
				playSound.play();
				//setting the status to dead to check winning condition for bounce
				activeComponent.setStatus("bounced");

				/* Source for below piece of code: 
				 https://stackoverflow.com/questions/13261767/java-ball-object-doesnt-bounce-off-of-drawn-rectangles-like-its-supposed-to
				*/
				// Flags...
				boolean vertical = false;
				boolean horizontal = false;
				boolean isLeft = false;
				boolean isTop = false;
				// Left side...
				if (insect.getX() == passiveX) {
					horizontal = true;
					isLeft = true;
					// Right side
				} else if (insect.getX() + insect.getWidth() == passiveX + passiveBounds.getWidth()) {
					horizontal = true;
				}
				// Top
				if (insect.getY() == passiveY) {
					vertical = true;
					isTop = true;
					// Bottom
				} else if (insect.getY() + insect.getHeight() == passiveY + passiveBounds.getHeight()) {
					vertical = true;
				}

				// Technically, we can really only collide with a single edge...more or less
				if (horizontal && vertical) {
					// Basically, we try and give precedence to the longer edge...
					if (insect.getWidth() > insect.getHeight()) {
						horizontal = false;
					} else {
						vertical = false;
					}
				}
				
				Movement move = activeComponent.getMovement();
				if (move instanceof AutomaticMovement) {
					AutomaticMovement autoMove = (AutomaticMovement)activeComponent.getMovement();
					// We collided with a horizontal side...
					if (horizontal) {
						autoMove.setSpeedX(-autoMove.getSpeedX());
						// Move the ball to the appropriate edge so we don't get caught...
						if (isLeft) {
							activeComponent.setPositionX(passiveX - activeBounds.getWidth());
						} else {
							activeComponent.setPositionX(passiveX + passiveBounds.getWidth());
						}
						// We collided with a vertical side...
					} else if (vertical) {
						autoMove.setSpeedY(-autoMove.getSpeedY());
						// Move the ball to the appropriate edge so we don't get caught...
						if (isTop) {
							activeComponent.setPositionY(passiveY - activeBounds.getHeight());
						} else {
							activeComponent.setPositionY(passiveY + passiveBounds.getHeight());
						}
					}
				} else if (move instanceof HorizontalMovement) {
					HorizontalMovement horiMove = (HorizontalMovement) activeComponent.getMovement();
					leftMovement = horiMove.getLeftMovement();
					rightMovement = horiMove.getRightMovement();
					
					if (activeX + horiMove.getSpeedX() <= passiveMaxX && moveLeft) {
						// Stop the object at the rightBoundary of the passive component
						activeComponent.setPositionX(passiveMaxX);
					}
					if (activeMaxX + horiMove.getSpeedX() >= passiveX && moveRight) {
						// Stop the object instead of bouncing it back
						activeComponent.setPositionX(passiveX - activeWidth);
					}
				} else if (move instanceof VerticalMovement) {
					VerticalMovement verMove = (VerticalMovement) activeComponent.getMovement();
					upMovement = verMove.getUpMovement();
					downMovement = verMove.getDownMovement();
					if (activeY + verMove.getSpeedY() <= passiveMaxY && moveUp) {
						// Stop the object instead of bouncing it back
						activeComponent.setPositionY(passiveMaxY);
					}
					if (activeMaxY + verMove.getSpeedY() >= passiveY && moveDown) {
						// Stop the object instead of bouncing it back
						activeComponent.setPositionY(passiveY - activeHeight);
					}
				}
				else if(move instanceof HorizontalVerticalKeyMovements) {
					
					HorizontalVerticalKeyMovements horiVerMove = (HorizontalVerticalKeyMovements) activeComponent.getMovement();
					upMovement = horiVerMove.getUpMovement();
					downMovement = horiVerMove.getDownMovement();
					leftMovement = horiVerMove.getLeftMovement();
					rightMovement = horiVerMove.getRightMovement();
					
					//System.out.println("activeX "+activeX);
					//System.out.println("downMovement "+downMovement);
					//System.out.println("activeX "+activeX);
					//System.out.println("passiveY "+passiveY);
					
					if (activeX + horiVerMove.getSpeedX() <= passiveMaxX && moveLeft) {
						//System.out.println("left");
						// Stop the object at the rightBoundary of the passive component
						activeComponent.setPositionX(passiveMaxX);
					}
					if (activeMaxX + horiVerMove.getSpeedX() >= passiveX && moveRight) {
						// Stop the object instead of bouncing it back
						//System.out.println("right");
						activeComponent.setPositionX(passiveX - activeWidth);
					}
					if (activeY + horiVerMove.getSpeedY() <= passiveMaxY && moveUp) {
						// Stop the object instead of bouncing it back
						//System.out.println("up");
						activeComponent.setPositionY(passiveMaxY);
					}
					if (activeMaxY + horiVerMove.getSpeedY() >= passiveY && moveDown) {
						// Stop the object instead of bouncing it back
						//System.out.println("down");
						activeComponent.setPositionY(passiveY - activeHeight);
					}
				}
			}
		//}
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
		//System.out.println("downMovement key "+keyCode);
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
