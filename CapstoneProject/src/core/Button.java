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
	private Color textColor; 
	private Color color; 
	/**
	 * Constructs a button with a given shape and text and color 
	 * @param shape
	 * @param text
	 * @param color
	 */
	public Button(Shape shape, String text, Color color) {
		this.shape = shape; 
		this.text = text;  
		this.color = color;
	}
	
	
	public double getXCoord() {
		return shape.getData().get(0);
	}
	
	public double getYCoord() {
		return shape.getData().get(1);
	}
	
	public double getWidth() {
		return shape.getData().get(2);
		
	}
	
	public double getHeight() {
		return shape.getData().get(3);
	}
	
	/**
	 * 
	 * @param x x coord 
	 * @param y y coord
	 * @return whether the point with coords x and y is in the button 
	 */
	public boolean isClicked(Point p) {
	//	System.out.println(x + " " + y + " " + shape.getX() + " " + shape.getY());
		return shape.isPointInside(p.getX(), p.getY()); 
	}
	
	/**
	 * draws the button at specified coord 
	 * @param p PApplet
	 * @param x xcoord
	 * @param y ycoord 
	 */
	public void draw(PApplet p, double x, double y) {
		shape.setFillColor(color, true);
		 shape.draw(p);
		 if(textColor != null) {
			 p.fill(textColor.getRGB());
		 }
		 else {
			 p.fill(128, 128, 0);
		 }

		 p.textSize(25);
		// make sure the text color is black ughhh p.text
		// p.fill(0);
		 p.text(text, (float)x, (float)y);
		// p.fill(255);
	}
	/**
	 * a more convenient draw method 
	 * @param p PApplet surface 
	 */
	public void draw (PApplet p) {
		shape.setStrokeWeight(2);
		shape.setFillColor(color, true);
		 shape.draw(p);
		// int grayvalue = 255/color.getRGB(); 
		 
		 if(textColor != null) {
			 p.fill(textColor.getRGB());
		 }
		 else {
			 p.fill(Color.black.getRGB());
		 }

		 p.textSize(25);
		// make sure the text color is black ughhh p.text
		 p.text(text, (float)shape.getX() + 5, (float)shape.getY() + 20);
		 p.fill(255);
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
	public void setColor(Color color) {
		this.color = color; 
	}
	/**
	 * 
	 * @return color of shape 
	 */
	public Color getColor() {
		return shape.getFillColor();
	}
	public void setTextColor(Color color) {
		this.textColor = color; 
	}
}
