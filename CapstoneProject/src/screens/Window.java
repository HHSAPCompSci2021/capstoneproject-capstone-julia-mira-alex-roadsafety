package screens;
import core.*; 
import core.DrawingSurface;
import mhaldar.shapes.*;
import java.awt.Color; 

public class Window extends Screen {
	PaintButton p; 
	/**
	 * creates the window with set width and height
	 * @param width the width of the screen
	 * @param height the height of the screen
	 */
	public Window(DrawingSurface surface, PaintButton p) {
		super(surface);
		this.surface = surface; 
		this.p = p; 
	}
	
	public void draw() {
		Button create = new Button(new Rectangle(5, 5, 50, 20), "create", new Color(221,160,221)); 
		Button back = new Button(new Rectangle(245, 5, 50, 20), "back", new Color(240, 128, 128)); 
		create.draw(surface);
		back.draw(surface);
		if(p.getPaint().isAvailable()) {
			surface.text("" + p.getPaint().getAmount(), 150, 5);
			Button use = new Button(new Rectangle(150, 200, 50, 50), "use", new Color(60,179,113)); 
			use.draw(surface);
		}
	}
	public void mousePressed() {
		
	}
}
