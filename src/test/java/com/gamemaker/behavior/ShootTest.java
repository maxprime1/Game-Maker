package com.gamemaker.behavior;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.gamemaker.GamePanel;
import com.gamemaker.model.Component;
import com.gamemaker.model.ComponentGroup;
import com.gamemaker.model.Triangle;

public class ShootTest {
	
	private ComponentGroup componentGroup;
	private Shoot shoot;
	private GamePanel gamePanel;
	
	@Mock
	private Graphics2D graphics2d;
	
	@Before
	public void setup() {
		componentGroup = new ComponentGroup();
		shoot = new Shoot(componentGroup, "ShootUp");
		graphics2d = Mockito.mock(Graphics2D.class);
		Component component = new Triangle(Color.RED, 10, 10, 20, 20);
		component.draw(graphics2d);
		componentGroup.addComponent("t1", component);
		gamePanel = new GamePanel();
		shoot.act(component, gamePanel);
	}
	
	@Test
	public void shootUpTest() {
		assertEquals(-10, shoot.getSpeedYPos());
	}
	
	@Test
	public void shootDownTest() {
		shoot.setSpeed("ShootDown");
		assertEquals(10, shoot.getSpeedYPos());
	}
	
	@Test
	public void shootLeftTest() {
		shoot.setSpeed("ShootLeft");
		assertEquals(-10, shoot.getSpeedXPos());
	}
	
	@Test
	public void shootRightTest() {
		shoot.setSpeed("ShootRight");
		assertEquals(10, shoot.getSpeedXPos());
	}
	
	@Test
	public void shootAllTest() {
		shoot.setSpeed("ShootAll (T,F,G,H)");
		assertEquals(10, shoot.getSpeedXPos());
	}
	
	
	
	@Test
	public void keyPressedTestSpace() {
		KeyEvent key = new KeyEvent(gamePanel, 0, 0, 0,  KeyEvent.VK_SPACE);
		shoot.keyPressed(key);
		assertEquals(2, componentGroup.getComponentMap().size());
		
	}
	
	@Test
	public void keyPressedTestT() {
		KeyEvent key1 = new KeyEvent(gamePanel, 0, 0, 0,  KeyEvent.VK_T);
		shoot.keyPressed(key1);
		assertEquals(2, componentGroup.getComponentMap().size());
		
	}
	
	@Test
	public void keyPressedTestH() {
		KeyEvent key1 = new KeyEvent(gamePanel, 0, 0, 0,  KeyEvent.VK_H);
		shoot.keyPressed(key1);
		assertEquals(2, componentGroup.getComponentMap().size());
		
	}
	
	@Test
	public void keyPressedTestF() {
		KeyEvent key1 = new KeyEvent(gamePanel, 0, 0, 0,  KeyEvent.VK_F);
		shoot.keyPressed(key1);
		assertEquals(2, componentGroup.getComponentMap().size());
		
	}
	
	@Test
	public void keyPressedTestG() {
		KeyEvent key1 = new KeyEvent(gamePanel, 0, 0, 0,  KeyEvent.VK_G);
		shoot.keyPressed(key1);
		assertEquals(2, componentGroup.getComponentMap().size());
		
	}
	
	@Test
	public void setSpeedXPos() {
		shoot.setSpeedXPos(10);
		assertEquals(shoot.getSpeedXPos(), 10);
		
	}
	
	@Test
	public void setSpeedYPos() {
		shoot.setSpeedYPos(10);
		assertEquals(shoot.getSpeedYPos(), 10);
		
	}
	
}
