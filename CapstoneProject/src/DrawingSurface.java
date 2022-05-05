import java.awt.Point;
import java.util.ArrayList;

import processing.core.PApplet;
import screens.*;
public class DrawingSurface extends PApplet implements ScreenSwitcher {
	
	private ArrayList<Integer> keys; 
	
	private Screen activeScreen;
	private ArrayList<Screen> screens;
}
