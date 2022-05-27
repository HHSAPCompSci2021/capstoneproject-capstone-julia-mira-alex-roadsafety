package core;
import mhaldar.shapes.*;
import processing.core.PApplet;
import java.awt.Color; 

import java.awt.Point;
/**
 * Creates a button that does stuff when clicked 
 * @author mhaldar640 and Julia
 */
public class Button {
	private Shape shape; 
	private String text; 
	private Color textColor; 
	private Color color; 
	
	/**
	 * Constructs a button with a given shape and text and color 
	 * @param shape shape of the button
	 * @param text the text displayed in button
	 * @param color color of button
	 */
	public Button(Shape shape, String text, Color color) {
		this.shape = shape; 
		this.text = text;  
		this.color = color;
	}
	
	/**
	 * returns x coordinate of button
	 * @return x coord 
	 */
	public double getXCoord() {
		return shape.getData().get(0);
	}
	
	/**
	 * return y coordinate of button
	 * @return y coord 
	 */
	public double getYCoord() {
		return shape.getData().get(1);
	}
	
	/**
	 * 
	 * @return width of button
	 */
	public double getWidth() {
		return shape.getData().get(2);
		
	}
	
	/**
	 * return the height of the button
	 * @return height of button
	 */
	public double getHeight() {
		return shape.getData().get(3);
	}
	
	/**
	 * returns color of button
	 * @return color of shape 
	 */
	public Color getColor() {
		return shape.getFillColor();
	}
	
	/**
	 * change what the button says 
	 * @param n the new text for the button
	 */
	public void setText(String n) {
		text = n; 
	}
	
	/**
	 * changes color of button 
	 * @param color new color
	 */
	public void setColor(Color color) {
		this.color = color; 
	}
	
	/**
	 * sets color of text
	 * @param color new text color 
	 */
	public void setTextColor(Color color) {
		this.textColor = color; 
	}
	
	/**
	 * checks if point is inside button
	 * @param x x coord 
	 * @param y y coord
	 * @return whether the point with coords x and y is in the button 
	 */
	public boolean isClicked(Point p) {
		return shape.isPointInside(p.getX(), p.getY()); 
	}
	
	/**
	 * if point p is inside button, the button "glows"
	 * @param p mouse point
	 * @param surface processing to draw highlight button
	 */
	public void highlight(Point p, PApplet surface) {
		if (shape.isPointInside(p.getX(), p.getY())) {
			surface.noFill();
			surface.stroke(113, 240, 147);
			surface.strokeWeight(5);
			surface.rect((float)this.getXCoord()+1, (float)this.getYCoord()+1, (float)(this.getWidth()-1.5), (float)(this.getHeight()-1.5));
		}
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
		p.text(text, (float)x, (float)y);
	}
	
	/**
	 * a more convenient draw method 
	 * @param p PApplet surface 
	 */
	public void draw (PApplet p) {
		shape.setStrokeWeight(2);
		shape.setFillColor(color, true);
		shape.setStrokeColor(new Color(0));
		shape.draw(p);
		 
		if(textColor != null) {
			p.fill(textColor.getRGB());
		}
		else {
			p.fill(Color.black.getRGB());
		}

		p.textSize(25);
		p.text(text, (float)shape.getCenterX() - (p.textWidth(text)/2), (float)shape.getCenterY() + 8);
		p.fill(255);
	}
	
	/**
	 * move the button
	 * @param p Point of destination 
	 */
	public void changeLocation(Point p) {
		shape.setPoint(p.getX() - 25, getYCoord());
	}
}
