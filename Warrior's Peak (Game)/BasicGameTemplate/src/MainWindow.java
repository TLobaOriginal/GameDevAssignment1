import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javafx.embed.swing.JFXPanel;
import util.UnitTests;

/*
 * Created by Abraham Campbell on 15/01/2020.
 *   Copyright (c) 2020  Abraham Campbell

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
   
   (MIT LICENSE ) e.g do what you want with this :-) 
 */ 



public class MainWindow {
	 private static JFrame frame = new JFrame("Game");   // Change to the name of your game
	 private static Model gameworld = new Model();
	 private static Viewer canvas = new  Viewer(gameworld);
	 private KeyListener Controller = new Controller();
	 private static int TargetFPS = 100;
	 private static boolean characterBio1 = false;
	 private static boolean characterBio2 = false;
	 private static boolean startFight = false;
	 private JLabel BackgroundImageForStartMenu;
	 private final JFXPanel fxPanel = new JFXPanel();
	 public static String resourcePath = "Warrior's Peak (Game)/BasicGameTemplate/res/";

	 public MainWindow() {
		frame.setSize(900, 700);  					// you can customise this later and adapt it to change on size.
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   //If exit // you can modify with your way of quitting , just is a template.
		frame.setLayout(null);
		frame.add(canvas);
		canvas.setBounds(0, 0, 880, 700);
		canvas.setBackground(new Color(255,255,255)); 	//white background  replaced by Space background but if you remove the background method this will draw a white screen
		canvas.setVisible(false);   							// this will become visible after you press the key.

		 JButton startGameMenuButton = new JButton("Start Game");
		 JButton continueButton1 = new JButton("Continue");
		 JButton continueButton2 = new JButton("Start Fight");// start button

		 startGameMenuButton.addActionListener(new ActionListener() {
			 @Override
			 public void actionPerformed(ActionEvent e) {
				 startGameMenuButton.setVisible(false);
				 BackgroundImageForStartMenu.setVisible(false);
				 File BackgroundToLoad = new File("Warrior's Peak (Game)/BasicGameTemplate/res/CharacterBio1.jpg");  //should work okay on OSX and Linux but check if you have issues depending your eclipse install or if your running this without an IDE
				 try {
					 BufferedImage myPicture = ImageIO.read(BackgroundToLoad);
					 BackgroundImageForStartMenu = new JLabel(new ImageIcon(myPicture));
					 BackgroundImageForStartMenu.setBounds(0, 0, 880, 700);
					 frame.add(BackgroundImageForStartMenu);
				 }  catch (IOException ex) {
					 ex.printStackTrace();
				 }

				 frame.add(continueButton1);
				 continueButton1.setVisible(true);
				 frame.setVisible(true);
			 }
		 });


		  continueButton1.addActionListener(new ActionListener() {
			  @Override
			  public void actionPerformed(ActionEvent e) {
				  continueButton1.setVisible(false);
				  BackgroundImageForStartMenu.setVisible(false);
				  File BackgroundToLoad = new File("Warrior's Peak (Game)/BasicGameTemplate/res/CharacterBio2.jpg");  //should work okay on OSX and Linux but check if you have issues depending your eclipse install or if your running this without an IDE
				  try {

					  BufferedImage myPicture = ImageIO.read(BackgroundToLoad);
					  BackgroundImageForStartMenu = new JLabel(new ImageIcon(myPicture));
					  BackgroundImageForStartMenu.setBounds(0, 0, 880, 700);
					  frame.add(BackgroundImageForStartMenu);
				  }  catch (IOException ex) {
					  ex.printStackTrace();
				  }

				  frame.add(continueButton2);
				  continueButton2.setVisible(true);
				  frame.setVisible(true);
			  }
		  });

		continueButton2.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				continueButton2.setVisible(false);
				BackgroundImageForStartMenu.setVisible(false);
				canvas.setVisible(true);
				canvas.addKeyListener(Controller);    //adding the controller to the Canvas
	            canvas.requestFocusInWindow();  	  // making sure that the Canvas is in focus so keyboard input will be taking in .
				startFight =true;
			}});

		 startGameMenuButton.setBounds(300, 500, 200, 70);
		 continueButton1.setBounds(600, 500, 200, 70);
		 continueButton2.setBounds(700, 500, 150, 50);
	        
		//loading background image
		File BackgroundToLoad = new File("Warrior's Peak (Game)/BasicGameTemplate/res/startscreen.png");  //should work okay on OSX and Linux but check if you have issues depending your eclipse install or if your running this without an IDE
		try {
				 
			BufferedImage myPicture = ImageIO.read(BackgroundToLoad);
			BackgroundImageForStartMenu = new JLabel(new ImageIcon(myPicture));
			BackgroundImageForStartMenu.setBounds(0, 0, 900, 700);
			frame.add(BackgroundImageForStartMenu);
		}  catch (IOException e) {
			e.printStackTrace();
		}
			 
		frame.add(startGameMenuButton);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		MainWindow hello = new MainWindow();  //sets up environment
		boolean gameOver = false;
		while(!gameOver)   //not nice but remember we do just want to keep looping till the end.  // this could be replaced by a thread but again we want to keep things simple
		{ 
			//swing has timer class to help us time this but I'm writing my own, you can of course use the timer, but I want to set FPS and display it

			int TimeBetweenFrames =  1000 / TargetFPS;
			long FrameCheck = System.currentTimeMillis() + (long) TimeBetweenFrames; 
			
			//wait till next time step 
		 	while (FrameCheck > System.currentTimeMillis()){}
			
			if(characterBio1){

			}
			if(characterBio2){

			}
			if(startFight)
			{
				gameOver = gameLoop();
			}
			
			//UNIT test to see if framerate matches 
		 UnitTests.CheckFrameRate(System.currentTimeMillis(),FrameCheck, TargetFPS);
		}
	} 
	//Basic Model-View-Controller pattern 
	private static boolean gameLoop() {
		// model update
		boolean gameOver = gameworld.gameLogic();
		// view update
		canvas.updateview();
		// Both these calls could be setup as  a thread but we want to simplify the game logic for you.  
		//score update  
		 frame.setTitle("Warrior's Peak (Game): Round " + gameworld.getRound());
		 return gameOver;
	}

}