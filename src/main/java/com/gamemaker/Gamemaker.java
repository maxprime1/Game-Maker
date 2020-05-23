/*
 * Changes : 
 * Asim : Added "Frogger" in shapes Array. And aaddButton action listener switch case. 
 */
package com.gamemaker;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JColorChooser;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.border.TitledBorder;

import org.apache.log4j.Logger;

import com.gamemaker.behavior.Action;
import com.gamemaker.behavior.AutomaticMovement;
import com.gamemaker.behavior.BounceBoundaryInteraction;
import com.gamemaker.behavior.BounceReaction;
import com.gamemaker.behavior.BoundaryInteraction;
import com.gamemaker.behavior.ComeThroughBoundaryInteraction;
import com.gamemaker.behavior.ExplodeReaction;
import com.gamemaker.behavior.HorizontalMovement;
import com.gamemaker.behavior.HorizontalVerticalKeyMovements;
import com.gamemaker.behavior.LeaveForeverBoundaryInteraction;
import com.gamemaker.behavior.MoveDownBoundaryInteraction;
import com.gamemaker.behavior.NoAction;
import com.gamemaker.behavior.NoBoundaryInteraction;
import com.gamemaker.behavior.NoMovement;
import com.gamemaker.behavior.NoReaction;
import com.gamemaker.behavior.Reaction;
import com.gamemaker.behavior.Shoot;
import com.gamemaker.behavior.StickReaction;
import com.gamemaker.behavior.VerticalMovement;

import com.gamemaker.model.Circle;
import com.gamemaker.model.Component;
import com.gamemaker.model.ComponentGroup;
import com.gamemaker.model.ImageSprite;
import com.gamemaker.model.Rectangle;
import com.gamemaker.model.ShapesModel;
import com.gamemaker.model.Triangle;
import com.gamemaker.statepersistence.GameData;
import com.gamemaker.statepersistence.GameDataOperations;

import javax.swing.UIManager;

public class Gamemaker extends JPanel {

	private JPanel contentPane;
	private JTabbedPane gameTabs;
	private JPanel toolPanel;
	private JPanel behaviourPanel;
	private JPanel designPanel;
	private JTextField objName;
	private JTextField yPosition;
	private JTextField widthTextField;
	private JTextField xPosition;
	private JTextField heightTextField;
	private JTextField speedX;
	private JTextField speedY;
	private JComboBox movementBox;
	private JComboBox eventBox;
	private JComboBox eventWithBox;
	private JComboBox shapeBox;
	private JComboBox windowBox;
	private JComboBox actionBox;
	private JComboBox gameOptionBox;
	private JLabel movementLabel;
	private JLabel eventLabel;
	private JLabel shapeLabel;
	private JLabel colorLabel;
	private JLabel nameLabel;
	private JLabel positionLabel;
	private JLabel windowLabel;
	private JLabel heightLabel;
	private JLabel widthLabel;
	private JLabel bgColorLabel;
	private JLabel dimensionLabel;
	private JLabel speedXLabel;
	private JLabel speedYLabel;
	private JLabel winLabel;
	private JRadioButton rdbtnWin;
	private JRadioButton rdbtnLose;
	private ButtonGroup gameflag;
	private JButton addButton;
	private JButton playButton;
	private JButton saveButton;
	private JButton loadButton;
	private JButton colorButton;
	private JButton bgColorButton;
	private JButton clearButton;
	private GamePanel gamePanel;
	private Component component;
	private ComponentGroup group;
	private Reaction reaction;
	private BoundaryInteraction boundaryInteraction;
	private Color color;
	private Color bgColor;
	private JButton addReaction;
	private HashMap<Component,Reaction > reactMap = new HashMap<>();	
	DefaultComboBoxModel eventsWithModel ;
	//private JButton addBackGround;
	//private JButton addShape;
	BufferedImage image;
	JFileChooser fileChooser;
	private File file ;
	boolean isTileDynamic;
	private boolean started;
	String tileName;
	
