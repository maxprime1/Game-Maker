package com.gamemaker.behavior;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.swing.JPanel;

import com.gamemaker.model.Component;
import com.gamemaker.model.ComponentGroup;

public class ExplodeReaction implements Reaction, KeyListener {

	private static final long serialVersionUID = 1L;

	@Override
	public void react(Component activeComponent, ComponentGroup group) throws InterruptedException {
	
		Iterator<Entry<String, Component>> iter = group.getComponentMap().entrySet().iterator();
		PlaySound playSound = new PlaySound("explode.wav");
		
		while(iter.hasNext()) {
			Component passiveComponent = iter.next().getValue();
			
			if (activeComponent == passiveComponent)
				continue; // skip the component itself
		
			// Check if the active component intersects with the passive component, explode the active component
			if (activeComponent.getShape().getBounds2D().intersects(passiveComponent.getShape().getBounds2D())){
				activeComponent.setStatus("dead");
				playSound.play();
			}
		}
	}

	@Override
	public void reactNew(Component activeComponent, Component passiveComponent, JPanel panel) throws InterruptedException {
		// TODO Auto-generated method stub
		
		
		
		
		//if (activeComponent == passiveComponent)
		//	continue; // skip the component itself
	
		// Check if the active component intersects with the passive component, explode the active component
		if (activeComponent.getShape().getBounds2D().intersects(passiveComponent.getShape().getBounds2D())){
			//System.out.println("explode react");
			activeComponent.setStatus("dead");
			//playSound.play();
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
