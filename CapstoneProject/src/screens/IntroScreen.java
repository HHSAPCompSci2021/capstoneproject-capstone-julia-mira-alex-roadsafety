package screens;

import core.Button;
import core.DrawingSurface;
import mhaldar.shapes.Rectangle;
import processing.core.PImage;

import java.awt.Color;
import java.awt.Point; 

public class IntroScreen extends Screen {
	
	
	private Button startButton;
	private Button instructionsButton;
	
	private PImage background;
	
	public IntroScreen(DrawingSurface surface) {
		super(800, 1600);
		this.surface = surface; 
		
		startButton = new Button(new Rectangle(DRAWING_WIDTH/2, DRAWING_HEIGHT/2, 100, 75), "start!", Color.red);
		instructionsButton = new Button(new Rectangle(DRAWING_WIDTH/2 - 50, DRAWING_HEIGHT/2 + 90, 200, 75), "instructions", new Color(0,208,255));
		
	}
	
	public void setup() {
		background = surface.loadImage("/additionalPictures/background-pic.png");
		background.resize(DRAWING_WIDTH, DRAWING_HEIGHT);
		

	}
	
	
	public void draw() {
		//surface.image(background,0,0);
		
		startButton.draw(surface, DRAWING_WIDTH/2 + 5, DRAWING_HEIGHT/2 + 40);
		instructionsButton.draw(surface, DRAWING_WIDTH/2 - 45, DRAWING_HEIGHT/2 + 130);

		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		startButton.highlight(p, surface);
		instructionsButton.highlight(p, surface);
	}
	
	
	
	public void mousePressed() {
		
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		if (startButton.isClicked(p)) {
			surface.switchScreen(ScreenSwitcher.PAINTING_SCREEN);
		}
		
		if (instructionsButton.isClicked(p)) {
			surface.switchScreen(ScreenSwitcher.INSTRUCTIONS_SCREEN);
		}
	}
}
