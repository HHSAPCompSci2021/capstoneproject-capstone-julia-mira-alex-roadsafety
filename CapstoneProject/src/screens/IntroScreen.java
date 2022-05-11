package screens;

import core.Button;
import core.DrawingSurface;
import mhaldar.shapes.Rectangle;


public class IntroScreen extends Screen {
	
	//add a DrawingSurface variable?
	
	private Button startButton;
	private Button instructionsButton;
	
	public IntroScreen(DrawingSurface surface) {
		super(800, 800);
		this.surface = surface; 
		
		startButton = new Button(new Rectangle(DRAWING_WIDTH/2, DRAWING_HEIGHT/2, 100, 75), "start!", new int[] {255,0,0});
		instructionsButton = new Button(new Rectangle(DRAWING_WIDTH/2, DRAWING_HEIGHT/2 + 90, 100, 75), "instructions", new int[] {0,255,128});
		
	}
	
	
	public void draw() {
		startButton.draw(surface, DRAWING_WIDTH/2 + 5, DRAWING_HEIGHT/2 + 20);
		instructionsButton.draw(surface, DRAWING_WIDTH/2 + 5, DRAWING_HEIGHT/2 + 110);

	
	}
	
	
	
	public void mousePressed() {
		
		System.out.println("introScreen");
		
		if (startButton.isClicked(surface.mouseX, surface.mouseY)) {
			System.out.println("start");
			
			surface.switchScreen(ScreenSwitcher.PAINTING_SCREEN);
		}
		
		if (instructionsButton.isClicked(surface.mouseX,surface.mouseY)) {
			System.out.println("instructions");

			surface.switchScreen(ScreenSwitcher.INSTRUCTIONS_SCREEN);
		}
	}

	
}
