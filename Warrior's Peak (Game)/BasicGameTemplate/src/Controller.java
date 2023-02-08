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
        
	private static boolean KeyAPressed= false;
	private static boolean KeySPressed= false;
	private static boolean KeyDPressed= false;
	private static boolean KeyWPressed= false;
	private static boolean KeyJPressed = false;
	private static boolean KeySpacePressed= false;

	private static boolean playerComboActive = false; //We can make combo string with keys
	private static boolean actionIsActive = false; //If the character is in an action then this will be set true
	private static Timer comboTimer;
	private static Timer actionTimer;
	private static boolean comboTimerStarted = false;
	private static boolean activeTimerStarted = false;

	public static List<Character> keyBoardInputs = new ArrayList<>();

	private static final Controller instance = new Controller();
	   
	 public Controller() {
		 comboTimer = new Timer();
		 actionTimer = new Timer();
	}

	public void startPlayerComboTimer(Timer timer, long delay){
		if(comboTimerStarted) {
			comboTimer.cancel();
		}
		comboTimerStarted = true;

		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				keyBoardInputs.removeAll(keyBoardInputs);
				playerComboActive = false;
				comboTimerStarted = false;
			}
		}, delay);
	}

	public void startPlayerActiveTimer(Timer timer, long delay){
		if(activeTimerStarted) {
			actionTimer.cancel();
		}
		activeTimerStarted = true;
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				actionIsActive = false;
				activeTimerStarted = false;
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
		switch (e.getKeyChar()) 
		{
			case 'a':setKeyAPressed(true);break;  
			case 's':setKeySPressed(true);break;
			case 'w':setKeyWPressed(true);break;
			case 'd':setKeyDPressed(true);break;
			case 'j':
				if(!actionIsActive && !isKeyJPressed()) { //If an action is not being carried out and
					keyBoardInputs.add('j');
					System.out.println("Key J is Pressed");
					setKeyJPressed(true);
					playerComboActive = true;
					actionIsActive = true;
					startPlayerComboTimer(comboTimer, 1500L);
					startPlayerActiveTimer(actionTimer, 400L);
				}
				break;
			case ' ':setKeySpacePressed(true);break;   
		    default:
		    	//System.out.println("Controller test:  Unknown key pressed");
		        break;
		}  
		
	 // You can implement to keep moving while pressing the key here . 
		
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{ 
		switch (e.getKeyChar()) 
		{
			case 'a':setKeyAPressed(false);break;  
			case 's':setKeySPressed(false);break;
			case 'w':setKeyWPressed(false);break;
			case 'd':setKeyDPressed(false);break;
			case 'j':setKeyJPressed(false);

			break;
			case ' ':setKeySpacePressed(false);break;   
		    default:
		    	//System.out.println("Controller test:  Unknown key pressed");
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

	public boolean isPlayerComboActive(){return playerComboActive;}

	public void setPlayerComboActive(boolean playerComboActive){Controller.playerComboActive = playerComboActive;}

	public boolean isActionIsActive(){return actionIsActive;}

	public void setActionIsActive(boolean playerComboActive){Controller.actionIsActive = actionIsActive;}

	public static boolean validSubstring(String comboList){
		 /* Combo valid algorithm
		 * If the string is a substring in the array*/;
		int counter = 0;
		char charToMatch = comboList.charAt(counter);
		for (char currentChar : keyBoardInputs) {
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
