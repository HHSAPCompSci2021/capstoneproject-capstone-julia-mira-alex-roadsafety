package mhaldar.shapes;
import processing.core.PApplet; 
import java.awt.Color; 
import java.util.ArrayList; 
/**
 * @author Mira Haldar
 * This is an abstract class that defines a shape 
 */
public abstract class Shape {
	//field
	private double x, y; 
	private Color fillColor, strokeColor; 
	private float strokeWeight; 
	boolean isFilled; 
	/**
	 * initializes a Shape with the given parameters 
	 * @param x x-coordinate
	 * @param y y-coordinate
	 */
	public Shape(double x, double y) {
		this.x = x; 
		this.y = y; 
		this.strokeColor = Color.black; 
		this.strokeWeight = 1;
		fillColor = null; 
		isFilled = false; 
	}
	/**
	 * initializes a Shape with these parameters 
	 * @param x the x-coordinate 
	 * @param y the y-coordinate 
	 * @param strokeColor the color of the outline 
	 * @param strokeWeight the thickness 
	 */
	public Shape(double x, double y, Color strokeColor, float strokeWeight) {
		this.x = x; 
		this.y = y; 
		this.strokeColor = strokeColor; 
		this.strokeWeight = strokeWeight; 
		this.fillColor = null; 
		isFilled = false; 
	}
	/**
	 * initializes a Shape with these parameters 
	 * @param x the x-coordinate 
	 * @param y the y-coordinate 
	 * @param strokeColor the color of the outline 
	 * @param strokeWeight the thickness 
	 * @param fillColor the color inside the shape 
	 * @post shapes won't be transparent- which might be a problem 
	 */
	public Shape(double x, double y, Color strokeColor, float strokeWeight, Color fillColor) {
		this.x = x; 
		this.y = y; 
		this.strokeColor = strokeColor; 
		this.fillColor = fillColor; 
		isFilled = true; 
		this.strokeWeight = strokeWeight; 
	}
	/**
	 * prepares PApplet surface for drawing. Each shape class has an override method which actually draws the shape
	 * @param surface the PApplet surface
	 */
	public void draw(PApplet surface) {
		if(isFilled) {
			surface.fill(fillColor.getRed(),fillColor.getGreen(), fillColor.getBlue());
		}
		surface.strokeWeight(strokeWeight); 
		surface.stroke(strokeColor.getRed(),strokeColor.getGreen(), strokeColor.getBlue());
		
	}
	/**
	 * checks to see if the given Shape intersects with another 
	 * @param other the given Shape
	 * @return true if other Shape is inside/overlapping with this Shape, false otherwise
	 * @pre the Shapes have to be from the same subclass 
	 */
	public abstract boolean intersects(Shape other);
	/**
	 *  checks if the given point is inside or on the Shape
	 * @param x x-coordinate of the point 
	 * @param y y-coordinate of the point 
	 * @return true if the point is inside or on the Shape, false otherwise 
	 */
	public abstract boolean isPointInside(double x, double y);
	/**
	 * @return the perimeter of the Shape
	 */
	public abstract double getPerimeter(); 
	/**
	 * 
	 * @return the area of the Shape
	 * @post for a Line this would return 0 
	 */
	public abstract double getArea(); 
		//for line this would be 0^ 
	/**
	 * 
	 * @return the x-coordinate of the center of a Shape 
	 */
	public abstract double getCenterX(); 
	/**
	 * 
	 * @return the y-coordinate of the center of a Shape 
	 */
	public abstract double getCenterY();
	/**
	 * given two shapes from the same subclass, return the one with the greater area
	 * @param other the given shape 
	 * @return the larger shape 
	 */
	public Shape findMaxShape(Shape other) {
	//	double a = other.getArea(); 
		if(this.getArea()<= other.getArea()) {
			return other; 
		}
		return this; 
	}
	/**
	 * get all the fields that a subclass contains 
	 * @return an Arraylist of subclass-specific fields 
	 * @post fill color, stroke color, and stroke weight won't be included 
	 */
	public abstract ArrayList<Double> getData();
	/**
	 * move the x and y coordinates
	 * @param x how much you want to move by on the x-axis
	 * @param y how much you want to move by on the y-axis 
	 */
	public void moveBy(double x, double y) {
		this.x +=x; 
		this.y += y; 
	}
	/**
	 * change the x and y coordinates 
	 * @param x new x-coordinate
	 * @param y new y-coordinate 
	 */
	public void setPoint(double x, double y) {
		this.x = x; 
		this.y = y; 
	}
	public String toString() {
		ArrayList<Double> data = this.getData();
		String s = ""; 
		for(int i = 0; i<data.size(); i++) {
			s+= data.get(i) + ",\t"; 
		}
		return s;  
	}
	/**
	 * 
	 * @return the x-coordinate of the shape 
	 */
	public double getX() {
		return x; 
	}
	/**
	 * 
	 * @return the y-coordinate of the shape 
	 */
	public double getY() {
		return y; 
	}
	/**
	 * change stroke color 
	 * @param strokeColor new outline color 
	 */
	public void setStrokeColor(Color strokeColor) {
		this.strokeColor = strokeColor; 
	}
	/**
	 * change outline thickness 
	 * @param strokeWeight new stroke weight
	 */
	public void setStrokeWeight( float strokeWeight) {
		this.strokeWeight = strokeWeight; 
	}
	/**
	 * changes the fill color 
	 * @param fillColor new fill color
	 * @param isFilled boolean If you want to remove the fill, set fillColor to null and this parameter to false
	 */
	public void setFillColor(Color fillColor, boolean isFilled) {
		this.fillColor = fillColor;
		this.isFilled = isFilled; 
	}
	/**
	 * 
	 * @return color of outline 
	 */
	public Color getstrokeColor() {
		return strokeColor; 
	}
	/**
	 * 
	 * @return get fill color 
	 */
	public Color getFillColor() {
		return fillColor; 
	}
	/**
	 * 
	 * @return thickness of outline
	 */
	public float getstrokeWeight() {
		return strokeWeight; 
	}
}
