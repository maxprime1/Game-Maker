package com.gamemaker.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.io.Serializable;

// Class to draw and hold information related to a triangle component
public class Triangle extends Component implements Serializable{

	private static final long serialVersionUID = 1L;

	public Triangle(Color color, int initialPositionX, int initialPositionY, int triangleWidth, int triangleHeight) {
		this.color = color;
		positionX = initialPositionX;
		positionY = initialPositionY;
		this.height= triangleHeight;
		this.width= triangleWidth;
		this.status = "active";
	}

	// draw() method draws the figure on the designated area
	@Override
	public void draw(Graphics2D g2d) {
		int[] x = new int[3];
		int[] y = new int[3];

		// Calculating X coordinates using the centroid, height and width
		x[0] = (int)positionX;
		x[1] = (int) ((int)positionX - (this.width/2));
		x[2] = (int) ((int)positionX + (this.width/2));

		// Calculating Y coordinates using the centroid, height and width
		y[0] = (int) ((int)positionY - (this.height/2));
		y[1] = (int) ((int)positionY + (this.height/2));
		y[2] = y[1];

		// Developing the shape and adding necessary properties
		shape = new Polygon(x, y, 3);
		g2d.setColor(this.color);
		g2d.fill(shape);
	}

}
