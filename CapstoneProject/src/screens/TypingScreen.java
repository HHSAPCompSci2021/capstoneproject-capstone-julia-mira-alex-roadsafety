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

	public final static String fileSeparator = System.getProperty("file.separator");
	private DrawingSurface surface; 
	private Paint paint; 
	private TypingGame game;
	private String text; 
	private String instructions; 
	private Button play;
	private Button quit; 
	private int amount; 
	private float y; 
	boolean playing; 
	/**
	 * creates a TypingScreen object with set width and height
	 * @param width the width of the screen
	 * @param height the height of the screen
	 */
	public TypingScreen (DrawingSurface surface) {
		super(800, 800);
		this.surface = surface; 
		//buttonColor = new Color(239, 183, 192, 255); 
		
		play = new Button(new Rectangle(25, 220, 80, 80), "Start", new Color(239, 183, 192, 255)); 
		quit = new Button(new Rectangle(0, 0, 50, 50), "Quit", new Color(239, 183, 192, 255)); 
		instructions = "Once you click start, you'll enter a typing game! Do your best to type accurately and speedily for a minute. The better your typing, the more paint you receive.";
		y = 10; 
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
		amount = 0; 
	}
	@Override
	public void draw() {
		
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		quit.draw(surface);
		quit.highlight(p, surface);
		if(!playing) {
			play.draw(surface);	
			play.highlight(p, surface); 
			surface.fill(0);
			surface.text(instructions, 25, 50, 600, 500);
		}
		if(playing) {
			surface.fill(255);
			surface.rect(0, 0, DRAWING_WIDTH, DRAWING_HEIGHT);
			quit.draw(surface);
			quit.highlight(p, surface);
			String user = game.getUser();
			surface.fill(0); 
			ArrayList<Boolean> scored = game.getScored();
			//surface.text( text, DRAWING_WIDTH/2, y, DRAWING_WIDTH/2, DRAWING_HEIGHT); 
			Color textCol; 
			if(game.gameOn()) {
				surface.textSize(30); 
				surface.fill(150); 
				int x = 55; 
				float newy = y; 
				for(int i = 0; i< text.length(); i++) {
					if(i > user.length()-1 || i> scored.size() -1) {
						textCol = Color.darkGray; 
						//surface.fill(125);
					}
					else if(scored.get(i)) {
						textCol = new Color(34,139,34); 
						//surface.fill(34,139,34);
					}
					else{
						textCol = new Color(220,20,60); 
						//surface.fill(220,20,60);
					}
					if(textCol != Color.darkGray && i >= text.length()/2) {
					//	System.out.println(i + "sgsdfg");
						y-=.02; 
					}
					surface.fill(textCol.getRGB()); 
					surface.text(text.charAt(i), x, newy +  surface.textDescent() + surface.textAscent()); 
					x += surface.textWidth(text.charAt(i));
					if(x>= DRAWING_WIDTH -55) {
						x = 55; 
						newy+= 2*(surface.textDescent() + surface.textAscent()); 
					}

				}
				x= 55; 
				newy = y+ (surface.textDescent() + surface.textAscent()); 
				for(int i = 0; i< user.length(); i++) {
					surface.fill(145); 
					surface.text(user.charAt(i), x, newy +  surface.textDescent() + surface.textAscent()); 
					x += surface.textWidth(user.charAt(i));
					if(x>= DRAWING_WIDTH -55) {
						x = 55; 
						newy+= 2*(surface.textDescent() + surface.textAscent()); 
					}
				}
				game.end(); 
				//surface.text(user, 50, y, DRAWING_WIDTH/2 -50, DRAWING_HEIGHT); 
				//for(int i)
			}
			else {
				double res = game.getScore(); 
				//int amount = 0; 

				if(res <= 10) {
					amount = 0; 
				}
				else if (res <= 20) {
					amount = 1; 
				}
				else if (res <= 50) {
					amount = 3; 
				}
				else if(res<= 60) {
					amount = 5; 
				}
				else if (res <= 80) {
					amount = 7; 
				}
				else {
					amount = 10; 
				}
				surface.textSize(50); 
				String result = "You scored " + res + "\n You will receive " + amount + " use(s)."; 
				surface.text(result, 50, 0, DRAWING_WIDTH/2 -75, DRAWING_HEIGHT); 
				paint.makeAvailable(amount);
			}
//			y-=.2; 
		}
		
		
	}
	public void mousePressed() {
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		if (play.isClicked(p) && !playing) {
			game = new TypingGame("TypingGames"+fileSeparator+"Game"+(int)(Math.random()*11 +1)+".txt");
			text = game.getFile(); 
			if(!game.gameOn())
				game.play();
				playing = true; 
		}
		if(quit.isClicked(p)) {
			surface.switchScreen(surface.PAINTING_SCREEN);
			playing=false;
		}
	}
	public void keyPressed() { /// y does keyPressed not work?? puzzling 
		if(game != null) {
			char key = surface.key;
			//System.out.println(" "  + key); 
			String s = game.getUser();
			if(game.gameOn()) {
				if(key == surface.BACKSPACE || key == surface.DELETE) {
					if(s.length()>0)
						game.delete();
				}
				else if(key == surface.RETURN || key == surface.ENTER) {
					game.setUser(s + "\n"); 
				}
				else if (key != surface.TAB || key != surface.ESC || key != surface.SHIFT) { //figure out how to COUNT OUT SHIFT
					game.type(key);
				}
				
		}
		}

	}
	/**
	 * checks whether the user won any paint 
	 */
	public boolean win() {
		boolean ret; 
		if(amount>0) {
			ret =  false; 
		}
		else {
			ret =  true; 
		}
		return ret; 
	}
	/**
	 * resets game 
	 */
	public void restart() {
		amount = 0; 
	}
}
