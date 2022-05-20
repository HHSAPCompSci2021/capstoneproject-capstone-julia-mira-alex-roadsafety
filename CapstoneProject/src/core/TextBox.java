//package core;
//import processing.core.*;
//import processing.awt.*; 
//import java.awt.color.*; 
//public class TextBox
//{
//  public  String    text   = "";
//  
//  private PGraphics buffer   = new PGraphics();
//  private PVector   pos      = new PVector();
//  private PVector   size     = new PVector();
//  private PVector   margin   = new PVector();
//  private float     scroll   = 0;
//  private color     backCol  = color(255,255,255,0);
//  private color     edgeCol  = color(255,255,255,0);
//  private color     textCol  = color(0);
//  private PFont     font     = new PFont();
//  
//  public PVector getPos()  {return pos;}
//  public PVector getSize() {return size;}
//  
//  public void setPos      (PVector pos)      {setPos(pos.x, pos.y);}
//  public void setPos      (float x, float y) {pos.x = x; pos.y = y;}
//  public void setMargin   (PVector margin)   {setMargin(margin.x, margin.y);}
//  public void setMargin   (float w, float h) {margin.x = w; margin.y = h;}
//  public void scroll      (float scroll)     {this.scroll = max(this.scroll+scroll, 0);}  // Scroll cannot go below 0.
//  public void setBackCol  (color c)          {this.backCol = c;}
//  public void setEdgeCol  (color c)          {this.edgeCol = c;}
//  public void setTextCol  (color c)          {this.textCol = c;}
//  public void setFont     (PFont font)       {this.font = font;}
//  
//  public void draw()
//  {
//    buffer.beginDraw();
//    {
//      buffer.clear();
//      buffer.background (backCol);
//      buffer.stroke     (edgeCol);
//      buffer.fill       (backCol);
//      buffer.rect       (0, 0, buffer.width-1, buffer.height-1);  // Border.
//      buffer.textFont   (font);
//      buffer.fill       (textCol);
//      buffer.textAlign  (LEFT, TOP);
//      buffer.text       (text, margin.x, margin.y-scroll, buffer.width-margin.x, buffer.height+scroll);
//    }
//    buffer.endDraw();
//  
//    image(buffer, pos.x, pos.y); 
//  }
//  
//  public boolean isInside(PVector checkPos) {return isInside(checkPos.x, checkPos.y);}
//  public boolean isInside(float checkPosX, float checkPosY)
//  {
//    boolean inWidth  = checkPosX > pos.x && checkPosX < pos.x+size.x;
//    boolean inHeight = checkPosY > pos.y && checkPosY < pos.y+size.y;
//    
//    return inWidth && inHeight;
//  }
//  
//  public TextBox(PVector size, PVector pos) {this(pos.x, pos.y, size.x, size.y);}
//  public TextBox(float x, float y, float w, float h)
//  {
//    pos.x  = x; pos.y = y; size.x = w; size.y = h;
//    buffer = createGraphics(w, h, P2D);
//  }
//}
