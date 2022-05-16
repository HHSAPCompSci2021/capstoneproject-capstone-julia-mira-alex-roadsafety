package mhaldar.shapes;
/** 
 * The Rectangle class creates a double-precision rectangle that is drawn with the Processing Library.
 * It can perform basic calculations. 
 *  @author Mira Haldar
 *  @version 9/21/21
 */
import processing.core.PApplet;

import java.awt.Color;
import java.lang.Math; 
import java.util.*; 

public class Rectangle extends Shape{
	private double width, height;
	/**
	 * initializes a Rectangle with x, y set to zero and widt and height set to zero: no args constructor
	 */
	public Rectangle() {
		super(0, 0);
		width = 0; 
		height = 0; 
	}
	/**
	 * Initializes a Rectangle with given parameters:
	 * @param xcoord x-coordinate of upper left point of rectangle 
	 * @param ycoord y-coordinate of upper left point of rectangle 
	 * @param w width of rectangle
	 * @param h height of rectangle 
	 * @pre w and h should be greater than or equal to 0 
	 */
	public Rectangle(double xcoord, double ycoord, double w, double h) {
		super(xcoord, ycoord); 
		width = w; 
		height = h; 
	}
	
	public double getPerimeter() {
		double p = 2*height + 2*width; 
		return Math.round(p*100)/100.0; 
	}
	/**
	 * 
	 * @return area of rectangle 
	 */
	public double getArea() {
		double area = height*width;
		return Math.round(area*100)/100.0; 
	}

	public boolean isPointInside(double x1, double y1) {
		if(x1>= this.getX()-.001 && x1<= this.getX()+width+.01) {
			if(y1>= this.getY()-.001 && y1<= this.getY()+height +.001) {
				return true; 
			}
		}
		return false; 
	}
	/**
	 * checks to see if the given rectangle is touching this one
	 * @param other the given Rectangle 
	 * @return true if other rectangle is inside/overlapping with this rectangle, false otherwise
	 * @pre other is not null, and it has to be another rectangle 
	 */
	public boolean intersects(Shape other) {
		ArrayList<Double> data = other.getData(); 
		double y = this.getY(); 
 		double x1 = data.get(0); 
		double y1 = data.get(1); 
		double w1 = data.get(2); 
		double h1 = data.get(3); 
		if((x1>= this.getX()-.001 && x1<= this.getX()+width+.01) || (x1+w1>= this.getX()-.001 && x1+w1<= this.getX()+width+.01)) {
			if((y1>= y-.001 && y1<= y+height+.01) || (y1+h1>= y-.001 && y1+h1<= y+height+.01)) {
				return true; 
			}
		}
		
		return false; 
	}
	@Override
	public void draw(PApplet drawer) {
		super.draw(drawer);
		double x = this.getX();
		double y = this.getY(); 
		drawer.rect((float)x, (float)y, (float)width, (float)height);
	}
	/**
	 * calculates the distance between the given point and center of the rectangle
	 * @param x1 x-coordinate of the center of the rectangle
	 * @param y1 y-coordinate of the center of the rectangle
	 * @return the distance between the point and the center of the rectangle
	 */
	//given a point, it will calculate the distance bw it and the center of the rectangle 
	public double calculateDistance(double x1, double y1) {
		double x = this.getX();
		double y = this.getY(); 
		double x2 = x + width/2;
		double y2 = y + height/2;
		double dist = Math.sqrt(Math.pow(x1-x2, 2)+ Math.pow(y1-y2, 2)); 
		return Math.round(dist*100)/100.0; 
	}
	/**
	 * 
	 * @return an ArrayList containing the fields of the rectangle 
	 */
	public ArrayList<Double> getData(){
		double x = this.getX();
		double y = this.getY(); 
		ArrayList<Double> data = new ArrayList(); 
		data.add(x);
		data.add(y);
		data.add(width);
		data.add(height);
		return data; 
	}
	/**
	 * sets bottom right coordinate of the rectangle 
	 * @param x1 x coordinate of point
	 * @param y1 y coordinate of point 
	 */
	public void setBottomRight(double x1, double y1) {
		double x = this.getX();
		double y = this.getY(); 
		width = Math.abs(x1-x);
		height = Math.abs(y1-y); 
	}
	@Override
	public double getCenterX() {
		double x = this.getX();
		return x + width/2;
	}
	@Override
	public double getCenterY() {
		double y = this.getY(); 
		return y + height/2;
	}
}
