package screens;

import core.DrawingSurface;
import mhaldar.shapes.Rectangle;

import java.awt.Color;

import core.*; 

/**
 * paintingScreen that is the screen with the drawing canvas that the user can draw on
 * 
 * @author 
 *
 */
public class PaintingScreen extends Screen{

	DrawingSurface surface; 
	Painting art; 
	Palette palette;
	Button end; 
	Button instructions; 
	
	private Button canvas, exit, finish, startOver, buy, draw, fill, miniGame;
	
	
	/**
	 * creates a PaintingScreen with set width and height
	 * @param width the width of the screen
	 * @param height the height of the screen
	 */
	public PaintingScreen (DrawingSurface surface) {
		super(1600, 800);
		this.surface = surface; 
		
		canvas = new Button(new Rectangle(0, 0, DRAWING_WIDTH * 3.0/5, DRAWING_HEIGHT - 10), "", Color.WHITE);
		
		exit = new Button(new Rectangle(DRAWING_WIDTH - 70, 5, 50, 50), "exit", Color.red);
		finish = new Button(new Rectangle(DRAWING_WIDTH - 70, 5 - 50 - 10, 50, 50), "finish", Color.red);
		startOver = new Button(new Rectangle(DRAWING_WIDTH - 70, 5 - 100 - 20, 50, 50), "start over", Color.red);
		buy = new Button(new Rectangle(DRAWING_WIDTH - 70, 5 - 150 - 30, 50, 50), "buy", Color.red);
		
		draw = new Button(new Rectangle(DRAWING_WIDTH/2, DRAWING_HEIGHT/2, 100, 75), "start!", Color.red);
		fill = new Button(new Rectangle(DRAWING_WIDTH/2, DRAWING_HEIGHT/2, 100, 75), "start!", Color.red);
	
		miniGame = new Button(new Rectangle(DRAWING_WIDTH/2, DRAWING_HEIGHT/2, 100, 75), "start!", Color.red);
		
	}

	@Override
	public void draw() {
		canvas.draw(surface, canvas.getXCoord(), canvas.getYCoord());
		exit.draw(surface, exit.getXCoord(), exit.getYCoord());
		finish.draw(surface, finish.getXCoord(), finish.getYCoord());
		startOver.draw(surface, startOver.getXCoord(), startOver.getYCoord());
		buy.draw(surface, buy.getXCoord(), buy.getYCoord());
		
	
		
	}
	
}