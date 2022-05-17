package screens;

import core.DrawingSurface;
import mhaldar.shapes.Rectangle;

import java.awt.Color;
import java.awt.Point;

import core.*; 

/**
 * paintingScreen that is the screen with the drawing canvas that the user can draw on
 * 
 * @author Julia
 *
 */
public class PaintingScreen extends Screen{

	private DrawingSurface surface;
	
	//private Painting canvas; //the drawing canvas where the user draws on
	private Palette palette; //the color palette that shows what colors user has
	
	private Button exit, finish, startOver; //other options other than drawing
	private Button draw, fill, erase; //drawing options
	
	private boolean drawState;
	Color selected; 
	boolean on; 
	
	/**
	 * creates a PaintingScreen with set width and height
	 * @param width the width of the screen
	 * @param height the height of the screen
	 */
	public PaintingScreen (DrawingSurface surface) {
		super(1600, 1600);
		this.surface = surface; 
		drawState = true;
		on  = true; 
		exit = new Button(new Rectangle(DRAWING_WIDTH - 150, 10, 100, 40), "exit", Color.red);
		finish = new Button(new Rectangle(DRAWING_WIDTH - 150, 100, 100, 40), "finish", Color.red);
		startOver = new Button(new Rectangle(DRAWING_WIDTH - 150, 300, 125, 40), "start over", Color.red);
	//	instructions = new Button(new Rectangle(DRAWING_WIDTH - 150, 295, 100, 40), "instructions", Color.red);
		
		draw = new Button(new Rectangle(DRAWING_WIDTH - 150, DRAWING_HEIGHT/2, 100, 40), "draw", Color.red);
		fill = new Button(new Rectangle(DRAWING_WIDTH - 150, DRAWING_HEIGHT/2 + 100, 100, 40), "fill", Color.red);
		palette = new Palette(); 
		selected = palette.getPaint(0).getColor();
		//canvas = new Painting((int)(800),  DRAWING_HEIGHT, surface);		
	}
	
	@Override
	public void draw() {
		
//		exit.draw(surface, exit.getXCoord(), exit.getYCoord());
//		finish.draw(surface, finish.getXCoord(), finish.getYCoord());
//		startOver.draw(surface, startOver.getXCoord(), startOver.getYCoord());
		//surface.fill(255);
	//	surface.background(255);
		exit.draw(surface);
		finish.draw(surface);
		startOver.draw(surface);
		
		fill.draw(surface);
		draw.draw(surface);
		
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		
		palette.draw(surface, false);
		//canvas.draw(surface, true, Color.WHITE, (int)p.getX(), (int)p.getY());
	
		//made array to prevent repeated code for each button
		
		
	}
	
	
	public void mousePressed() {
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
	
		
		 if (exit.isClicked(p)) {
			//System.out.println("exit");
			surface.switchScreen(surface.INTRO_SCREEN);
		}
		
		else if (finish.isClicked(p)) {
			surface.switchScreen(surface.END_SCREEN);
		}
		
		else if (startOver.isClicked(p)) {
			//canvas.restart();
			surface.clearGraphics(); 
			palette.restart();
		}
		
		else if (draw.isClicked(p)) {
			System.out.println("drawing");
			drawState = true;
		}
		
		else if (fill.isClicked(p)) {
			drawState = false;
			//System.out.println("fill");
		}
		else {
			int pos = palette.selectPaint(p); 
			if(pos >= 0) {
				System.out.println(pos); 
				palette.getPaint(pos).createWindow(surface, this);
			}
			else if(selected!= null) {
			//	canvas.draw(surface, drawState, selected, (int)p.getX(), (int)p.getY());
			}
		}
		
		
	}
	
	
	//the drawing aspect of the drawing
	//makes sure the draw option is selected
	//makes the canvas call its drawing method
	public void mouseDragged() {
		super.mouseDragged(); 
		//Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		//on = true; 
		//canvas.draw(surface, drawState, selected, (int)p.getX(), (int)p.getY());
	}
	public void mouseReleased() {
		//on = false; 
	}
	/**
	 * changes the current color  that will be used to another one 
	 * @param c the new color 
	 */
	public void selectedColor(Color c) {
		selected = c; 
	}
	/**
	 * 
	 * @return selected current color 
	 */
	public Color getColor() {
		return selected; 
	}
	/**
	 * 
	 * @return whether it's drawing or filling 
	 */
	public boolean drawing() {
		return drawState; 
	}
	//public 

}