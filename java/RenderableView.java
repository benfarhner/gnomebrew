/*

RenderableView.java

Abstract class for views that render as images

*/

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

public abstract class RenderableView implements KeyListener
{
    public abstract void update();
    public abstract BufferedImage render(Dimension size);
    
    public void keyTyped(KeyEvent e) { }
    public void keyPressed(KeyEvent e) { }
    public void keyReleased(KeyEvent e) { }
}
