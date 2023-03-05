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
	Model gameWorld =new Model();
	 
	public Viewer(Model World) {
		this.gameWorld = World;
	}

	public Viewer(LayoutManager layout) {
		super(layout);
	}

	public Viewer(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
	}

	public Viewer(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
	}

	public void updateView() {
		this.repaint();
	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		//Draw background 
		drawBackground(g);

		//Draw the ground
		drawGround((int) gameWorld.getGround().getCentre().getX(),
				(int) gameWorld.getGround().getCentre().getY(),
				gameWorld.getGround().getTexture(), g);

		//Draw player
		drawPlayer((int) gameWorld.getPlayer1().getCentre().getX(),
				(int) gameWorld.getPlayer1().getCentre().getY(),
				gameWorld.getPlayer1().getTexture(), g);

		drawPlayer((int) gameWorld.getPlayer2().getCentre().getX(),
				(int) gameWorld.getPlayer2().getCentre().getY(),
				gameWorld.getPlayer2().getTexture(), g);

		//Draw Payer stats
		drawPlayerStats(gameWorld.getPlayer1(),
				(int) gameWorld.getPlayer1().getHealthBar().getCentre().getX(),
				(int) gameWorld.getPlayer1().getHealthBar().getCentre().getY() + 50,
				(int) gameWorld.getPlayer1().getHealthBar().getCentre().getX() + 70,
				(int) gameWorld.getPlayer1().getHealthBar().getCentre().getY() + 120,
				g);

		drawPlayerStats(gameWorld.getPlayer2(),
				(int) gameWorld.getPlayer2().getHealthBar().getCentre().getX(),
				(int) gameWorld.getPlayer1().getHealthBar().getCentre().getY() + 50,
				(int) gameWorld.getPlayer2().getHealthBar().getCentre().getX() + 40,
				(int) gameWorld.getPlayer2().getHealthBar().getCentre().getY() + 120,
				g);

		//Draw Power ups
		gameWorld.getPowerUpList().forEach((powerUp) ->{
			drawPowerUps((int) powerUp.getCentre().getX(), (int)powerUp.getCentre().getY(), powerUp.getWidth(), powerUp.getHeight(), powerUp.getTexture(), g);
		});

		drawPlayer1Health((int) gameWorld.getPlayer1().getHealthBar().getCentre().getX(),
				(int) gameWorld.getPlayer1().getHealthBar().getCentre().getY(),
				(int) gameWorld.getPlayer1().getHealthBar().getCentre().getX() + 150,
				(int) gameWorld.getPlayer1().getHealthBar().getCentre().getY() + 50,
				gameWorld.getPlayer1().getHealthBar().getTexture(), g);

		drawPlayer2Health((int) gameWorld.getPlayer2().getHealthBar().getCentre().getX(),
				(int) gameWorld.getPlayer2().getHealthBar().getCentre().getY(),
				(int) gameWorld.getPlayer2().getHealthBar().getCentre().getX() + 100,
				(int) gameWorld.getPlayer2().getHealthBar().getCentre().getY() + 50,
				gameWorld.getPlayer2().getHealthBar().getTexture(), g);
	}

	private void drawPlayerStats(Fighter player, int x, int y, int textX, int textY, Graphics g) {
		File TextureToLoad = new File(gameWorld.resourcePath + "CharacterInfoScreen.png");  //should work okay on OSX and Linux but check if you have issues depending your eclipse install or if your running this without an IDE
		try {
			Image myImage = ImageIO.read(TextureToLoad);
			g.drawImage(myImage, x, y, 300, 150, null);
			g.setFont(new Font("AvantGrande", Font.BOLD, 20));
			g.setColor(Color.WHITE);
			g.drawString("Attack Power: " + player.attackPower, textX, textY);
			g.drawString("Defence: " + player.defence, textX, textY + 30);
		} catch (IOException e) {
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
			g.drawString(Integer.toString((int) gameWorld.getPlayer1().getHealthBar().getHealthPoints()), textX, textY);
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
			g.drawString(Integer.toString((int) gameWorld.getPlayer2().getHealthBar().getHealthPoints()), textX, textY);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	private void drawPlayerHitBox(Rectangle playerHB, Graphics g) {
		if(gameWorld.colliding())
			g.setColor(Color.RED);
		else
			g.setColor(Color.GREEN);
		g.drawRect((int)playerHB.getX(), (int)playerHB.getY(), (int)playerHB.getWidth(), (int)playerHB.getHeight());
	}

	private void drawGroundHitBox(Rectangle groundHB, Graphics g) {
		if(gameWorld.groundCollision())
			g.setColor(Color.RED);
		else
			g.setColor(Color.GREEN);
		g.drawRect((int)groundHB.getX(), (int)groundHB.getY(), (int)groundHB.getWidth(), (int)groundHB.getHeight());
	}

	private void drawGround(int x, int y, String texture, Graphics g) {
		File TextureToLoad = new File(texture);
		try {
			Image myImage = ImageIO.read(TextureToLoad);
			g.drawImage(myImage, x, y, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void drawPowerUps(int x, int y, int width, int height, String texture, Graphics g) {
		File TextureToLoad = new File(texture);  //should work okay on OSX and Linux but check if you have issues depending your eclipse install or if your running this without an IDE
		try {
			Image myImage = ImageIO.read(TextureToLoad);
			g.drawImage(myImage, x, y, width, height, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void drawBackground(Graphics g)
	{
		File TextureToLoad = new File(gameWorld.resourcePath + "Sky.png");  //should work okay on OSX and Linux but check if you have issues depending your eclipse install or if your running this without an IDE
		try {
			Image myImage = ImageIO.read(TextureToLoad); 
			 g.drawImage(myImage, 0,0, 1000, 1000, 0 , 0, 1000, 1000, null); 
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void drawPlayer(int x, int y, String texture, Graphics g) {
		File TextureToLoad = new File(texture);  //should work okay on OSX and Linux but check if you have issues depending your eclipse install or if your running this without an IDE 
		try {
			Image myImage = ImageIO.read(TextureToLoad);
			g.drawImage(myImage, x, y, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
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
