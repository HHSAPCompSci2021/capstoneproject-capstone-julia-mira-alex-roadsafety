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

	private Palette palette; //the color palette that shows what colors user has
	private Button exit, finish, startOver, instructions; //other options other than drawing
	private Button draw, fill, erase, blur; //drawing options
	private Button background1, background2, background3;
	private Button widthSlider, widthButton, mode;
	private int width;
	private int drawState; // 0 is draw, 1 is fill, 2 is erase, 3 is blur 
	private Color selected; 
	boolean on; 
	
	/**
	 * creates a PaintingScreen with set width and height
	 * @param surface drawing surface for screen
	 */
	public PaintingScreen (DrawingSurface surface) {
		super(1600, 1600);
		this.surface = surface; 
		drawState = 0;
		on  = true;
		
		themeColor = new Color(239, 183, 192, 255);
		
		exit = new Button(new Rectangle(DRAWING_WIDTH - 180, 10, 150, 40), "exit", themeColor);
		finish = new Button(new Rectangle(DRAWING_WIDTH - 180, 100, 150, 40), "finish", themeColor);
		instructions = new Button(new Rectangle(DRAWING_WIDTH - 180, 195, 150, 40), "instructions", themeColor);
		startOver = new Button(new Rectangle(DRAWING_WIDTH - 180, 290, 150, 40), "clear", themeColor);
		draw = new Button(new Rectangle(DRAWING_WIDTH - 180, DRAWING_HEIGHT/2, 150, 40), "draw", themeColor);
		erase = new Button(new Rectangle(DRAWING_WIDTH - 180, DRAWING_HEIGHT/2 +200, 150, 40), "erase", themeColor); 
		fill = new Button(new Rectangle(DRAWING_WIDTH - 180, DRAWING_HEIGHT/2 + 100, 150, 40), "fill", themeColor);
		blur = new Button(new Rectangle(DRAWING_WIDTH - 180, DRAWING_HEIGHT/2 + 300, 150, 40), "blur", themeColor); 
		
		palette = new Palette(); 
		selected = palette.getPaint(0).getColor();
		
		width = 3;
		widthSlider = new Button(new Rectangle(DRAWING_WIDTH/2 + 100, DRAWING_HEIGHT - 300, 350, 40), "", Color.WHITE);
		widthButton = new Button(new Rectangle(DRAWING_WIDTH/2 + 150, DRAWING_HEIGHT - 300, 50, 40), Integer.toString(width), themeColor);
		
		mode = new Button(new Rectangle(DRAWING_WIDTH/2 + 145,DRAWING_HEIGHT - 450, 250, 50), "", themeColor); 
		
		background1 = new Button(new Rectangle(DRAWING_WIDTH/2 + 50, DRAWING_HEIGHT - 80, 120, 40), "theme1", themeColor);
		background2 = new Button(new Rectangle(DRAWING_WIDTH/2 + 200, DRAWING_HEIGHT - 80, 120, 40), "theme2", themeColor);;
		background3 = new Button(new Rectangle(DRAWING_WIDTH/2 + 350, DRAWING_HEIGHT - 80, 120, 40), "theme3", themeColor);;
	}

	/**
	 * sets up background image
	 */
	public void setup() {
		background = surface.loadImage("additionalPictures"+fileSeparator+BGName);
		background.resize(DRAWING_WIDTH, DRAWING_HEIGHT);
	}
	
	/**
	 * returns width of screen
	 * @return
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * draws the screen
	 */
	@Override
	public void draw() {
		surface.image(background, 0, 0);
		
		surface.rect(0, 0, DRAWING_WIDTH/2 , DRAWING_HEIGHT);
		
		surface.strokeWeight(2);
		surface.fill(themeColor.getRGB());
		surface.rect(DRAWING_WIDTH/2 +75, DRAWING_HEIGHT-300, 400, 40);
		surface.fill(0);
		surface.textSize(23);
		surface.text("brush thickness: (click on bar)", DRAWING_WIDTH/2 + 100, DRAWING_HEIGHT - 310);
		
		widthSlider.draw(surface);
		widthButton.draw(surface);
		
		if(drawState == 0) {
			mode.setText("DRAW"); 
		}
		else if (drawState == 1) {
			mode.setText("FILL");
		}
		else if(drawState == 2) {
			mode.setText("ERASE"); 
		}
		else {
			mode.setText("BLUR"); 
		}
		mode.draw(surface); 
		exit.draw(surface);
		finish.draw(surface);
		instructions.draw(surface);
		startOver.draw(surface);
		fill.draw(surface);
		draw.draw(surface);
		erase.draw(surface);
		blur.draw(surface);
		background1.draw(surface);
		background2.draw(surface);
		background3.draw(surface);
		
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		
		exit.highlight(p, surface);
		finish.highlight(p, surface);
		instructions.highlight(p, surface);
		startOver.highlight(p, surface);
		fill.highlight(p, surface);
		draw.highlight(p, surface);
		erase.highlight(p, surface);
		blur.highlight(p, surface);
		background1.highlight(p, surface);
		background2.highlight(p, surface);
		background3.highlight(p, surface);
		
		palette.draw(surface, false);
		
		exit.setColor(themeColor);
		finish.setColor(themeColor);
		startOver.setColor(themeColor);
		instructions.setColor(themeColor);
		draw.setColor(themeColor);
		fill.setColor(themeColor);
		exit.setColor(themeColor);
		erase.setColor(themeColor);
		blur.setColor(themeColor);
		background1.setColor(themeColor);
		background2.setColor(themeColor);
		background3.setColor(themeColor);
		widthButton.setColor(themeColor);
		mode.setColor(themeColor);
	}
	
	//total length of bar is 350
	//starts at 
	//divide by 25? 50? to get 14? 7? different widths
	/**
	 * changes point to width
	 * @param p mouse point
	 */
	public void clickToIndex(Point p) {
		double x = p.getX();
		double start = DRAWING_WIDTH/2 + 100;
		if (x < start+50)
			width = 1;
		else if (x < start+100)
			width = 3;
		else if (x < start+150)
			width = 5;
		else if (x < start+200)
			width = 7;
		else if (x < start+250)
			width = 9;
		else if (x < start+300)
			width = 11;
		else if (x < start+350)
			width = 13;
	}
	
	/**
	 * depending on which mouse buttons are pressed, do the button's features
	 */
	public void mousePressed() {
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		if (draw.isClicked(p)) {
			System.out.println("drawing");
			drawState = 0;
		}
		
		else if (fill.isClicked(p)) {
			drawState = 1;
		}
		else if(erase.isClicked(p)) {
			drawState = 2; 
		}
		
		else if (exit.isClicked(p)) {
			surface.exit();
		}
		
		else if (finish.isClicked(p)) {
			surface.finish(); 
			surface.switchScreen(surface.END_SCREEN);
		}
		 
		else if(instructions.isClicked(p)) {
			surface.switchScreen(surface.INSTRUCTIONS_SCREEN);
		}
		 
		else if (startOver.isClicked(p)) {
			surface.clearGraphics(); 
			palette.restart();
		}
		else if(blur.isClicked(p)) {
			drawState = 3; 
		}
		
		else if(widthSlider.isClicked(p)) {
			clickToIndex(p);
			widthButton.setText(Integer.toString(width));
			widthButton.changeLocation(p);
		}
		
		else if(background1.isClicked(p)) {
			changeTheme(new Color(239, 183, 192, 255), "background1.jpg");
			setup();			
		}
		
		else if(background2.isClicked(p)) {
			changeTheme(new Color(195,153,107,255), "background2.png"); //brown
			setup();
		}
		
		else if(background3.isClicked(p)) {
			changeTheme(new Color(255,144,3,255), "background3.jpg"); //dark orange
			setup();
		}
		
		else {
			int pos = palette.selectPaint(p); 
			if(pos >= 0) {
				System.out.println(pos); 
				selected = null; 
				palette.getPaint(pos).createWindow(surface);
			}
			else if(selected!= null) {
			}
		}
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
	 * @return return its mode(draw, erase, or fill) 
	 */
	public int mode() {
		return drawState; 
	}
}