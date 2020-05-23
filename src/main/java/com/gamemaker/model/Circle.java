package com.gamemaker.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.io.Serializable;

public class Circle extends Component implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public Circle(Color color, int initialPositionX, int initialPositionY, int ballWidth, int ballHeight) {
		this.color = color;
		positionX = initialPositionX;
		positionY = initialPositionY;
		this.width=ballWidth;
		this.height=ballHeight;
		this.status = "active";
	}

	@Override
	public void draw(Graphics2D g2d) {
		shape = new Ellipse2D.Double(positionX, positionY, width, height);
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
