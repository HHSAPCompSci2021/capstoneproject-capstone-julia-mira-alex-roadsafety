package screens;
import core.*; 

public abstract class Screen {
	Button[] buttons ; 
	/**
	 * the dimensions of the screen
	 */
	public int DRAWING_WIDTH, DRAWING_HEIGHT;
	
	/**
	 * creates a screen with no set size
	 */
	public Screen() {
		
	}
	
	/**
	 * creates a screen with set width and height
	 * 
	 * @param width the width of the screen
	 * @param height the height of the screen
	 * 
	 */
	public Screen(int width, int height) {
		this.DRAWING_WIDTH = width;
		this.DRAWING_HEIGHT = height;
	}
	
	/**
	 * set up of the Screen 
	 */
	public void setup() {
		
	}
	
	public abstract void draw(); 
	
	/**
	 * depending on where (or what) the mouse presses on, the program will do something
	 */
	public Button mousePressed(double x, double y) {
		for(Button b : buttons) {
			if(b.isClicked(x, y))
				return b; 
		}
		return null; 
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

