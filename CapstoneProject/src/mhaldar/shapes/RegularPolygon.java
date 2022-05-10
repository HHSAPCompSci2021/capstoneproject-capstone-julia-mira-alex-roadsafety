package mhaldar.shapes;
import java.awt.Color; 
//import mhaldar.shapes.*; 
import java.util.ArrayList;
import processing.core.PApplet;
/**
 * creates a regular polygon 
 * @author mhaldar640
 *
 */
public class RegularPolygon extends Shape{
	private double numSides, length; 
	private ArrayList<Line> lines; 
	/**
	 * constructs a regular polygon using the following parameters: 
	 * @param x x-coordinate of center of polygon
	 * @param y y-coordinate of center of polygon 
	 * @param numSides number of sides the polygon has 
	 * @param length the length of the sides(each side is the same length) 
	 */
	public RegularPolygon(double x, double y, int numSides, double length) {
		super(x, y);
		this.numSides = numSides; 
		this.length = length; 
		float R = (float) calcR(); 
		lines = new ArrayList<Line>(); 
		double theta = this.calcVertexAngle();
	//	System.out.println(Math.toDegrees(theta)); 
		double currentAngle = Math.PI - theta/2; 
		float x1 = (float)getCenterX();
		float y1 = (float)getCenterY(); 
		Line l = new Line(0, 0, 0, 0); 
		for(int i = 0; i< numSides; i++) {
			if(i==0) {
				l = l.constructLineAngle(x1+R, y1, Math.toDegrees(currentAngle), length); 
			}
			else {
				ArrayList<Double> data = l.getData();
				l = l.constructLineAngle(data.get(1), data.get(3), Math.toDegrees(currentAngle), length);
				l.setFillColor(getFillColor(), false);
			} 
			if(i ==0 ) {
				//l.setStrokeColor(Color.RED);
			}
			currentAngle = currentAngle + Math.PI - theta; 
			lines.add(l); 
		}
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean intersects(Shape other) {
		RegularPolygon shape =  (RegularPolygon) other; 
//		 
		Circle r1 = new Circle(getX(), getY(), calcr());
		Circle r2 = new Circle(other.getX(), other.getY(), shape.calcr()); 
		if (r2.intersects(r1))
			return true;
		else {
		for(int i = 0; i<lines.size(); i++) {
			for(int k = 0; k< shape.getLines().size(); k++) {
				//System.out.println(shape.getLines().get(k).getX() + "  " + shape.getLines().get(k).getY())); 
				if(lines.get(i).intersects(shape.getLines().get(k))) {
//					if(numSides == 3) {
//						System.out.println(lines.get(i).getIntersectionX(shape.getLines().get(k)) + " , " + lines.get(i).getIntersectionY(shape.getLines().get(k)));
//					}
					return true; 
				}
				if(this.isPointInside(shape.getLines().get(k).getX(),shape.getLines().get(k).getY())) {
					return true; 
				}
				//if(other.)
			}
		}
//		// TODO Auto-generated method stub
//		//System.out.println("false \n"); 
		}
		return false;
	}

	@Override
	public boolean isPointInside(double x, double y) {
		if(Math.sqrt( Math.pow( x-getCenterX(), 2) + Math.pow(y-getCenterY(), 2))> calcR()) {
			//System.out.println( Math.pow( x-getCenterX(), 2) + Math.pow(y-getCenterY(), 2));
			return false; 
		}
		if(Math.sqrt( Math.pow( x-getCenterX(), 2) + Math.pow(y-getCenterY(), 2))<= calcr()) {
			return true; 
		}
//		for(int i = 0; i< lines.size(); i++) {
//			double minAngle = lines.get(i).findAngle(); 
//			Line check = new Line(lines.get(i).getData().get(0), lines.get(i).getData().get(2), x, y); 
//			check.draw(surface);
////			Line ch2 = new Line(check.getData().get(0), check.getData().get(2),  check.getData().get(0) +100,  check.getData().get(2));
////			ch2.draw(surface);
//			System.out.println(minAngle + " " + Math.cos(minAngle) + " " + check.findAngle() + " " + Math.cos(check.findAngle())); 
//			if(Math.cos(check.findAngle()) >  Math.cos(minAngle) && Math.sin(check.findAngle()) <  Math.sin(minAngle)) {
////				if(minAngle- 2*Math.PI >= 0 && minAngle-2*Math.PI <= check.findAngle()) {
////					continue; 
////				}
//				System.out.println("false \n"); 
//				return false; 
//			}
//			//if(Math.cos(minAngle) )
//			
//		}
		for(int i = 0; i< lines.size(); i++) {
			Line l = lines.get(i); 
			double m = l.getSlope();
			double b = l.getIntercept(); 
			//System.out.println(m + "  " + b); 
			if(l.getX()< getCenterX() && l.getData().get(1) < getCenterX() && x< l.getX() && x < l.getData().get(1)) {
				//System.out.println("false \n"); 
				return false; 
			}
			else if(l.getX()>getCenterX() && l.getData().get(1) > getCenterX() && x> l.getX() && x > l.getData().get(1)) {
				//System.out.println("false \n"); 
				return false; 
			}
			if((m*x + b) >= getCenterY()) {
				if(y> (m*x + b)) {
					//System.out.println("false \n"); 
					return false; 
				}
			}
			else {
				if(y < (m*x + b)) {
					//System.out.println("false \n"); 
					return false; 
				}
			}
			//if(l)
		}
	//	System.out.println("true \n"); 
		return true;
	}

	@Override
	public double getPerimeter() {
		return length*numSides;
	}

	@Override
	public double getArea() {
		return calcr()*length*numSides/2; 
	}

	@Override
	public double getCenterX() {
		// TODO Auto-generated method stub
		return getX();
	}

	@Override
	public double getCenterY() {
		// TODO Auto-generated method stub
		return getY();
	}

	@Override
	/**
	 * FOR THIS CLASS THIS METHOD IS NOT USED; IT DOESN'T RETURN ANYTHING
	 */
	public ArrayList<Double> getData() {
		return null;
	}
	/**
	 * 
	 * @return the inradius of the polygon 
	 */
	public double calcr() {
		return .5* length/Math.tan(Math.PI/numSides);
	}
	/**
	 * 
	 * @return the circumradius of the polygon 
	 */
	public double calcR() {
		return .5*length/Math.sin(Math.PI/numSides); 
	}
	/**
	 * 
	 * @return vertex angle in radians
	 */
	public double calcVertexAngle() {
		return (numSides-2)*Math.PI/numSides; 
	}
	/**
	 * 
	 * @return side length of the polygon- they're all the same 
	 */
	public double getSideLength() {
		return length; 
	}
	/**
	 * draws the polygon 
	 * @param surface PApplet surface to draw on 
	 */
	public void draw(PApplet surface) {
		super.draw(surface);
		for(int i = 0; i< lines.size(); i++) {
			lines.get(i).draw(surface);
		}
	
	
		
		//surface.line((float)(x+ calcR()), (float)y); 
		
	}
	/**
	 * draws the circumcircle + incircle onto the PApplet 
	 * @param surface PApplet to drw on
	 */
	public void drawBoundingCircles(PApplet surface) {
		surface.noFill(); 
		surface.circle((float)getCenterX(), (float)getCenterY(), (float)calcR()*2);
		surface.circle((float)getCenterX(), (float)getCenterY(), (float)calcr()*2); 
	}
	/**
	 * 
	 * @return Arraylist of the lines 
	 */
	public ArrayList<Line> getLines(){
		return lines; 
	}
	/**
	 * change the side length of the polygon 
	 * @param length the new side length
	 */
	public void setlength(double length) {
		this.length = length; 
	}
	/**
	 * 
	 * @return the number of sides 
	 */
	public int getNumside() {
		return (int) this.numSides; 
	}
	/**
	 * 
	 * @return the interior angle - same for all angles 
	 */
	public double vertexAngle() {
		return this.calcVertexAngle(); 
	}
	//@Override
	public void moveBy(double x, double y) {
		super.moveBy(x, y);
		for(int i = 0; i< lines.size(); i++) {
			lines.get(i).moveBy(x, y); 
		}
	}
}
