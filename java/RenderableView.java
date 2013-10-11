/*

RenderableView.java

Abstract class for views that render as images

*/

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

public abstract class RenderableView implements Updateable, KeyListener
{
    public abstract BufferedImage render(Dimension size);
    
    public void update(long ms) { }
    
    public void keyTyped(KeyEvent e) { }
    public void keyPressed(KeyEvent e) { }
    public void keyReleased(KeyEvent e) { }
}
