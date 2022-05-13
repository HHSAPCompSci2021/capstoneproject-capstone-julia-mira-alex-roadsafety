package core;
import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JButton; 

import mhaldar.shapes.Shape;
import processing.core.PApplet;
import screens.*; 
public class PaintButton extends Button {
	private Window w; 
//	Shape shape; 
	private Paint paint;  
	boolean mixed; 
	public PaintButton(Shape button, String text, Paint p) {
		super(button, text, p.getColor()); 
		paint = p; 
	}
	//draw paint w/ an x over it if unavailable and do all sorts of things to the window 
	/**
	 * draws the button at specified coord; draws x over it if color is unavailable 
	 * @param p PApplet
	 * @param x xcoord
	 * @param y ycoord 
	 */
	public void draw(PApplet surface, float x, float y) {
		//draw button at specified coord 
		
		if(!paint.isAvailable()) {
			super.setText("X");
		}
		else {
			super.setText(" " + paint.getAmount()); 
		}
		super.draw(surface,  x+5, y+20);
	}
	public Paint getPaint() {
		return paint; 
	}
	/**
	 * changes the paint button to false 
	 */
	public void setMixed() {
		mixed = true; 
	}
	/**
	 * 
	 * @return whether the paint is mixed or not 
	 */
	public boolean isMixed() {
		return mixed; 
	}
}

