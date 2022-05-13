package screens;

import core.DrawingSurface;
import core.*; 

/**
 * paintingScreen that is the screen with the drawing canvas that the user can draw on
 * 
 * @author 
 *
 */
public class PaintingScreen extends Screen{

	DrawingSurface surface; 
	Painting art; 
	Palette palette;
	Button draw; 
	Button fill; 
	Button end; 
	Button instructions; 
	/**
	 * creates a PaintingScreen with set width and height
	 * @param width the width of the screen
	 * @param height the height of the screen
	 */
	public PaintingScreen (DrawingSurface surface) {
		super(1600, 800);
		this.surface = surface; 
	}

	@Override
	public void draw() {
		
		
	}
	
}