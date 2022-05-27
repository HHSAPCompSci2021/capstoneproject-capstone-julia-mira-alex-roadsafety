package core;

import java.awt.Point;
import java.util.ArrayList;

import processing.core.*;
import java.awt.Color;
import screens.*;
/**
 * draws things onto PApplet 
 * @author mhaldar640
 *
 */
public class DrawingSurface extends PApplet implements ScreenSwitcher {
	PGraphics pg; 
	PImage eraser, brush, blur;
	private Screen activeScreen;
	private float ratioX, ratioY;
	private ArrayList<Screen> screens;
	private boolean mouseDragged = false;
	private boolean mouseClicked = false; 
	private final static String fileSeparator = System.getProperty("file.separator");
	private static int paintingnum; 
	private float v = (float)( .5 / 9.0);
	private float[][] kernel = {{ (float).0947416, (float).118318, (float).0947416 }, 
	                    		{(float).118318, (float).147761, (float).118318 }, 
	                    		{(float).0947416, (float).118318, (float).0947416 }};
	private PGraphics outline; 
	
	
	/**
	 * drawingsurface that adds all the screens to an array
	 */
	public DrawingSurface() {
		screens = new ArrayList<Screen>();
	
		paintingnum = 0; 
		IntroScreen screen1 = new IntroScreen(this);
		InstructionsScreen screen2 = new InstructionsScreen(this);
		PaintingScreen screen3 = new PaintingScreen(this); 
		TypingScreen screen4 = new TypingScreen(this); 
		MixingScreen screen5 = new MixingScreen(this, screen3); 
		EndScreen screen6 = new EndScreen(this); 
		Window window = new Window(this, screen3); 
		screens.add(screen1);
		screens.add(screen3);
		screens.add(screen2);
		screens.add(screen4);
		screens.add(screen5);
		screens.add(screen6); 
		screens.add(window); 
		activeScreen = screens.get(0); 
	}
	
	/**
	 * sets up the background image
	 */
	public void setup() {
		brush = loadImage("additionalPictures" +   fileSeparator + "brush.png");
		eraser = loadImage("additionalPictures" +  fileSeparator + "eraser.png"); 
		blur = loadImage("additionalPictures" +  fileSeparator + "blur.png");
		cursor(); 
		background(255); 
		for (Screen s : screens)
			s.setup();
		pg= createGraphics(800, 1600); 
		outline = createGraphics(800, 1600);
		frameRate(80); 
	}
	
	/**
	 * returns the screens
	 * @return arraylist of all the screens
	 */
	public ArrayList<Screen> getScreens(){
		return screens;
	}
	
	/**
	 * return the PGraphics 
	 */
	public PGraphics getGraphics() {
		return g; 
	}
	
	/**
	 * draws the current screen
	 */
	public void draw() {
		ratioX = (float) width/activeScreen.DRAWING_WIDTH;
		ratioY = (float) height/activeScreen.DRAWING_HEIGHT;

		push();
		
		scale(ratioX, ratioY);
		
		activeScreen.draw();
		if(activeScreen != screens.get(PAINTING_SCREEN)){
			cursor(ARROW); 
		}
		if(activeScreen == screens.get(PAINTING_SCREEN)) {
			PaintingScreen pscreen = (PaintingScreen) activeScreen; 
			Color c = pscreen.getColor();
			pg.beginDraw();
			outline.beginDraw();
			
			Point p = actualCoordinatesToAssumed(new Point(mouseX, mouseY));
			PImage space = null; 
			int size = (int) pscreen.getWidth(); 
			int y = (int) p.getY(); 
			int x = (int) p.getX(); 

			Point p0 =  actualCoordinatesToAssumed(new Point(pmouseX, pmouseY));
			if(c!= null && mousePressed && pscreen.mode() == 0) {
				pg.fill(c.getRGB()); 
				outline.fill(0); 
				outline.stroke(0);
				outline.noFill();
				outline.rect(0, 0, outline.width, outline.height); 
			    pg.strokeWeight(size);
			    outline.strokeWeight(size); 
				pg.stroke(c.getRGB()); 
				pg.line((float)p.getX(), (float)p.getY(), (float)p0.getX(), (float)p0.getY()); 
				outline.line((float)p.getX(), (float)p.getY(), (float)p0.getX(), (float)p0.getY()); 
				if(p.getX() <= width) {
				cursor(brush, 0, 30); 
				}
			}
			else if( mousePressed && pscreen.mode() == 2) {
				pg.fill(Color.white.getRGB()); 
				outline.stroke(Color.white.getRGB()); 
				pg.stroke(255); 
				pg.strokeWeight(size*(float)2); 
				outline.strokeWeight(size*(float)2);
				pg.line((float)p.getX(), (float)p.getY(), (float)p0.getX(), (float)p0.getY()); 
				outline.line((float)p.getX(), (float)p.getY(), (float)p0.getX(), (float)p0.getY()); 
				if(p.getX() <= width) {
					cursor(eraser, 16, 16); 
				}
			}
			else if( mousePressed && pscreen.mode() == 3 && p.getX() < pg.width) {
				space = pg.get(x-size, y-size, (int) 1.5*size,  (int) 1.5*2*size); 
				if(space!= null && space.pixels.length >0 && pg.pixels[y*pg.width + x] != 0) {
					for(int i  = 0; i< 7; i++) {
						space.filter(BLUR); 
					}
					pg.image(space, x- size/2, y-size/2);
				}
				if(p.getX() <= pg.width) {
					cursor(blur, 16, 16); 
				}
			}
				outline.endDraw();
				pg.endDraw(); 
				image(pg, 0, 0); 
			if(space!= null) {
				image(space, x-size/2, y-size/2);
			}
		}
		pop();
	}
	
