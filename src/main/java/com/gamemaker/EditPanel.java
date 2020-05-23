package com.gamemaker;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.gamemaker.behavior.AutomaticMovement;
import com.gamemaker.behavior.BounceBoundaryInteraction;
import com.gamemaker.behavior.BounceReaction;
import com.gamemaker.behavior.BoundaryInteraction;
import com.gamemaker.behavior.ExplodeReaction;
import com.gamemaker.behavior.HorizontalMovement;
import com.gamemaker.behavior.LeaveForeverBoundaryInteraction;
import com.gamemaker.behavior.Movement;
import com.gamemaker.behavior.NoBoundaryInteraction;
import com.gamemaker.behavior.NoMovement;
import com.gamemaker.behavior.NoReaction;
import com.gamemaker.behavior.Reaction;
import com.gamemaker.behavior.VerticalMovement;
import com.gamemaker.model.Component;
import com.gamemaker.model.ComponentGroup;

/**
 * @author Nisha G
 *
 */
public class EditPanel extends JFrame {
	private Component currentComponent;
	private ComponentGroup group;
	private JLabel positionXLabel;
	private JLabel positionYLabel;
	private JTextField positionX;
	private JTextField positionY;
	private JLabel heightLabel;
	private JLabel widthLabel;
	private JTextField componentWidth;
	private JTextField componentHeight;
	private JTextField speedX;
	private JTextField speedY;
	private JLabel speedXLabel;
	private JLabel speedYLabel;
	private GamePanel gamePanel;
	private JButton updateButton;
	private JButton deleteButton;
	private JComboBox movementBox;
	private Movement newMovement;
	private JComboBox boundaryBox;
	private JComboBox eventBox;
	private BoundaryInteraction boundaryInteraction;
	private Reaction reaction;
	private JLabel componentName;
	private static final String ARROWS = "arrows";
	private static final String KEYS = "keys";

	public void buildUI() {
		componentName = new JLabel();
		componentName.setBounds(146, 50, 60, 16);

		positionXLabel = new JLabel("X:");
		positionX = new JTextField();
		positionXLabel.setBounds(116, 107, 60, 16);
		positionX.setBounds(176, 107, 43, 16);

		positionYLabel = new JLabel("Y:");
		positionY = new JTextField();
		positionYLabel.setBounds(219, 107, 60, 16);
		positionY.setBounds(279, 107, 43, 16);

		widthLabel = new JLabel("Width:");
		componentWidth = new JTextField();
		widthLabel.setBounds(116, 137, 60, 16);
		componentWidth.setBounds(176, 137, 43, 16);

		heightLabel = new JLabel("Height:");
		componentHeight = new JTextField();
		heightLabel.setBounds(219, 137, 60, 16);
		componentHeight.setBounds(279, 137, 43, 16);

		speedXLabel = new JLabel("Speed X:");
		speedX = new JTextField();
		speedXLabel.setBounds(116, 167, 60, 16);
		speedX.setBounds(176, 167, 43, 16);

		speedYLabel = new JLabel("Speed Y:");
		speedY = new JTextField();
		speedYLabel.setBounds(220, 167, 60, 16);
		speedY.setBounds(280, 167, 43, 16);

		movementBox = new JComboBox();
		movementBox.setBounds(171, 207, 113, 22);
		String[] movements = { "No Movement", "Automatic Movement", "Horizontal (Arrows)", "Horizontal (A,D)",
				"Vertical (Arrows)", "Vertical (W,S)" };
		movementBox.setModel(new DefaultComboBoxModel(movements));

		updateButton = new JButton("Update");
		updateButton.setBounds(136, 277, 83, 30);

		deleteButton = new JButton("Delete");
		deleteButton.setBounds(226, 277, 83, 30);

		boundaryBox = new JComboBox();
		boundaryBox.setBounds(300, 207, 113, 22);
		String[] boundaryOptions = { "No Boundary Event", "Bounce", "Leave Forever" };
		boundaryBox.setModel(new DefaultComboBoxModel(boundaryOptions));

		eventBox = new JComboBox();
		eventBox.setBounds(40, 207, 113, 22);
		String[] reactions = { "No Event", "Bounce", "Explode" };
		eventBox.setModel(new DefaultComboBoxModel(reactions));
	}

