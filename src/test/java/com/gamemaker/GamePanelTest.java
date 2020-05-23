package com.gamemaker;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;


import java.awt.image.BufferedImage;

import javax.swing.JButton;

import org.junit.Before;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;


public class GamePanelTest {

	private GamePanel gamePanel;
	
	@Before
	public void setup() {
	gamePanel = Mockito.mock(GamePanel.class);
	
	}
	@org.junit.Test
	public void testSetImage()
	{
		BufferedImage image= Mockito.mock(BufferedImage.class);
		gamePanel.setImage(image,false);
		assertFalse(gamePanel.getImage()==image);
		
		
	}
	@org.junit.Test
	public void testIsSetBgColor()
	{
		gamePanel.isSetBgColor(true);
		verify(gamePanel).isSetBgColor(true);
	}
	@org.junit.Test
	public void testSetDrag()
	{
		gamePanel.setDrag(true);
		verify(gamePanel).setDrag(true);
	}

}
