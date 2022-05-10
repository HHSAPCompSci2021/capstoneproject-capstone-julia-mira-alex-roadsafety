package core;
import mhaldar.shapes.*;
import processing.core.PApplet;

import java.awt.Point;
/**
 * Creates a button that does stuff when clicked 
 * @author mhaldar640
 *
 */
public class Button {
	private Shape shape; 
	private String text; 
	private int[] color; 
	/**
	 * Constructs a button with a given shape and text and color 
	 * @param shape
	 * @param text
	 * @param color
	 */
	public Button(Shape shape, String text, int[] color) {
		this.shape = shape; 
		this.text = text; 
		this.color = color; 
	}
	/**
	 * 
	 * @param x x coord 
	 * @param y y coord
	 * @return whether the point with coords x and y is in the button 
	 */
	public boolean isClicked(double x, double y ) {
		return shape.isPointInside(x, y); 
	}
	/**
	 * draws the button at specified coord 
	 * @param p PApplet
	 * @param x xcoord
	 * @param y ycoord 
	 */
	public void draw(PApplet p) {
		 shape.draw(p);
		 
	}
}
