package core;
import processing.core.*;
import java.awt.Color; 
import java.awt.Point;

import javax.swing.text.GapContent;

import processing.awt.*; 

public class Filler {
	//PGraphics g; 
	public Filler() {
		//g = pg; 
	}
	public void fill(int x, int y, Color c, boolean[][] outline, PGraphics g ) {
	
		int col = c.getRGB();
		if(y< 0 || y>= outline.length || x < 0 || x>= outline[y].length) {
			return; 
		}
		if(g.get().get(x, y) == col || outline[y][x]) {
			return; 
		}
		else {
			g.circle(x, y, 3); 
			for(int i = -1; i<2; i++) {
				for(int j = -1; j< 2; j++) {
					if(i!= 0 || j!= 0) {
						fill(x + i, y + j, c, outline, g); 
					}
				}
			}
		}
	}
}
