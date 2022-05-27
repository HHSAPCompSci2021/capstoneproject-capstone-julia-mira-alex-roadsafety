package screens;
import java.awt.Color;

import core.*;
import processing.core.PImage; 

/**
 * screen class
 * @author julia
 *
 */
public abstract class Screen {
	protected DrawingSurface surface;
	protected static Color themeColor;
	protected PImage background;
	protected static String BGName;
	protected final static String fileSeparator = System.getProperty("file.separator");
	
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
		BGName = "background1.jpg";
	}
	
	
	public void changeTheme(Color newColor, String BG) {
		themeColor = newColor;
		BGName = BG;
	}
	
	/**
	 * sets up screen
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

	}
	
	/**
	 * depending on where the mouse is released, the program will do something
	 */
	public void mouseReleased() {
		
	}
	
	/**
	 * based on which key is pressed, do something
	 */
	public void keyPressed() {
		
	}
	
}