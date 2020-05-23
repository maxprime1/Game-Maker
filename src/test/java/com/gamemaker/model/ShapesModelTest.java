package com.gamemaker.model;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;

import javax.swing.ImageIcon;

import org.junit.Before;
import org.junit.Test;

public class ShapesModelTest {
	
	private ShapesModel shapesModel;

	@Before
	public void setup() {
		String imagePath = System.getProperty("user.dir") + File.separator + "GameData" + File.separator + "images" + File.separator + "soccer.png";
		shapesModel = new ShapesModel(new ImageIcon(imagePath), "name");
	}
	
	@Test
	public void getImage() {
		assertTrue(shapesModel.getImage() instanceof ImageIcon);
	}
	
	@Test
	public void getName() {
		assertEquals("name", shapesModel.getName());
	}
	

}
