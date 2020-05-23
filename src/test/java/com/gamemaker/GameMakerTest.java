package com.gamemaker;

import java.awt.event.ActionEvent;

import javax.swing.JButton;

import org.junit.Before;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import com.gamemaker.model.ComponentGroup;

public class GameMakerTest {
	@Mock
	private GamePanel gamePanel;
	private ComponentGroup componentGroup;
	private Gamemaker gameMaker;
	
	@Before
	public void setup() {
	gamePanel = Mockito.mock(GamePanel.class);
	gameMaker = Mockito.mock(Gamemaker.class);
	
	}
	
	@org.junit.Test
	public void testSave()
	{
		ActionEvent e=Mockito.mock(ActionEvent.class);
		when(e.getSource()).thenReturn(new JButton("Save"));
		gameMaker.buildToolPanel();
		e.getActionCommand();
	}
	
}
