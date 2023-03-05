import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


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
 
 * Credits: Kelly Charles (2020)
 */ 
public class Viewer extends JPanel {
	private long CurrentAnimationTime= 0; 
	
	Model gameworld =new Model(); 
	 
	public Viewer(Model World) {
		this.gameworld=World;
		// TODO Auto-generated constructor stub
	}

	public Viewer(LayoutManager layout) {
		super(layout);
		// TODO Auto-generated constructor stub
	}

	public Viewer(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	public Viewer(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	public void updateview() {
		
		this.repaint();
		// TODO Auto-generated method stub
		
	}
	
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		CurrentAnimationTime++; // runs animation time step 
		
		
		//Draw player Game Object 
		int x = (int) gameworld.getPlayer1().getCentre().getX();
		int y = (int) gameworld.getPlayer1().getCentre().getY();
		int width = gameworld.getPlayer1().getWidth();
		int height = gameworld.getPlayer1().getHeight();
		String texture = gameworld.getPlayer1().getTexture();
		
		//Draw background 
		drawBackground(g);

		//Draw the ground
		drawGround((int)gameworld.getGround().getCentre().getX(),
				(int)gameworld.getGround().getCentre().getY(),
				(int)gameworld.getGround().getWidth(),
				(int)gameworld.getGround().getHeight(),
				gameworld.getGround().getTexture(), g);

		//Draw player
		drawPlayer((int)gameworld.getPlayer1().getCentre().getX(),
				(int)gameworld.getPlayer1().getCentre().getY(),
				gameworld.getPlayer1().getTexture(), g);

		drawPlayer((int)gameworld.getPlayer2().getCentre().getX(),
				(int)gameworld.getPlayer2().getCentre().getY(),
				gameworld.getPlayer2().getTexture(), g);

		drawPlayerStats(gameworld.getPlayer1(),
				(int)gameworld.getPlayer1().getHealthBar().getCentre().getX(),
				(int)gameworld.getPlayer1().getHealthBar().getCentre().getY() + 50,
				(int)gameworld.getPlayer1().getHealthBar().getCentre().getX() + 70,
				(int)gameworld.getPlayer1().getHealthBar().getCentre().getY() + 120,
				g);

		drawPlayerStats(gameworld.getPlayer2(),
				(int)gameworld.getPlayer2().getHealthBar().getCentre().getX(),
				(int)gameworld.getPlayer1().getHealthBar().getCentre().getY() + 50,
				(int)gameworld.getPlayer2().getHealthBar().getCentre().getX() + 40,
				(int)gameworld.getPlayer2().getHealthBar().getCentre().getY() + 120,
				g);

		//Draw Power ups
		gameworld.getPowerUpList().forEach((powerUp) ->{
			drawPowerUps((int) powerUp.getCentre().getX(), (int)powerUp.getCentre().getY(), powerUp.getWidth(), powerUp.getHeight(), powerUp.getTexture(), g);
		});

		drawPlayer1Health((int)gameworld.getPlayer1().getHealthBar().getCentre().getX(),
				(int)gameworld.getPlayer1().getHealthBar().getCentre().getY(),
				(int)gameworld.getPlayer1().getHealthBar().getCentre().getX() + 150,
				(int)gameworld.getPlayer1().getHealthBar().getCentre().getY() + 50,
				gameworld.getPlayer1().getHealthBar().getTexture(), g);

		drawPlayer2Health((int)gameworld.getPlayer2().getHealthBar().getCentre().getX(),
				(int)gameworld.getPlayer2().getHealthBar().getCentre().getY(),
				(int)gameworld.getPlayer2().getHealthBar().getCentre().getX() + 100,
				(int)gameworld.getPlayer2().getHealthBar().getCentre().getY() + 50,
				gameworld.getPlayer2().getHealthBar().getTexture(), g);


		//drawPlayerHitBox(gameworld.getPlayer1HitBox(), g);
		//drawPlayerHitBox(gameworld.getPlayer2HitBox(), g);
		//drawGroundHitBox(gameworld.getGroundHitBox(), g);
		//Draw Bullets
		// change back
	}

	private void drawPlayerStats(Fighter player, int x, int y, int textX, int textY, Graphics g) {
		File TextureToLoad = new File("Warrior's Peak (Game)/BasicGameTemplate/res/CharacterInfoScreen.png");  //should work okay on OSX and Linux but check if you have issues depending your eclipse install or if your running this without an IDE
		try {
			Image myImage = ImageIO.read(TextureToLoad);
			g.drawImage(myImage, x, y, 300, 150, null);
			g.setFont(new Font("AvantGrande", Font.BOLD, 20));
			g.setColor(Color.WHITE);
			g.drawString("Attack Power: " + player.attackPower, textX, textY);
			g.drawString("Defence: " + player.defence, textX, textY + 30);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void drawPlayer1Health(int x, int y, int textX, int textY, String texture, Graphics g) {
		File TextureToLoad = new File(texture);  //should work okay on OSX and Linux but check if you have issues depending your eclipse install or if your running this without an IDE
		try {
			Image myImage = ImageIO.read(TextureToLoad);
			g.drawImage(myImage, x, y, 300, 100, null);
			g.setFont(new Font("AvantGrande", Font.BOLD, 28));
			g.setColor(Color.BLACK);
			g.drawString(Integer.toString((int)gameworld.getPlayer1().getHealthBar().getHealthPoints()), textX, textY);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void drawPlayer2Health(int x, int y, int textX, int textY, String texture, Graphics g) {
		File TextureToLoad = new File(texture);  //should work okay on OSX and Linux but check if you have issues depending your eclipse install or if your running this without an IDE
		try {
			Image myImage = ImageIO.read(TextureToLoad);
			g.drawImage(myImage, x, y, 300, 100, null);
			g.setFont(new Font("AvantGrande", Font.BOLD, 28));
			g.setColor(Color.BLACK);
			g.drawString(Integer.toString((int)gameworld.getPlayer2().getHealthBar().getHealthPoints()), textX, textY);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	private void drawPlayerHitBox(Rectangle playerHB, Graphics g) {
		if(gameworld.colliding())
			g.setColor(Color.RED);
		else
			g.setColor(Color.GREEN);
		g.drawRect((int)playerHB.getX(), (int)playerHB.getY(), (int)playerHB.getWidth(), (int)playerHB.getHeight());
	}

	private void drawGroundHitBox(Rectangle groundHB, Graphics g) {
		if(gameworld.groundCollision())
			g.setColor(Color.RED);
		else
			g.setColor(Color.GREEN);
		g.drawRect((int)groundHB.getX(), (int)groundHB.getY(), (int)groundHB.getWidth(), (int)groundHB.getHeight());
	}

	private void drawGround(int x, int y, int width, int height, String texture, Graphics g) {
		File TextureToLoad = new File(texture);  //should work okay on OSX and Linux but check if you have issues depending your eclipse install or if your running this without an IDE
		try {
			Image myImage = ImageIO.read(TextureToLoad);
			//The spirte is 32x32 pixel wide and 4 of them are placed together so we need to grab a different one each time
			//remember your training :-) computer science everything starts at 0 so 32 pixels gets us to 31
			int currentPositionInAnimation= ((int) (CurrentAnimationTime%4 )*32); //slows down animation so every 10 frames we get another frame so every 100ms
			g.drawImage(myImage, x, y, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void drawPowerUps(int x, int y, int width, int height, String texture, Graphics g) {
		File TextureToLoad = new File(texture);  //should work okay on OSX and Linux but check if you have issues depending your eclipse install or if your running this without an IDE
		try {
			Image myImage = ImageIO.read(TextureToLoad);
			//The spirte is 32x32 pixel wide and 4 of them are placed together so we need to grab a different one each time
			//remember your training :-) computer science everything starts at 0 so 32 pixels gets us to 31
			//int currentPositionInAnimation= ((int) (CurrentAnimationTime%4 )*32); //slows down animation so every 10 frames we get another frame so every 100ms
			//g.drawImage(myImage, x,y, x+width, y+height, currentPositionInAnimation  , 0, currentPositionInAnimation+31, 32, null);
			g.drawImage(myImage, x, y, width, height, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void drawBackground(Graphics g)
	{
		File TextureToLoad = new File("Warrior's Peak (Game)/BasicGameTemplate/res/Sky.png");  //should work okay on OSX and Linux but check if you have issues depending your eclipse install or if your running this without an IDE
		try {
			Image myImage = ImageIO.read(TextureToLoad); 
			 g.drawImage(myImage, 0,0, 1000, 1000, 0 , 0, 1000, 1000, null); 
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void drawBullet(int x, int y, int width, int height, String texture,Graphics g)
	{
		File TextureToLoad = new File(texture);  //should work okay on OSX and Linux but check if you have issues depending your eclipse install or if your running this without an IDE 
		try {
			Image myImage = ImageIO.read(TextureToLoad); 
			//64 by 128 
			 g.drawImage(myImage, x,y, x+width, y+height, 0 , 0, 63, 127, null); 
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	private void drawPlayer(int x, int y, String texture, Graphics g) {
		File TextureToLoad = new File(texture);  //should work okay on OSX and Linux but check if you have issues depending your eclipse install or if your running this without an IDE 
		try {
			Image myImage = ImageIO.read(TextureToLoad);
			//The spirte is 32x32 pixel wide and 4 of them are placed together so we need to grab a different one each time 
			//remember your training :-) computer science everything starts at 0 so 32 pixels gets us to 31  
			//int currentPositionInAnimation= ((int) ((CurrentAnimationTime%40)/10))*32; //slows down animation so every 10 frames we get another frame so every 100ms
			//g.drawImage(myImage, x,y, x+width, y+height, currentPositionInAnimation  , 0, currentPositionInAnimation+31, 32, null);
			g.drawImage(myImage, x, y, null);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		 
		//g.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, observer));
		//Lighnting Png from https://opengameart.org/content/animated-spaceships  its 32x32 thats why I know to increament by 32 each time 
		// Bullets from https://opengameart.org/forumtopic/tatermands-art 
		// background image from https://www.needpix.com/photo/download/677346/space-stars-nebula-background-galaxy-universe-free-pictures-free-photos-free-images
		
	}
}


/*
 * 
 * 
 *              VIEWER HMD into the world                                                             
                                                                                
                                      .                                         
                                         .                                      
                                             .  ..                              
                               .........~++++.. .  .                            
                 .   . ....,++??+++?+??+++?++?7ZZ7..   .                        
         .   . . .+?+???++++???D7I????Z8Z8N8MD7I?=+O$..                         
      .. ........ZOZZ$7ZZNZZDNODDOMMMMND8$$77I??I?+?+=O .     .                 
      .. ...7$OZZ?788DDNDDDDD8ZZ7$$$7I7III7??I?????+++=+~.                      
       ...8OZII?III7II77777I$I7II???7I??+?I?I?+?+IDNN8??++=...                  
     ....OOIIIII????II?I??II?I????I?????=?+Z88O77ZZO8888OO?++,......            
      ..OZI7III??II??I??I?7ODM8NN8O8OZO8DDDDDDDDD8DDDDDDDDNNNOZ= ......   ..    
     ..OZI?II7I?????+????+IIO8O8DDDDD8DNMMNNNNNDDNNDDDNDDNNNNNNDD$,.........    
      ,ZII77II?III??????DO8DDD8DNNNNNDDMDDDDDNNDDDNNNDNNNNDNNNNDDNDD+.......   .
      7Z??II7??II??I??IOMDDNMNNNNNDDDDDMDDDDNDDNNNNNDNNNNDNNDMNNNNNDDD,......   
 .  ..IZ??IIIII777?I?8NNNNNNNNNDDDDDDDDNDDDDDNNMMMDNDMMNNDNNDMNNNNNNDDDD.....   
      .$???I7IIIIIIINNNNNNNNNNNDDNDDDDDD8DDDDNM888888888DNNNNNNDNNNNNNDDO.....  
       $+??IIII?II?NNNNNMMMMMDN8DNNNDDDDZDDNN?D88I==INNDDDNNDNMNNMNNNNND8:..... 
   ....$+??III??I+NNNNNMMM88D88D88888DDDZDDMND88==+=NNNNMDDNNNNNNMMNNNNND8......
.......8=+????III8NNNNMMMDD8I=~+ONN8D8NDODNMN8DNDNNNNNNNM8DNNNNNNMNNNNDDD8..... 
. ......O=??IIIIIMNNNMMMDDD?+=?ONNNN888NMDDM88MNNNNNNNNNMDDNNNMNNNMMNDNND8......
........,+++???IINNNNNMMDDMDNMNDNMNNM8ONMDDM88NNNNNN+==ND8NNNDMNMNNNNNDDD8......
......,,,:++??I?ONNNNNMDDDMNNNNNNNNMM88NMDDNN88MNDN==~MD8DNNNNNMNMNNNDND8O......
....,,,,:::+??IIONNNNNNNDDMNNNNNO+?MN88DN8DDD888DNMMM888DNDNNNNMMMNNDDDD8,.... .
...,,,,::::~+?+?NNNNNNNMD8DNNN++++MNO8D88NNMODD8O88888DDDDDDNNMMMNNNDDD8........
..,,,,:::~~~=+??MNNNNNNNND88MNMMMD888NNNNNNNMODDDDDDDDND8DDDNNNNNNDDD8,.........
..,,,,:::~~~=++?NMNNNNNNND8888888O8DNNNNNNMMMNDDDDDDNMMNDDDOO+~~::,,,.......... 
..,,,:::~~~~==+?NNNDDNDNDDNDDDDDDDDNNND88OOZZ$8DDMNDZNZDZ7I?++~::,,,............
..,,,::::~~~~==7DDNNDDD8DDDDDDDD8DD888OOOZZ$$$7777OOZZZ$7I?++=~~:,,,.........   
..,,,,::::~~~~=+8NNNNNDDDMMMNNNNNDOOOOZZZ$$$77777777777II?++==~::,,,......  . ..
...,,,,::::~~~~=I8DNNN8DDNZOM$ZDOOZZZZ$$$7777IIIIIIIII???++==~~::,,........  .  
....,,,,:::::~~~~+=++?I$$ZZOZZZZZ$$$$$777IIII?????????+++==~~:::,,,...... ..    
.....,,,,:::::~~~~~==+?II777$$$$77777IIII????+++++++=====~~~:::,,,........      
......,,,,,:::::~~~~==++??IIIIIIIII?????++++=======~~~~~~:::,,,,,,.......       
.......,,,,,,,::::~~~~==+++???????+++++=====~~~~~~::::::::,,,,,..........       
.........,,,,,,,,::::~~~======+======~~~~~~:::::::::,,,,,,,,............        
  .........,.,,,,,,,,::::~~~~~~~~~~:::::::::,,,,,,,,,,,...............          
   ..........,..,,,,,,,,,,::::::::::,,,,,,,,,.,....................             
     .................,,,,,,,,,,,,,,,,.......................                   
       .................................................                        
           ....................................                                 
               ....................   .                                         
                                                                                
                                                                                
                                                                 GlassGiant.com
                                                                 */
