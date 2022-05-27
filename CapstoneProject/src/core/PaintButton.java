package core;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JButton; 

import mhaldar.shapes.Shape;
import processing.core.PApplet;
import screens.*; 

/**
 * button to display to user
 * @author julia and Mira
 *
 */
public class PaintButton extends Button {
	private Window w; 
	private Paint paint;  
	private boolean mixed;
	private MixedPalette mpalette; 
	private PaintButton parent; 
	
	/**
	 * constructor for a normal paintbutton
	 * @param button shape 
	 * @param text message/purpose
	 * @param p paint 
	 */
	public PaintButton(Shape button, String text, Paint p) {
		super(button, text, p.getColor()); 
		paint = p; 
		mpalette = new MixedPalette(this); 
	}
	
	/**
	 * constructor for mixed paintbuttons 
	 * @param button button
	 * @param text text displayed
	 * @param p paint color the button is holding
	 * @param parent parent paintbutton
	 */
	public PaintButton(Shape button, String text, Paint p, PaintButton parent) {
		super(button, text, p.getColor()); 
		paint = p; 
		mixed = true; 
		this.parent = parent; 
	}
	
	/**
	 * draws the button at specified coord; draws x over it if color is unavailable 
	 * @param p PApplet
	 * @param x xcoord
	 * @param y ycoord 
	 */
	public void draw(PApplet surface, float x, float y) {
		if(!paint.isAvailable()) {
			super.setText("X");
		}
		else {
			surface.fill(0);
			super.setText(" " + paint.getAmount()); 
		}
		super.draw(surface,  x+5, y+20);
	}
	
	/**
	 * @return returns the paint the button is holding
	 */
	public Paint getPaint() {
		return paint; 
	}
	
	/**
	 * 
	 * @return whether the paint is mixed or not 
	 */
	public boolean isMixed() {
		return mixed; 
	}
	
	/**
	 * creates a window for user to customize their color
	 */
	public void createWindow(DrawingSurface surface) { 
		w = (Window) surface.getScreens().get(surface.WIN); 
		w.choosePaint(this);
		surface.switchScreen(surface.WIN);
	}
	
	/**
	 * if mixed then this is useful
	 * @return the parent PaintButton that spawned this one 
	 */
	public PaintButton getParent() {
		return parent; 
	}
	
	/**
	 * 
	 * @return available sub shades of paint 
	 */
	public MixedPalette getMixes() {
		return mpalette; 
	}
}

