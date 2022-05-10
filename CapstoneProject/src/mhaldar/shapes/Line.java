package mhaldar.shapes;
import processing.core.PApplet;
import java.util.*;
import java.awt.Color;
import java.lang.Math; 
/**
 * @author Mira Haldar
 * This class creates a Line object w/ double precision. 
 *
 */
public class Line extends Shape{
	private double xe, ye, slope, intercept; 
	/**
	 * intializes a line with the following coordinates 
	 * @param x1 the x-coordinate of the first point
	 * @param y1 the y-coordinate of the first point 
	 * @param x2 the x-coordinate of the second point 
	 * @param y2 the y-coordinate of the second point 
	 */
	public Line( double x1, double y1, double x2, double y2) {
		super(x1, y1); 
		xe = x2; 
		ye = y2; 
		slope = (y2-y1)/(x2-x1); 
		intercept = y2-(slope*x2);
		//System.out.println(xs + " gf " + xe + "  " + ys + " " + ye ); 
	}
	/**
	 * constructs a line with different parameters in the constructor 
	 * @param x the x-coordinate of the first point 
	 * @param y the y-coordinate of the first point 
	 * @param angle the angle of the line in degrees (0 is horizontal right) 
	 * @param length the length of the Line 
	 * @return Line the new line 
	 */
	public Line constructLineAngle(double x, double y, double angle, double length) {
		double x2 = x + length*Math.cos(angle); 
		double y2 = y - length*Math.sin(angle); 
		//System.out.println(angle);
		Line l = new Line(x, y, x2, y2); 
		return l; 
	}
	/**
	 * Sets this line’s second point (x2, y2) to a new coordinate
	 * @param x2 x-coordinate of the given point 
	 * @param y2 y-coordinate of the given point 
	 */
	// Sets this line’s second point (x2, y2) to a new coordinate
	public void setPoint2(double x2, double y2) {
		xe = x2; 
		ye = y2; 
	}
	@Override
	public void draw(PApplet drawer) {
		super.draw(drawer);
		drawer.line((float)this.getX(), (float)this.getY(), (float)xe, (float)ye);
	}
	/**
	 * get a list of the coordinates
	 * @return Line fields 
	 */
	//get a list of the coordinates
	public ArrayList<Double> getData() {	
		ArrayList<Double> coordinates = new ArrayList<Double>();
		coordinates.add(this.getX());
		coordinates.add(xe); 
		coordinates.add(this.getY());
		coordinates.add(ye);
		return coordinates; 
	}
	/**
	 * Returns the x coordinate of the intersection point of this line and the other line (assuming they continue forever)
	 * @param other Line to find intersection with 
	 * @return x-coordinate of intersection, NaN if collinear 
	 * @pre if the Line has an undefined slope, it probably won't work. Also, the argument must be a Line. 
	 */
	// Returns the x coordinate of the intersection point of this line and the other line (assuming they continue forever)
	public double getIntersectionX(Shape other) {
		ArrayList<Double> otherco = other.getData();
		double x1 = this.getX();
		double x2 = xe;
		double x3 = otherco.get(0);
		double x4 = otherco.get(1); 
		double y1 = this.getY();
		double y2 = ye;
		double y3 = otherco.get(2);
		double y4 = otherco.get(3);
//		if(Math.round(((x1 -x2)*(y3-y4)-(y1-y2)*(x3-x4)*100.0))/100.0 == 0){
//			return Double.NaN; 
//		}
		//return (((x1*y2 -y1*x2)*(x3 -x4)-(x1 -x2)*(x3*y4-y3*x4))/((x1 -x2)*(y3-y4)-(y1-y2)*(x3-x4)));
		double m1 = (y4-y3)/(x4-x3); 
		double b1 = y4-(m1*x4); 
		double m2 = (y2-y1)/(x2-x1); 
		double b2 = y2-(m2*x2); 
		//System.out.println(m1 + " " + b1 + " " + m2 + " " + b2); 
		if(m1 == m2 && b1 == b2) {
			return Double.NaN; 
		}
		return Math.round(((b2-b1)/(m1-m2))*100.0)/100.0; 
	}
	/**
	 * Returns the y coordinate of the intersection point of this line and the other line (assuming they continue forever)
	 * @param other Line to find intersection with 
	 * @return y-coordinate of intersection, NaN if collinear 
	 * @pre if the Line has an undefined slope, it probably won't work. It also can't be any other shape than a Line 
	 */
	// Returns the y coordinate of the intersection point of this line and the other line (assuming they continue forever)
	public double getIntersectionY(Shape other) {
		ArrayList<Double> otherco = other.getData();
		double x1 = this.getX();
		double x2 = xe;
		double x3 = otherco.get(0);
		double x4 = otherco.get(1); 
		double y1 = this.getY();
		double y2 = ye;
		double y3 = otherco.get(2);
		double y4 = otherco.get(3);
		// remember to change this later to have a rounding point bc doubles are weird 
//		if(Math.round(((x1-x2)*(y3-y4)-(y1-y2)*(x3-x4))*100.0)/100.0 == 0){
//			return Double.NaN; 
//		}
		//return (((x1*y2-y1*x2)*(y3-y4)-(y1-y2)*(x3*y4-y3*x4))/((x1-x2)*(y3-y4)-(y1-y2)*(x3-x4))); 
		double m1 = (y4-y3)/(x4-x3); 
		double b1 = y4-(m1*x4); 
		double m2 = (y2-y1)/(x2-x1); 
		double b2 = y2-(m2*x2);
		if(m1 == m2 && b1 == b2 || (x1 == x2 && y1 == y2) || (x3 == x4 && y3 == y4)) {
			return Double.NaN; 
		}
		return Math.round((m1*(b2-b1)/(m1-m2) + b1)*100.0)/100.0 ; 
	}
	/**
	 * checks to see if two lines intersect 
	 * @param other Line object to check against 
	 * @return true, if lines intersect on PApplet surface, false if otherwise 
	 * @pre relies on getIntersectionX and getIntersectionY so their precondition applies here as well. The Argument passed should be a Line.
	 */
	public boolean intersects(Shape other) {
		ArrayList<Double> otherco = other.getData();
		double x1 = this.getX();
		double x2 = xe;
		double x3 = otherco.get(0);
		double x4 = otherco.get(1); 
		double y1 = this.getY();
		double y2 = ye;
		double y3 = otherco.get(2);
		double y4 = otherco.get(3);
		double checkX = this.getIntersectionX(other);
		double checkY = this.getIntersectionY(other);
//		System.out.println(checkX + " " + checkY); 
//		System.out.println(xs + " " + ys + "  " + xe + " " + ye); 
//		System.out.println(x3 + " " + y3 + "  " + x4 + " " + y4);
		// (NaN, NaN) as checks covers three cases: parallel, collinear, and if a point is involved. so you'd have to go thru and check all those cases
		if(Double.isNaN(checkY) || Double.isNaN(checkX)) {
				checkX = this.getX(); 
				checkY = this.getY(); 
				if( checkX <= Math.max(x1, x2) && checkX  >= Math.min(x1, x2) && checkX <= Math.max(x3, x4) && checkX  >= Math.min(x3, x4)) {
					if(checkY <= Math.max(y1, y2) && checkY  >= Math.min(y1, y2) && checkY <= Math.max(y3, y4) && checkY  >= Math.min(y3, y4)) {
						return true; 
					}
				}
				checkX = xe; 
				checkY = ye; 
		}
		if( checkX <= Math.max(x1, x2) && checkX  >= Math.min(x1, x2) && checkX <= Math.max(x3, x4) && checkX  >= Math.min(x3, x4)) {
			if(checkY <= Math.max(y1, y2) && checkY  >= Math.min(y1, y2) && checkY <= Math.max(y3, y4) && checkY  >= Math.min(y3, y4)) {
				return true;
			}
		}
		return false;
		
	}
	@Override
	public boolean isPointInside(double x, double y) {
		Line dot = new Line(x, y, x, y);
		return intersects(dot);
	}
	@Override
	public double getPerimeter() {
		double length =   Math.sqrt(Math.pow(this.getX()-xe, 2) + Math.pow(this.getY()-ye, 2))    ; 
		return length;
	}
	@Override
	public double getArea() {
		return 0;
	}
	@Override
	public double getCenterX() {
		return (this.getX()+xe)/2;
	}
	@Override
	public double getCenterY() {
		return (this.getY()+ye)/2;
	}
	/**
	 * 
	 * @return angle the line makes in radians, within the range of pi/2 to -pi/2
	 */
	public double findAngle() {
		 double x1 = getX(); 
		double x2 = getX() + 100; 
		double y1 = getY(); 
		double y2 =  getY();
		double x3 = getX(); 
		double x4 =  xe; 
		double y3= getY(); 
		double y4 =  ye;
		double dx1 = x2-x1;
		double dy1 = y2-y1;
		double dx2 = x4-x3;
		double dy2 = y4-y3;
		
		double d = dx1*dx2 + dy1*dy2;   // dot product of the 2 vectors
		double l2 = (dx1*dx1+dy1*dy1)*(dx2*dx2+dy2*dy2); // product of the squared lengths

		double angle = Math.acos(d/Math.sqrt(l2));
	//	double angle = Math.atan((ye-getY())/(xe-getX())); 
//		System.out.println(ye-getY()); 
//		System.out.println("gsdfgsfd"); 
	//	System.out.println(angle + " angle");
//		if(angle<=0) {
//			return angle += Math.PI; 
//		}
//		if(xe< getX()&&  ye< getY() ) {
//			System.out.println("dfsdfg");
//			if(angle <= Math.PI/2) {
//				angle = Math.PI- angle; 
//			}
//		}
//		else if (xe < getX() && ye > getY()) {
//			System.out.println("larp");
//			if( angle <= Math.PI/2)
//				angle += Math.PI; 
//			else {
//				angle = 2*Math.PI - angle; 
//			}
//		}
//		else if(xe > getX() && ye > getY()) {
//			System.out.println("ono");
//			if( angle <= Math.PI/2)
//				angle = 2*Math.PI - angle; 
//			else {
//				angle += Math.PI; 
//			}
//		}
//		else {
//			//angle+= 2*Math.PI; 
//		}
		return angle; 
	}
	public double getSlope() {
		return slope; 
	}
	public double getIntercept() {
		return intercept; 
	}
	@Override
	public void moveBy(double x, double y) {
		this.setPoint(this.getX() + x, this.getY()+y); 
		this.setPoint2(xe+ x, ye + y); 
	}
}
