import java.awt.Point;
import java.util.ArrayList;

import processing.core.PApplet;
import screens.*;
public class DrawingSurface extends PApplet implements ScreenSwitcher {
	
	private ArrayList<Integer> keys; 
	
	private Screen activeScreen;
	private ArrayList<Screen> screens;
	public DrawingSurface() {
		//setting up all the other screens 
		//creating a window for the intro with the below buttons 
		//buttons: instruction, play game, horizontal, or landscape- actually this is better off as a window 

	}
}