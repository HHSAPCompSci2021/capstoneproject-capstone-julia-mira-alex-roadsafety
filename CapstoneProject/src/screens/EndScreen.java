package screens;
import java.awt.Color;
import java.awt.Point;

import core.*;
import mhaldar.shapes.Rectangle;
import processing.core.*; 

/**
 * screen that is displayed to user whenever they finish the game
 * 
 * @author 
 *
 */
public class EndScreen extends Screen {
	
	Button back;
	
	/**
	 * creates the endScreen with set width and height
	 * @param width the width of the screen
	 * @param height the height of the screen
	 */
	public EndScreen(DrawingSurface surface) {
		super(800, 1600);
		this.surface = surface; 
		back = new Button(new Rectangle(1500, 0, 100, 100), "back", Color.cyan); 

	}

	@Override
	public void draw() {
		surface.rect(0, 0, (float)super.DRAWING_WIDTH, (float)super.DRAWING_HEIGHT);
	
		String instructions = "Yay you made a painting :D";
		
		surface.fill(0, 208, 312);
//		surface.background(0,208,312);
		surface.text(instructions, 50, 50, (float)500, (float)super.DRAWING_HEIGHT);
//		surface.fill(255);
		
		back.draw(surface);
		
//		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
//		if (back.isClicked(p)) {
//			surface.noFill();
//			surface.fill(0);
//			surface.stroke(113, 240, 147);
//			surface.strokeWeight(5);
//			surface.rect((float)back.getXCoord(), (float)back.getYCoord(), (float)back.getWidth(), (float)back.getHeight());
//			
//		}
	}
}
