package screens;

import core.DrawingSurface;
import core.Paint;

/**
 * the typing screen in which the user can play the typing game to earn paint
 * 
 * @author 
 *
 */
public class TypingScreen extends Screen{
// lets make it a side by side comparison of user input and actual text+ figure out how to do a scrolling feature???
	DrawingSurface surface; 
	Paint paint; 
	/**
	 * creates a TypingScreen object with set width and height
	 * @param width the width of the screen
	 * @param height the height of the screen
	 */
	public TypingScreen (DrawingSurface surface) {
		super(1600, 800);
		this.surface = surface; 
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
	
	//blah blah blah 
	//then you get the score and after you get the score you assign an amount which you can the add to your color 
}
