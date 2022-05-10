package mhaldar.shapes;
import java.awt.Color;
/** 
 * The Circle class creates a double-precision circle that is drawn with the Processing Library.
 * It can do basic calculations. 
 *  @author Mira Haldar
 *  @version 9/21/21
 */
import java.util.ArrayList;

import processing.core.PApplet;

public class Circle extends Shape{
	private double diameter;
	/**
	 * @author Mira Haldar
	 * Initializes a Circle with x-coordinate, y-coordinate, and diameter as 0.
	 */
	public Circle() {
		super(0, 0); 
		diameter = 0;  
	}
	/**
	 * Initializes a Circle with given parameters:
	 * @param xcoord x-coordinate of circle center 
	 * @param ycoord y-coordinate of circle center
	 * @param d  diameter of circle 
	 */
	public Circle(double xcoord, double ycoord, double d) {
		super(xcoord, ycoord); 
		diameter = d; 

	}
	/**
	 * @return the circumference/perimeter of the circle 
	 */
	public double getPerimeter() {
		double c = diameter*Math.PI; 
		return Math.round(c*100)/100.0; 
	}
	/**
	 * @return area of the circle 
	 */
	public double getArea() {
		double area =Math.pow(diameter/2, 2)*Math.PI;
		return Math.round(area*100)/100.0; 
	}
	/**
	 * Checks whether the given coordinates are inside the circle 
	 * @param x1 x-coordinate of point
	 * @param y1 y-coordinate of point
	 * @return true if point is inside or on circle, false otherwise
	 */
	public boolean isPointInside(double x1, double y1) {
		if(this.calculateDistance(x1, y1)<= diameter/2+.1) {
			return true;
		}
		return false; 
	}
	/**
	 * Checks if circles are touching 
	 * @param other The other circle to check intersection 
	 * @return true if other circle is touching or in circle, false otherwise 
	 * @pre other is not null. Also, it should be a Circle. 
	 */
	public boolean intersects(Shape other) {
		if(this.calculateDistance(other.getData().get(0),other.getData().get(1))<= (diameter/2+other.getData().get(2)/2+.1)) {
			//System.out.println(other.getData().get(2) + " " + diameter);  
			return true;
		}
		return false; 
	}
	@Override
	public void draw(PApplet drawer) {
		super.draw(drawer);
		drawer.circle((float)this.getX(), (float)this.getY(), (float)diameter);
	}
	/**
	 * calculates the distance between the given point and center of the circle
	 * @param x1 x-coordinate of the point
	 * @param y1 y-coordinate of the point 
	 * @return the distance between the point and the center of the circle 
	 */
	//given a point, it will calculate the distance bw it and the center of the circle 
	public double calculateDistance(double x1, double y1) {
		double dist=   Math.sqrt(Math.pow(x1-this.getX() , 2)+ Math.pow(y1-this.getY(), 2)); 
		//System.out.println(dist); 
		//return Math.round(dist*100)/100.0; 
		return dist;
	}
	/**
	 * 
	 * @return an ArrayList containing all the fields of this Circle 
	 */
	public ArrayList<Double> getData(){
		ArrayList<Double> data = new ArrayList<Double>(); 
		data.add(this.getX());
		data.add(this.getY());
		data.add(diameter);
		return data; 
	}
	/**
	 * changes the radius to be the distance between the current center and the given point
	 * @param x1 x-coordinate of the center of the circle 
	 * @param y1 y-coordinate of the center of the circle 
	 */
	public void setRadius(double x1, double y1) {
		diameter = Math.sqrt(Math.pow(x1-this.getX(), 2) + Math.pow(y1-this.getY(), 2)); 
	}
	/**
	 * changes the radius to be the distance between the current center and the given point
	 * @param radius the new radius of the circle
	 */
	public void setRadius(double radius) {
		diameter = 2*radius; 
	}
	@Override
	public double getCenterX() {
		return this.getX();
	}
	@Override
	public double getCenterY() {
		// TODO Auto-generated method stub
		return this.getY();
	}
}
