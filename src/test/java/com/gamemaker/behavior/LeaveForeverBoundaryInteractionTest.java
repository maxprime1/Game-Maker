package com.gamemaker.behavior;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.awt.Rectangle;

import javax.swing.JPanel;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.gamemaker.GamePanel;
import com.gamemaker.model.Component;

public class LeaveForeverBoundaryInteractionTest {
	private BoundaryInteraction leaveForeverBoundaryInteraction;
	private Component component;
	private JPanel gamePanel;

	@Before
	public void setup() {
		leaveForeverBoundaryInteraction = new LeaveForeverBoundaryInteraction();
		component = Mockito.mock(Component.class);
		gamePanel = Mockito.mock(GamePanel.class);
		when(gamePanel.getWidth()).thenReturn(24);
		when(gamePanel.getHeight()).thenReturn(27);
		when(component.getShape()).thenReturn(new Rectangle(500, 700, 2, 2));
	}

	@org.junit.Test
	public void testAutomaticMovement() throws InterruptedException {
		leaveForeverBoundaryInteraction.execute(component, gamePanel);
		verify(component).setStatus("dead");
	}

}
