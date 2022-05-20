package screens;
import java.awt.Color;
import java.awt.Point;

import core.*;
import mhaldar.shapes.Rectangle;
import processing.core.*; 

/**
 * screen that is displayed to user whenever they finish the game
 * 
 * @author Julia
 *
 */
public class EndScreen extends Screen {
	
	private Button exit;
	private Color themeColor;
	private PImage background;
	private PImage finishedPainting;
	public final static String fileSeparator = System.getProperty("file.separator");
	private final static int finalWidth = 480;
	private final static int finalHeight = 500;
	
	/**
	 * creates the endScreen with set width and height
	 * @param width the width of the screen
	 * @param height the height of the screen
	 */
	public EndScreen(DrawingSurface surface) {
		super(800, 1600);
		this.surface = surface; 
		
		themeColor = new Color(239, 183, 192, 255);
		exit = new Button(new Rectangle(1500, 0, 100, 50), "exit", themeColor); 

	}
	public void setup() {
		background = surface.loadImage("additionalPictures"+fileSeparator+"pink-mountains.jpg");
		background.resize(DRAWING_WIDTH, DRAWING_HEIGHT);
	}
	
	
	@Override
	public void draw() {
		surface.image(background,0,0);
		
		surface.fill(255);
		surface.rect(DRAWING_WIDTH/2 - 240, DRAWING_HEIGHT/2 - 250, finalWidth, finalHeight);
		finishedPainting = surface.loadImage("additionalPictures" + fileSeparator + "Painting0.png");
		finishedPainting.resize(finalWidth, finalHeight);
		surface.image(finishedPainting, DRAWING_WIDTH/2 - 200, DRAWING_HEIGHT/2 - 250);
		
		String instructions = "congratulations on finishing your painting!";
		
		surface.text(instructions, DRAWING_HEIGHT - (surface.textWidth(instructions)/2), 100);

		exit.draw(surface);
		
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		exit.highlight(p, surface);
	}
	
	public void mousePressed() {
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		if(exit.isClicked(p)) {
//			surface.clearGraphics();
//			surface.switchScreen(surface.INTRO_SCREEN);
			surface.exit();
		}
	}
}
