package com.gamemaker.model;

import java.awt.Image;

import javax.swing.ImageIcon;

public class ShapesModel {
	private ImageIcon image;
	private String name;
	
	public ShapesModel(ImageIcon image, String name) {
		super();
		Image newimg = image.getImage().getScaledInstance(20, 20,  Image.SCALE_SMOOTH);  
		this.image = new ImageIcon(newimg);  
		this.name = name;
	}
	
	public ImageIcon getImage() {
		return image;
	}
	public void setImage(ImageIcon image) {
		this.image = image;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
