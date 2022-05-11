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
	
	private ArrayList<Integer> keys; 
	
	private Screen activeScreen;
	
	public float ratioX, ratioY;
	private ArrayList<Screen> screens;
	
	
	
	public DrawingSurface() {
		
		keys = new ArrayList<Integer>();
		screens = new ArrayList<Screen>();
		
		//add  the screen classes
		
		IntroScreen screen1 = new IntroScreen(this);
		InstructionsScreen screen2 = new InstructionsScreen(this);
		PaintingScreen screen3 = new PaintingScreen(this); 
		TypingScreen screen4 = new TypingScreen(this); 
		MixingScreen screen5 = new MixingScreen(this); 
		screens.add(screen1);
		screens.add(screen2);
		screens.add(screen3);
		screens.add(screen4);
		screens.add(screen5);
		//setting up all the other screens 
		//creating a window for the intro with the below buttons 
		activeScreen = screens.get(0); 
	}
	
	public void setup() {
		for (Screen s : screens)
			s.setup();
	}
	
	public void draw() {
		ratioX = (float)width/activeScreen.DRAWING_WIDTH;
		ratioY = (float)height/activeScreen.DRAWING_HEIGHT;

		push();
		
		scale(ratioX, ratioY);
		
		activeScreen.draw();
		
		pop();
	}
	
	public void switchScreen(int i) {
		activeScreen = screens.get(i);
		activeScreen.draw();
	}
	
	public void mousePressed() {
		System.out.println("mouse pressed");
		System.out.println(activeScreen);
		activeScreen.mousePressed();
	}
}