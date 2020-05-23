package com.gamemaker.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Map;
import java.util.Set;

import com.gamemaker.GamePanel;
import com.gamemaker.behavior.Reaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class ComponentGroup extends Component {

	private static final long serialVersionUID = 1L;
	private Map<String, Component> componentMap;
	private Map<String, Reaction> winMap;
	private Map<String, Reaction> loseMap;
	
	private Color backgroundColor;
	private BufferedImage backGroundImage;
	private File file;

	
	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public BufferedImage getBackGroundImage() {
		return backGroundImage;
	}

	public void setBackGroundImage(BufferedImage backGroundImage) {
		this.backGroundImage = backGroundImage;
	}

	public Color getColor() {
		return backgroundColor;
	}

	@Override
	public void setColor(Color color) {
		this.backgroundColor = color;
	}

	public ComponentGroup()
	{
		componentMap = new HashMap<String, Component>();
		winMap = new HashMap<String, Reaction>();
		loseMap= new HashMap<String, Reaction>();
	}
	
	@Override
	public void draw(Graphics2D g2d) {
		for (Map.Entry<String, Component> entry : componentMap.entrySet()) {
			// Get component from each entry and call component.draw() method
			entry.getValue().draw(g2d);
		}
	}
	
	public void addComponent(String name, Component component)
	{
		componentMap.put(name, component);
	}
	
	public void removeComponent(Component component)
	{
		// Remove component from hash map by its name
		componentMap.remove(component.getName());
	}
	
	public Map<String, Component> getComponentMap() {
		return componentMap;
	}

	public void setComponentMap(Map<String, Component> componentMap) {
		this.componentMap = componentMap;
	}

	public Map<String, Reaction> getWinMap() {
		return winMap;
	}

	public void setWinMap(Map<String, Reaction> winMap) {
		this.winMap = winMap;
	}

	public Map<String, Reaction> getLoseMap() {
		return loseMap;
	}

	public void setLoseMap(Map<String, Reaction> loseMap) {
		this.loseMap = loseMap;
	}

	public void addWinning(String name,Reaction reaction)
	{
		winMap.put(name, reaction);
	}
	
	public void addLosing(String name,Reaction reaction) {
		loseMap.put(name, reaction);
	}
	
	public void update(Graphics2D g, GamePanel panel) throws InterruptedException
	{
		Set<Component> deadSet = new HashSet<>();
		Set<Component> stopSet = new HashSet<>();
		for (Map.Entry<String, Component> entry : componentMap.entrySet()) {
			//System.out.println(entry);
			Component c = entry.getValue();
			c.move();
			c.act(panel);
			c.boundaryInteract(panel);
			
			HashMap<Component,Reaction > map = c.getReactionsMap(); 
			
			if (map.isEmpty())
			{
				c.react(this);
				
			}
			else {
				//System.out.println("component group");
				for (Map.Entry<Component,Reaction> comp : map.entrySet()) {
					
					if (comp.getKey()!=null && !c.equals(comp.getKey()))
						comp.getValue().reactNew(c, comp.getKey(), panel);
					
					
				}
			}
			//System.out.println(map);
			//c.react(this);
			if (c.getStatus().equals("dead")) {
				deadSet.add(c);
				map.remove(c);
			}
			if(c.getStatus().equals("bounced"))
				stopSet.add(c);
			
		}
			
		//check game end conditions for explode events
		for (Component deadComponent : deadSet) {
			//System.out.println(deadComponent);
			panel.removeKeyListener((KeyListener)deadComponent.getMovement());
			panel.removeKeyListener((KeyListener)deadComponent.getAction());
			
			mapHelper(deadComponent.getName(), panel);
			this.removeComponent(deadComponent);
		}
		
		//check game end conditions for all other events
		for (Component oneComponent : stopSet) {	
			mapHelper(oneComponent.getName(), panel);		
		}
		this.draw(g);
	}
	
	private void mapHelper(String componentName, GamePanel panel) {
		//System.out.println(winMap);
		if(winMap.containsKey(componentName)) {
			winMap.remove(componentName);
			if(winMap.size()==0)
				panel.stopGame("WIN");
		} else if (loseMap.containsKey(componentName)) {
			loseMap.remove(componentName);
			if(loseMap.size()==0)
				panel.stopGame("LOSE");
		}	
	}
}