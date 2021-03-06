package screens;

import java.awt.Color;
import java.awt.Point;
import mhaldar.shapes.*;
import processing.core.PImage;
import core.Button;
import core.DrawingSurface;
import core.Paint;
import core.TypingGame;
import core.*; 
import java.util.*; 
/**
 * the typing screen in which the user can play the typing game to earn paint
 * 
 * @author Alex
 *
 */
public class TypingScreen extends Screen{

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
//		super(800, 800);
		super(800, 1600);
		this.surface = surface; 
		//buttonColor = new Color(239, 183, 192, 255); 
		
		themeColor = new Color(239, 183, 192, 255);
		
		quit = new Button(new Rectangle(25, 25, 100, 50), "Quit", themeColor); 
		play = new Button(new Rectangle(25, 265, 100, 50), "Start", themeColor); 
		instructions = "Once you click start, you'll enter a typing game! Do your best to type accurately and speedily for a minute. The better your typing, the more paint you receive.";
		y = 10; 
		//System.out.println(text); 
	}
	
	/**
	 * sets up screen
	 */
	public void setup() {
		background = surface.loadImage("additionalPictures"+fileSeparator+BGName);
		background.resize(DRAWING_WIDTH, DRAWING_HEIGHT);
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
	
	/**
	 * draws the screen
	 */
	@Override
	public void draw() {
		setup();
		play.setColor(themeColor);
		quit.setColor(themeColor);
		
		surface.image(background, 0,0);
		
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		quit.draw(surface);
		quit.highlight(p, surface);
		if(!playing) {
			play.draw(surface);	
			play.highlight(p, surface); 
			surface.fill(0);
			surface.text(instructions, 35, 95, 600, 500);
		}
		if(playing) {
			surface.fill(255);
			surface.rect(0, 0, DRAWING_WIDTH, DRAWING_HEIGHT);
			surface.image(background,0,0);
			quit.draw(surface);
			quit.highlight(p, surface);
			String user = game.getUser();
			surface.fill(0); 
			ArrayList<Boolean> scored = game.getScored();
			//surface.text( text, DRAWING_WIDTH/2, y, DRAWING_WIDTH/2, DRAWING_HEIGHT); 
			Color textCol = null, fillCol; 
			fillCol = null; 
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
						fillCol = new Color(34, 139, 34, 50); 
						//surface.fill(34,139,34);
					}
					else{
						textCol = new Color(220,20,60); 
						fillCol = new Color(220, 20, 60, 50); 
						//surface.fill(220,20,60);
					}
					if(textCol != Color.darkGray && newy >= 10*( surface.textDescent() + surface.textAscent())) {
					//	System.out.println(i + "sgsdfg");
						y-= ( surface.textDescent() + surface.textAscent()); 
					}
					surface.fill(textCol.getRGB()); 
					surface.text(text.charAt(i), x, newy +  surface.textDescent() + surface.textAscent()+80); 
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
					surface.text(user.charAt(i), x, newy +  surface.textDescent() + surface.textAscent()+80); 
					if(scored.get(i)) {
						x += surface.textWidth(text.charAt(i));
					}
					else {
						x+= surface.textWidth(user.charAt(i)); 
					}
					if(x>= DRAWING_WIDTH -55) {
						x = 55; 
						newy+= 2*(surface.textDescent() + surface.textAscent()); 
					}
				}
				game.end(); 
			}
			else {
				double res = game.getScore(); 
				//int amount = 0; 

				if(res <= 10 || res == Double.NaN || res == Double.MAX_VALUE || res == Double.MIN_VALUE){
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
				else if (res <= 100) {
					amount = 10; 
				}
				surface.textSize(30); 
				String result = "You scored " + res + "\n You will receive " + amount + " use(s)."; 
				surface.text(result, 50, 100, DRAWING_WIDTH/2 -75, DRAWING_HEIGHT); 
				if(amount > 0) {
					paint.makeAvailable(amount);
					PaintingScreen pscr = (PaintingScreen) surface.getScreens().get(surface.PAINTING_SCREEN); 
					pscr.selectedColor(paint.getColor()); 
				}
			}
		}
	}
	
	/**
	 * if play is clicked, play game
	 * if quit is clicked, quit game
	 */
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
	
	/**
	 * different keys do different tasks during the game (the backspace, return, and tab specifically)
	 */
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
		if(amount<=0) {
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
		y = 10; 
	}
}
