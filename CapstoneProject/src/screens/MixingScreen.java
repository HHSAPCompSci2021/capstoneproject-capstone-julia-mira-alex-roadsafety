package screens;
import java.awt.Point;
import java.awt.Color; 
import mhaldar.shapes.*;
import core.*; 

import core.DrawingSurface;

public class MixingScreen extends Screen{
	DrawingSurface surface; 
	MixedPalette mixed; 
	Button back; 
	PaintingScreen pscreen; 
	/**
	 * creates a MixingScreen with set width and height
	 * @param width the width of the screen
	 * @param height the height of the screen
	 */
	public MixingScreen (DrawingSurface surface, PaintButton p, PaintingScreen pscreen) {
		super(1600, 800);
		this.surface = surface; 
		mixed = p.getMixes(); 
		this.pscreen = pscreen; 
		back = new Button(new Rectangle(0, 0, 50, 50), "Back", Color.yellow); 
	}
	/**
	 * default constructor 
	 */
	public MixingScreen(DrawingSurface surface) {
		super(1600, 800);
		this.surface = surface; 
	}
	/**
	 * choose which color to mix 
	 * @param p og Paint for mixing 
	 */
	public void chooseColor(PaintButton p) {
		mixed = p.getMixes(); 
	}

	@Override
	public void draw() {
		
		mixed.draw(surface, true);
		
	}
	//makes the shade that corresponds to the selected Paint
	public void mousePressed() {
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		int chosen = mixed.selectPaint(p); 
		if(chosen >= 0 ) {
			mixed.getPaint(chosen).createWindow(surface, pscreen);
		}
		if(mixed.getPaint(2).getPaint().getAmount() <= 0) {
			surface.switchScreen(surface.PAINTING_SCREEN);
		}
		if(back.isClicked(p)) {
			surface.switchScreen(surface.PAINTING_SCREEN);
		}
	}
}
