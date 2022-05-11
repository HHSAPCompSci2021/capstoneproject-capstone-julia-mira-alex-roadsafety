package screens;

import core.DrawingSurface;

/**
 * paintingScreen that is the screen with the drawing canvas that the user can draw on
 * 
 * @author 
 *
 */
public class PaintingScreen extends Screen{
//	Painting art; 
	DrawingSurface surface; 
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
		// TODO Auto-generated method stub
		
	}
	
}