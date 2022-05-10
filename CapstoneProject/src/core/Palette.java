package core;
import processing.awt.*;
import processing.core.*;
import java.awt.Point; 
import mhaldar.shapes.*;

public class Palette {
// just arrange paints in a nice visual array and have a sort method so you can separate available from unavailable
	private Paint[] collection; // it'll end up looking like a nice 4 by six array maybe??? 
	int divide; 
	private Rectangle bg; 
	/**
	 * set up a Palette of 24 Paints 
	 */
	public Palette() {
		divide = 0; 
		collection = new Paint[24]; 
		// create set up all the colors over here 
	}
	protected void sort() {
		for(int i = 0; i< collection.length; i++) {
			if(!collection[i].isAvailable()) {
				
			}
		}
	}
	/**
	 * draw the Palette onto the surface, with the point being it's upper left coord 
	 * @param surface
	 */
	public void draw(PApplet surface, Point p) {
		
	}
	/**
	 * changes Paint p to given boolean for available 
	 * @param p
	 * @param available
	 */
	public void changePaintAvailability(Paint p, boolean available) {
		
	}
	/**
	 * 
	 * @param x xcoord
	 * @param y ycoord
	 * @return the paint that the coordinate lands on
	 */
	public Paint selectPaint( int x, int y) {
		return null; 
	}
	public void draw(PApplet surface, int x, int y) {
		
	}
	//other methods: usePaint? parameter is how much used and it becomes zero then it can't be used n e more? 
}
