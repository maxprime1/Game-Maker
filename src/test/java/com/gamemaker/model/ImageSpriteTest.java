package com.gamemaker.model;

import static org.junit.Assert.assertEquals;

import java.awt.Graphics2D;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.Invocation;

public class ImageSpriteTest {

	private Component imageSprite;

	@Mock
	private Graphics2D graphics2D;

	@Before
	public void setUp() {
		imageSprite = new ImageSprite("soccer.png", 10, 12, 20, 22);
		graphics2D = Mockito.mock(Graphics2D.class);
	}

	@Test
	public void test() {
		assertEquals(10, imageSprite.getPositionX(), 0);
		assertEquals(12, imageSprite.getPositionY(), 0);
	}

	@Test
	public void drawTest() {
		imageSprite.draw(graphics2D);
		Collection<Invocation> invocations = Mockito.mockingDetails(graphics2D).getInvocations();
		assertEquals(1, invocations.size());
	}
}