	private static final String circleURL = "http://www.kidsmathgamesonline.com/images/pictures/shapes/circle.jpg";
	private static final String rectangleURL = "http://clipart-library.com/images/rcjrpp69i.png";
	private static final String triangleURL = "http://www.pngmart.com/files/4/Triangle-PNG-Clipart.png";
	private static final String soccerURL = "https://upload.wikimedia.org/wikipedia/en/thumb/e/ec/Soccer_ball.svg/900px-Soccer_ball.svg.png";
	private static final String spaceShipURL = "http://clipart-library.com/images/yTkAEopLc.png";
	private static final String spaceInvaderURL = "https://cyber-ny.com/invaders/58569ce1b9d62.png";
	private static final String froggerURL = "https://www.pinclipart.com/picdir/big/281-2815777_froggy-clipart-png-download.png";
	private static final String[] URLARRAY =  {circleURL, rectangleURL, triangleURL, soccerURL, spaceShipURL, spaceInvaderURL, froggerURL};

	
	private static final Logger logger = Logger.getLogger(GamePanel.class);

	public Gamemaker(GamePanel gp) {
		group = new ComponentGroup();
		started = false;
		setBounds(0, 0, 320, 710);
		this.setLayout(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		contentPane.setLayout(null);
		gamePanel = gp;
		gameTabs = new JTabbedPane(JTabbedPane.TOP);
		gameTabs.setBounds(0, 0, 325, 670);
		this.add(gameTabs);
		gameTabs.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

		toolPanel = new JPanel();
		gameTabs.addTab("Design", null, toolPanel, null);
		toolPanel.setLayout(null);
		toolPanel.setBounds(0, 0, 315, 670);

		movementBox = new JComboBox();
		movementBox.setBounds(20, 375, 113, 22);
		toolPanel.add(movementBox);

		speedXLabel = new JLabel("SpeedX");
		speedXLabel.setBounds(20, 390, 50, 50);
		toolPanel.add(speedXLabel);

		speedX = new JTextField("0");
		speedX.setBounds(75, 405, 43, 20);
		toolPanel.add(speedX);

		speedYLabel = new JLabel("SpeedY");
		speedYLabel.setBounds(125, 390, 50, 50);
		toolPanel.add(speedYLabel);

		speedY = new JTextField("0");
		speedY.setBounds(175, 405, 43, 20);
		speedY.setColumns(10);
		toolPanel.add(speedY);

		JLabel actionLabel = new JLabel("Action");
		actionLabel.setBounds(20, 436, 56, 16);
		toolPanel.add(actionLabel);

		actionBox = new JComboBox();
		actionBox.setBounds(20, 454, 113, 25);
		toolPanel.add(actionBox);


		eventBox = new JComboBox();
		eventBox.setBounds(140, 454, 97, 25);
		toolPanel.add(eventBox);



		JLabel eventWithLabel = new JLabel("Event With");
		eventWithLabel.setBounds(145, 481, 96, 25);
		toolPanel.add(eventWithLabel);

		eventsWithModel = new DefaultComboBoxModel();
		eventWithBox = new JComboBox(eventsWithModel);
		eventWithBox.setBounds(140, 500, 97, 25);
		toolPanel.add(eventWithBox);


		addReaction = new JButton("Add Event");
		addReaction.setBounds(100, 530, 100, 30);
		toolPanel.add(addReaction);

		movementLabel = new JLabel("Movement");
		movementLabel.setBounds(20, 354, 56, 16);
		toolPanel.add(movementLabel);

		eventLabel = new JLabel("Event");
		eventLabel.setBounds(148, 436, 56, 16);
		toolPanel.add(eventLabel);

		winLabel = new JLabel("Win/Lose");
		winLabel.setBounds(20, 481, 97, 25);
		toolPanel.add(winLabel);

		gameOptionBox = new JComboBox();
		gameOptionBox.setBounds(20, 500, 113, 22);
		toolPanel.add(gameOptionBox);

		addButton = new JButton("Add");
		addButton.setBounds(50, 570, 67, 25);
		toolPanel.add(addButton);

		playButton = new JButton("Play");
		playButton.setBounds(200, 570, 67, 25);
		toolPanel.add(playButton);

		saveButton = new JButton("Save");
		saveButton.setBounds(75, 10, 77, 25);
		toolPanel.add(saveButton);

		loadButton = new JButton("Load");
		loadButton.setBounds(160, 10, 77, 25);
		toolPanel.add(loadButton);

		clearButton = new JButton("Clear");
		clearButton.setBounds(125, 570, 67, 25);
		toolPanel.add(clearButton);

		gameflag = new ButtonGroup();
		gameflag.add(rdbtnLose);
		gameflag.add(rdbtnWin);

		designPanel = new JPanel();
		designPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Design",
				TitledBorder.CENTER, TitledBorder.TOP, null, Color.BLACK));
		designPanel.setBounds(6, 61, 298, 238);
		toolPanel.add(designPanel);
		designPanel.setLayout(null);

