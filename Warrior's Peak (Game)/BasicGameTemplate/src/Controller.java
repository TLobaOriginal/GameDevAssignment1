import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;

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
	private static boolean KeySpacePressed= false;
	private static boolean KeyKPressed = false;
	private static boolean KeyRightKeyPressed=false;
	private static boolean KeyLeftKeyPressed=false;
	private static boolean KeyUpKeyPressed=false;
	private static boolean KeyDownKeyPressed=false;

	//Player 1 items!
	private static boolean player1ComboActive = false; //We can make combo string with keys
	private static boolean actionPlayer1IsActive = false; //If the character is in an action then this will be set true
	private static Timer comboTimerPlayer1;
	private static Timer actionTimerPlayer1;
	private static boolean comboTimer1Started = false;
	private static boolean activeTimer1Started = false;

	//Player 2 items!
	private static boolean player2ComboActive = false; //We can make combo string with keys
	private static boolean actionPlayer2IsActive = false; //If the character is in an action then this will be set true
	private static Timer comboTimerPlayer2;
	private static Timer actionTimerPlayer2;
	private static boolean comboTimer2Started = false;
	private static boolean activeTimer2Started = false;

	public static List<Character> keyBoardInputs1 = new ArrayList<>();
	public static List<Character> keyBoardInputs2 = new ArrayList<>();

	private static final Controller instance = new Controller();
	   
	 public Controller() {
		 comboTimerPlayer1 = new Timer();
		 actionTimerPlayer1 = new Timer();
		 comboTimerPlayer2 = new Timer();
		 actionTimerPlayer2 = new Timer();
	}

	public void startPlayerComboTimer(Timer timer, long delay, List<Character> inputs, boolean playerComboActive, boolean comboTimerStart){
		startPlayerCombo(playerComboActive, comboTimerStart);
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				inputs.removeAll(inputs);
				stopPlayerCombo(playerComboActive, comboTimerStart);
			}
		}, delay);
	}

	public void startPlayerActiveTimer(Timer timer, long delay, boolean playerActive, boolean playerActiveTimer){
		startPlayerActiveState(playerActive, playerActiveTimer);
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				actionPlayer1IsActive = false;
				activeTimer1Started = false;
			}
		}, delay);
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
					keyBoardInputs1.add('j');

					setKeyJPressed(true);
					player1ComboActive = true;
					actionPlayer1IsActive = true;
					startPlayerComboTimer(comboTimerPlayer1, 2500L, keyBoardInputs1, player1ComboActive, comboTimer1Started);
					startPlayerActiveTimer(actionTimerPlayer1, 200L, actionPlayer1IsActive, activeTimer1Started);
				}
				break;
			case KeyEvent.VK_RIGHT: setKeyRightKeyPressed(true);break;
			case KeyEvent.VK_LEFT: setKeyLeftKeyPressed(true);break;
			case KeyEvent.VK_UP: setKeyUpKeyPressed(true);break;
			case KeyEvent.VK_DOWN: setKeyDownKeyPressed(true);break;
			case KeyEvent.VK_K:
				setKeyKPressed(true);
				if(!actionPlayer2IsActive && !isKeyDownKeyPressed()) { //If an action is not being carried out and
					keyBoardInputs2.add('k');
					setKeyKPressed(true);
					player2ComboActive = true;
					actionPlayer2IsActive = true;
					startPlayerComboTimer(comboTimerPlayer2, 2500L, keyBoardInputs2, player2ComboActive, comboTimer2Started);
					startPlayerActiveTimer(actionTimerPlayer2, 200L, actionPlayer2IsActive, activeTimer2Started);
				}
				break;
		    default:
		    	System.out.println("Controller test:  Unknown key pressed");
		        break;
		}  
		
	 // You can implement to keep moving while pressing the key here . 
		
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
		    	System.out.println("Controller test:  Unknown key pressed");
		        break;
		}  
		 //upper case 
	
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


	public void startPlayerCombo(boolean playerComboActive, boolean playerComboTimeStarted){
		 playerComboActive = true;
		 playerComboTimeStarted = true;
	}
	public void stopPlayerCombo(boolean playerComboActive, boolean playerComboTimeStarted){
		 playerComboActive = false;
		 playerComboTimeStarted = false;
	}

	public void startPlayerActiveState(boolean playerActiveState, boolean playerActiveTimer){
		 playerActiveState = true;
		 playerActiveTimer = true;
	}
	public void stopPlayerActiveState(boolean playerActiveState, boolean playerActiveTimer){
		playerActiveState = false;
		playerActiveTimer = false;
	}

	public static boolean validSubstringPlayer1(String comboList){
		 /* Combo valid algorithm
		 * If the string is a substring in the array*/;
		System.out.println("Looking for: " + comboList);
		System.out.print("Input string collection: ");
		for(Character character: keyBoardInputs1){
			System.out.print(character);
		}
		System.out.println("");
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
		 * If the string is a substring in the array*/;
		System.out.println("Looking for: " + comboList);
		System.out.print("Input string collection: ");
		for(Character character: keyBoardInputs2){
			System.out.print(character);
		}
		System.out.println("");
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

	public void endOfComboPlayer1(long stuckTime, long comboTime) {actionPlayer1IsActive = true;
		startPlayerComboTimer(comboTimerPlayer1, comboTime, keyBoardInputs1, player1ComboActive, comboTimer1Started);
		startPlayerActiveTimer(actionTimerPlayer1, stuckTime, actionPlayer1IsActive, activeTimer1Started);
	}

	public void endOfComboPlayer2(long stuckTime, long comboTime) {
		actionPlayer2IsActive = true;
		startPlayerComboTimer(comboTimerPlayer2, comboTime, keyBoardInputs2, player2ComboActive, comboTimer2Started);
		startPlayerActiveTimer(actionTimerPlayer2, stuckTime, actionPlayer2IsActive, activeTimer2Started);
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
