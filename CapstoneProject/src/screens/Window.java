package screens;
import core.*; 
import core.DrawingSurface;
import mhaldar.shapes.*;
import java.awt.Color;
import java.awt.Point; 

/**
 * screen that shows the mixing options for the colors
 * @author Mira and Julia
 *
 */
public class Window extends Screen {
	private PaintButton p; 
	private PaintingScreen pscreen; 
	private Button create, back, use, mix; 
	
	/**
	 * creates the window with set width and height
	 * @param surface PApplet
	 */
	public Window(DrawingSurface surface, PaintingScreen screen) {
		super(800, 1600);
		
		this.surface = surface; 
		this.p = p; 
		pscreen = screen; 
		
		themeColor = new Color(239, 183, 192, 255);
		
		create = new Button(new Rectangle(20, 20, 150, 50), "get more", themeColor); 
		back = new Button(new Rectangle(DRAWING_WIDTH*(3.0/4), 20, 150, 50), "back", themeColor); 
	}

	/**
	 * selects the paint and sets it to field
	 * @param p the color chosen
	 */
	public void choosePaint(PaintButton p) {
		this.p = p; 
	}
	
	/**
	 * draws the screen
	 */
	public void draw() {
		create.setColor(themeColor);
		back.setColor(themeColor);
	
		surface.fill(p.getColor().getRGB()); 
		surface.rect(0, 0, DRAWING_WIDTH, DRAWING_HEIGHT);
		surface.fill(Color.white.getRGB());
		
		create.draw(surface);
		back.draw(surface);
		String amount; 
		if(p.getPaint().isAvailable()) {
			amount = "Amount: " + p.getPaint().getAmount();
			use = new Button(new Rectangle(20, 170, 150, 50), "use", themeColor); 
			if(!p.isMixed() && p.getColor()!= Color.black) { 
				mix = new Button(new Rectangle(20, 320, 150, 50), "mix", themeColor); 
				mix.draw(surface);
			}
			use.draw(surface);
		}
		
		else {
			amount = "Not available"; 
		}
		
		if(p.getColor() == Color.white) {
			surface.fill(0);
		}
		
		surface.text(amount, DRAWING_WIDTH/2 - surface.textWidth(amount)/2, 60);
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
	
	/**
	 * if use button clicked, go back to drawing screen
	 * if mix clicked, go to mixing screen
	 * if back is clicked, go back to drawing screen
	 * or go to the typing screen
	 */
	public void mousePressed() {
		Point point = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		if(p.getPaint().isAvailable()) {
			if(use.isClicked(point)) {
				p.getPaint().makeAvailable(p.getPaint().getAmount()-1);
				pscreen.selectedColor(p.getColor());
				surface.switchScreen(surface.PAINTING_SCREEN);
			}
			if(mix != null && mix.isClicked(point)) {
				MixingScreen m = (MixingScreen) surface.getScreens().get(surface.MIXING_SCREEN); 
				m.chooseColor(p);
				surface.switchScreen(surface.MIXING_SCREEN);
			}
		}
		if(create.isClicked(point)) {
			if(p.isMixed()) {
				p.getPaint().makeAvailable(p.getPaint().getAmount()+1);
				p.getParent().getPaint().makeAvailable(p.getParent().getPaint().getAmount()-1); 
				System.out.println(p.getParent().getPaint().getAmount());
			}
			else if(p.getColor() == Color.white || p.getColor() == Color.black) {
				p.getPaint().makeAvailable(p.getPaint().getAmount()+1);
			}
			else {
				TypingScreen t = (TypingScreen) surface.getScreens().get(surface.TYPING_SCREEN); 
				t.chooseColor(p.getPaint()); 
				surface.switchScreen(surface.TYPING_SCREEN);
				t.restart();
			}
		}
		if(back.isClicked(point)) {
			surface.switchScreen(surface.PAINTING_SCREEN);
		}
	}
}
