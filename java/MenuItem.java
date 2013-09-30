/*

MenuItem.java

Wrapper for an item displayed in a Menu.

*/

import java.awt.*;

public class MenuItem
{
    /*
     * Constants
     */
    
    public final static int LeftAligned = 0;
    public final static int Centered = 1;
    public final static int RightAligned = 2;
    
    /*
     * Properties
     */
    
    protected int id;
    protected String text;
    protected boolean enabled;
    protected Dimension padding;
    protected int alignment;
    
    /*
     * Constructors
     */
    
    public MenuItem()
    {
        this(0, "", true, Centered);
    }
    
    public MenuItem(int id, String text)
    {
        this(id, text, true, Centered);
    }
    
    public MenuItem(int id, String text, boolean enabled, int alignment)
    {
        this.id = id;
        this.text = text;
        this.enabled = enabled;
        this.padding = new Dimension(0, 0);
        this.alignment = alignment;
    }
    
    /*
     * Accessors
     */
    
    public int getID()
    {
        return id;
    }
    
    public String getText()
    {
        return text;
    }
    
    public boolean isEnabled()
    {
        return enabled;
    }
    
    public Dimension getPadding()
    {
        return padding;
    }
    
    public int getAlignment()
    {
        return alignment;
    }
    
    /*
     * Mutators
     */
    
    public void setID(int id)
    {
        this.id = id;
    }
    
    public void setText(String text)
    {
        this.text = text;
    }
    
    public void enable()
    {
        enabled = true;
    }
    
    public void disable()
    {
        enabled = false;
    }
    
    public void setPadding(Dimension padding)
    {
        if (padding != null)
        {
            this.padding = padding;
        }
    }
    
    public void setAlignment(int alignment)
    {
        this.alignment = alignment;
    }
    
    /*
     * Public Methods
     */
    
    public void handleInput(int keycode)
    {
    }
    
    public String toString()
    {
        return text;
    }
}
