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
	Button back; 
	
	/**
	 * creates the instructionScreen with set width and height
	 * @param width the width of the screen
	 * @param height the height of the screen
	 */
	public InstructionsScreen(DrawingSurface surface) {
		super(800, 1600);
		this.surface = surface; 
		back = new Button(new Rectangle(1500, 0, 100, 50), "back", Color.cyan); 

	}

	@Override
	public void draw() {
		
		surface.rect(0, 0, (float)super.DRAWING_WIDTH, (float)super.DRAWING_HEIGHT);
		
		String instructions = "In the main screen, you'll be able to draw (after clicking the draw button) by clicking on an available color, choosing the paint option"
				+ " and then hovering your mouse over the painting surface. You can fill by clicking the fill button and then following the same process. Clicking on an area"
				+ " in the painting will fill it. If you want to get an unavailable color(they have an X) then click on it and play a typing game to earn amounts. If you want to "
				+ " make a shade, then click on an available color and select the mix option. You'll have four options for shades/tints.";
		surface.fill(0, 208, 312);
		surface.text(instructions, 50, 50, (float)500, (float)super.DRAWING_HEIGHT);
		
		back.draw(surface);
		
		
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		back.highlight(p, surface);
		
	}
	public void mousePressed() {
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		System.out.println(p.getX() + " " + p.getY()); 
		if(back.isClicked(p)) {
			surface.switchScreen(surface.INTRO_SCREEN);
		}
	}
}
