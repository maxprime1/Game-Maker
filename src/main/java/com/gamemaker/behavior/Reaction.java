package com.gamemaker.behavior;

import java.io.Serializable;

import javax.swing.JPanel;

import com.gamemaker.model.Component;
import com.gamemaker.model.ComponentGroup;

public interface Reaction extends Serializable {
	void react(Component activeComponent, ComponentGroup group) throws InterruptedException;
	void reactNew(Component activeComponent, Component passiveComponent, JPanel panel)throws InterruptedException;
}
