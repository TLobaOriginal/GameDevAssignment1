import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import java.util.Timer;

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

//Singeton pattern
public class Controller implements KeyListener {

	//Player 1 keys
	private static boolean KeyAPressed= false;
	private static boolean KeySPressed= false;
	private static boolean KeyDPressed= false;
	private static boolean KeyWPressed= false;
	private static boolean KeyJPressed = false;

	//Player 2 keys
	private static boolean KeySpacePressed = false;
	private static boolean KeyKPressed = false;
	private static boolean KeyRightKeyPressed = false;
	private static boolean KeyLeftKeyPressed = false;
	private static boolean KeyUpKeyPressed = false;
	private static boolean KeyDownKeyPressed = false;

	//Player 1 items!
	private static boolean actionPlayer1IsActive = false; //If the character is in an action then this will be set true

	//Player 2 items!
	private static boolean actionPlayer2IsActive = false; //If the character is in an action then this will be set true

	public static List<Character> keyBoardInputs1 = new ArrayList<>();
	public static List<Character> keyBoardInputs2 = new ArrayList<>();

	private static final Controller instance = new Controller();
	   
	 public Controller() {

	}
	 
	 public static Controller getInstance(){
	        return instance;
	    }
	   
	@Override
	// Key pressed , will keep triggering 
	public void keyTyped(KeyEvent e) { 
		 
	}

