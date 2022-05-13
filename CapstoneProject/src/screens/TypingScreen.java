package screens;

import core.Button;
import core.DrawingSurface;
import core.Paint;
import core.TypingGame;

/**
 * the typing screen in which the user can play the typing game to earn paint
 * 
 * @author 
 *
 */
public class TypingScreen extends Screen{
// lets make it a side by side comparison of user input and actual text+ figure out how to do a scrolling feature???
	public final static String fileSeparator = System.getProperty("file.separator");
	DrawingSurface surface; 
	Paint paint; 
	TypingGame game;
	Button play;
	/**
	 * creates a TypingScreen object with set width and height
	 * @param width the width of the screen
	 * @param height the height of the screen
	 */
	public TypingScreen (DrawingSurface surface) {
		super(surface);
		this.surface = surface; 
		game = new TypingGame("TypingGames"+fileSeparator+"Game"+(int)(Math.random()*1)+".txt");
	}
	/**
	 * choose the paint you're typing for 
	 * @param p the paint 
	 */
	public void chooseColor(Paint p) {
		paint = p; 
	}
	@Override
	public void draw() {
		// TODO Auto-generated method stub
		
	}
	public void mousePressed() {
		
	}
	//blah blah blah 
	//then you get the score and after you get the score you assign an amount which you can the add to your color 
}
