package core;
//look into BufferedImage or whatever 
import processing.core.*;
import processing.awt.*;
//import processing.*; 
import java.awt.image.*; 
import java.awt.*;
/**
 * creates a painting where the user can draw and fill in areas 
 * @author mhaldar640
 *
 */
public class Painting {
	private PImage piece; 
	//Graphics g; 
	private boolean[][] outline; 
	/**
	 * constructs an empty painting with given height and width 
	 * @param width
	 * @param height
	 */
	public Painting(int width, int height) {
		piece = new PImage(width, height);
		outline = new boolean[width][height]; 
		//g = piece.createGraphics();
	}
	/**
	 * fills an area with given color, starting at Point p 
	 * @param color  int array storing rgb values for the color 
	 * @param x  x coord
	 * @param y y coord 
	 */
	public void fill(int[] color, int x, int y) {
		piece.loadPixels();
		color c = color(color[0], color[1], color[2]); 
		if(y< 0 || y>= outline.length || x < 0 || x>= outline[y].length) {
			return; 
		}
		if(outline[y][x] || piece.get(x, y) == c) {
			return; 
		}
		else {	
			piece.set(x, y, c); 
			piece.updatePixels();
			fill(color, x+1, y); 
			piece.updatePixels();
		//	piece.updatePixels();
			fill(color, x-1, y); 
			piece.updatePixels();
			fill(color, x, y+1); 
			piece.updatePixels();
			fill(color, x, y-1); 
			piece.updatePixels();
			fill(color, x+1, y-1); 
			piece.updatePixels();
			fill(color, x+1, y+1); 
			piece.updatePixels();
			fill(color, x-1, y+1); 
			piece.updatePixels();
			fill(color, x-1, y-1); 
			piece.updatePixels();
		}
	}
	/**
	 * draws with a given color at point p 
	 * @param color int array storing rgb values for the color 
	 * @param x x coord 
	 * @param y y coord
	 */
	public void outline(int[] color, int x, int y ) {
		color c = color(color[0], color[1], color[2]); 
		piece.set(x, y, c); 
		outline[x][y] = true; 
		piece.set(x-1, y, c);
		outline[x-1][y] = true; 
		piece.set(x, y-1, c); 
		outline[x][y-1] = true; 
		piece.set(x-1, y+1, c);
		outline[x-1][y+1] = true; 
		piece.set(x-1, y-1, c); 
		outline[x-1][y-1] = true; 
		piece.set(x, y+1, c); 
		outline[x][y+1] = true; 
		piece.set(x+1, y-1, c); 
		outline[x+1][y-1] = true; 
		piece.set(x+1, y, c); 
		outline[x+1][y] = true; 
		piece.set(x+1, y+1, c); 
		outline[x+1][y+1] = true; 
	}
	/**
	 * draws the painting onto PApplet 
	 * @param surface
	 * @param fill
	 * @param color
	 */
	public void draw(PApplet surface, boolean fill, int[] color, int x, int y) {
		if(fill) {
			this.fill(color, x, y);
		}
		else {
			outline(color, x, y); 
		}
		surface.image(piece, 0, 0); 
	}
}
