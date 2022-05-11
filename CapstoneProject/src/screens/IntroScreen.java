package screens;

import core.Button;
import core.DrawingSurface;
import mhaldar.shapes.Rectangle;


public class IntroScreen extends Screen {
	
	//add a DrawingSurface variable?
	
	private Button startButton;
	private Button instructionsButton;
	
	public IntroScreen(int width, int height) {
		super(width, height);
		
		startButton = new Button(new Rectangle(0, 0, 50, 50), "start!", new int[] {0,0,0});
		instructionsButton = new Button(new Rectangle(200, 200, 50, 50), "instructions", new int[] {0,0,0});
		
	}
	
	
	public void draw(DrawingSurface surface) {
		surface.rect(0, 0, DRAWING_WIDTH, DRAWING_HEIGHT); //make it so that it accesses the startButton's properties
		//the button has a draw method julia
	}
	
	
	
	public void mousePressed(double x, double y) {
		if (startButton.isClicked(x, y)) {
			
//			surface.switchScreen(ScreenSwitcher.)
		}
	}

	
}
