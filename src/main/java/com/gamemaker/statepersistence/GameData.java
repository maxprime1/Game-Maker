package com.gamemaker.statepersistence;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.Serializable;
import java.util.Map;

import com.gamemaker.behavior.Reaction;
import com.gamemaker.model.Component;

public class GameData implements Serializable {

	private static final long serialVersionUID = 1L;
	private Map<String, Component> componentMap;
	private Map<String, Reaction> winMap;
	private Map<String, Reaction> loseMap;
	private Color backgroundColor;
	//private BufferedImage backGroundImage;
	private File file;
 
	


	public GameData(Map<String, Component> componentMap, Map<String, Reaction> winMap,
			Map<String, Reaction> loseMap, Color color, File file) {
		this.componentMap = componentMap;
		this.winMap = winMap;
		this.loseMap = loseMap;
		this.backgroundColor = color;
		this.file = file;
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
//
//	public BufferedImage getBackGroundImage() {
//		return backGroundImage;
//	}
//
//	public void setBackGroundImage(BufferedImage backGroundImage) {
//		this.backGroundImage = backGroundImage;
//	}
}
