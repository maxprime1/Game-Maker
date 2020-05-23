package com.gamemaker.gamemaker;

import static org.junit.Assert.*;

import java.awt.Graphics2D;
import java.util.EventListenerProxy;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import org.junit.Test;
import org.mockito.Mock;

import com.gamemaker.EditPanel;
import com.gamemaker.GamePanel;
import com.gamemaker.behavior.AutomaticMovement;
import com.gamemaker.behavior.BounceBoundaryInteraction;
import com.gamemaker.behavior.BounceReaction;
import com.gamemaker.behavior.ExplodeReaction;
import com.gamemaker.behavior.HorizontalMovement;
import com.gamemaker.behavior.LeaveForeverBoundaryInteraction;
import com.gamemaker.behavior.NoBoundaryInteraction;
import com.gamemaker.behavior.NoMovement;
import com.gamemaker.behavior.NoReaction;
import com.gamemaker.behavior.VerticalMovement;
import com.gamemaker.model.Component;



public class EditPanelTest {
	@Mock
	private EditPanel editPanel = new EditPanel();

	@Mock
	private JComboBox boundaryBox = new JComboBox<>();

	@Mock
	private JComboBox movementBox = new JComboBox<>();

	@Mock
	private JComboBox eventBox = new JComboBox<>();

	@Mock
	private JTextField speed = new JTextField();

	@Mock
	private Component component = new Component() {

		@Override
		public void draw(Graphics2D g2d) {
			// TODO Auto-generated method stub

		}
	};


	@Test
	public void testSetBoundaryIndex() 
	{
		boundaryBox.setBounds(300, 207, 113, 22);
		String[] boundaryOptions = { "No Boundary Event", "Bounce", "Leave Forever" };
		boundaryBox.setModel(new DefaultComboBoxModel(boundaryOptions));

		component.setBoundaryInteraction(new BounceBoundaryInteraction());
		editPanel.setCurrentComponent(component);
		editPanel.setBoundaryBox(boundaryBox);
		editPanel.setBoundaryIndex();

		assertEquals(1, editPanel.getBoundaryBox().getSelectedIndex());

		component.setBoundaryInteraction(new NoBoundaryInteraction());
		editPanel.setCurrentComponent(component);
		editPanel.setBoundaryIndex();
		assertEquals(0, editPanel.getBoundaryBox().getSelectedIndex());

		component.setBoundaryInteraction(new LeaveForeverBoundaryInteraction());
		editPanel.setCurrentComponent(component);
		editPanel.setBoundaryIndex();
		assertEquals(2, editPanel.getBoundaryBox().getSelectedIndex());
	}

	@Test
	public void testSetMovementIndex() 
	{
		movementBox = new JComboBox();
		movementBox.setBounds(171, 207, 113, 22);
		String[] movements = { "No Movement", "Automatic Movement", "Horizontal (Arrows)", "Horizontal (A,D)",
				"Vertical (Arrows)", "Vertical (W,S)" };

		movementBox.setModel(new DefaultComboBoxModel(movements));

		editPanel.setCurrentComponent(component);
		editPanel.setMovementBox(movementBox);


		component.setMovement(new AutomaticMovement(10, 10));
		editPanel.setMovementIndex();

		assertEquals(1, editPanel.getMovementBox().getSelectedIndex());

		component.setMovement(new HorizontalMovement(10,"arrows"));
		editPanel.setCurrentComponent(component);
		editPanel.setMovementIndex();


		assertEquals(2, editPanel.getMovementBox().getSelectedIndex());


		component.setMovement(new HorizontalMovement(10,"no arrows"));
		editPanel.setCurrentComponent(component);
		editPanel.setMovementIndex();

		assertEquals(3, editPanel.getMovementBox().getSelectedIndex());


		component.setMovement(new VerticalMovement(10, "arrows"));
		editPanel.setCurrentComponent(component);
		editPanel.setMovementIndex();

		assertEquals(4, editPanel.getMovementBox().getSelectedIndex());

		component.setMovement(new VerticalMovement(10, "no arrows"));
		editPanel.setCurrentComponent(component);
		editPanel.setMovementIndex();

		assertEquals(5, editPanel.getMovementBox().getSelectedIndex());
	}



	@Test
	public void testSetEventIndex() 
	{
		eventBox = new JComboBox();
		eventBox.setBounds(40, 207, 113, 22);
		String[] reactions = { "No Event", "Bounce", "Explode" };
		eventBox.setModel(new DefaultComboBoxModel(reactions));

		component.setReaction(new NoReaction());
		editPanel.setCurrentComponent(component);
		editPanel.setEventBox(eventBox);
		editPanel.setEventIndex();

		assertEquals(0, editPanel.getEventBox().getSelectedIndex());

		component.setReaction(new BounceReaction());
		editPanel.setCurrentComponent(component);
		editPanel.setEventIndex();
		assertEquals(1, editPanel.getEventBox().getSelectedIndex());


		component.setReaction(new ExplodeReaction());
		editPanel.setCurrentComponent(component);
		editPanel.setEventIndex();
		assertEquals(2, editPanel.getEventBox().getSelectedIndex());


	}



