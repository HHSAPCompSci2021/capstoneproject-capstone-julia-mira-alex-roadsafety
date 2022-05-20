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
	Color buttonColor;
	
	/**
	 * creates the window with set width and height
	 * @param surface PApplet
	 */
	public Window(DrawingSurface surface, PaintButton p, PaintingScreen screen) {
		super(800, 600);
		
		this.surface = surface; 
		this.p = p; 
		pscreen = screen; 
		
		buttonColor = new Color(239, 183, 192, 255);
		
		create = new Button(new Rectangle(10, 10, 150, 80), "get more", buttonColor); 
		back = new Button(new Rectangle(450, 10, 100, 80), "back", buttonColor); 
		
	}
	/**
	 * default constructor 
	 * @param surface PApplet
	 */
	public Window(DrawingSurface surface) {
		super(800, 600);
		
		buttonColor = new Color(239, 183, 192, 255);
		
		create = new Button(new Rectangle(10, 10, 150, 80), "get more", buttonColor); 
		back = new Button(new Rectangle(450, 10, 100, 80), "back", buttonColor); 
		
	}
	public void draw() {
		
		surface.fill(p.getColor().getRGB()); 
		surface.rect(0, 0, DRAWING_WIDTH, DRAWING_HEIGHT);
		surface.fill(Color.white.getRGB());
		
		create.draw(surface);
		back.draw(surface);
		
		if(p.getPaint().isAvailable()) {
			surface.text("Amount: " + p.getPaint().getAmount(), 200, 75);
			use = new Button(new Rectangle(120, 200, 50, 50), "use", buttonColor); 
			if( p.getColor() != Color.white && p.getColor() != Color.black && !p.isMixed()) { 
				mix = new Button(new Rectangle(180, 200, 30, 30), "mix", buttonColor); 
				mix.draw(surface);
			}
			use.draw(surface);
			
		}
		else {
			surface.text("Not Available", 100, 75);
		}
		
		Point point = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		create.highlight(point, surface);
		back.highlight(point, surface);
		if (use != null) {
			use.highlight(point, surface);
		}
		if (mix != null) {
			mix.highlight(point, surface);
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
		//	p.getPaint().makeAvailable(p.getPaint().getAmount()+1);
			if(p.isMixed()) {
				p.getPaint().makeAvailable(p.getPaint().getAmount()+1);
				p.getParent().getPaint().makeAvailable(p.getPaint().getAmount()-1); 
			}
			else {
				TypingScreen t = (TypingScreen) surface.getScreens().get(surface.TYPING_SCREEN); 
				t.chooseColor(p.getPaint()); 
				surface.switchScreen(surface.TYPING_SCREEN);
			}
			
		}
		if(back.isClicked(point)) {
			surface.switchScreen(surface.PAINTING_SCREEN);
		}
	}
}
