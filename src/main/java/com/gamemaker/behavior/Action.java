package com.gamemaker.behavior;

import java.io.Serializable;

import com.gamemaker.GamePanel;
import com.gamemaker.model.Component;

public interface Action extends Serializable {
	public void act(Component component, GamePanel gamePanel);
}
