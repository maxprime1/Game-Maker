package com.gamemaker.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;

import com.gamemaker.GamePanel;
import com.gamemaker.behavior.Action;
import com.gamemaker.behavior.BoundaryInteraction;
import com.gamemaker.behavior.ComeThroughBoundaryInteraction;
import com.gamemaker.behavior.Movement;
import com.gamemaker.behavior.Reaction;

public abstract class Component implements Serializable {

	private static final long serialVersionUID = 1L;
	protected Shape shape;
	protected double positionX;
	protected double positionY;
	protected Movement movement;
	protected Action action;
	protected Reaction reaction;
	protected BoundaryInteraction boundaryInteraction;
	protected String name;
	protected Color color;
	protected String status;
	protected double height;
	protected double width;
	//private static HashMap<String,ArrayList<Object>> hash = new HashMap<>();
	protected HashMap<Component,Reaction > map = new HashMap<>(); 
	
	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getPositionX() {
		return positionX;
	}

	public void setPositionX(double positionX) {
		this.positionX = positionX;
	}

	public double getPositionY() {
		return positionY;
	}

	public void setPositionY(double positionY) {
		this.positionY = positionY;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	public Color getColor() {
		return this.color;
	}
	public Shape getShape() {
		return shape;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Reaction getReaction() {
		return reaction;
	}

	public void setReaction(Reaction reaction) {
		this.reaction = reaction;
	}

	public Movement getMovement() {
		return movement;
	}

	public void setMovement(Movement movement) {
		this.movement = movement;
	}

	public BoundaryInteraction getBoundaryInteraction() {
		return boundaryInteraction;
	}

	public void setBoundaryInteraction(BoundaryInteraction boundaryInteraction) {
		this.boundaryInteraction = boundaryInteraction;
	}

	public abstract void draw(Graphics2D g2d);

	public void move() {
		this.movement.move(this);
	}

	public void act(GamePanel gamePanel) {
		this.action.act(this, gamePanel);
	}

	public void react(ComponentGroup group) throws InterruptedException {
		this.reaction.react(this, group);
	}

	public void boundaryInteract(JPanel gamePanel) throws InterruptedException {
		//System.out.println("Boundary Interaction Called");
		this.boundaryInteraction.execute(this, gamePanel);
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}
	// the reactions of this component with every other component is stored in this hashmap. Thus allowing the component to have different reactions with different components
	public void setReactionsMap(HashMap<Component,Reaction > map) {
		this.map.putAll(map);
		//System.out.println(this.map);
	}
	
	public HashMap<Component,Reaction > getReactionsMap() {
		//System.out.println(this.map);
		return this.map;
	}



}
