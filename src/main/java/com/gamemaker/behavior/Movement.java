package com.gamemaker.behavior;

import java.io.Serializable;

import com.gamemaker.model.Component;

public interface Movement extends Serializable  {
	public void move(Component component);
	public int getSpeedX();
	public int getSpeedY();
}
