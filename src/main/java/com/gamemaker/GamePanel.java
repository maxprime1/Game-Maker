package com.gamemaker;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import org.apache.log4j.Logger;

import com.gamemaker.behavior.Reaction;
import com.gamemaker.model.Component;
import com.gamemaker.model.ComponentGroup;

public class GamePanel extends JPanel implements MouseListener, MouseMotionListener {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(GamePanel.class);
	private transient Graphics2D graphobj = null;
	private ComponentGroup componentGroup;
	private Timer myTimer;

	private Component selectedShape;
	private double widthDifference;
	private double heightDifference;
	private boolean drag;
	private Cursor def;
	private Cursor move;
	private BufferedImage image;
	private boolean flag = false;
	private boolean isPaused  = true;
	
	private boolean isSetBgColor = true;

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image, boolean flag) {
		System.out.println("background image set");
		this.image = image;
		this.flag = flag;
	}

	public void removeImage(boolean flag)
	{
		this.flag = flag;
	}
	public GamePanel() {
			addMouseListener(this);
			addMouseMotionListener(this);
			drag = true;
			def = new Cursor(Cursor.DEFAULT_CURSOR);
			move = new Cursor(Cursor.MOVE_CURSOR);
	}
	
	public void drawComponents(ComponentGroup group) {
		this.setBackground(group.getColor());
		try
		{
			//this.setImage(ImageIO.read(group.getFile()), true);
			componentGroup = group;
			repaint();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override // Called Automatically when Jpanel is set visible
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		graphobj = (Graphics2D) g;
		//flag false bgvolor false
		System.out.println("paint called");
		if (!flag && !isSetBgColor)		
		{     
			this.setBackground(Color.white);
		}  
		else if (flag)
		{
			g.drawImage(image, 0, 0, getWidth(), getHeight(), this); 
			//isSetBgColor = false;
			System.out.println("setting backfrounf");

		}

		if (componentGroup != null)
		{
			
			componentGroup.draw(graphobj);
		}

	}

	public void startGame() {
		
		this.setFocusable(true);
		this.requestFocus();
		GamePanel panel=this;
		this.setIsPaused(false);
		// adding event handler for movement of components
		Iterator<Entry<String, Component>> componentIterator = componentGroup.getComponentMap().entrySet().iterator();
		while (componentIterator.hasNext()) {
			Component component = componentIterator.next().getValue();
			this.addKeyListener((KeyListener) component.getMovement());
			this.addKeyListener((KeyListener) component.getAction());
			this.addKeyListener((KeyListener) component.getBoundaryInteraction());
			//add keyListner to every reaction the current component has with other components
			HashMap<Component,Reaction > map = component.getReactionsMap();			
            for (Map.Entry<Component,Reaction> comp : map.entrySet()) {
				
				if (comp.getKey()!=null)
					this.addKeyListener((KeyListener) comp.getValue());
			}
		}
			myTimer = new Timer(50, new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					try {
						componentGroup.update(graphobj,panel);
					} catch (InterruptedException e) {
						Thread.currentThread().interrupt();
						logger.debug(e.getMessage());
					}
					repaint();
				}
			});
			

		
		myTimer.start();
	}
	
	public void isSetBgColor(boolean isSetBgColor)
	{
		isSetBgColor = true;
	}

	public void stopGame(String status)
	{
        JFrame frame = new JFrame("GAME OVER");
        JOptionPane.showMessageDialog(frame, status);
        frame.setSize(200,200);
		myTimer.stop();
		setDrag(true);
	}
	
	public void setDrag(boolean flag) {
		drag = flag;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if(!drag)
			return;
		int x = e.getX();
		int y = e.getY();
		if((x-widthDifference) >=0 && (x+ selectedShape.getShape().getBounds2D().getWidth() - widthDifference) <= this.getWidth())
			selectedShape.setPositionX(x - widthDifference);
		if((y-heightDifference) >=0 && (y+ selectedShape.getShape().getBounds2D().getHeight() - heightDifference) <= this.getHeight())
			selectedShape.setPositionY(y - heightDifference);
		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// Intentionally left blank

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		EditPanel editPanel = new EditPanel();

		Iterator<Entry<String, Component>> componentIterator = componentGroup.getComponentMap().entrySet().iterator();
		while (componentIterator.hasNext()) {
			Component component = componentIterator.next().getValue();
			if( component.getShape().contains(e.getX(), e.getY())) {
				editPanel.buildPanel(componentGroup, this,component);
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		//  Intentionally left blank
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// Intentionally left blank
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(!drag)
			return;
		Iterator<Entry<String, Component>> componentIterator = componentGroup.getComponentMap().entrySet().iterator();
		while (componentIterator.hasNext()) {
			Component component = componentIterator.next().getValue();
			if( component.getShape().contains(e.getX(), e.getY())) {
				selectedShape = component;
				widthDifference = e.getX() - selectedShape.getPositionX();
				heightDifference = e.getY() - selectedShape.getPositionY();
				this.setCursor(move);
				break;
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		selectedShape = null;
		widthDifference =0;
		heightDifference = 0;
		this.setCursor(def);
	}
	
	public void pauseGame()
	{
		if(myTimer!=null)
			myTimer.stop();
		setDrag(true);
	}
	public void resumeGame()
	{
		if(myTimer!=null)
			myTimer.start();
		setDrag(false);
	}
	public boolean getIsPaused() {
		return isPaused;
	}
	public void setIsPaused(boolean pause) {
		isPaused = pause;
	}
	
}
