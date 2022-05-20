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
	private Screen activeScreen;
	public float ratioX, ratioY;
	private ArrayList<Screen> screens;
	boolean mouseDragged = false;
	boolean mouseClicked = false; 
	static final float MAX_BRUSH = (float) 2;
	static final float MIN_BRUSH = -1;
	static int paintingnum; 
	float v = (float)( .5 / 9.0);
	float[][] kernel = {{ v, v, v }, 
	                    { v, v, v }, 
	                    { v, v, v }};
	private PGraphics outline; 
//	private double width, height;
	
	
	public DrawingSurface() {
//		this.width = width;
//		this.height = height;
		screens = new ArrayList<Screen>();
		
		//add  the screen classes
		paintingnum = 0; 
		IntroScreen screen1 = new IntroScreen(this);
		InstructionsScreen screen2 = new InstructionsScreen(this);
		PaintingScreen screen3 = new PaintingScreen(this); 
		TypingScreen screen4 = new TypingScreen(this); 
		MixingScreen screen5 = new MixingScreen(this); 
		EndScreen screen6 = new EndScreen(this); 
		Window window = new Window(this); 
		screens.add(screen1);
		screens.add(screen3);
		screens.add(screen2);
		screens.add(screen4);
		screens.add(screen5);
		screens.add(screen6); 
		screens.add(window); 
		//setting up all the other screens 
		//creating a window for the intro with the below buttons 
		activeScreen = screens.get(0); 
	}
	
//	public int getMaxWidth() {
//		return (int)width;
//	}
//	
//	public int getMaxHeight() {
//		return (int)height;
//	}
//	
	
