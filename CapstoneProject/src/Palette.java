import processing.awt.*;
import processing.core.*;
import java.awt.Point; 
import mhaldar.shapes.*;

public class Palette {
// just arrange paints in a nice visual array and have a sort method so you can separate available from unavailable
	private Paint[][] collection; //a nice 4 by six array maybe??? 
	private Rectangle bg; 
	/**
	 * set up a Palette of 24 Paints 
	 */
	public Palette() {
		// create set up all the colors over here 
	}
	protected void sort() {
		
	}
	/**
	 * draw the Palette onto the surface, with the point being it's upper left coord 
	 * @param surface
	 */
	public void draw(PApplet surface, Point p) {
		
	}
	/**
	 * changes Paint p to given boolean for availble 
	 * @param p
	 * @param available
	 */
	public void changePaintAvailability(Paint p, boolean available) {
		
	}
	/**
	 *@param p 
	 * @return paint that Point p is on 
	 */
	public Paint selectPaint(Point p) {
		return null; 
	}
	//other methods: usePaint? 
}
