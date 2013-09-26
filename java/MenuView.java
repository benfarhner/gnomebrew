/*

MenuView.java

*/

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;

public class MenuView extends RenderableView
{
    /*
     * Properties
     */
    
    private Menu menu;
    
    /*
     * Constructors
     */
    
    public MenuView(Menu menu)
    {
        this.menu = menu;
    }
    
    /*
     * Public Methods
     */
    
    public void update()
    {
        menu.update();
    }
    
    public BufferedImage render(Dimension size)
    {
        return menu.render(size);
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
        menu.handleInput(e.getKeyCode());
    }
}
