package screens;

import java.awt.Point;

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
		super(1600, 800);
		this.surface = surface; 
		game = new TypingGame("TypingGames"+fileSeparator+"Game"+(int)(Math.random()*1)+".txt");
		//initialize the buttons 
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
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		if (play.isClicked(p)) {
			if(!game.gameOn())
				game.play();
		}
	}
	public void keyPressed() {
		char key = surface.key;
		String s = game.getUser();
		if(game.gameOn()) {
			if(key == surface.BACKSPACE || key == surface.DELETE) {
				if(s.length()>0)
					game.setUser(s.substring(0,s.length()-2));
			}
			else if(key == surface.RETURN || key == surface.ENTER) {

				long money = game.end();

			}
			else if (key == surface.TAB || key == surface.ESC) {
				
			}
			else {
				game.type(key);
			}
		}
		
		
	}
	//blah blah blah 
	//then you get the score and after you get the score you assign an amount which you can the add to your color 
}
