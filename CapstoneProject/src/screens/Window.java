package screens;
import core.*; 
import core.DrawingSurface;
import mhaldar.shapes.*;
import java.awt.Color;
import java.awt.Point; 

public class Window extends Screen {
	PaintButton p; 
	PaintingScreen pscreen; 
	Button create, back, use, mix; 
	/**
	 * creates the window with set width and height
	 * @param surface PApplet
	 */
	public Window(DrawingSurface surface, PaintButton p, PaintingScreen screen) {
		super(300, 300);
		this.surface = surface; 
		this.p = p; 
		pscreen = screen; 
	}
	/**
	 * default constructor 
	 * @param surface PApplet
	 */
	public Window(DrawingSurface surface) {
		super(300, 300);
	}
	public void draw() {
		surface.fill(p.getColor().getRGB()); 
		surface.rect(0, 0, DRAWING_WIDTH, DRAWING_HEIGHT);
		surface.fill(Color.white.getRGB());
		create = new Button(new Rectangle(5, 5, 70, 70), "get\nmore", new Color(221,160,221)); 
		back = new Button(new Rectangle(200, 5, 100, 20), "back", new Color(240, 128, 128)); 
		create.draw(surface);
		back.draw(surface);
		if(p.getPaint().isAvailable()) {
			surface.text("Amount: " + p.getPaint().getAmount(), 100, 75);
			use = new Button(new Rectangle(120, 200, 50, 50), "use", new Color(60,179,113)); 
			if( p.getColor() != Color.white && p.getColor() != Color.black && !p.isMixed()) { 
				mix = new Button(new Rectangle(180, 200, 30, 30), "mix", new Color(60, 179, 113)); 
				mix.draw(surface);
			}
			use.draw(surface);
			
		}
		else {
			surface.text("Not Available", 100, 75);
		}
	}
	public void mousePressed() {
		Point point = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		if(p.getPaint().isAvailable()) {
			if(use.isClicked(point)) {
				p.getPaint().makeAvailable(p.getPaint().getAmount()-1);
				pscreen.selectedColor(p.getColor());
				surface.switchScreen(surface.PAINTING_SCREEN);
			}
			if(mix != null && mix.isClicked(point)) {
				MixingScreen m = new MixingScreen(surface, p, pscreen); 
				surface.getScreens().remove(surface.MIXING_SCREEN); 
				surface.getScreens().add(surface.MIXING_SCREEN, m); 
				surface.switchScreen(surface.MIXING_SCREEN);
				surface.switchScreen(surface.MIXING_SCREEN);
			}
		}
		if(create.isClicked(point)) {
			p.getPaint().makeAvailable(p.getPaint().getAmount()+1);
			if(p.isMixed()) {
				p.getParent().getPaint().makeAvailable(p.getPaint().getAmount()-1); 
			}
//			else {
//				surface.switchScreen(surface.TYPING_SCREEN);
//			}
			
		}
		if(back.isClicked(point)) {
			surface.switchScreen(surface.PAINTING_SCREEN);
		}
	}
}
