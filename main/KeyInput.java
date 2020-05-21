package main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import main.Game.STATE;

public class KeyInput extends KeyAdapter{
	/** Instance of our game */
	private Game game;
	/** The integer code that represents which key is being pressed */
	private int keyCode;
	/** A character representation of the key that is being pressed */
	private char key;
	/** Creates a Key Input Listener to listen and respond to key commands */
	public KeyInput(Game game) {
		this.game = game;
	}
	
	/** Responds to the pressing down of keyboard keys */
	public void keyPressed(KeyEvent e) {
		keyCode = e.getExtendedKeyCode();
		key = e.getKeyChar();
		//Implement what key events do to the game
		
		if (game.gameState == STATE.Game) {
			//render Game Key Commands
			if (keyCode == KeyEvent.VK_DOWN) {
			
			} else if (keyCode == KeyEvent.VK_UP) {
			
			} else if (keyCode == KeyEvent.VK_RIGHT) {
			
			} else if (keyCode == KeyEvent.VK_LEFT) {
			
			}
		
			if (key == 'a') {
			
			}
		} else if (game.gameState == STATE.Menu) {
			//render Menu Key Commands
		}   // <-- add more logic if new gameStates are created
	}
	
}
