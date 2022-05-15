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
public class Painting extends PApplet {
	 PGraphics piece; 
	private boolean[][] outline; 
	public void setup() {
		piece = createGraphics(100, 100) ;
		assert piece != null; 
		System.out.println("successful"); 
	}
	/**
	 * constructs an empty painting with given height and width 
	 * @param width
	 * @param height
	 */
	public Painting(int width, int height, PApplet surface) {
		setup(); 
		piece.beginDraw();
		piece.background(255);
		outline = new boolean[height][width]; 
	}
	/**
	 * fills an area with given color, starting at Point p 
	 * @param color  int array storing rgb values for the color 
	 * @param x  x coord
	 * @param y y coord 
	 */
	public void fill(Color color, int x, int y) {
		
	//	piece.beginDraw();
		int c = color.getRGB(); 
	//	color c = color(color[0], color[1], color[2]); 
		if(y< 0 || y>= outline.length || x < 0 || x>= outline[y].length) {
		//	piece.endDraw();
			return; 
		}
		if(outline[y][x] || piece.pixels[y*piece.width+x] == c) {
		//	piece.endDraw(); 
			return; 
		}
		else {	
			piece.noStroke(); 
			piece.circle(x, y, 10); 
			//piece.set(x, y, c); 
			piece.updatePixels();
			fill(color, x+1, y); 
		//	piece.updatePixels();
			piece.updatePixels();
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
	public void outline(Color color, int x, int y ) {
		//ok  look into this color thing fr 
		int c = color.getRGB(); 
		piece.fill(c); 
		piece.circle(x, y, 5); 
		//piece.endDraw();
	}
	/**
	 * draws the painting onto PApplet 
	 * @param surface
	 * @param fill whether it's drawing or not 
	 * @param color
	 */
	public void draw(PApplet surface, boolean draw, Color color, int x, int y) {
		if(x<= piece.width && y<= piece.height) {
			if(!draw) {
				this.fill(color, x, y);
			}
			else {
				outline(color, x, y); 
			}
			surface.image(piece, 0, 0); 
		}
	}
	//public void
	/**
	 * the painting starts over 
	 */
	public void restart() {
		piece.endDraw(); 
		//piece.beginDraw();
	}
}
