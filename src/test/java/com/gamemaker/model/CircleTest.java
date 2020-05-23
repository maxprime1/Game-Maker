package com.gamemaker.model;

import static org.junit.Assert.assertEquals;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.Invocation;

public class CircleTest {

	private Component circle;

	@Mock
	private Graphics2D graphics2d;

	@Before
	public void setUp() {
		circle = new Circle(Color.BLUE, 10, 12, 20, 22);
		graphics2d = Mockito.mock(Graphics2D.class);
	}

	@Test
	public void test() {
		assertEquals(((Circle) circle).getColor(), Color.BLUE);
		assertEquals(10, circle.getPositionX(), 0);
		assertEquals(12, circle.getPositionY(), 0);
		assertEquals(20, circle.getWidth(), 0);
		assertEquals(22, circle.getHeight(), 0);
	}

	@Test
	public void drawTest() {
		circle.draw(graphics2d);
		Collection<Invocation> invocations = Mockito.mockingDetails(graphics2d).getInvocations();
		assertEquals(2, invocations.size());
	}
}