	@Override
	public void keyPressed(KeyEvent e) 
	{ 
		switch (e.getKeyCode())
		{
			case KeyEvent.VK_A:setKeyAPressed(true);break;
			case KeyEvent.VK_S:setKeySPressed(true);break;
			case KeyEvent.VK_W:setKeyWPressed(true);break;
			case KeyEvent.VK_D:setKeyDPressed(true);break;
			case KeyEvent.VK_J:
				if(!actionPlayer1IsActive && !isKeyJPressed()) { //If an action is not being carried out and
					//keyBoardInputs1.add('j');
					setKeyJPressed(true);
				}
				break;
			case KeyEvent.VK_RIGHT: setKeyRightKeyPressed(true);break;
			case KeyEvent.VK_LEFT: setKeyLeftKeyPressed(true);break;
			case KeyEvent.VK_UP: setKeyUpKeyPressed(true);break;
			case KeyEvent.VK_DOWN: setKeyDownKeyPressed(true);break;
			case KeyEvent.VK_K:
				if(!actionPlayer2IsActive && !isKeyKPressed()) { //If an action is not being carried out and
					//keyBoardInputs2.add('k');
					setKeyKPressed(true);
				}
				break;
		    default:
		    	System.out.println("Controller test:  Unknown key pressed");
		        break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{ 
		switch (e.getKeyCode())
		{
			case KeyEvent.VK_A:setKeyAPressed(false);break;
			case KeyEvent.VK_S:setKeySPressed(false);break;
			case KeyEvent.VK_W:setKeyWPressed(false);break;
			case KeyEvent.VK_D:setKeyDPressed(false);break;
			case KeyEvent.VK_J:setKeyJPressed(false);break;
			case KeyEvent.VK_RIGHT: setKeyRightKeyPressed(false);break;
			case KeyEvent.VK_LEFT: setKeyLeftKeyPressed(false);break;
			case KeyEvent.VK_UP: setKeyUpKeyPressed(false);break;
			case KeyEvent.VK_DOWN: setKeyDownKeyPressed(false);break;
			case KeyEvent.VK_K: setKeyKPressed(false);break;
			default:
		    	System.out.println("Controller test:  Unknown key released");
		        break;
		}
	}


	public boolean isKeyAPressed() {
		return KeyAPressed;
	}
	public void setKeyAPressed(boolean keyAPressed) {
		KeyAPressed = keyAPressed;
	}

	public boolean isKeyJPressed() {
		return KeyJPressed;
	}
	public void setKeyJPressed(boolean keyJPressed) {
		KeyJPressed = keyJPressed;
	}

	public boolean isKeySPressed() {
		return KeySPressed;
	}
	public void setKeySPressed(boolean keySPressed) {
		KeySPressed = keySPressed;
	}

	public boolean isKeyDPressed() {
		return KeyDPressed;
	}
	public void setKeyDPressed(boolean keyDPressed) {
		KeyDPressed = keyDPressed;
	}

	public boolean isKeyWPressed() {
		return KeyWPressed;
	}
	public void setKeyWPressed(boolean keyWPressed) {
		KeyWPressed = keyWPressed;
	}

	public boolean isKeySpacePressed() {
		return KeySpacePressed;
	}
	public void setKeySpacePressed(boolean keySpacePressed) {
		KeySpacePressed = keySpacePressed;
	}

	public boolean isKeyDownKeyPressed() {return KeyDownKeyPressed;}
	public static void setKeyDownKeyPressed(boolean keyDownKeyPressed) {KeyDownKeyPressed = keyDownKeyPressed;}

	public boolean isKeyLeftKeyPressed() {return KeyLeftKeyPressed;}
	public static void setKeyLeftKeyPressed(boolean keyLeftKeyPressed) {KeyLeftKeyPressed = keyLeftKeyPressed;}

	public boolean isKeyRightKeyPressed() {return KeyRightKeyPressed;}
	public static void setKeyRightKeyPressed(boolean keyRightKeyPressed) {KeyRightKeyPressed = keyRightKeyPressed;}

	public boolean isKeyUpKeyPressed() {return KeyUpKeyPressed;}
	public static void setKeyUpKeyPressed(boolean keyUpKeyPressed) {KeyUpKeyPressed = keyUpKeyPressed;}

	public boolean isKeyKPressed() {
		return KeyKPressed;
	}
	public void setKeyKPressed(boolean keyKPressed) {
		KeyKPressed = keyKPressed;
	}

	public static boolean validSubstringPlayer1(String comboList){
		 /* Combo valid algorithm
		 * If the string is a substring in the array
		 * */
		int counter = 0;
		char charToMatch = comboList.charAt(counter);
		for (char currentChar : keyBoardInputs1) {
			if (currentChar == charToMatch) {
				counter++;
				if (counter == comboList.length())
					return true;
			} else{
				counter = 0;
			}
			charToMatch = comboList.charAt(counter);
		}
		 return false;
	}

	public static boolean validSubstringPlayer2(String comboList){
		/* Combo valid algorithm
		 * If the string is a substring in the array
		 * */
		int counter = 0;
		char charToMatch = comboList.charAt(counter);
		for (char currentChar : keyBoardInputs2) {
			if (currentChar == charToMatch) {
				counter++;
				if (counter == comboList.length())
					return true;
			} else{
				counter = 0;
			}
			charToMatch = comboList.charAt(counter);
		}
		return false;
	}

	public void freezePlayerHasBeenHit(Fighter player, long stuck){
		 if(!player.stuck) {
			 new StuckTimerThread(player, stuck);
		 }
	}

	public void playerPowerUpAnimationTimer(Fighter player, long stuckAndInvulnerableTime){
		 new PowerUpTimer(player, stuckAndInvulnerableTime);
	}

	public void player1AttackFreezeTime(Fighter player, long stuckTime, long comboTime) {
		if(!player.comboActive) {
			new ComboTimerThread(player, comboTime, keyBoardInputs1);
		}
		if(!player.stuck) {
			new StuckTimerThread(player, stuckTime);
		}
	}

	public void player2AttackFreezeTime(Fighter player, long stuckTime, long comboTime) {
		if(!player.comboActive) {
			new ComboTimerThread(player, comboTime, keyBoardInputs2);
		}
		if(!player.stuck) {
			new StuckTimerThread(player, stuckTime);
		}
	}

	static class ComboTimerThread{
		 ComboTimerThread(Fighter player, long milliseconds, List<Character> inputs){
			 new Thread(() ->{
				 player.comboActive = true;
				 try {
					 Thread.sleep(milliseconds);
				 } catch (InterruptedException e) {
					 e.printStackTrace();
				 }
				 player.comboActive = false;
				 inputs.removeAll(inputs);
			 }).start();
		 }
	}

	static class StuckTimerThread{
		StuckTimerThread(Fighter player, long milliseconds){
			new Thread(() ->{
				player.stuck = true;
				System.out.println("DEBUG: Stuck Timer Thread started...");
				try {
					Thread.sleep(milliseconds);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("DEBUG: Stuck Timer Thread ending...");
				player.stuck = false;
			}).start();
		}
	}

	static class PowerUpTimer{
		PowerUpTimer(Fighter player, long milliseconds){
			new Thread(() ->{
				player.stuck = true;
				player.invulnerable = true;
				System.out.println("DEBUG: Stuck Timer Thread started...");
				try {
					Thread.sleep(milliseconds);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("DEBUG: Stuck Timer Thread ending...");
				player.stuck = false;
				player.invulnerable = false;
			}).start();
		}
	}
}

/*
 * 
 * KEYBOARD :-) . can you add a mouse or a gamepad

 *@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ @@@@@@@@@@@@@@@

  @@@     @@@@    @@@@    @@@@    @@@@     @@@     @@@     @@@     @@@     @@@  

  @@@     @@@     @@@     @@@@     @@@     @@@     @@@     @@@     @@@     @@@  

  @@@     @@@     @@@     @@@@    @@@@     @@@     @@@     @@@     @@@     @@@  

@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

@     @@@     @@@     @@@      @@      @@@     @@@     @@@     @@@     @@@     @

@     @@@   W   @@@     @@@      @@      @@@     @@@     @@@     @@@     @@@     @

@@    @@@@     @@@@    @@@@    @@@@    @@@@     @@@     @@@     @@@     @@@     @

@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@N@@@@@@@@@@@@@@@@@@@@@@@@@@@

@@@     @@@      @@      @@      @@      @@@     @@@     @@@     @@@     @@@    

@@@   A   @@@  S     @@  D     @@      @@@     @@@     @@@     @@@     @@@     @@@    

@@@@ @  @@@@@@@@@@@@ @@@@@@@    @@@@@@@@@@@@    @@@@@@@@@@@@     @@@@   @@@@@   

    @@@     @@@@    @@@@    @@@@    $@@@     @@@     @@@     @@@     @@@     @@@

    @@@ $   @@@      @@      @@ /Q   @@ ]M   @@@     @@@     @@@     @@@     @@@

    @@@     @@@      @@      @@      @@      @@@     @@@     @@@     @@@     @@@

@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

@       @@@                                                @@@       @@@       @

@       @@@              SPACE KEY       @@@        @@ PQ     

@       @@@                                                @@@        @@        

@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 * 
 * 
 * 
 * 
 * 
 */