	/**
	 * changes the active screen
	 */
	public void switchScreen(int i) {
		activeScreen = screens.get(i);
		this.background(255,255,255);
	}
	
	/**
	 * draws if active screen is painting_screen
	 * calls the active screen's mousepressed method
	 */
	public void mousePressed() {
		if(activeScreen == screens.get(PAINTING_SCREEN)) {
			PaintingScreen pscreen = (PaintingScreen) activeScreen; 
			Color c = pscreen.getColor();
			Point p = actualCoordinatesToAssumed(new Point(mouseX, mouseY));
			int x = (int) p.getX(); 
			int y = (int)p.getY(); 
			if(c!= null && pscreen.mode() == 1 && y< pg.height && x< pg.width-1 ) {
				pg.loadPixels(); 
				outline.loadPixels(); 
				fill(p, c, outline); 
				pg.updatePixels();
			}	
		}
		activeScreen.mousePressed();
	}
	
	/**
	 * sets mouseDragged and mouseClicked to true and false respectively
	 */
	public void mouseDragged() {
		mouseDragged = true; 
		mouseClicked = false; 
	}
	
	/**
	 * sets mouseDragged and mouseClicked to false
	 */
	public void mouseReleased() {
		Point p = assumedCoordinatesToActual(new Point(mouseX, mouseY)); 
	
		cursor(ARROW); 
		mouseDragged = false; 
		mouseClicked = false; 
	}
	
	/**
	 * returns the point based on dimensions of screen
	 * @param assumed point
	 * @return assumed point based on dimensions of screen
	 */
	public Point assumedCoordinatesToActual(Point assumed) {
		return new Point((int)(assumed.getX()*ratioX), (int)(assumed.getY()*ratioY));
	}

	/**
	 * returns the actual point based on dimensions of screen
	 * @param actual point
	 * @return actual point based on dimensions of screen
	 */
	public Point actualCoordinatesToAssumed(Point actual) {
		return new Point((int)(actual.getX()/ratioX) , (int)(actual.getY()/ratioY));
	}
	
	/**
	 * sets mouseClicked to true
	 */
	public void mouseClicked() {
		mouseClicked = true; 
		
	}
	
	/**
	 * fill an area in the painting 
	 * @param p Point of origin 
	 * @param c desired color 
	 * @param outline outline to check borders 
	 */
	public void fill(Point p, Color c, PGraphics outline) { 
		ArrayList<Point> pixels = new ArrayList<Point>(); 
		pixels.add(p); 
		int[] p1 = pg.pixels; 
		int[] p2 = outline.pixels; 
		int black = Color.BLACK.getRGB();
		int col = c.getRGB(); 
		while(pixels.size()!= 0) {
			p = pixels.get(0); 
			int x = (int) p.getX(); 
			int y = (int)p.getY(); 
			if(y*pg.width + x < 0 || y*pg.width + x >= 1280000 ) {
				pixels.remove(p); 
			}
			else if(p1[y*pg.width + x] == col || p2[y*outline.width + x] == black) {
				pixels.remove(p); 
			}
			else {
				p1[y*pg.width + x] = col; 
				for(int i = -1; i<2; i++) {
					for(int j = -1; j< 2; j++) {
						if(i!= 0 || j!= 0) {
							Point point = new Point(x + i, y + j); 
							pixels.add(point); 
						}
					}
				}
			}
		}
			
	}
	
	/**
	 * start over 
	 */
	public void clearGraphics() {
		push();
		
		scale(ratioX, ratioY);
		pg.beginDraw();
		pg.clear();
		pg.fill(255);
		pg.noStroke();
		pg.rect(0, 0, 800, 1600);
		outline.clear(); 
		pg.endDraw();
		image(pg, 0, 0); 
		pop(); 
	}
	
	/**
	 * download the paintings 
	 */
	public void finish() {
		ratioX = (float) pg.width/(2*activeScreen.DRAWING_WIDTH);
		ratioY = (float) pg.height/activeScreen.DRAWING_HEIGHT;
		String paintingnm = "additionalPictures" + fileSeparator + "Painting" + paintingnum + ".png"; 
		push(); 
		pg.scale(ratioX, ratioY);
		pop(); 
		pg.save(paintingnm); 
		paintingnum ++; 
	}
	
	/**
	 * calls active screen's keypressed method
	 */
	public void keyPressed() {
		activeScreen.keyPressed(); 
	}
	
	/**
	 * blurs the drawing
	 * @param size size of blur
	 * @param p point used to blur
	 */
	public void blur(int size, Point p) {
		pg.updatePixels();
	}
}