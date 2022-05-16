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
	
	private final PImage background;
	
	public IntroScreen(DrawingSurface surface) {
		super(1600, 800);
		this.surface = surface; 
		
		background = null; //surface.loadImage();
		
		startButton = new Button(new Rectangle(DRAWING_WIDTH/2, DRAWING_HEIGHT/2, 100, 75), "start!", Color.red);
		instructionsButton = new Button(new Rectangle(DRAWING_WIDTH/2 - 50, DRAWING_HEIGHT/2 + 90, 200, 75), "instructions", new Color(0,255,128));
		
	}
	
	
	public void draw() {
		//surface.image(background,0,0);
		
		startButton.draw(surface, DRAWING_WIDTH/2 + 5, DRAWING_HEIGHT/2 + 40);
		instructionsButton.draw(surface, DRAWING_WIDTH/2 - 45, DRAWING_HEIGHT/2 + 130);

	
	}
	
	
	
	public void mousePressed() {
		
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		if (startButton.isClicked(p)) {
			System.out.println("start");
			
			surface.switchScreen(ScreenSwitcher.PAINTING_SCREEN);
		}
		
		if (instructionsButton.isClicked(p)) {
			System.out.println("instructions");

			surface.switchScreen(ScreenSwitcher.INSTRUCTIONS_SCREEN);
		}
	}
	
	//trying to make it so it highlights which button you are hovering over
	public void mouseMoved() {
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		
		if (startButton.isClicked(p)) {
			System.out.println("start hover");
			
			surface.stroke(113, 240, 147);
			
		}
		else {
			surface.stroke(0);
		}
		
		if (instructionsButton.isClicked(p)) {
			System.out.println("instructions hover");

		}
	}

	
}
