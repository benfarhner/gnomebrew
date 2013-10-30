/*

DialogView.java

*/

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;

public class DialogView extends RenderableView
{
    /*
     * Properties
     */
    
    private Dialog dialog;
    
    /*
     * Constructors
     */
    
    public DialogView(Dialog dialog)
    {
        this.dialog = dialog;
    }
    
    /*
     * Public Methods
     */
    
    public void update(long ms)
    {
        dialog.update(ms);
    }
    
    public BufferedImage render(Dimension size)
    {
        return dialog.render(size);
    }
    
    /*
     * KeyListener Methods
     */
    
    public void keyTyped(KeyEvent e)
    {
    }
    
    public void keyPressed(KeyEvent e)
    {
    }
    
    public void keyReleased(KeyEvent e)
    {
        dialog.handleInput(e.getKeyCode());
        notifyViewChanged();
    }
}
