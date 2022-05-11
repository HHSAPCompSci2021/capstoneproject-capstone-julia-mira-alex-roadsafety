package core;
import java.awt.Color; 
/**
 * a palette based off of one color, contains shades 
 * 
 *
 */
public class MixedPalette extends Palette{
	PaintButton[] shades;
	//these have text as outlined in readme 
	//there's like five of them 
	/**
	 * creates a palette with paints based off of root color c 
	 * @param c
	 */
	public MixedPalette(Color color ) {
		shades = new PaintButton[5]; 
		//v light, sort of light, normal, sorta dark, v dark 
		
	}

}
