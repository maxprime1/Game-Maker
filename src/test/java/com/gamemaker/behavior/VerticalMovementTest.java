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

public class VerticalMovementTest {
	private VerticalMovement verticalMovement;
	
	@Mock
	private GamePanel gamePanel;
	
	private KeyEvent upKey;
	
	private KeyEvent downKey;

	@Before
	public void setup() {
		verticalMovement = new VerticalMovement(10, "arrows");
		gamePanel = Mockito.mock(GamePanel.class);
		upKey = new KeyEvent(gamePanel, 0, 0, 0, KeyEvent.VK_UP);
		downKey = new KeyEvent(gamePanel, 0, 0, 0, KeyEvent.VK_DOWN);
	}

	@Test
	public void testGetSpeedY() {
		assertEquals(10, verticalMovement.getSpeedY());
	}

	@Test
	public void testSetSpeedY() {
		verticalMovement.setSpeedY(12);
		assertEquals(12, verticalMovement.getSpeedY());
	}
	
	@Test
	public void testLeftKeyPressed() {
		verticalMovement.keyPressed(upKey);
		assertTrue(verticalMovement.isUp());
	}
	
	@Test
	public void testRightKeyPressed() {
		verticalMovement.keyPressed(downKey);
		assertTrue(verticalMovement.isDown());
	}
	
	@Test
	public void testLeftKeyReleased() {
		verticalMovement.keyReleased(upKey);
		assertFalse(verticalMovement.isUp());
	}
	
	@Test
	public void testRightKeyReleased() {
		verticalMovement.keyReleased(downKey);
		assertFalse(verticalMovement.isDown());
	}
}
