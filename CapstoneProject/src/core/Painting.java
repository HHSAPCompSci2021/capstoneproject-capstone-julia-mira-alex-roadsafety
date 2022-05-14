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
	public Painting(int width, int height, PApplet surface) {
		piece = surface.createImage(width, height, surface.ARGB);
		//set this to white 
		piece.loadPixels();
		for(int i = 0; i< piece.pixels.length; i++) {
			piece.pixels[i] = 255; 
		}
		outline = new boolean[width][height]; 
	}
	/**
	 * fills an area with given color, starting at Point p 
	 * @param color  int array storing rgb values for the color 
	 * @param x  x coord
	 * @param y y coord 
	 */
	public void fill(Color color, int x, int y) {
		piece.loadPixels();
		int c = color.getRGB(); 
	//	color c = color(color[0], color[1], color[2]); 
		if(y< 0 || y>= outline.length || x < 0 || x>= outline[y].length) {
			return; 
		}
		if(outline[y][x] || piece.pixels[y*piece.width+x] == c) {
			return; 
		}
		else {	
			piece.pixels[y*piece.width + x] = c; 
			//piece.set(x, y, c); 
		//	piece.updatePixels();
			fill(color, x+1, y); 
		//	piece.updatePixels();
		//	piece.updatePixels();
			fill(color, x-1, y); 
	//		piece.updatePixels();
			fill(color, x, y+1); 
		//	piece.updatePixels();
			fill(color, x, y-1); 
		//	piece.updatePixels();
			fill(color, x+1, y-1); 
		//	piece.updatePixels();
			fill(color, x+1, y+1); 
		//	piece.updatePixels();
			fill(color, x-1, y+1); 
		//	piece.updatePixels();
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
	public void outline(Color color, int x, int y ) {
		//ok  look into this color thing fr 
		int c = color.getRGB(); 
	//	color c = color(color[0], color[1], color[2]); 
		for(int i = -1; i< 2; i++) {
			for(int j = -1; j< 2; j++) {
				if(y>= 0 || y<outline.length || x >= 0 || x <outline[y].length) {
					piece.pixels[(y+i)*piece.width + x+j] = c; 
					outline[y][x] = true;
				}
			}
		}
		piece.updatePixels();
	}
	/**
	 * draws the painting onto PApplet 
	 * @param surface
	 * @param fill
	 * @param color
	 */
	public void draw(PApplet surface, boolean fill, Color color, int x, int y) {
		if(x<= piece.width && y<= piece.height) {
			if(fill) {
				this.fill(color, x, y);
			}
			else {
				outline(color, x, y); 
			}
			surface.image(piece, 0, 0); 
		}
	}
}
