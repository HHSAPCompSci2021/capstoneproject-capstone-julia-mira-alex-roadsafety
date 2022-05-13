package screens;
import core.*; 

public abstract class Screen {
	Button[] buttons ; 
	
	public DrawingSurface surface;
	
	/**
	 * the dimensions of the screen
	 */
	public double DRAWING_WIDTH, DRAWING_HEIGHT;
	
	
	
	/**
	 * creates a screen with set width and height
	 * 
	 * @param width the width of the screen
	 * @param height the height of the screen
	 * 
	 */
	public Screen(DrawingSurface surface) {
		this.DRAWING_WIDTH = surface.getMaxWidth();
		this.DRAWING_HEIGHT = surface.getMaxHeight();
	}
	
	
	/**
	 * set up of the Screen 
	 */
	public void setup() {
		
	}
	
	/**
	 * displays the screen
	 */
	public void draw() {
		
		
	}
	
	/**
	 * depending on where (or what) the mouse presses on, the program will do something
	 */
	public void mousePressed() {
//		for(Button b : buttons) {
//			if(b.isClicked(x, y))
//				return b; 
//		}
//		return null; 
		System.out.println("screen class");
		
	}
	
	/**
	 * depending on where the mouse moves, the program will do something
	 */
	public void mouseMoved() {
		
	}
	
	/**
	 * depending on where the mouse is dragged, the program will do something
	 */
	public void mouseDragged() {
		
	}
	
	/**
	 * depending on where the mouse is released, the program will do something
	 */
	public void mouseReleased() {
		
	}
	
	
	
}