		bgColor = Color.WHITE;

		bgColorLabel = new JLabel("Background Color");
		bgColorLabel.setBounds(10,40,160,16);
		toolPanel.add(bgColorLabel);

//		addBackGround = new JButton("AddBgImage");
//		addBackGround.setBounds(170,40,100,20);
//		toolPanel.add(addBackGround);

		bgColorButton = new JButton();
		bgColorButton.setBounds(125, 40, 20, 20);
		bgColorButton.setBackground(bgColor);
		toolPanel.add(bgColorButton);

		objName = new JTextField();
		objName.setBounds(160, 50, 100, 20);
		designPanel.add(objName);
		objName.setColumns(10);

		yPosition = new JTextField();
		yPosition.setToolTipText("y Position");
		yPosition.setBounds(104, 127, 43, 20);
		designPanel.add(yPosition);
		yPosition.setText("0");
		yPosition.setColumns(10);

		widthTextField = new JTextField();
		widthTextField.setBounds(101, 205, 43, 20);
		designPanel.add(widthTextField);
		widthTextField.setText("0");
		widthTextField.setColumns(10);

		xPosition = new JTextField();
		xPosition.setToolTipText("x Position");
		xPosition.setBounds(38, 127, 43, 20);
		designPanel.add(xPosition);
		xPosition.setText("0");
		xPosition.setColumns(10);

		heightTextField = new JTextField();
		heightTextField.setBounds(203, 205, 43, 20);
		designPanel.add(heightTextField);
		heightTextField.setText("0");
		heightTextField.setColumns(10);

		color = Color.BLACK;

		colorButton = new JButton();
		colorButton.setBounds(188, 126, 20, 20);
		colorButton.setBackground(color);
		designPanel.add(colorButton);

		shapeLabel = new JLabel("Shape");
		shapeLabel.setBounds(25, 25, 56, 16);
		designPanel.add(shapeLabel);
		
//		addShape = new JButton("AddTile");
//		addShape.setBounds(25, 25, 100, 25);
//		designPanel.add(addShape);
			
		
		nameLabel = new JLabel("Name");
		nameLabel.setBounds(160, 25, 56, 16);
		designPanel.add(nameLabel);

		positionLabel = new JLabel("Position");
		positionLabel.setBounds(59, 98, 56, 16);
		designPanel.add(positionLabel);

		colorLabel = new JLabel("Color");
		colorLabel.setBounds(190, 98, 56, 16);
		designPanel.add(colorLabel);

		widthLabel = new JLabel("Width");
		widthLabel.setBounds(54, 207, 43, 16);
		designPanel.add(widthLabel);

		heightLabel = new JLabel("Height");
		heightLabel.setBounds(156, 207, 43, 16);
		designPanel.add(heightLabel);

		dimensionLabel = new JLabel("Dimensions");
		dimensionLabel.setBounds(110, 178, 69, 16);
		designPanel.add(dimensionLabel);

		windowLabel = new JLabel("Boundary Event");
		windowLabel.setBounds(148, 354, 133, 16);
		toolPanel.add(windowLabel);

		windowBox = new JComboBox();
		windowBox.setBounds(149, 375, 120, 22);
		toolPanel.add(windowBox);

