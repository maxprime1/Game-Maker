package com.gamemaker.behavior;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;

import org.apache.log4j.Logger;

import com.gamemaker.GamePanel;
import com.gamemaker.model.Circle;
import com.gamemaker.model.Component;
import com.gamemaker.model.ComponentGroup;

public class Shoot implements Action, KeyListener {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(Shoot.class);
	private ComponentGroup group;
	private int bulletsCount;
	private Component component;
	private GamePanel gamePanel;
	private int speedXneg = 0;
	private int speedYneg = 0;
	private int speedXpos = 0;
	private int speedYpos = 0;
	private int positionXpos = 0;
	private int positionYpos = 0;
	private int positionXneg = 0;
	private int positionYneg = 0;
	
	private int positionXVpos = 0;
	private int positionYVpos = 0;
	private int positionXVneg = 0;
	private int positionYVneg = 0;
	private String shootDirection;

	// Constructor to initialize an object
	public Shoot(ComponentGroup group, String shootDirection) {
		this.group = group;
		bulletsCount = 0;
		this.shootDirection = shootDirection;
	}

	// Setting the speed of the object based the direction choosed
	public void setSpeed(String shootDirection) {
		Rectangle2D rectangle = component.getShape().getBounds2D();
		int minX = (int) rectangle.getMinX();
		int minY = (int) rectangle.getMinY();
		int maxX = (int) rectangle.getMaxX();
		int maxY = (int) rectangle.getMaxY();
		switch(shootDirection) {
			case "ShootUp":
				this.speedYpos = -10;
				positionXpos = (maxX + minX) /2 - 5;
				positionYpos = minY - 20;
				break;
			case "ShootDown":
				this.speedYpos = 10;
				positionXpos = (maxX + minX) /2 - 5;
				positionYpos = maxY + 20;
				
				break;
			case "ShootLeft":
				this.speedXpos = -10;
				positionXpos = minX - 20;
				positionYpos = (maxY + minY) /2 - 5;
				break;
			case "ShootRight":
				this.speedXpos = 10;
				positionXpos = maxX + 20;
				positionYpos = (maxY + minY) /2 - 5;
				break;
			case "ShootAll (T,F,G,H)":
				this.speedXpos = 10;
				this.speedXneg = -10;
				this.speedYpos = 10;
				this.speedYneg = -10;
				
				positionXpos = maxX + 20;
				positionXneg = minX -20;
				positionYpos = (maxY + minY) /2 - 5;
				positionYVpos = maxY +20;
				positionXVpos = (maxX +minX) /2 - 5;
				positionYVneg = minY - 20;
				break;
			default:
				logger.debug("Deafult case is triggered with option: "+shootDirection);
				break;
		}
	}

	@Override
	public void act(Component component, GamePanel gamePanel) {
		this.component = component;
		this.gamePanel = gamePanel;
		setSpeed(shootDirection);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// Not using this method
	}

	// Triggers whenever any key pressed
	@Override
	public void keyPressed(KeyEvent e) {
		PlaySound playSound = new PlaySound("shoot.wav");
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			Component bullet = new Circle(Color.PINK, positionXpos, positionYpos, 10, 10);
			bullet.setName("Bullet"+bulletsCount++);
			bullet.setMovement(new AutomaticMovement(this.speedXpos, this.speedYpos));
			bullet.setReaction(new NoReaction());
			bullet.setAction(new NoAction());
			bullet.setBoundaryInteraction(new NoBoundaryInteraction(gamePanel, group));
			this.group.addComponent(bullet.getName(), bullet);
			gamePanel.drawComponents(group);
			try {
				playSound.play();
			} catch (InterruptedException e1) {
				Thread.currentThread().interrupt();
				logger.error(e1.getMessage());
			}
		}
		
		if (e.getKeyCode() == KeyEvent.VK_H) {
			Component bullet = new Circle(Color.PINK, positionXpos, positionYpos, 10, 10);
			bullet.setName("Bullet"+bulletsCount++);
			bullet.setMovement(new AutomaticMovement(this.speedXpos, 0));
			bullet.setReaction(new NoReaction());
			bullet.setAction(new NoAction());
			bullet.setBoundaryInteraction(new NoBoundaryInteraction(gamePanel, group));
			this.group.addComponent(bullet.getName(), bullet);
			gamePanel.drawComponents(group);
			try {
				playSound.play();
			} catch (InterruptedException e1) {
				Thread.currentThread().interrupt();
				logger.error(e1.getMessage());
			}
		}
		
		if (e.getKeyCode() == KeyEvent.VK_F) {
			Component bullet = new Circle(Color.PINK, positionXneg, positionYpos, 10, 10);
			bullet.setName("Bullet"+bulletsCount++);
			bullet.setMovement(new AutomaticMovement(this.speedXneg, 0));
			bullet.setReaction(new NoReaction());
			bullet.setAction(new NoAction());
			bullet.setBoundaryInteraction(new NoBoundaryInteraction(gamePanel, group));
			this.group.addComponent(bullet.getName(), bullet);
			gamePanel.drawComponents(group);
			try {
				playSound.play();
			} catch (InterruptedException e1) {
				Thread.currentThread().interrupt();
				logger.error(e1.getMessage());
			}
		}
		
		if (e.getKeyCode() == KeyEvent.VK_T) {
			Component bullet = new Circle(Color.PINK, positionXVpos, positionYVneg, 10, 10);
			bullet.setName("Bullet"+bulletsCount++);
			bullet.setMovement(new AutomaticMovement(0, this.speedYneg));
			bullet.setReaction(new NoReaction());
			bullet.setAction(new NoAction());
			bullet.setBoundaryInteraction(new NoBoundaryInteraction(gamePanel, group));
			this.group.addComponent(bullet.getName(), bullet);
			gamePanel.drawComponents(group);
			try {
				playSound.play();
			} catch (InterruptedException e1) {
				Thread.currentThread().interrupt();
				logger.error(e1.getMessage());
			}
		}
		
		if (e.getKeyCode() == KeyEvent.VK_G) {
			Component bullet = new Circle(Color.PINK, positionXVpos, positionYVpos, 10, 10);
			bullet.setName("Bullet"+bulletsCount++);
			bullet.setMovement(new AutomaticMovement(0, this.speedYpos));
			bullet.setReaction(new NoReaction());
			bullet.setAction(new NoAction());
			bullet.setBoundaryInteraction(new NoBoundaryInteraction(gamePanel, group));
			this.group.addComponent(bullet.getName(), bullet);
			gamePanel.drawComponents(group);
			try {
				playSound.play();
			} catch (InterruptedException e1) {
				Thread.currentThread().interrupt();
				logger.error(e1.getMessage());
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// Not using this method
	}

	public int getSpeedXPos() {
		return speedXpos;
	}

	public void setSpeedXPos(int speedXpos) {
		this.speedXpos = speedXpos;
	}

	public int getSpeedYPos() {
		return speedYpos;
	}

	public void setSpeedYPos(int speedYpos) {
		this.speedYpos = speedYpos;
	}

}