//	public void settings() {
//		fullScreen();
//		
//	}
//	
	public void setup() {
		background(255); 
		for (Screen s : screens)
			s.setup();
		pg= createGraphics(800, 1600); 
		outline = createGraphics(800, 1600);
		frameRate(80); 
	}
	
	public void draw() {
		//this.setSize(activeScreen.DRAWING_WIDTH, activeScreen.DRAWING_HEIGHT);
		ratioX = (float) width/activeScreen.DRAWING_WIDTH;
		ratioY = (float) height/activeScreen.DRAWING_HEIGHT;

		push();
		
		scale(ratioX, ratioY);
		
		activeScreen.draw();
		if(activeScreen == screens.get(PAINTING_SCREEN)) {
			PaintingScreen pscreen = (PaintingScreen) activeScreen; 
			Color c = pscreen.getColor();
			pg.beginDraw();
			outline.beginDraw();
			//if(pscreen.drawing()) {
			Point p = actualCoordinatesToAssumed(new Point(mouseX, mouseY));
			int x = (int) p.getX(); 
			int y = (int)p.getY(); 
			Point p0 =  actualCoordinatesToAssumed(new Point(pmouseX, pmouseY));
			float d = dist(mouseX, mouseY, pmouseX, pmouseY); // how fast did the user move the "pen"?
			float maxStrokeWidth = pscreen.getWidth() + MAX_BRUSH; 
			float minStrokeWidth = pscreen.getWidth() + MIN_BRUSH; 
			 float brushWidth = map(d, 0, 40, maxStrokeWidth, minStrokeWidth); // the slower the move, the wider the stroke
			if(c!= null && mousePressed && pscreen.mode() == 0) {
				pg.fill(c.getRGB()); 
				outline.fill(0); 
				outline.stroke(0);
			   
			    if(brushWidth < minStrokeWidth) 
			    brushWidth = minStrokeWidth;
			    pg.strokeWeight(pscreen.getWidth());
			    outline.strokeWeight(brushWidth); 
			   // System.out.println(brushWidth); 
				//pg.strokeWeight(5);
				pg.stroke(c.getRGB()); 
				pg.line((float)p.getX(), (float)p.getY(), (float)p0.getX(), (float)p0.getY()); 
				//if()
				outline.line((float)p.getX(), (float)p.getY(), (float)p0.getX(), (float)p0.getY()); 
				
//				pg.noStroke(); 
//				pg.circle((float)p.getX(), (float)p.getY(), 5);
			}
			else if( mousePressed && pscreen.mode() == 2) {
				pg.fill(Color.white.getRGB()); 
				outline.stroke(Color.white.getRGB()); 
				pg.stroke(255); 
				pg.strokeWeight(pscreen.getWidth()); 
				outline.strokeWeight(pscreen.getWidth());
				pg.line((float)p.getX(), (float)p.getY(), (float)p0.getX(), (float)p0.getY()); 
				//if()
				outline.line((float)p.getX(), (float)p.getY(), (float)p0.getX(), (float)p0.getY()); 
//				fill(x, y, c, outline); 
//				//mouseClicked = false; 
			}
			else if( mousePressed && pscreen.mode() == 3) {
				pg.loadPixels();
				for(int j = y - (int)brushWidth/2; j < y + (int)brushWidth; j++) { // ask shelby how brushstroke works
					float sumr = 0; // Kernel sum for this pixel
					float sumg = 0;
					float sumb = 0; 
				      for (int ky = -1; ky <= 1; ky++) {
				        for (int kx = -1; kx <= 1; kx++) {
				          // Calculate the adjacent pixel for this kernel point
				          int pos = (y + ky)*pg.width + (x + kx);
				          // Image is grayscale, red/green/blue are identical //ok but it ISNT actually 
				          float valr = red(pg.pixels[pos]);
				          float valg = green(pg.pixels[pos]); 
				          float valb = blue(pg.pixels[pos]); 
				          // Multiply adjacent pixels based on the kernel values
				          sumr += kernel[ky+1][kx+1] * valr; // questionable considering color 
				          sumg += kernel[ky+1][kx+1] * valg; 
				          sumb += kernel[ky+1][kx+1] * valb; 
				        }
				      }
				      Color bcol = new Color(sumr, sumg, sumb); 
				      pg.pixels[j*pg.width + x ] = bcol.getRGB();
				}
			}
			//}
			outline.endDraw();
			pg.endDraw(); 
			image(pg, 0, 0); 
		}
		pop();
	}
	
	public void switchScreen(int i) {
		activeScreen = screens.get(i);
		this.background(255,255,255);
		//PImage im = loadImage("background-pic.png");
	//	this.background(im);
		//fill(0);
		//activeScreen.draw();
	}
	
	public void mousePressed() {
//		System.out.println("mouse pressed");
//		System.out.println(activeScreen);
		
		if(activeScreen == screens.get(PAINTING_SCREEN)) {
			PaintingScreen pscreen = (PaintingScreen) activeScreen; 
			Color c = pscreen.getColor();
			Point p = actualCoordinatesToAssumed(new Point(mouseX, mouseY));
			int x = (int) p.getX(); 
			int y = (int)p.getY(); 
			if(c!= null && pscreen.mode() == 1 && y< pg.height && x< pg.width-1 ) {
				//pg.beginDraw();
				pg.loadPixels(); 
				outline.loadPixels(); 
				fill(p, c, outline); 
				pg.updatePixels();
				//outline.updatePixels();
				//pg.endDraw();
				//image(pg, 0, 0); 
			}	
			
		}
		activeScreen.mousePressed();
	}
	public void mouseDragged() {
		mouseDragged = true; 
		mouseClicked = false; 
	}
	public void mouseReleased() {
		mouseDragged = false; 
		mouseClicked = false; 
		
	}
	public Point assumedCoordinatesToActual(Point assumed) {
		return new Point((int)(assumed.getX()*ratioX), (int)(assumed.getY()*ratioY));
	}

	public Point actualCoordinatesToAssumed(Point actual) {
		return new Point((int)(actual.getX()/ratioX) , (int)(actual.getY()/ratioY));
	}
	/**
	 * 
	 * @return the number of screens; 
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
	public void mouseClicked() {
		mouseClicked = true; 
		
	}
	public void fill(Point p, Color c, PGraphics outline) { 
		
		// list
		// add x,y
		// while (!list.isEmpty()) {
		//remove from list
		// if needs color, then color & add neighbors
	//}
		ArrayList<Point> pixels = new ArrayList<Point>(); 
		pixels.add(p); 
		//int i = 0; 
		int[] p1 = pg.pixels; 
		int[] p2 = outline.pixels; 
		int black = Color.BLACK.getRGB();
		int col = c.getRGB(); 
		while(pixels.size()!= 0) {
			p = pixels.get(0); 
			int x = (int) p.getX(); 
			int y = (int)p.getY(); 
			if(y*pg.width + x < 0 || y*pg.width + x >= 1280000 ) {
				//System.out.println(y*pg.width + x); 
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
	public void finish() {
		String paintingnm = "additionalPictures/Painting" + paintingnum + ".png"; 
		pg.save(paintingnm); 
		paintingnum ++; 
	}
	public void keyPressed() {
		activeScreen.keyPressed(); 
	}
	
//	public void setBack2() {
//		if (activeScreen == screen2) {
//			screen2.setBack2(true);
//		}
//	}
	
}