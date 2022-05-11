package core;
import processing.awt.*;
import processing.core.*;
import java.awt.Point; 
import mhaldar.shapes.*;
import java.util.*; 
import java.awt.Color; 

public class Palette {
// just arrange paints in a nice visual array and have a sort method so you can separate available from unavailable
	private ArrayList<PaintButton> collection; // it'll end up looking like a nice 4 by six array maybe??? 
	int divide; 
	private Rectangle bg; 
	/**
	 * set up a Palette of 14 Paints 
	 */
	public Palette() {
		divide = 1; 
		collection = new ArrayList<PaintButton>(); 
		bg = new Rectangle(500, 0, 300, 600); 
		Paint[] paints = new Paint[14]; 
		paints[0] = new Paint(Color.black, 100);
		paints[2] = new Paint(Color.white, 100); 
		paints[3] = new Paint(Color.red, 0); 
		paints[4] = new Paint(Color.blue, 0); 
		paints[5] = new Paint(Color.orange, 0); 
		paints[6] = new Paint(Color.PINK, 0);
		paints[7] = new Paint(Color.yellow, 0); 
		paints[8] = new Paint(Color.cyan, 0); 
		paints[9] = new Paint(new Color(255, 0, 127), 0);
		paints[10] = new Paint(new Color(51, 255, 133), 0); 
		
		// create set up all the colors over here 
		int count = 0; 
		for(int j = 0; j < 6; j ++ ) {
			for(int i = 0; i< 4; i++) {
				count ++; 
				Rectangle r =  new Rectangle(500 + 50*j, 80*j, 50, 80); 
				
			}
		}
		
	}
	protected void sort() {
		for(int i = 0; i< collection.size(); i++) {
			Paint p = collection.get(i).getPaint(); 
			if(!p.isAvailable() && i<= divide) {
				collection.add(collection.remove(i)); 
				divide --; 
			}
			else if(p.isAvailable() && i> divide) {
				collection.add(0, collection.remove(i)); 
				divide ++; 
			}
		}
	}
	/**
	 * draw the Palette onto the surface, with the point being it's upper left coord 
	 * @param surface
	 */
	public void draw(PApplet surface, Point p) {
		//draw a rectangle 
		//draw 14 evenly paced paints on rectangle 
	}
	/**
	 * changes Paint p to have the given amount 
	 * @param p the paint 
	 * @param available whether its available or not 
	 */
	public void changePaintAvailability(PaintButton p, int amount) {
		p.getPaint().makeAvailable(amount);
	}
	/**
	 * 
	 * @param x xcoord
	 * @param y ycoord
	 * @return the paint button that the coordinate lands on. null if the point isn't on a paintbutton
	 */
	public PaintButton selectPaint( int x, int y) {
		if(!bg.isPointInside(x, y)) {
			return null; 
		}
		for(PaintButton i: collection) {
			if(i.isClicked(x, y)) {
				i.createWindow();
				return i; 
			}
		}
		//make amount decrease
		//and check if it's available first
		//if it isn't return null 
		return null; 
	}
	public void draw(PApplet surface, int x, int y) {
		
	}


}
