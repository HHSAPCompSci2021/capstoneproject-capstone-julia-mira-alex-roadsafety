package core;
import mhaldar.shapes.Shape;
import screens.*; 
public class PaintButton extends Button {
	Window w; 
	public PaintButton(Shape button, String text, Paint p) {
		super(button, text, p.getColor()); 
	}
	//draw paint w/ an x over it if unavailable and do all sorts of things to the window 
}
