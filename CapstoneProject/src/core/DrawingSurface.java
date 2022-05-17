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
	Filler filler; 
	private ArrayList<Screen> screens;
	boolean mouseDragged = false;
	boolean mouseClicked = false; 
	float maxStrokeWidth = (float) 7;
	float minStrokeWidth = 4;
	private PGraphics outline; 
//	private double width, height;
	

	public DrawingSurface() {
//		this.width = width;
//		this.height = height;
		screens = new ArrayList<Screen>();
		
		//add  the screen classes
		
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
		frameRate(60); 
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
			Point p = actualCoordinatesToAssumed(new Point(mouseX, mouseY));
			int x = (int) p.getX(); 
			int y = (int)p.getY(); 
			if(c!= null && mousePressed && pscreen.drawing()) {
				Point p0 =  actualCoordinatesToAssumed(new Point(pmouseX, pmouseY));
				pg.fill(c.getRGB()); 
				outline.fill(0); 
				stroke(0);
			    float d = dist(mouseX, mouseY, pmouseX, pmouseY); // how fast did the user move the "pen"?
			    float brushWidth = map(d, 0, 40, maxStrokeWidth, minStrokeWidth); // the slower the move, the wider the stroke
			    if(brushWidth < minStrokeWidth) brushWidth = minStrokeWidth;
			    pg.strokeWeight(brushWidth);
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
			else if(c!= null && mouseClicked && !pscreen.drawing()) {
				System.out.println("streammorale"); 
				fill(x, y, c, outline); 
				//mouseClicked = false; 
			}
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
	public void fill(int x, int y, Color c, PGraphics outline) {
			
			int col = c.getRGB();
//			if(y< 0 || y>= outline.length || x < 0 || x>= outline[y].length) {
//				return; 
//			}
			int[] p1 = pg.pixels; 
			int[] p2 = outline.pixels; 
			if(p1[y*pg.width + x] == col || p2[y*outline.width + x] == 0) {
				//System.out.println("agfsfgsdg"); 
				return; 
			}
			else {
				p1[y*pg.width + x] = col; 
				for(int i = -1; i<2; i++) {
					for(int j = -1; j< 2; j++) {
						if(i!= 0 || j!= 0) {
							fill(x + i, y + j, c, outline); 
							pg.updatePixels();
						}
					}
				}
			}
	}
}