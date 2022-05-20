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
	public final static String fileSeparator = System.getProperty("file.separator");

	private PImage background;
	
	Color themeColor;
	
	public IntroScreen(DrawingSurface surface) {
		super(800, 1600);
		this.surface = surface; 
		
		themeColor = new Color(239, 183, 192, 255);
		
		startButton = new Button(new Rectangle(DRAWING_WIDTH/2, DRAWING_HEIGHT/2, 100, 75), "start!", new Color(239, 183, 192, 255));
		instructionsButton = new Button(new Rectangle(DRAWING_WIDTH/2 - 50, DRAWING_HEIGHT/2 + 90, 200, 75), "instructions", themeColor);
		
		
	}
	
	public void setup() {
		background = surface.loadImage("additionalPictures"+fileSeparator+"background-pic.png");
		background.resize(DRAWING_WIDTH, DRAWING_HEIGHT);
	}
	
	
	public void draw() {
		surface.image(background,0,0);
		
		startButton.draw(surface);
		instructionsButton.draw(surface);

		surface.fill(0);
		surface.textSize(50);
		String message = "WELCOME TO HAPPY LITTLE ACCIDENTS";
		surface.text(message, DRAWING_WIDTH/2 - surface.textWidth(message)/2+3, 200+3);
		
		surface.fill(themeColor.getRGB());
		surface.textSize(50);
		surface.text(message, DRAWING_WIDTH/2 - surface.textWidth(message)/2, 200);

		
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
