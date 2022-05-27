package core;
import processing.awt.*;
import processing.core.*;
import java.awt.Color; 

public class Paint {
	private Color color; 
	private int amount; 
	private boolean available; 

	/**
	 * sets up the color, amount of color, and availability
	 * @param color color of paint
	 * @param amount amount of paint
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
	}
	
	/**
	 * @return color of the paint 
	 */
	public Color getColor() {
		return color ; 
	}
	
	/**
	 * @return if paint is available
	 */
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
	 * @param amount new amount of paint
	 */
	public void makeAvailable(int amount) {
		this.amount = amount; 
		if(amount <= 0) {
			available = false; 
		}
		else {
			available = true; 	
		}
	}
	
	/**
	 * 
	 * @return the amount of paint left, or the number or uses it has 
	 */
	public int getAmount() {
		return amount;  
	}
}
