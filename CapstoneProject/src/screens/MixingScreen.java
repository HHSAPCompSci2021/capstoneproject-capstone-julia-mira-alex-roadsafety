package screens;

import core.DrawingSurface;

public class MixingScreen extends Screen{
	DrawingSurface surface; 
	/**
	 * creates a MixingScreen with set width and height
	 * @param width the width of the screen
	 * @param height the height of the screen
	 */
	public MixingScreen (DrawingSurface surface) {
		super(1600, 800);
		this.surface = surface; 
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		
	}
	//makes the shade that corresponds to the selected Paint
	public void createShade() {
		 
	}
}
