package screens;
import javax.swing.*; 
import core.*;
import java.util.*; 
//figure out how to import things from a default package 
//a thing that PaintButtons have that displays relevant info and buttons 

/**
 * the window that PaintButtons have that displays relevant info and buttons
 * 
 * @author 
 *
 */
public class Window extends Screen {
	private Paint[] p ; 
	//ArrayList<Button> buttons; 
	private JPanel display; 
	/**
	 * creates a window object with no set size
	 */
	public Window() {
		super();
		//figure out a set size 
	}
	
	/**
	 * creates a window object with set width and height
	 * @param width the width of the screen
	 * @param height the height of the screen
	 */
	public Window (int width, int height) {
		super(width, height);
		
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * adds the button to the window
	 * @param b the button that will be added
	 */
	public void addButton(Button b) {
	//	buttons.add(b); 
		//drawing buttons yee 
	}
	/**
	 * yeet the button into the chosen screen depending on the button chosen 
	 * its either TypingScreen or MixingScreen 
	 * if not either then it just doesn't do anything
	 * @param x the xcoord 
	 * @param y the ycoord 
	 */
	public void selectButton(int x, int y) {
		
	}
	
}
