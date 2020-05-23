package com.gamemaker.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

public class Rectangle extends Component implements Serializable{

	private static final long serialVersionUID = 1L;

	public Rectangle(Color color, int initialPositionX, int initialPositionY, int rectWidth, int rectHeight)
	{
		this.color=color;
		positionX=initialPositionX;
		positionY=initialPositionY;
		this.height=rectHeight;
		this.width=rectWidth;
		this.status = "active";
	}

	@Override
	public void draw(Graphics2D g2d) {
		shape = new Rectangle2D.Double(positionX, positionY, width, height);
		g2d.setColor(this.color);
		g2d.fill(shape);
	}

	public Color getColor() {
		return color;
	}

	@Override
	public void setColor(Color color) {
		this.color = color;
	}
}