		behaviourPanel = new JPanel();
		behaviourPanel.setBorder(new TitledBorder(null, "Behaviour", TitledBorder.CENTER, TitledBorder.TOP));
		behaviourPanel.setBounds(6, 331, 298, 280);
		toolPanel.add(behaviourPanel);



	}

	public void buildToolPanel() {
		String[] shapes = { "Circle", "Rectangle", "Triangle", "Soccer", "SpaceShip", "SpaceInvader","Frogger" };
		String dirPath = System.getProperty("user.dir") + File.separator + "GameData" + File.separator + "images" + File.separator;

		Vector model = new Vector();
		for (int index = 0; index < shapes.length; index++) {
			String imagePath = dirPath + shapes[index] + ".png";
			
			try {
				model.addElement(new ShapesModel(new ImageIcon(new URL(URLARRAY[index])), shapes[index]));
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				logger.debug(e1.getMessage());

			}
		}

		shapeBox = new JComboBox(model);
		shapeBox.setBounds(25, 50, 80, 20);
		designPanel.add(shapeBox);
		shapeBox.setRenderer(new ItemRenderer());

		String[] movements = { "No Movement", "Automatic Movement", "Horizontal (Arrows)","Horizontal (A,D)", "Vertical (Arrows)","Vertical (W,S)","Horizontal & Vertical (Arrows)","Horizontal & Vertical (A,D,W,S)"};
		String[] actions = {"No Action", "ShootUp", "ShootDown", "ShootLeft", "ShootRight", "ShootAll (T,F,G,H)"};
		String[] reactions = { "No Event", "Bounce", "Explode", "Stick" };

		movementBox.setModel(new DefaultComboBoxModel(movements));
		actionBox.setModel(new DefaultComboBoxModel(actions));
		eventBox.setModel(new DefaultComboBoxModel(reactions));

		String[] boundaryOptions = { "No Boundary Event", "Bounce", "Leave Forever", "Come Through","Move Down Boundary Interaction" };
		windowBox.setModel(new DefaultComboBoxModel(boundaryOptions));

		String[] gameOptions = { "No Win/Lose", "Win", "Lose" };
		gameOptionBox.setModel(new DefaultComboBoxModel(gameOptions));

		playButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
					
					if(!started) {
						gamePanel.startGame();
						started=true;
						playButton.setText("Pause");
					}
				    
					else if(!gamePanel.getIsPaused())
					{
					    gamePanel.pauseGame();
					    gamePanel.setIsPaused(true);
					    playButton.setText("Play");
					}
					
					else
					{
						gamePanel.resumeGame();
						gamePanel.setIsPaused(false);
						playButton.setText("Pause");
					}
				   
				
			}
		});
		// to add multiple reactions to the same component (eg: pacman bounces off the ball and eats the food)
		addReaction.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//				Object obj[] = reactionsWith.getSelectedValues();
				//				System.out.println(obj);
				//				/*for(int i=0; i<obj.length;i++) {
				//					
				//				}*/
				String eventWithString = (String) eventWithBox.getSelectedItem();
				String reactionString = (String) eventBox.getSelectedItem();

				switch (reactionString) {
				case ("Explode"):
					reaction = new ExplodeReaction();					
				break;
				case ("Bounce"):
					reaction = new BounceReaction();
				break;
				case ("Stick"):
					reaction = new StickReaction();
				break;
				default:
					reaction = new NoReaction();
				}

				Map<String, Component> compMap = group.getComponentMap();
				if (eventWithString.equals("All")) {
					for(Map.Entry<String, Component> comp : compMap.entrySet()) {
						reactMap.put(compMap.get(comp.getKey()), reaction);
					}
				}
				else {
					reactMap.put(compMap.get(eventWithString), reaction);
					System.out.println(reactMap);

				}


			}

		});

		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String shapeString = ((ShapesModel) shapeBox.getSelectedItem()).getName();
				String movementString = (String) movementBox.getSelectedItem();
				String actionString = (String) actionBox.getSelectedItem();
				String reactionString = (String) eventBox.getSelectedItem();
				String boundaryString = (String) windowBox.getSelectedItem();
				String eventWithString = (String) eventWithBox.getSelectedItem();
				int positionX = Integer.parseInt(xPosition.getText());
				int positionY = Integer.parseInt(yPosition.getText());
				String componentName = objName.getText();
				int componentHeight = Integer.parseInt(heightTextField.getText());
				int componentWidth = Integer.parseInt(widthTextField.getText());
				String gameOption= (String) gameOptionBox.getSelectedItem();
				HashMap<Component,Reaction > map1 = new HashMap<>(); 
				
				if ( !isTileDynamic ){
					switch (shapeString) {
					case "Circle":
						component = new Circle(color, positionX, positionY, componentWidth, componentHeight);
						break;
					case "Rectangle":
						component = new Rectangle(color, positionX, positionY, componentWidth, componentHeight);
						break;
					case "Triangle":
						component = new Triangle(color, positionX, positionY, componentWidth, componentHeight);
						break;
					case "Soccer":
						component = new ImageSprite(URLARRAY[3], positionX, positionY, componentWidth, componentHeight);
						break;
					case "SpaceShip":
						component = new ImageSprite(URLARRAY[4], positionX, positionY, componentWidth, componentHeight);
						break;
					case "SpaceInvader":
						component = new ImageSprite(URLARRAY[5], positionX, positionY, componentWidth, componentHeight);
						break;
					case "Frogger":
						component = new ImageSprite(URLARRAY[6], positionX, positionY, componentWidth, componentHeight);
						break;
					default:
						break;
					}
				}
				else
				{
					component = new ImageSprite(tileName.trim(), positionX, positionY, componentWidth, componentHeight);
					isTileDynamic = false;
				}
					
				component.setName(componentName);
				group.addComponent(component.getName(), component);
				
				populateEventsWithCombo();

				//listModel.addElement(componentName);

				//}

				gamePanel.drawComponents(group);

				switch (movementString) {
				case ("Automatic Movement"):
					component.setMovement(new AutomaticMovement(Integer.parseInt(speedX.getText()),
							Integer.parseInt(speedY.getText())));
				break;
				case ("Horizontal (Arrows)"):
					component.setMovement(
							new HorizontalMovement(Math.abs(Integer.parseInt(speedX.getText())), "arrows"));
				break;
				case ("Horizontal (A,D)"):
					component.setMovement(new HorizontalMovement(Math.abs(Integer.parseInt(speedX.getText())), "keys"));
				break;
				case ("Vertical (Arrows)"):
					component.setMovement(new VerticalMovement(Math.abs(Integer.parseInt(speedY.getText())), "arrows"));
				break;
				case ("Vertical (W,S)"):
					component.setMovement(new VerticalMovement(Math.abs(Integer.parseInt(speedY.getText())), "keys"));
				break;
				case ("Horizontal & Vertical (Arrows)"):
					component.setMovement(new HorizontalVerticalKeyMovements(Math.abs(Integer.parseInt(speedX.getText())),Math.abs(Integer.parseInt(speedY.getText())),"arrows"));
				break;
				case ("Horizontal & Vertical (A,D,W,S)"):
					component.setMovement(new HorizontalVerticalKeyMovements(Math.abs(Integer.parseInt(speedX.getText())),Math.abs(Integer.parseInt(speedY.getText())),"keys"));
				break;
				default:
					component.setMovement(new NoMovement());
				}

				Action action = new NoAction();
				switch (actionString) {
				case ("ShootUp"):
				case ("ShootDown"):
				case ("ShootLeft"):
				case ("ShootRight"):
				case ("ShootAll (T,F,G,H)"):	
					action = new Shoot(group, actionString);
				break;
				default:
					break;
				}
				component.setAction(action);

				switch (reactionString) {
				case ("Explode"):
					reaction = new ExplodeReaction();					
				break;
				case ("Bounce"):
					reaction = new BounceReaction();
				break;
				case ("Stick"):
					reaction = new StickReaction();
				break;
				default:
					reaction = new NoReaction();
				}



				if(!reactMap.isEmpty()) {
					component.setReactionsMap(reactMap);
					reactMap.clear();
				}
				else {
					component.setReaction(reaction);
				}


				switch (boundaryString) {
				case ("Bounce"):
					boundaryInteraction = new BounceBoundaryInteraction();
				break;
				case ("Leave Forever"):
					boundaryInteraction = new LeaveForeverBoundaryInteraction();
				break;
				case ("Come Through"):
				{	boundaryInteraction = new ComeThroughBoundaryInteraction();
				//System.out.println("Come Through Switch Case");
				break;
				}
				case ("Move Down Boundary Interaction"):
					boundaryInteraction = new MoveDownBoundaryInteraction();
				break;
				default:
					boundaryInteraction = new NoBoundaryInteraction(gamePanel, group);	

				}
				component.setBoundaryInteraction(boundaryInteraction);

				switch (gameOption) {
				case "Win":
					group.addWinning(componentName, reaction);
					break;
				case "Lose":
					group.addLosing(componentName, reaction);
					break;
				default:
					break;
				}
				gamePanel.pauseGame();

			}


		});

		saveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				GameDataOperations dataOperations = new GameDataOperations();
				//System.out.println(group.getFile());
				dataOperations.saveToFile(group);
			}
		});

		loadButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				GameDataOperations dataOperations = new GameDataOperations();
