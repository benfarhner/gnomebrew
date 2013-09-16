/*

Menu.js

High-level class to render a menu with selectable options.

*/

import java.awt.*;
import java.awt.image.*;

public class Menu
{
    /*
     * Properties
     */
    
    protected BufferedImage view;
    protected Location location;
    protected Dimension size;
    
    /*
     * Constructors
     */
    
    public Menu(Dimension size)
    {
        this.location = new Location();
        this.size = size;
        this.view = new BufferedImage(size.width,
                                      size.height,
                                      BufferedImage.TYPE_INT_ARGB);
    }
    
    /*
     * Public Methods
     */
    
    public BufferedImage render()
    {
        return view;
    }
    
    public Location getLocation()
    {
        return location;
    }
    
    public Dimension getSize()
    {
        return size;
    }
    
    public boolean handleInput(int keycode)
    {
        return true;
    }
    
    /*
    Protected Methods
    */
    
    protected void renderBorder()
    {
    }
}
