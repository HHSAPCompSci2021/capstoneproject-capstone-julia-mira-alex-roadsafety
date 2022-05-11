package screens;

import core.Button;
import core.DrawingSurface;
import mhaldar.shapes.Rectangle;


public class IntroScreen extends Screen {
	
	//add a DrawingSurface variable?
	DrawingSurface surface; 
	private Button startButton;
	private Button instructionsButton;
	
	public IntroScreen(DrawingSurface surface) {
		super(1600, 800);
		this.surface = surface; 
		
		startButton = new Button(new Rectangle(DRAWING_WIDTH/2, DRAWING_HEIGHT/2, 100, 75), "start!", new int[] {255,0,0});
		instructionsButton = new Button(new Rectangle(DRAWING_WIDTH/2, DRAWING_HEIGHT/2 + 90, 100, 75), "instructions", new int[] {0,255,128});
		
	}
	
	
	public void draw() {
		startButton.draw(surface, DRAWING_WIDTH/2 + 5, DRAWING_HEIGHT/2 + 20); //make it so that it accesses the startButton's properties
		instructionsButton.draw(surface, DRAWING_WIDTH/2 + 5, DRAWING_HEIGHT/2 + 110);
		//the button has a draw method julia
	}
	
	
	
	public void mousePressed(double x, double y) {
		if (startButton.isClicked(x, y)) {
			
//			surface.switchScreen(ScreenSwitcher.)
		}
	}

	
}
