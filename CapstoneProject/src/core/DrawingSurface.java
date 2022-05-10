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
	
	
	private ArrayList<Screen> screens;
	
	
	
	public DrawingSurface() {
		
		keys = new ArrayList<Integer>();
		screens = new ArrayList<Screen>();
		
		//add the the screen classes
		
//		IntroScreen screen1 = new IntroScreen();
		
		
		//setting up all the other screens 
		//creating a window for the intro with the below buttons 
		//buttons: instruction, play game, horizontal, or landscape- actually this is better off as a window 

	}
}