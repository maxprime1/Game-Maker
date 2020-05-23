package com.gamemaker.model;

import static org.junit.Assert.assertEquals;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import com.gamemaker.behavior.ExplodeReaction;
import com.gamemaker.behavior.Reaction;

public class ComponentGroupTest { 
	
	private ComponentGroup group;
	private Component component;
	private Reaction reaction;
	
	@Before
	public void setup() {	
		component = new Triangle(Color.BLACK, 100, 150, 20, 20);
		reaction = new ExplodeReaction();
		group=new ComponentGroup();
		}

	@Test
	public void addComponentTest()
	{
		 group.addComponent("test1", component);
		 assertEquals(group.getComponentMap().size(), 1);
	}
	
	@Test
	public void addWinningTest()
	{
		group.addWinning("win1", reaction);
		assertEquals(group.getWinMap().size(), 1);
	}
	
}