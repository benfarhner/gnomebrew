/*

MenuItem.java

Wrapper for an item displayed in a Menu.

*/

public class MenuItem
{
    /*
     * Properties
     */
    
    private int id;
    private String text;
    private boolean enabled;
    
    /*
     * Constructors
     */
    
    public MenuItem()
    {
        this(0, "", true);
    }
    
    public MenuItem(int id, String text)
    {
        this(id, text, true);
    }
    
    public MenuItem(int id, String text, boolean enabled)
    {
        this.id = id;
        this.text = text;
        this.enabled = enabled;
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
    
    /*
     * Public Methods
     */
    
    public String toString()
    {
        return text;
    }
}
