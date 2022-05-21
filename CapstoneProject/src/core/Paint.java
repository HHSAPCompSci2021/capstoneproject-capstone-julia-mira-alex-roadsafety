package core;
import processing.awt.*;
import processing.core.*;
import java.awt.Color; 

public class Paint {
	private Color color; 
	private int amount; 
	private boolean available; 
	//private MixedPalette mixed; 
	/**
	 * creates a Paint with the given color
	 * default amount is 0, default availability is false, created MixedPalette
	 * @param color 
	 */
	public Paint(Color color, int amount) {
		if(amount == 0) {
			available = false; 
			this.amount = 0; 
		}
		else {
			available = true; 
			this.amount = amount; 
		}
		this.color = color; 
//		if(shouldMix) {
//			 mixed = new MixedPalette(this); 
//		}
	 
	}
	/**
	 * @return color of the paint 
	 */
	public Color getColor() {
		return color ; 
	}
	public boolean isAvailable() {
		if(amount <= 0) {
			available = false; 
		}
		else {
			available = true; 
		}
		return available; 
	}
	/**
	 * makes available with given amount. if amount is zero, then availability is false 
	 * @param amount
	 */
	public void makeAvailable(int amount) {
		if(amount <= 0) {
			available = false; 
		}
		else {
			available = true; 	
		}
		this.amount = amount; 
	}
	/**
	 * 
	 * @return the amount of paint left, or the number or uses it has 
	 */
	public int getAmount() {
		return amount;  
	}

}
