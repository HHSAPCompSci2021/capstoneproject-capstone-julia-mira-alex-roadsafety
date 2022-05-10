package core;
import processing.awt.*;
import processing.core.*;

public class Paint {
	private int[] color; 
	private double amount; 
	private boolean available; 
	private MixedPalette mixed; 
	/**
	 * creates a Paint with the given color
	 * default amount is 0, default availability is false, created MixedPalette
	 * @param color 
	 */
	public Paint(int[] color, double amount) {
		available = false; 
		amount = 0; 
		mixed = new MixedPalette(color); 
	}
	/**
	 * @return color of the paint 
	 */
	public int[] getColor() {
		return color ; 
	}
	public boolean isAvailable() {
		return available; 
	}
	/**
	 * makes available with given amount 
	 * @param amount
	 */
	public void makeAvailable(double amount) {
		
	}
	/**
	 * 
	 * @return the amount of paint left 
	 */
	public int getAmount() {
		
	}

}
