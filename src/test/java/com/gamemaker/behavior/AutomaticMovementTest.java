package com.gamemaker.behavior;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import com.gamemaker.model.Circle;
import com.gamemaker.model.Component;

import static org.junit.Assert.assertEquals;

public class AutomaticMovementTest {

	private AutomaticMovement automaticMovement;

	@Before
	public void setup() {
		automaticMovement = new AutomaticMovement(10, 10);
	}

	@Test
	public void testGetSpeedX() {
		assertEquals(10, automaticMovement.getSpeedX());
	}

	@Test
	public void testGetSpeedY() {
		assertEquals(10, automaticMovement.getSpeedY());
	}

	@Test
	public void testSetSpeedX() {
		automaticMovement.setSpeedX(12);
		assertEquals(12, automaticMovement.getSpeedX());
	}

	@Test
	public void testSetSpeedY() {
		automaticMovement.setSpeedY(15);
		assertEquals(15, automaticMovement.getSpeedY());
	}

	@Test
	public void testMove() {
		Component component = new Circle(Color.RED, 10, 10, 10, 10);
		automaticMovement.move(component);
		assertEquals(20, (int)component.getPositionX());
		assertEquals(20, (int)component.getPositionY());
	}

}
