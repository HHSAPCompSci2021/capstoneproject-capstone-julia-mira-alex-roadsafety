package core;
import java.io.*; 
import java.lang.*; 
import java.awt.Color; 
import java.util.*; 

public class ColorSorter implements Comparator<PaintButton>{
	public int compare(PaintButton a, PaintButton b) {
		int acol =  a.getPaint().getColor().getRGB(); 
		int bcol = b.getPaint().getColor().getRGB(); 
		if(acol > bcol) {
		//	System.out.println("sfsfgf");
			return -1; 
		}
		else {
		//	System.out.println("sfsfgf");
			return 1; 
		}
	}
}
