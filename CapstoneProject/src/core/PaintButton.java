package core;
import mhaldar.shapes.Shape;
import processing.core.PApplet;
import screens.*; 
public class PaintButton extends Button {
	private Window w; 
//	Shape shape; 
	private Paint paint;  
	public PaintButton(Shape button, String text, Paint p) {
		super(button, text, p.getColor()); 
		paint = p; 
	}
	//draw paint w/ an x over it if unavailable and do all sorts of things to the window 
	/**
	 * draws the button at specified coord; draws x over it if color is unavailable 
	 * @param p PApplet
	 * @param x xcoord
	 * @param y ycoord 
	 */
	public void draw(PApplet surface, int x, int y) {
		//draw button at specified coord 
		super.draw(surface, x, y);
		if(!paint.isAvailable()) {
			
		}
	}
	public Paint getPaint() {
		return paint; 
	}
}