	public void buildPanel(ComponentGroup group, GamePanel gamePanel, Component component) {
		this.group = group;
		this.gamePanel = gamePanel;
		this.currentComponent = component;

		this.setResizable(false);
		this.setBounds(100, 100, 450, 350);

		JPanel contentPane = new JPanel();
		contentPane.setBounds(320, 0, 490, 610);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);

		// build the form UI
		buildUI();

		componentName.setText("Edit: " + currentComponent.getName());
		positionX.setText(Double.toString(currentComponent.getPositionX()));
		positionY.setText(Double.toString(currentComponent.getPositionY()));

		componentWidth.setText(Double.toString(currentComponent.getShape().getBounds().getWidth()));
		componentHeight.setText(Double.toString(currentComponent.getShape().getBounds().getHeight()));

		speedX.setText(Integer.toString(currentComponent.getMovement().getSpeedX()));
		speedY.setText(Integer.toString(currentComponent.getMovement().getSpeedY()));

		setMovementIndex();
		setBoundaryIndex();
		setEventIndex();

		updateButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				switchMovement();
				switchBoundary();
				switchEvent();

				currentComponent.setPositionX(Double.parseDouble(positionX.getText()));
				currentComponent.setPositionY(Double.parseDouble(positionY.getText()));
				currentComponent.setHeight(Double.parseDouble(componentHeight.getText()));
				currentComponent.setWidth(Double.parseDouble(componentWidth.getText()));
				currentComponent.setMovement(newMovement);
				currentComponent.setBoundaryInteraction(boundaryInteraction);
				currentComponent.setReaction(reaction);
				gamePanel.drawComponents(group);
			}
		});

		deleteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// remove the component from the group
				group.removeComponent(currentComponent);
				gamePanel.drawComponents(group);
			}
		});
		contentPane.add(widthLabel);
		contentPane.add(heightLabel);
		contentPane.add(componentHeight);
		contentPane.add(componentWidth);
		contentPane.add(positionXLabel);
		contentPane.add(positionYLabel);
		contentPane.add(positionX);
		contentPane.add(positionY);
		contentPane.add(updateButton);
		contentPane.add(deleteButton);
		contentPane.add(movementBox);
		contentPane.add(speedXLabel);
		contentPane.add(speedYLabel);
		contentPane.add(speedX);
		contentPane.add(speedY);
		contentPane.add(boundaryBox);
		contentPane.add(eventBox);
		contentPane.add(componentName);
		this.add(contentPane);
		this.setVisible(true);
	}

	public void setBoundaryIndex() {
		if (currentComponent.getBoundaryInteraction() instanceof NoBoundaryInteraction) {
			boundaryBox.setSelectedIndex(0);
		}

		if (currentComponent.getBoundaryInteraction() instanceof BounceBoundaryInteraction) {
			boundaryBox.setSelectedIndex(1);
		}

		if (currentComponent.getBoundaryInteraction() instanceof LeaveForeverBoundaryInteraction) {
			boundaryBox.setSelectedIndex(2);
		}
	}

	public void setMovementIndex() {
		if (currentComponent.getMovement() instanceof AutomaticMovement) {
			movementBox.setSelectedIndex(1);
		}

		if (currentComponent.getMovement() instanceof HorizontalMovement) {
			if (((HorizontalMovement) currentComponent.getMovement()).getControl().equals(ARROWS)) {
				movementBox.setSelectedIndex(2);
			} else {
				movementBox.setSelectedIndex(3);
			}
		}

		if (currentComponent.getMovement() instanceof VerticalMovement) {
			if (((VerticalMovement) currentComponent.getMovement()).getControl().equals(ARROWS)) {
				movementBox.setSelectedIndex(4);
			} else {
				movementBox.setSelectedIndex(5);
			}
		}
	}

	public void setEventIndex() {
		if (currentComponent.getReaction() instanceof NoReaction) {
			eventBox.setSelectedIndex(0);
		}
		if (currentComponent.getReaction() instanceof BounceReaction) {
			eventBox.setSelectedIndex(1);
		}
		if (currentComponent.getReaction() instanceof ExplodeReaction) {
			eventBox.setSelectedIndex(2);
		}
	}

	public void switchMovement() {
		switch (movementBox.getSelectedIndex()) {
		case 1:
			newMovement = new AutomaticMovement(Integer.parseInt(speedX.getText()), Integer.parseInt(speedY.getText()));
			break;
		case 2:
			newMovement = new HorizontalMovement(Math.abs(Integer.parseInt(speedX.getText())), ARROWS);
			break;
		case 3:
			newMovement = new HorizontalMovement(Math.abs(Integer.parseInt(speedX.getText())), KEYS);
			break;
		case 4:
			newMovement = new VerticalMovement(Math.abs(Integer.parseInt(speedY.getText())), ARROWS);
			break;
		case 5:
			newMovement = new VerticalMovement(Math.abs(Integer.parseInt(speedY.getText())), KEYS);
			break;
		default:
			newMovement = new NoMovement();
		}
	}


	public void switchBoundary() {
		switch (boundaryBox.getSelectedIndex()) {
		case 0:
			boundaryInteraction = new NoBoundaryInteraction(gamePanel, group);
			break;
		case 1:
			boundaryInteraction = new BounceBoundaryInteraction();
			break;
		case 2:
			boundaryInteraction = new LeaveForeverBoundaryInteraction();
			break;
		default:
			boundaryInteraction = new NoBoundaryInteraction(gamePanel, group);
		}
	}

	public void switchEvent() {
		switch (eventBox.getSelectedIndex()) {
		case 0:
			reaction = new NoReaction();
			break;
		case 1:
			reaction = new BounceReaction();
			break;
		case 2:
			reaction = new ExplodeReaction();
			break;
		default:
			reaction = new NoReaction();
		}
	}

	public Component getCurrentComponent() {
		return currentComponent;
	}

	public void setCurrentComponent(Component currentComponent) {
		this.currentComponent = currentComponent;
	}

	public JComboBox getBoundaryBox() {
		return boundaryBox;
	}

	public void setBoundaryBox(JComboBox boundaryBox) {
		this.boundaryBox = boundaryBox;
	}

	public JComboBox getMovementBox() {
		return movementBox;
	}

	public void setMovementBox(JComboBox movementBox) {
		this.movementBox = movementBox;
	}
	
	public JComboBox getEventBox() {
		return eventBox;
	}

	public void setEventBox(JComboBox eventBox) {
		this.eventBox = eventBox;
	}

	public Movement getNewMovement() {
		return newMovement;
	}

	public void setNewMovement(Movement newMovement) {
		this.newMovement = newMovement;
	}

	public Reaction getReaction() {
		return reaction;
	}

	public void setReaction(Reaction reaction) {
		this.reaction = reaction;
	}

	public JTextField getSpeedX() {
		return speedX;
	}

	public void setSpeedX(JTextField speedX) {
		this.speedX = speedX;
	}

	public JTextField getSpeedY() {
		return speedY;
	}

	public void setSpeedY(JTextField speedY) {
		this.speedY = speedY;
	}

	public BoundaryInteraction getBoundaryInteraction() {
		return boundaryInteraction;
	}

	public void setBoundaryInteraction(BoundaryInteraction boundaryInteraction) {
		this.boundaryInteraction = boundaryInteraction;
	}

	
}
