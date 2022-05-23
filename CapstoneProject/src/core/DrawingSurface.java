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
	public float ratioX, ratioY;
	private ArrayList<Screen> screens;
	private boolean mouseDragged = false;
	private boolean mouseClicked = false; 
//	private static final float MAX_BRUSH = (float) 2;
//	private static final float MIN_BRUSH = -1;
	public final static String fileSeparator = System.getProperty("file.separator");
	private static int paintingnum; 
	private float v = (float)( .5 / 9.0);
	private float[][] kernel = {{ (float).0947416, (float).118318, (float).0947416 }, 
	                    		{(float).118318, (float).147761, (float).118318 }, 
	                    		{(float).0947416, (float).118318, (float).0947416 }};
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
	
	public void draw() {
		//this.setSize(activeScreen.DRAWING_WIDTH, activeScreen.DRAWING_HEIGHT);
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
			
			//if(pscreen.drawing()) {
			Point p = actualCoordinatesToAssumed(new Point(mouseX, mouseY));
			PImage space = null; 
			int size = (int) pscreen.getWidth(); 
			int y = (int) p.getY(); 
			int x = (int) p.getX(); 

		//	int x = (int) p.getX(); 
			//int y = (int)p.getY(); 
			Point p0 =  actualCoordinatesToAssumed(new Point(pmouseX, pmouseY));
//			float d = dist(mouseX, mouseY, pmouseX, pmouseY); // how fast did the user move the "pen"?
//			float maxStrokeWidth = pscreen.getWidth() + MAX_BRUSH; 
//			float minStrokeWidth = pscreen.getWidth() + MIN_BRUSH; 
//			 float brushWidth = map(d, 0, 40, maxStrokeWidth, minStrokeWidth); // the slower the move, the wider the stroke
			if(c!= null && mousePressed && pscreen.mode() == 0) {
				pg.fill(c.getRGB()); 
				outline.fill(0); 
				outline.stroke(0);
				outline.noFill();
				outline.rect(0, 0, outline.width, outline.height); 
//			    if(brushWidth < minStrokeWidth) 
//			    brushWidth = minStrokeWidth;
			    pg.strokeWeight(size);
			    outline.strokeWeight(size); 
			   // System.out.println(brushWidth); 
				//pg.strokeWeight(5);
				pg.stroke(c.getRGB()); 
				pg.line((float)p.getX(), (float)p.getY(), (float)p0.getX(), (float)p0.getY()); 
				//if()
				outline.line((float)p.getX(), (float)p.getY(), (float)p0.getX(), (float)p0.getY()); 
				if(p.getX() <= width) {
				cursor(brush, 0, 30); 
				}
//				pg.noStroke(); 
//				pg.circle((float)p.getX(), (float)p.getY(), 5);
			}
			else if( mousePressed && pscreen.mode() == 2) {
				pg.fill(Color.white.getRGB()); 
				outline.stroke(Color.white.getRGB()); 
				pg.stroke(255); 
				pg.strokeWeight(size*(float)2); 
				outline.strokeWeight(size*(float)2);
				pg.line((float)p.getX(), (float)p.getY(), (float)p0.getX(), (float)p0.getY()); 
				//if()
				outline.line((float)p.getX(), (float)p.getY(), (float)p0.getX(), (float)p0.getY()); 
				if(p.getX() <= width) {
					cursor(eraser, 16, 16); 
				}
//				fill(x, y, c, outline); 
//				//mouseClicked = false; 
			}
			else if( mousePressed && pscreen.mode() == 3 && p.getX() < pg.width) {
				
//				int size = (int) pscreen.getWidth()/2; 
//				int y = (int) p.getY(); 
//				int x = (int) p.getX(); 
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
			//}
		//	if(pscreen.mode() != 3) {
				outline.endDraw();
				pg.endDraw(); 
				image(pg, 0, 0); 
			//}
			if(space!= null) {
				image(space, x-size/2, y-size/2);
			}
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
		Point p = assumedCoordinatesToActual(new Point(mouseX, mouseY)); 
	
		cursor(ARROW); 
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
	/**
	 * fill an area in the painting 
	 * @param p Point of origin 
	 * @param c desired color 
	 * @param outline outline to check borders 
	 */
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
	public void keyPressed() {
		activeScreen.keyPressed(); 
	}
	public void blur(int size, Point p) {
		//pg.loadPixels();
		
//		
//		for(int j = y - size; j < y + size; j++) { 
//			for(int i = x = size; i< x + size; i++) {
//				if(j> 0 && j < pg.height && i>0 && i < pg.width) {
//					//check if valid 
//					System.out.println(j + " " + y); 
//					float sumr = 0; // Kernel sum for this pixel
//					float sumg = 0;
//					float sumb = 0; 
//				      for (int ky = -1; ky <= 1; ky++) {
//				        for (int kx = -1; kx <= 1; kx++) {
//				        
//				          // Calculate the adjacent pixel for this kernel point
//				          int pos = (j + ky)*pg.width + (i + kx);
//				          // Image is grayscale, red/green/blue are identical //ok but it ISNT actually 
//				          float valr = red(pg.pixels[pos]);
//				          float valg = green(pg.pixels[pos]); 
//				          float valb = blue(pg.pixels[pos]); 
//				          // Multiply adjacent pixels based on the kernel values
//				          sumr += kernel[ky+1][kx+1] * valr; // questionable considering color 
//				          sumg += kernel[ky+1][kx+1] * valg; 
//				          sumb += kernel[ky+1][kx+1] * valb; 
//				        }
//				      }
//				      Color bcol = new Color(sumr, sumg, sumb); 
//				      pg.pixels[j*pg.width + x ] = bcol.getRGB();
//				}
//		}
//		}
		pg.updatePixels();
	}
//	public void setBack2() {
//		if (activeScreen == screen2) {
//			screen2.setBack2(true);
//		}
//	}
	
}