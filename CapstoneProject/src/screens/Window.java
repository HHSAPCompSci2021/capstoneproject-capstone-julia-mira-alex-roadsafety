package screens;
import core.*; 
import core.DrawingSurface;
import mhaldar.shapes.*;
import java.awt.Color;
import java.awt.Point; 

public class Window extends Screen {
	private PaintButton p; 
	private PaintingScreen pscreen; 
	private Button create, back, use, mix; 
	private Color buttonColor;
	
	/**
	 * creates the window with set width and height
	 * @param surface PApplet
	 */
	public Window(DrawingSurface surface, PaintingScreen screen) {
		super(800, 600);
		
		this.surface = surface; 
		this.p = p; 
		pscreen = screen; 
		
		buttonColor = new Color(239, 183, 192, 255);
		
		create = new Button(new Rectangle(10, 10, 150, 80), "get more", buttonColor); 
		back = new Button(new Rectangle(450, 10, 100, 80), "back", buttonColor); 
		
	}
//	/**
//	 * default constructor 
//	 * @param surface PApplet
//	 */
//	public Window(DrawingSurface surface) {
//		super(800, 600);
//		
//		buttonColor = new Color(239, 183, 192, 255);
//		
//		create = new Button(new Rectangle(10, 10, 150, 80), "buy", buttonColor); 
//		back = new Button(new Rectangle(450, 10, 100, 80), "back", buttonColor); 
//		
//	}
	public void choosePaint(PaintButton p) {
		this.p = p; 
	}
	public void draw() {
		
		surface.fill(p.getColor().getRGB()); 
		surface.rect(0, 0, DRAWING_WIDTH, DRAWING_HEIGHT);
		surface.fill(Color.white.getRGB());
		
		create.draw(surface);
		back.draw(surface);
		String amount; 
		if(p.getPaint().isAvailable()) {
			amount = "Amount: " + p.getPaint().getAmount();
			use = new Button(new Rectangle(10, 200, 50, 50), "use", buttonColor); 
			if(!p.isMixed()) { 
				mix = new Button(new Rectangle(10, 110, 40, 40), "mix", buttonColor); 
				mix.draw(surface);
			}
			use.draw(surface);
			// p.getColor() != Color.white && p.getColor() != Color.black && 
		}
		else {
			amount = "Not available"; 
		}
		surface.text(amount, DRAWING_WIDTH/2 - surface.textWidth(amount)/2, 30);
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
				MixingScreen m = (MixingScreen) surface.getScreens().get(surface.MIXING_SCREEN); 
				m.chooseColor(p);
				surface.switchScreen(surface.MIXING_SCREEN);
			}
		}
		if(create.isClicked(point)) {
		//	p.getPaint().makeAvailable(p.getPaint().getAmount()+1);
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
//				if(t.win()) {
//					pscreen.selectedColor(p.getColor());
//				}
				t.restart();
			}
			
		}
		if(back.isClicked(point)) {
			surface.switchScreen(surface.PAINTING_SCREEN);
		}
	}
}
