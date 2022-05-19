package screens;

import core.DrawingSurface;
import mhaldar.shapes.Rectangle;
import processing.core.PImage;

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
	
	private PImage background;
	public final static String fileSeparator = System.getProperty("file.separator");
	
	//private Painting canvas; //the drawing canvas where the user draws on
	private Palette palette; //the color palette that shows what colors user has
	
	private Button exit, finish, startOver, instructions; //other options other than drawing
	private Button draw, fill, erase; //drawing options
	private Color buttonColor;
	
	private int drawState; // 0 is draw, 1 is fill, 2 is erase 
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
		drawState = 0;
		on  = true;
		
		buttonColor = new Color(239, 183, 192, 255);
		
		exit = new Button(new Rectangle(DRAWING_WIDTH - 180, 10, 150, 40), "exit", buttonColor);
		finish = new Button(new Rectangle(DRAWING_WIDTH - 180, 100, 150, 40), "finish", buttonColor);
		instructions = new Button(new Rectangle(DRAWING_WIDTH - 180, 195, 150, 40), "instructions", buttonColor);
		startOver = new Button(new Rectangle(DRAWING_WIDTH - 180, 290, 150, 40), "start over", buttonColor);
		draw = new Button(new Rectangle(DRAWING_WIDTH - 180, DRAWING_HEIGHT/2, 150, 40), "draw", buttonColor);
		erase = new Button(new Rectangle(DRAWING_WIDTH - 180, DRAWING_HEIGHT/2 +200, 150, 40), "erase", buttonColor); 
		fill = new Button(new Rectangle(DRAWING_WIDTH - 180, DRAWING_HEIGHT/2 + 100, 150, 40), "fill", buttonColor);
		
		palette = new Palette(); 
		selected = palette.getPaint(0).getColor();
		//canvas = new Painting((int)(800),  DRAWING_HEIGHT, surface);	
	}

	public void setup() {
		background = surface.loadImage("additionalPictures"+fileSeparator+"pink-mountains.jpg");
		background.resize(DRAWING_WIDTH, DRAWING_HEIGHT);
	}
	
	@Override
	public void draw() {
		
//		exit.draw(surface, exit.getXCoord(), exit.getYCoord());
//		finish.draw(surface, finish.getXCoord(), finish.getYCoord());
//		startOver.draw(surface, startOver.getXCoord(), startOver.getYCoord());
		//surface.fill(255);
	//	surface.background(255);
		
		surface.image(background, 0, 0);
		
		exit.draw(surface);
		finish.draw(surface);
		instructions.draw(surface);
		startOver.draw(surface);
		fill.draw(surface);
		draw.draw(surface);
		erase.draw(surface);

		
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		
		exit.highlight(p, surface);
		finish.highlight(p, surface);
		instructions.highlight(p, surface);
		startOver.highlight(p, surface);
		fill.highlight(p, surface);
		draw.highlight(p, surface);
		erase.highlight(p, surface);
		
		
		palette.draw(surface, false);
		//canvas.draw(surface, true, Color.WHITE, (int)p.getX(), (int)p.getY());
	
		//made array to prevent repeated code for each button
		//keep this? or change to for each loop
				
				//go through each button to see if it is being hovered over (made array to prevent repeated code for each button)
//				for (Button e : buttonList) {
//					e.highlight(p, surface);
//				}
	}
	
	
	public void mousePressed() {
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		if (draw.isClicked(p)) {
			System.out.println("drawing");
			drawState = 0;
		}
		
		else if (fill.isClicked(p)) {
			drawState = 1;
			//System.out.println("fill");
		}
		else if(erase.isClicked(p)) {
			drawState = 2; 
		}
		
		else if (exit.isClicked(p)) {
			//System.out.println("exit");
			surface.switchScreen(surface.INTRO_SCREEN);
		}
		
		else if (finish.isClicked(p)) {
			surface.finish(); 
			surface.switchScreen(surface.END_SCREEN);
		}
		 
		else if(instructions.isClicked(p)) {
			surface.switchScreen(surface.INSTRUCTIONS_SCREEN);
		}
		 
		else if (startOver.isClicked(p)) {
			//canvas.restart();
			surface.clearGraphics(); 
			palette.restart();
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
	public int mode() {
		return drawState; 
	}
	//public 

}