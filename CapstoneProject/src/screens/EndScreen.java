package screens;
import core.*; 
import processing.core.*; 

/**
 * screen that is displayed to user whenever they want to view the instructions
 * this is used during the intro screen and in-game
 * 
 * @author 
 *
 */
public class EndScreen extends Screen {
	
	
	/**
	 * creates the instructionScreen with set width and height
	 * @param width the width of the screen
	 * @param height the height of the screen
	 */
	public EndScreen(DrawingSurface surface) {
		super(1600, 800);
		this.surface = surface; 
	}

	@Override
	public void draw() {
		surface.rect(0, 0, super.DRAWING_WIDTH, super.DRAWING_HEIGHT);
		String instructions = "In the main screen, you'll be able to draw (after clicking the draw button) by clicking on an available color, choosing the paint option"
				+ " and then hovering your mouse over the painting surface. You can fill by clicking the fill button and then following the same process. Clicking on an area"
				+ "in the painting will fill it. If you want to get an unavailable color(they have an X) then click on it and play a typing game to earn amounts. If you want to "
				+ " make a shade, then click on an available color and select the mix option. You'll have four options for shades/tints.";
		surface.fill(0, 208, 312);
		surface.text(instructions, 50, 50, 500, super.DRAWING_HEIGHT);
		surface.fill(255);
		
	}
}
