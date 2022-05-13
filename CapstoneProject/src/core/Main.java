package core; 
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import java.awt.Dimension;

import javax.swing.JFrame;

import processing.awt.PSurfaceAWT;
import processing.core.PApplet;

public class Main {

	public static void main(String args[]) {

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		
		
		DrawingSurface drawing = new DrawingSurface(width, height);
		PApplet.runSketch(new String[]{""}, drawing);
		PSurfaceAWT surf = (PSurfaceAWT) drawing.getSurface();
		PSurfaceAWT.SmoothCanvas canvas = (PSurfaceAWT.SmoothCanvas) surf.getNative();
		JFrame window = (JFrame)canvas.getFrame();


		window.setExtendedState(JFrame.MAXIMIZED_BOTH); 
//		window.setUndecorated(true);
		window.setVisible(true);
		
		
//		window.setSize(800, 600);
		window.setMinimumSize(new Dimension(100,100));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(true);

		window.setVisible(true);
		
		
		canvas.requestFocus();
	}

}