//				JFileChooser fileChooser = new JFileChooser();
//				fileChooser.setCurrentDirectory(new java.io.File(dataOperations.getStoragePath()));
//				int result = fileChooser.showOpenDialog(getParent());
//				File selectedFile = null;
//				if (result == JFileChooser.APPROVE_OPTION) {
//					selectedFile = fileChooser.getSelectedFile();
//				}
				
				//File file = new File(getStoragePath()+"game"+".sav");
				group = dataOperations.readObjectFromFile(dataOperations.getStoragePath()+"game.sav");
				File file = group.getFile();
				//System.out.println(group.getFile());
				if( file != null)
				{
					try {
						image = ImageIO.read(group.getFile());
						gamePanel.setImage(image, true);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						logger.error(e1.getMessage());
						e1.printStackTrace();
					}
					
				}
				
				populateEventsWithCombo();
				gamePanel.drawComponents(group);
			}
		});

		colorButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Color prevColor = color;
				color = JColorChooser.showDialog(null, "Choose a Color", color);
				if (color == null) {
					color = prevColor;
				}
				colorButton.setBackground(color);
			}

		});

		bgColorButton.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Color prevColor = bgColor;
				bgColor = JColorChooser.showDialog(null, "Choose a Color", bgColor);
				if( bgColor == null) {
					bgColor = prevColor;
				}
				bgColorButton.setBackground(bgColor);
				setBackgroundColor(bgColor);
				group.setColor(bgColor);
			}

		});
		
