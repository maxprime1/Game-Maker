package com.gamemaker.behavior;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.event.KeyEvent;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.gamemaker.GamePanel;

public class HorizontalMovementTest {
	private HorizontalMovement horizontalMovement;
	
	@Mock
	private GamePanel gamePanel;
	
	private KeyEvent leftKey;
	
	private KeyEvent rightKey;

	@Before
	public void setup() {
		horizontalMovement = new HorizontalMovement(10, "arrows");
		gamePanel = Mockito.mock(GamePanel.class);
		leftKey = new KeyEvent(gamePanel, 0, 0, 0, KeyEvent.VK_LEFT);
		rightKey = new KeyEvent(gamePanel, 0, 0, 0, KeyEvent.VK_RIGHT);
	}

	@Test
	public void testGetSpeedX() {
		assertEquals(10, horizontalMovement.getSpeedX());
	}

	@Test
	public void testSetSpeedX() {
		horizontalMovement.setSpeedX(12);
		assertEquals(12, horizontalMovement.getSpeedX());
	}
	
	@Test
	public void testLeftKeyPressed() {
		horizontalMovement.keyPressed(leftKey);
		assertTrue(horizontalMovement.isLeft());
	}
	
	@Test
	public void testRightKeyPressed() {
		horizontalMovement.keyPressed(rightKey);
		assertTrue(horizontalMovement.isRight());
	}
	
	@Test
	public void testLeftKeyReleased() {
		horizontalMovement.keyReleased(leftKey);
		assertFalse(horizontalMovement.isLeft());
	}
	
	@Test
	public void testRightKeyReleased() {
		horizontalMovement.keyReleased(rightKey);
		assertFalse(horizontalMovement.isRight());
	}

}
