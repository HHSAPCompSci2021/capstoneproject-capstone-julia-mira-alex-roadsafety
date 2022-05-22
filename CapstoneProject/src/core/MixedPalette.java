package core;
import java.awt.Color; 
import mhaldar.shapes.*; 
import java.util.ArrayList; 
/**
 * a palette based off of one color, contains shades 
 * 
 *
 */
public class MixedPalette extends Palette{
	//these have text as outlined in readme 
	//there's like five of them 
	/**
	 * creates a palette with paints based off of root color c 
	 * @param c
	 */
	public MixedPalette(PaintButton paint) {
		super(new Rectangle(10, 400, 525, 100)); 
	//	shades = new PaintButton[5]; 
		//v light, sort of light, normal, sorta dark, v dark 
		collection = new ArrayList<PaintButton>(); 
		Color c = paint.getPaint().getColor(); 
		Color newc = new Color((int)(c.getRed()*.65), (int)(c.getBlue()*.65), (int)(c.getGreen()*.65)); 
		Color newc1 = c.darker(); 
		Color newc2 = newc1.darker(); 
		Color newc3 = c.brighter(); 
		Color newc4 = newc3.brighter(); 
		//shades[0] = ; 
		collection.add(new PaintButton( new Rectangle(10, 400, 100, 100), "X", new Paint(newc, 0), paint)); 
		collection.add(new PaintButton( new Rectangle(110, 400, 100, 100), "X", new Paint(newc2, 0), paint)); 
		collection.add(new PaintButton( new Rectangle(215, 250, 75, 75), "", paint.getPaint(), null)); 
		collection.add(new PaintButton( new Rectangle(325, 400, 100, 100), "", new Paint(newc3, 0), paint));
		collection.add(new PaintButton( new Rectangle(425, 400, 100, 100), "", new Paint(newc4, 0), paint)); 
//		for(PaintButton b: collection ) {
//			b.setMixed();
//		}
	}
	
}
