package screens;

import java.awt.Color;
import java.awt.Point;
import mhaldar.shapes.*;
import core.Button;
import core.DrawingSurface;
import core.Paint;
import core.TypingGame;
import core.*; 
/**
 * the typing screen in which the user can play the typing game to earn paint
 * 
 * @author 
 *
 */
public class TypingScreen extends Screen{
// lets make it a side by side comparison of user input and actual text+ figure out how to do a scrolling feature???
	public final static String fileSeparator = System.getProperty("file.separator");
	private DrawingSurface surface; 
	private Paint paint; 
	private TypingGame game;
	private Button play;
	private int time; 
	boolean playing; 
	/**
	 * creates a TypingScreen object with set width and height
	 * @param width the width of the screen
	 * @param height the height of the screen
	 */
	public TypingScreen (DrawingSurface surface) {
		super(1600, 800);
		this.surface = surface; 
		game = new TypingGame("TypingGames"+fileSeparator+"Game"+(int)(Math.random()*11 +1)+".txt");
		play = new Button(new Rectangle(0, 0, 50, 50), "Start", new Color(239, 183, 192, 255)); 
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
		play.draw(surface);		
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		play.highlight(p, surface); 
		
		if(playing) {
			//background
			//two text boxes 
			//overlay the stuff according to typing accuracy 
		}
	}
	public void mousePressed() {
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		if (play.isClicked(p)) {
			if(!game.gameOn())
				game.play();
				playing = true; 
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

				game.end();
				double score = game.getScore(); 

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
