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
public class InstructionsScreen extends Screen {
	DrawingSurface surface; 
	/**
	 * creates the instructionScreen with set width and height
	 * @param width the width of the screen
	 * @param height the height of the screen
	 */
	public InstructionsScreen(DrawingSurface surface) {
		super(1600, 800);
		this.surface = surface; 
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		
	}
}
