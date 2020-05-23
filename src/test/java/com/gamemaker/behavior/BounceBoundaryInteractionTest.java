package com.gamemaker.behavior;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.awt.Rectangle;

import javax.swing.JPanel;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.gamemaker.GamePanel;
import com.gamemaker.model.Component;

public class BounceBoundaryInteractionTest {
	
	private BoundaryInteraction bounceBoundaryInteraction;
	private Component component;
	private JPanel gamePanel;

	@Before
	public void setup() {
		bounceBoundaryInteraction = new BounceBoundaryInteraction();
		component = Mockito.mock(Component.class);
		gamePanel = Mockito.mock(GamePanel.class);
		when(gamePanel.getWidth()).thenReturn(24);
		when(gamePanel.getHeight()).thenReturn(27);
		when(component.getShape()).thenReturn(new Rectangle(0, 0, 20, 20));
	}

	@org.junit.Test
	public void testAutomaticMovement() throws InterruptedException {
		AutomaticMovement autoMove = Mockito.mock(AutomaticMovement.class);
		when(autoMove.getSpeedX()).thenReturn(5);
		when(autoMove.getSpeedY()).thenReturn(3);
		when(component.getMovement()).thenReturn(autoMove);
		
		bounceBoundaryInteraction.execute(component, gamePanel);
		verify(autoMove).setSpeedX(-5);
	}
}
