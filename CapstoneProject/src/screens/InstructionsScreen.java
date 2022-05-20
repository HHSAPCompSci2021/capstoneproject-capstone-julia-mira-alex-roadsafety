package screens;
import java.awt.Color;
import java.awt.Point;

import mhaldar.shapes.*;
import core.*; 
import processing.core.*; 

/**
 * screen that is displayed to user whenever they want to view the instructions
 * this is used during the intro screen and in-game
 * 
 * @author Julia and Mira
 *
 */
public class InstructionsScreen extends Screen {
	private Button back, back2;
	private boolean showBack2;
	private PImage background;
	private Color themeColor;
	public final static String fileSeparator = System.getProperty("file.separator");

	private int count;
	
	/**
	 * creates the instructionScreen with set width and height
	 * @param width the width of the screen
	 * @param height the height of the screen
	 */
	public InstructionsScreen(DrawingSurface surface) {
		super(800, 1600);
		this.surface = surface; 
		
		count = 0;
		
		themeColor = new Color(239, 183, 192, 255);
		back = new Button(new Rectangle(1500, 0, 100, 50), "back", themeColor); 
	}

	public void setup() {
		background = surface.loadImage("additionalPictures"+fileSeparator+"background-pic.png");
		background.resize(DRAWING_WIDTH, DRAWING_HEIGHT);
	}
	
	@Override
	public void draw() {
		surface.image(background, 0, 0);
		
		String instructions = "In the main screen, you'll be able to draw (after clicking the draw button) by clicking on an available color, choosing the paint option"
				+ " and then hovering your mouse over the painting surface. You can fill by clicking the fill button and then following the same process. Clicking on an area"
				+ " in the painting will fill it. If you want to get an unavailable color(they have an X) then click on it and play a typing game to earn amounts. If you want to "
				+ " make a shade, then click on an available color and select the mix option. You'll have four options for shades/tints.";
		surface.fill(0);
		surface.textSize(30);
		surface.text(instructions, 100, 80, DRAWING_WIDTH - 180, DRAWING_HEIGHT-10);
		
		back.draw(surface);
		
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		back.highlight(p, surface);
		
//		if (showBack2) {
//			back2 = new Button(new Rectangle(1350, 100, 250, 50), "back to drawing", themeColor); 
//			back2.draw(surface);
//			back2.highlight(p, surface);
//		}
	}
	
	public void setBack2(boolean var) {
		showBack2 = var;
	}
	
	public void mousePressed() {
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		System.out.println(p.getX() + " " + p.getY()); 
		if(back.isClicked(p) && count == 0) {
			count++;
			surface.switchScreen(surface.INTRO_SCREEN);
		}
		
		else if (back.isClicked(p) && count != 0) {
			surface.switchScreen(surface.PAINTING_SCREEN);
		}
		
	}
}
