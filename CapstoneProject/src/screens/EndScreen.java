package screens;
import core.*; 
import processing.core.*; 

/**
 * screen that is displayed to user whenever they finish the game
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
		surface.rect(0, 0, (float)super.DRAWING_WIDTH, (float)super.DRAWING_HEIGHT);
		String instructions = "Yay you made a painting :D";
		surface.fill(0, 208, 312);
		surface.text(instructions, 50, 50, (float)500, (float)super.DRAWING_HEIGHT);
		surface.fill(255);
	}
}
