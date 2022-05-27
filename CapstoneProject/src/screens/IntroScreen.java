package screens;

import core.Button;
import core.DrawingSurface;
import mhaldar.shapes.Rectangle;
import processing.core.PImage;
import java.awt.Color;
import java.awt.Point; 

/**
 * intro screen (home screen)
 * first screen displayed to user
 * @author julia
 *
 */
public class IntroScreen extends Screen {
	
	private Button startButton;
	private Button instructionsButton;
	private Color color;

	/**
	 * sets up all the buttons
	 * @param surface
	 */
	public IntroScreen(DrawingSurface surface) {
		super(800, 1600);
		this.surface = surface; 
		
		color = new Color(239, 183, 192, 255);
		startButton = new Button(new Rectangle(DRAWING_WIDTH/2, DRAWING_HEIGHT/2, 100, 50), "start!", color);
		instructionsButton = new Button(new Rectangle(DRAWING_WIDTH/2 - 50, DRAWING_HEIGHT/2 + 70, 200, 50), "instructions", color);
	}
	
	/**
	 * sets up the background image
	 */
	public void setup() {
		background = surface.loadImage("additionalPictures"+fileSeparator+"defaultBackground.png");
		background.resize(DRAWING_WIDTH, DRAWING_HEIGHT);
	}
	
	/**
	 * draws the main screen (home page)
	 */
	public void draw() {
		surface.image(background,0,0);
		
		startButton.draw(surface);
		instructionsButton.draw(surface);

		surface.fill(0);
		surface.textSize(50);
		String message = "WELCOME TO HAPPY LITTLE ACCIDENTS";
		surface.text(message, DRAWING_WIDTH/2 - surface.textWidth(message)/2+3, 200+3);
		
		surface.fill(color.getRGB());
		surface.textSize(50);
		surface.text(message, DRAWING_WIDTH/2 - surface.textWidth(message)/2, 200);

		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		startButton.highlight(p, surface);
		instructionsButton.highlight(p, surface);
	}
	
	/**
	 * if start is clicked, start game
	 * if instructions is clicked, go to isntruction screen
	 */
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
