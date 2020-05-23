package com.gamemaker.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.Invocation;

import static org.junit.Assert.assertEquals;

public class TriangleTest {
	
	@Mock
	private Graphics2D graphics2d;
	
	private Component component;
	
	@Before
	public void setup() {
		component = new Triangle(Color.BLACK, 100, 150, 20, 20);
		graphics2d = Mockito.mock(Graphics2D.class);
	}
	
	@Test
	public void drawTest() {
		component.draw(graphics2d);
		Collection<Invocation> invocations = Mockito.mockingDetails(graphics2d).getInvocations();
		assertEquals(2, invocations.size());
	}
}
