package screens;

import core.DrawingSurface;
import mhaldar.shapes.Rectangle;

import java.awt.Color;
import java.awt.Point;

import core.*; 

/**
 * paintingScreen that is the screen with the drawing canvas that the user can draw on
 * 
 * @author 
 *
 */
public class PaintingScreen extends Screen{

	private DrawingSurface surface;
	
	private Painting canvas; //the drawing canvas where the user draws on
	private Palette palette; //the color palette that shows what colors user has
	
	private Button exit, finish, startOver, instructions; //other options other than drawing
	private Button draw, fill, erase; //drawing options
	
	private boolean drawState;
	
	/**
	 * creates a PaintingScreen with set width and height
	 * @param width the width of the screen
	 * @param height the height of the screen
	 */
	public PaintingScreen (DrawingSurface surface) {
		super(surface);
		this.surface = surface; 
		
		drawState = false;
		
		exit = new Button(new Rectangle(DRAWING_WIDTH - 150, 10, 100, 40), "exit", Color.red);
		finish = new Button(new Rectangle(DRAWING_WIDTH - 150, 100, 100, 40), "finish", Color.red);
		startOver = new Button(new Rectangle(DRAWING_WIDTH - 150, 195, 100, 40), "start over", Color.red);
		instructions = new Button(new Rectangle(DRAWING_WIDTH - 150, 295, 100, 40), "instructions", Color.red);
		
		draw = new Button(new Rectangle(DRAWING_WIDTH - 150, DRAWING_HEIGHT/2, 100, 40), "draw", Color.red);
		fill = new Button(new Rectangle(DRAWING_WIDTH - 150, DRAWING_HEIGHT/2 + 100, 100, 40), "fill", Color.red);
	
		canvas = new Painting((int)(DRAWING_WIDTH * 3.0 * 5), (int)(DRAWING_HEIGHT - 10), surface);		
	}

	@Override
	public void draw() {
		
//		exit.draw(surface, exit.getXCoord(), exit.getYCoord());
//		finish.draw(surface, finish.getXCoord(), finish.getYCoord());
//		startOver.draw(surface, startOver.getXCoord(), startOver.getYCoord());
//		
		
		instructions.draw(surface);
		exit.draw(surface);
		finish.draw(surface);
		startOver.draw(surface);
		
		draw.draw(surface);
		fill.draw(surface);
		
		
		
//		canvas.draw(surface, true, new int[] {255, 255, 255}, 0,0);
	
		
	}
	
	
	public void mousePressed() {
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
	
		if (instructions.isClicked(p)) {
			System.out.println("instructions");
			
		}
		
		else if (exit.isClicked(p)) {
			System.out.println("exit");
		}
		
		else if (finish.isClicked(p)) {
			System.out.println("finish");
		}
		
		else if (startOver.isClicked(p)) {
			System.out.println("start over");
		}
		
		else if (draw.isClicked(p)) {
			System.out.println("drawing");
			drawState = true;
		}
		
		else if (startOver.isClicked(p)) {
			System.out.println("drawing");
		}
		
	}
	
	
	//the drawing aspect of the drawing
	//makes sure the draw option is selected
	//makes the canvas call its drawing method
	public void mouseDragged() {
		if (drawState == true) {
			
		}
	}
	
}