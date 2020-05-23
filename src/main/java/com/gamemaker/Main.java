package com.gamemaker;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import org.apache.log4j.BasicConfigurator;

public class Main extends JFrame {
	
	private static final long serialVersionUID = 1L;
	

	public static void main(String[] args) {
		
		
		
		BasicConfigurator.configure(); // configuration of log4j.
		JFrame gameMaker = new JFrame(); // Initial Empty window opened. This the main JFrame
		gameMaker.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameMaker.setResizable(false);
		gameMaker.setBounds(100, 100, 900, 800);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		gameMaker.add(contentPane);
		contentPane.setLayout(null);
		gameMaker.setVisible(true);
		
		
		
		GamePanel gamePanel = new GamePanel(); // This is the right side panel for playing the game
		
		gamePanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		//gamePanel.setBounds(320, 0, 490, 610);
		gamePanel.setBounds(320, 20, 490, 600);
	
		contentPane.add(gamePanel);
		gamePanel.setBackground(Color.WHITE);
		gamePanel.setLayout(null);
		gamePanel.revalidate();

		
		/*
		 * JButton pauseButton = new JButton("Pause"); pauseButton.setBounds(400, 640,
		 * 150, 30); contentPane.add(pauseButton); //pauseButton.setFocusable(false);
		 * pauseButton.addActionListener(new ActionListener() {
		 * 
		 * @Override public void actionPerformed(ActionEvent e) { // TODO Auto-generated
		 * method stub if(!gamePanel.getIsPaused()) { gamePanel.pauseGame();
		 * gamePanel.setIsPaused(true); }
		 * 
		 * else { gamePanel.resumeGame(); gamePanel.setIsPaused(false); }
		 * 
		 * 
		 * }
		 * 
		 * });
		 */
		
		
		Gamemaker gm = new Gamemaker(gamePanel); // The left side panel for making the game.
		contentPane.add(gm);
		gm.revalidate();
		gm.buildToolPanel();
		
		
		
	
	}
}
