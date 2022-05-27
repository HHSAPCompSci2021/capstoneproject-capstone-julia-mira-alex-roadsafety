package core;
import java.io.*; 
import java.lang.*; 
import java.awt.Color; 
import java.util.*; 

/**
 * the class that sorts colors
 * @author Mira
 *
 */
public final class ColorSorter implements Comparator<Paint> {
    @Override
    /**
     * compares two colors using Comparator<Paint>
     */
    public int compare(Paint p1, Paint p2) {
    	Color c1 = p1.getColor(); 
    	Color c2 = p2.getColor();
    //	System.out.println(c1 + " nay" + c2); 
        float[] hsb1 = Color.RGBtoHSB(c1.getRed(), c1.getGreen(), c1.getBlue(), null);
        float[] hsb2 = Color.RGBtoHSB(c2.getRed(), c2.getGreen(), c2.getBlue(), null);
        if (hsb1[0] < hsb2[0])
            return -1;
        if (hsb1[0] > hsb2[0])
            return 1;
        if (hsb1[1] < hsb2[1])
            return -1;
        if (hsb1[1] > hsb2[1])
            return 1;
        if (hsb1[2] < hsb2[2])
            return -1;
        if (hsb1[2] > hsb2[2])
            return 1;
        return 0;
    }
}
