package screens;

import java.awt.Color;
import java.awt.Point;
import mhaldar.shapes.*;
import core.Button;
import core.DrawingSurface;
import core.Paint;
import core.TypingGame;
import core.*; 
import java.util.*; 
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
	String text; 
	private Button play;
	private Button quit; 
	private int time; 
	private float y; 
	boolean playing; 
	/**
	 * creates a TypingScreen object with set width and height
	 * @param width the width of the screen
	 * @param height the height of the screen
	 */
	public TypingScreen (DrawingSurface surface) {
		super(1600, 1600);
		this.surface = surface; 
		//buttonColor = new Color(239, 183, 192, 255); 
		game = new TypingGame("TypingGames"+fileSeparator+"Game"+(int)(Math.random()*11 +1)+".txt");
		play = new Button(new Rectangle(400, 800, 300, 300), "Start", new Color(239, 183, 192, 255)); 
		quit = new Button(new Rectangle(0, 0, 50, 50), "Quit", new Color(239, 183, 192, 255)); 
		text = game.getFile(); 
		y = 0; 
		//System.out.println(text); 
	}
	/**
	 * choose the paint you're typing for 
	 * @param p the paint 
	 */
	public void chooseColor(Paint p) {
		play.setColor(p.getColor());
		quit.setColor(p.getColor());
		play.setTextColor(Color.white);
		quit.setTextColor(Color.white);
		paint = p; 
	}
	@Override
	public void draw() {
		
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		quit.draw(surface);
		quit.highlight(p, surface);
		if(!playing) {
			play.draw(surface);	
			play.highlight(p, surface); 
		}
		if(playing) {
			surface.rect(0, 0, DRAWING_WIDTH, DRAWING_HEIGHT);
			quit.draw(surface);
			quit.highlight(p, surface);
			String user = game.getUser();
			surface.fill(0); 
			ArrayList<Boolean> scored = game.getScored();
			//surface.text( text, DRAWING_WIDTH/2, 0, DRAWING_WIDTH/2, DRAWING_HEIGHT); 
			if(game.gameOn()) {
				//surface.clear(); 
				surface.textSize(50); 
				surface.fill(125); 
				int x = 50; 
				int y = 10; 
				for(int i = 0; i< text.length(); i++) {
					if(i > user.length()-1 || i> scored.size() -1) {
						surface.fill(125);
					}
					else if(scored.get(i)) {
						surface.fill(34,139,34);
					}
					else{
						surface.fill(220,20,60);
					}
					
					surface.text(text.charAt(i), x, y +  surface.textDescent() + surface.textAscent()); 
					x += surface.textWidth(text.charAt(i));
					if(x>= DRAWING_WIDTH -50) {
						x = 0; 
						y+= surface.textDescent() + surface.textAscent(); 
					}
					
				}
				//surface.text(user, 50, y, DRAWING_WIDTH/2 -50, DRAWING_HEIGHT); 
				//for(int i)
				//two text boxes 
				//loop thru and determine accuracy  
				game.end(); 
			}
			else {
				double res = game.getScore()*100.0/text.length(); 
				int amount = game.getScore()/10; 
				
				if(res <= 5) {
					amount = 0; 
				}
				else if (res <= 50) {
					amount = 1; 
				}
				else if (res <= 70) {
					amount = 3; 
				}
				else if(res<= 85) {
					amount = 5; 
				}
				else if (res <= 95) {
					amount = 7; 
				}
				else {
					amount = 10; 
				}
				surface.textSize(50); 
				String result = "You scored " + res + "% accuracy. \n You will receive " + amount + " uses."; 
				surface.text(result, 50, 0, DRAWING_WIDTH/2 -75, DRAWING_HEIGHT); 
				paint.makeAvailable(amount);
			}
			
		}
		
		
	}
	public void mousePressed() {
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		if (play.isClicked(p) && !playing) {
			if(!game.gameOn())
				game.play();
				playing = true; 
		}
		if(quit.isClicked(p)) {
			surface.switchScreen(surface.PAINTING_SCREEN);
		}
	}
	public void keyPressed() { /// y does keyPressed not work?? puzzling 
		char key = surface.key;
		//System.out.println(" "  + key); 
		String s = game.getUser();
		if(game.gameOn()) {
			if(key == surface.BACKSPACE || key == surface.DELETE) {
				if(s.length()>0)
					game.setUser(s.substring(0,s.length()-1));
			}
			else if(key == surface.RETURN || key == surface.ENTER) {
				game.setUser(s + "\n"); 

			}
			else if (key != surface.TAB || key != surface.ESC || key != surface.SHIFT) { //figure out how to COUNT OUT SHIFT
				game.type(key);
			}
			else {
				
			}
		}
		
		
	}
	//blah blah blah 
	//then you get the score and after you get the score you assign an amount which you can the add to your color 
}