//		addShape.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				fileChooser = new JFileChooser();
//				fileChooser.setMultiSelectionEnabled(false);
//				int option = fileChooser.showOpenDialog(gamePanel);		            
//				if(option == JFileChooser.APPROVE_OPTION){
//					file = fileChooser.getSelectedFile();
//					isTileDynamic = true;
//					tileName = file.getName();
//					System.out.println(tileName);	
//				}
//				
//			}
//		});

//		addBackGround.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				fileChooser = new JFileChooser();
//				fileChooser.setMultiSelectionEnabled(false);
//
//
//				if ( addBackGround.getText().equalsIgnoreCase("RemoveBGImage"))
//				{
//					gamePanel.removeImage(false);
//					gamePanel.isSetBgColor(true);
//					addBackGround.setText("AddBgImage");
//					gamePanel.revalidate();
//					gamePanel.repaint();	
//				}
//				else
//				{
//					int option = fileChooser.showOpenDialog(gamePanel);		            
//					if(option == JFileChooser.APPROVE_OPTION){
//						file = fileChooser.getSelectedFile();
//						
//						try 
//						{
//							image = ImageIO.read(file);
//							gamePanel.setImage(image,true);
//							gamePanel.isSetBgColor(false);
//							addBackGround.setText("RemoveBGImage");
//							System.out.println("calling revalidate");
//							gamePanel.revalidate();
//							gamePanel.repaint();
//							group.setFile(file);
//							System.out.println(group.getFile());
//							group.setBackGroundImage(image);
//						}
//						catch (IOException e1) 
//						{
//							logger.error(e1.getMessage());
//						}
//					}
//				}
//
//			}
//
//		});

		clearButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				resetForm();

			}
		});
	}

	public void setBackgroundColor(Color color)
	{
		gamePanel.setBackground(color);
		gamePanel.isSetBgColor(true);
		gamePanel.removeImage(false);

	}
	public void resetForm()
	{
		//reset the entire form here
		objName.setText("");
		yPosition.setText("");
		widthTextField.setText("");
		xPosition.setText("");
		heightTextField.setText("");
		speedX.setText("");
		speedY.setText("");
		movementBox.setSelectedIndex(0);
		eventBox.setSelectedIndex(0);
		shapeBox.setSelectedIndex(0);
		windowBox.setSelectedIndex(0);
		actionBox.setSelectedIndex(0);
		gameOptionBox.setSelectedIndex(0);
	}
	
	public void populateEventsWithCombo(){
		Map<String, Component> map = group.getComponentMap();
		//eventWithBox.setModel(new DefaultComboBoxModel((Vector) componentMap.keySet()));
		for (Map.Entry<String, Component> comp : map.entrySet()) {


		if(eventsWithModel.getIndexOf(comp.getKey()) == -1 ) {
			eventsWithModel.addElement(comp.getKey());
		}
		if (eventsWithModel.getSize()==2) {
			eventsWithModel.addElement("All");
		}
		}
	}
}
