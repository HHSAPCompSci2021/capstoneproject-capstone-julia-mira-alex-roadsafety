package core;

import java.awt.Point;
import java.util.ArrayList;

import processing.core.PApplet;
import screens.*;
/**
 * draws things onto PApplet 
 * @author mhaldar640
 *
 */
public class DrawingSurface extends PApplet implements ScreenSwitcher {
	
	
	private Screen activeScreen;
	
	public float ratioX, ratioY;
	private ArrayList<Screen> screens;
	
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
		Window window = new Window(this, null); 
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
		for (Screen s : screens)
			s.setup();
	}
	
	public void draw() {
		//this.setSize(activeScreen.DRAWING_WIDTH, activeScreen.DRAWING_HEIGHT);
		ratioX = (float) width/activeScreen.DRAWING_WIDTH;
		ratioY = (float) height/activeScreen.DRAWING_HEIGHT;

		push();
		
		scale(ratioX, ratioY);
		
		activeScreen.draw();
		
		pop();
	}
	
	public void switchScreen(int i) {
		activeScreen = screens.get(i);
		this.clear();
		//fill(0);
		//activeScreen.draw();
	}
	
	public void mousePressed() {
//		System.out.println("mouse pressed");
//		System.out.println(activeScreen);
		activeScreen.mousePressed();
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

}