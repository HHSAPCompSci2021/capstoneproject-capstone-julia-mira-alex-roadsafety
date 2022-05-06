//look into BufferedImage or whatever 
import processing.core.*;
import java.awt.image.*; 
import java.awt.Point;
/**
 * creates a painting where the user can draw and fill in areas 
 * @author mhaldar640
 *
 */
public class Painting {
	private BufferedImage piece; 
	private boolean[][] outline; 
	/**
	 * constructs an empty painting with given height and width 
	 * @param width
	 * @param height
	 */
	public Painting(int width, int height) {
		
	}
	/**
	 * fills an area with given color, starting at Point p 
	 * @param color
	 * @param p
	 */
	public void fill(int[] color, Point p) {
		
	}
	/**
	 * draws with a given color at point p 
	 * @param color
	 * @param p
	 */
	public void outline(int[] color, Point p) {
		
	}
	/**
	 * draws the painting onto PApplet 
	 * @param surface
	 * @param fill
	 * @param color
	 */
	public void draw(PApplet surface, boolean fill, int[] color) {
		
	}
}
