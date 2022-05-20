package screens;
import core.*; 

public abstract class Screen {
	
	public DrawingSurface surface;
	
	/**
	 * the dimensions of the screen
	 */
	public int DRAWING_WIDTH, DRAWING_HEIGHT;
	
	/**
	 * creates a screen with set width and height
	 * 
	 * @param width the width of the screen
	 * @param height the height of the screen
	 * 
	 */
	public Screen(int height, int width) {
		this.DRAWING_WIDTH = width;
		this.DRAWING_HEIGHT = height;
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
		//return true; 
		System.out.println("mouseDragged in Screen");
	}
	
	/**
	 * depending on where the mouse is released, the program will do something
	 */
	public void mouseReleased() {
		
	}
	
	public void keyPressed() {
		
	}
	
}