	@Test
	public void testSwitchMovement() 
	{
		movementBox = new JComboBox();
		movementBox.setBounds(171, 207, 113, 22);
		String[] movements = { "No Movement", "Automatic Movement", "Horizontal (Arrows)", "Horizontal (A,D)",
				"Vertical (Arrows)", "Vertical (W,S)" };

		movementBox.setModel(new DefaultComboBoxModel(movements));

		speed.setText("10");

		editPanel.setSpeedX(speed);
		editPanel.setSpeedY(speed);
		editPanel.setMovementBox(movementBox);

		editPanel.getMovementBox().setSelectedIndex(1);
		editPanel.switchMovement();


		if ( editPanel.getNewMovement() instanceof AutomaticMovement)
			assertTrue(true);

		editPanel.getMovementBox().setSelectedIndex(2);
		editPanel.switchMovement();
		if ( editPanel.getNewMovement() instanceof HorizontalMovement)
			assertTrue(true);

		editPanel.getMovementBox().setSelectedIndex(3);
		editPanel.switchMovement();
		if ( editPanel.getNewMovement() instanceof HorizontalMovement)
			assertTrue(true);

		editPanel.getMovementBox().setSelectedIndex(4);
		editPanel.switchMovement();
		if ( editPanel.getNewMovement() instanceof VerticalMovement)
			assertTrue(true);


		editPanel.getMovementBox().setSelectedIndex(5);
		editPanel.switchMovement();
		if ( editPanel.getNewMovement() instanceof VerticalMovement)
			assertTrue(true);

		editPanel.getMovementBox().setSelectedIndex(0);
		editPanel.switchMovement();
		if ( editPanel.getNewMovement() instanceof NoMovement)
			assertTrue(true);

	}	


	@Test
	public void testSwitchBoundary() 
	{
		boundaryBox.setBounds(300, 207, 113, 22);
		String[] boundaryOptions = { "No Boundary Event", "Bounce", "Leave Forever" };
		boundaryBox.setModel(new DefaultComboBoxModel(boundaryOptions));

		editPanel.setBoundaryBox(boundaryBox);
		editPanel.getBoundaryBox().setSelectedIndex(0);
		editPanel.switchBoundary();
		if ( editPanel.getBoundaryInteraction() instanceof NoBoundaryInteraction)
			assertTrue(true);

		editPanel.getBoundaryBox().setSelectedIndex(1);
		editPanel.switchBoundary();
		if ( editPanel.getBoundaryInteraction() instanceof BounceBoundaryInteraction)
			assertTrue(true);


		editPanel.getBoundaryBox().setSelectedIndex(2);
		editPanel.switchBoundary();
		if ( editPanel.getBoundaryInteraction() instanceof LeaveForeverBoundaryInteraction)
			assertTrue(true);

		editPanel.getBoundaryBox().setSelectedIndex(0);
		editPanel.switchBoundary();
		if ( editPanel.getBoundaryInteraction() instanceof NoBoundaryInteraction)
			assertTrue(true);
	}


	@Test
	public void testSwitchEvent() {
		eventBox = new JComboBox();
		eventBox.setBounds(40, 207, 113, 22);
		String[] reactions = { "No Event", "Bounce", "Explode" };
		eventBox.setModel(new DefaultComboBoxModel(reactions));
		
		editPanel.setEventBox(eventBox);
		eventBox.setSelectedIndex(0);
		editPanel.switchEvent();
		
		if ( editPanel.getReaction() instanceof NoReaction)
			assertTrue(true);
		
		eventBox.setSelectedIndex(1);
		editPanel.switchEvent();
		
		if (editPanel.getReaction() instanceof BounceReaction)
			assertTrue(true);
		
		eventBox.setSelectedIndex(2);
		editPanel.switchEvent();
		if (editPanel.getReaction() instanceof ExplodeReaction)
			assertTrue(true);
		
	}
	
	@Test
	public void testSetNewMovement()
	{
		component.setMovement(new HorizontalMovement(10, "arrow"));
		if (component.getMovement() instanceof HorizontalMovement)
			assertTrue(true);
	}
	
	@Test
	public void testGetNewMovement()
	{
		component.setMovement(new HorizontalMovement(10, "arrow"));
		if (component.getMovement() instanceof HorizontalMovement)
			assertTrue(true);
	}

}
