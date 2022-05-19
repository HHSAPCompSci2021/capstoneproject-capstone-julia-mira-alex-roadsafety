package core;
import processing.awt.*;
import processing.core.*;
import java.awt.Point; 
import mhaldar.shapes.*;
import java.util.*; 
import java.awt.Color; 

public class Palette {
// just arrange paints in a nice visual array and have a sort method so you can separate available from unavailable
	protected ArrayList<PaintButton> collection; // it'll end up looking like a nice 4 by six array maybe??? 
	private int divide; 
	private Rectangle bg; 
	private ColorSorter cs; 
	/**
	 * set up a Palette of 14 Paints 
	 */
	public Palette() {
		divide = 1; 
		collection = new ArrayList<PaintButton>(); 
		bg = new Rectangle(800, 0, 900, 900); 
		Paint[] paints = new Paint[36]; 
		paints[0] = new Paint(Color.black, 100);
		paints[1] = new Paint(Color.white, 100); 
		paints[2] =  new Paint(Color.cyan, 0); 
		paints[3] = new Paint(Color.red, 0); 
		paints[4] = new Paint(Color.blue, 0); 
		paints[5] = new Paint(Color.orange, 0); 
		paints[6] = new Paint(Color.PINK, 0);
		paints[7] = new Paint(Color.yellow, 0); 
		paints[8] =  new Paint(new Color(150, 0, 24), 0);
		paints[9] = new Paint(new Color(255, 0, 127), 0);
		paints[10] = new Paint(Color.gray, 0); 
		paints[11] = new Paint(new Color(0, 255, 127), 0); 
		paints[12] = new Paint(new Color(0, 128, 255), 0); 
		paints[13] = new Paint(new Color(127, 0, 255), 0); 
		paints[14] = new Paint(new Color(160, 82, 45), 0) ; 
		paints[15] = new Paint(new Color(0, 206, 209), 0); 
		paints[16] = new Paint(new Color(75, 0 , 130), 0); 
		paints[17] = new Paint(new Color(79, 151, 163), 0); 
		paints[18] = new Paint(new Color(199, 21, 133), 0); 
		paints[19] = new Paint(new Color(100, 149, 237), 0);
		paints[20] = new Paint(new Color(119, 136, 153), 0);
		paints[21] = new Paint(new Color(107, 142, 35), 0); 
		paints[22] = new Paint(new Color(218, 165, 32), 0); 
		paints[23] = new Paint(new Color(250, 160, 122), 0);
		paints[24] = new Paint(new Color(255,69,0), 0); 
		paints[25] = new Paint(new Color(189,183,107), 0); 
		paints[26] = new Paint(new Color(152,251,152), 0); 
		paints[27] = new Paint(new Color(176,224,230), 0); 
		paints[28] = new Paint(new Color(218,112,214), 0); 
		paints[29] = new Paint(new Color(205,133,63), 0); 
		paints[30] = new Paint(new Color(188,143,143), 0);
		paints[31] = new Paint(new Color(240,255,240), 0); 
		paints[32] = new Paint(new Color(30,144,255), 0); 
		paints[33] = new Paint(new Color(127,255,212), 0); 
		paints[34] = new Paint(new Color(123,104,238), 0); 
		paints[35] = new Paint(new Color(255, 254, 113), 0); 
		cs = new ColorSorter(); 
		//expand palette later 
		// create set up all the colors over here 
		int count = 0; 
		for(int j = 0; j < 9; j ++ ) {
			for(int i = 0; i< 4; i++) {
				Rectangle r =  new Rectangle(800 + 150*i, 100*j, 150, 100); 
				Paint p = paints[count]; 
				collection.add(new PaintButton(r, "", p)); 
				count ++; 
			}
		}
		//System.out.println(collection.size()); 
		Collections.sort(collection, cs);
		
	}
	public Palette(Rectangle r ) {
		divide = -1; 
		bg = r; 
	}
	private void sort() {
		
	}
//	}
	/**
	 * draw the Palette onto the surface
	 * @param surface PApplet
	 */
	public void draw(PApplet surface, boolean mix) {
		//System.out.println(collection.size()); 
		surface.fill(Color.white.getRGB()); 
		for(PaintButton pb : collection) {
			//System.out.println(co); 
			pb.draw(surface);
		}
	}
	/**
	 * changes Paint p to increase/decrease by the given amount 
	 * @param p the paint 
	 * @param available whether its available or not 
	 */
	public void changePaintAvailability(int p, int amount) {
		collection.get(p).getPaint().makeAvailable(collection.get(p).getPaint().getAmount()+  amount);
		System.out.println(collection.get(p).getPaint().getAmount()); 
	}
	/**
	 * 
	 * @param x xcoord
	 * @param y ycoord
	 * @return the position of the paint inside the palette 
	 */
	public int selectPaint( Point p) {
		if(!bg.isPointInside(p.getX(), p.getY())) {
			return -1; 
		}
		for(int i = 0; i< collection.size(); i++ ) {
	//		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
			if(collection.get(i).isClicked(p)) {
				//collection.get(i).createWindow();
				return i; 
			}
		}
		//make amount decrease
		//and check if it's available first
		//if it isn't return null 
		return -1; 
	}
	/**
	 * 
	 * @param i the index of the paint 
	 * @return the PaintButton at index i 
	 */
	public PaintButton getPaint(int i) {
		return collection.get(i); 
	}
	/**
	 * 
	 * @return all paints in palette
	 */
	public ArrayList<PaintButton> getPaints() {
		return collection; 
	}
	/**
	 * all amounts are reset 
	 */
	public void restart() {
		collection.get(0).getPaint().makeAvailable(100);
		collection.get(1).getPaint().makeAvailable(100);
		for(int i = 2; i< collection.size(); i++) {
			collection.get(i).getPaint().makeAvailable(0); 
			Palette m = collection.get(i).getMixes(); 
			if(m != null) {
				for(PaintButton b: m.getPaints()) {
					b.getPaint().makeAvailable(0);
				}
			}
		}
	}

}
