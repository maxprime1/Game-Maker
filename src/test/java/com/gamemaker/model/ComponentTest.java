package com.gamemaker.model;

import static org.junit.Assert.assertEquals;

import java.awt.Color;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import com.gamemaker.behavior.ExplodeReaction;
import com.gamemaker.behavior.Reaction;

public class ComponentTest {


	private Component component;
	private Reaction reaction;
	
	@Before
	public void setup() {	
		
		component = new Triangle(Color.BLACK, 100, 150, 20, 20);
		reaction = new ExplodeReaction();
		}
		
	@Test
	public void addComponentTest()
	{
		
		component.map.put(component, reaction);
		assertEquals(component.map.size(),1);
	}
	

	
	@Test
	public void setReactionsMapTest() {
		HashMap<Component,Reaction > map =new HashMap<Component, Reaction>();
		map.put(component, reaction);
		component.setReactionsMap(map);
		assertEquals(map.get(component),component.getReactionsMap().get(component));
	}
	
	@Test
	public void setColorTest() {
		component = new Triangle(Color.BLACK, 100, 150, 20, 20);
		reaction = new ExplodeReaction();
		component.setColor(Color.BLUE);
		assertEquals(component.getColor(), component.color);
	}
	
	@Test
	public void setStatusTest() {
		component.setStatus("Dead");
		assertEquals(component.getStatus(), "Dead");
	}
	
}
