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
	private Button draw, home;
	
	/**
	 * creates the instructionScreen with set width and height
	 * @param width the width of the screen
	 * @param height the height of the screen
	 */
	public InstructionsScreen(DrawingSurface surface) {
		super(800, 1600);
		this.surface = surface; 
		
		themeColor = new Color(239, 183, 192, 255);
		draw = new Button(new Rectangle(1500, 0, 100, 50), "draw", themeColor); 
		home = new Button(new Rectangle(1500, 100, 100, 50), "home", themeColor);
	}

	public void setup() {
		background = surface.loadImage("additionalPictures"+fileSeparator+BGName);
		background.resize(DRAWING_WIDTH, DRAWING_HEIGHT);
	}
	
	@Override
	public void draw() {
		setup();
		draw.setColor(themeColor);
		home.setColor(themeColor);
		surface.image(background, 0, 0);
		
		String instructions = "In the main screen, you'll be able to draw (after clicking the draw button) by clicking on an available color, choosing the paint option"
				+ " and then hovering your mouse over the painting surface. You can fill by clicking the fill button and then following the same process. Clicking on an area"
				+ " in the painting will fill it. If you want to get an unavailable color then click on it and play a typing game to earn amounts. If you want to"
				+ " make a shade, then click on an available color and select the mix option. You'll have four options for shades/tints.";
		surface.fill(0);
		surface.textSize(30);
		surface.text(instructions, 100, 80, DRAWING_WIDTH - 180, DRAWING_HEIGHT-10);
		
		draw.draw(surface);
		home.draw(surface);
		
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		draw.highlight(p, surface);
		home.highlight(p, surface);
	}
	
	public void mousePressed() {
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		System.out.println(p.getX() + " " + p.getY()); 
		
		if (draw.isClicked(p)) {
			surface.switchScreen(surface.PAINTING_SCREEN);
		}
		
		else if(home.isClicked(p)) {
			surface.switchScreen(surface.INTRO_SCREEN);
		}
		
	}
}
