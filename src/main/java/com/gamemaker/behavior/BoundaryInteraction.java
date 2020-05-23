package com.gamemaker.behavior;

import java.io.Serializable;

import javax.swing.JPanel;

import com.gamemaker.model.Component;

public interface BoundaryInteraction extends Serializable {
	public void execute(Component activeComponent, JPanel jPanel) throws InterruptedException;
}
