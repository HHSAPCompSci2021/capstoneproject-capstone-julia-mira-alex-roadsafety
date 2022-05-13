package screens;
import core.*; 
import core.DrawingSurface;

public class Window extends Screen {
	PaintButton p; 
	/**
	 * creates the window with set width and height
	 * @param width the width of the screen
	 * @param height the height of the screen
	 */
	public Window(DrawingSurface surface, PaintButton p) {
		super(500, 500);
		this.surface = surface; 
		this.p = p; 
	}
	
	public void draw() {
		if(p.isMixed()) {
			if(p.getPaint().isAvailable()) {
				
			}
			else {
				
			}
		}
		else {
			if(p.getPaint().isAvailable()) {
				
			}
			else{
				
			}
		}
	}
}
