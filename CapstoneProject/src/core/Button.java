package core;
import mhaldar.shapes.*;
import processing.core.PApplet;
import java.awt.Color; 

import java.awt.Point;
/**
 * Creates a button that does stuff when clicked 
 * @author mhaldar640
 *
 */
public class Button {
	private Shape shape; 
	private String text; 
	private int[] textColor; 
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
	public void draw(PApplet p, double x, double y) {
		shape.setFillColor(new Color(color[0], color[1], color[2]), true);
		 shape.draw(p);
		 if(textColor != null) {
			 p.fill(textColor[0], textColor[1], textColor[2]);
		 }
		 else {
			 p.fill(128, 128, 0);
		 }
		 p.textSize(25);
		// make sure the text color is black ughhh p.text
		 p.text(text, (float)x, (float)y);
		 
	}
	/**
	 * change what the button says 
	 * @param n the new text for the button
	 */
	public void setText(String n) {
		text = n; 
	}
	/**
	 * change hideous color of button 
	 * @param color
	 */
	public void setColor(int[] color) {
		this.color = color; 
	}
	/**
	 * 
	 * @return color of shape 
	 */
	public Color getColor() {
		return shape.getFillColor();
	}
	public void setTextColor(int[] color) {
		this.textColor = color; 
	}
}
