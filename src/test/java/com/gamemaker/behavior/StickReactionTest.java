package com.gamemaker.behavior;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.mockito.Mockito;

import com.gamemaker.GamePanel;
import com.gamemaker.model.Component;
import com.gamemaker.model.ComponentGroup;

public class StickReactionTest {
	private StickReaction reaction;
	private Component activeComponent;
	private Component passiveComponent;
	private GamePanel gamePanel;
	private ComponentGroup componentGroup;
	
	@Before
	public void setup() {
		reaction = new StickReaction();
		activeComponent = Mockito.mock(Component.class);
		passiveComponent = Mockito.mock(Component.class);
		gamePanel = Mockito.mock(GamePanel.class);
		componentGroup=Mockito.mock(ComponentGroup.class);
		when(gamePanel.getWidth()).thenReturn(24);
		when(gamePanel.getHeight()).thenReturn(27);
		when(activeComponent.getShape()).thenReturn(new Rectangle(0, 0, 20, 20));
		when(passiveComponent.getShape()).thenReturn(new Rectangle(0, 10, 20, 20));
		Map<String, Component> componentMap=new HashMap<String, Component>();
		componentMap.put("Rec1" ,activeComponent);
		componentMap.put("Rec2" ,passiveComponent);
		when(componentGroup.getComponentMap()).thenReturn(componentMap);
	}

	@org.junit.Test
	public void testreactNew() throws InterruptedException {
		AutomaticMovement autoMove = Mockito.mock(AutomaticMovement.class);
		when(autoMove.getSpeedX()).thenReturn(5);
		when(autoMove.getSpeedY()).thenReturn(3);
		when(activeComponent.getMovement()).thenReturn(autoMove);
		when(passiveComponent.getMovement()).thenReturn(autoMove);
		reaction.reactNew(activeComponent,passiveComponent, gamePanel);
		verify(activeComponent).setStatus("sticked");
		
	}
	
	@org.junit.Test
	public void testReact() throws InterruptedException {
		Map<String, Component> componentMap=new HashMap<String, Component>();
		AutomaticMovement autoMove = Mockito.mock(AutomaticMovement.class);
		when(autoMove.getSpeedX()).thenReturn(5);
		when(autoMove.getSpeedY()).thenReturn(3);
		when(activeComponent.getMovement()).thenReturn(autoMove);
		when(passiveComponent.getMovement()).thenReturn(autoMove);
		reaction.react(activeComponent,componentGroup);
		verify(activeComponent).setStatus("sticked");
		
		verify(passiveComponent).setPositionX(activeComponent.getPositionX());
	}
	

}
