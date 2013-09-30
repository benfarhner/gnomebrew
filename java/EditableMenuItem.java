/*

EditableMenuItem.java

Menu item whose text can be edited.

*/

import java.awt.event.*;

public class EditableMenuItem extends MenuItem
{
    /*
     * Properties
     */
    
    protected String label;
    
    /*
     * Constructors
     */
    
    public EditableMenuItem()
    {
        this(0, "", "", true, Centered);
    }
    
    public EditableMenuItem(int id, String label, String text)
    {
        this(id, label, text, true, Centered);
    }
    
    public EditableMenuItem(int id,
                            String label,
                            String text,
                            boolean enabled,
                            int alignment)
    {
        super(id, text, enabled, alignment);
        this.label = label;
    }
    
    /*
     * Accessors
     */
     
    public String getLabel()
    {
        return label;
    }
    
    /*
     * Mutators
     */
    
    public void setLabel(String label)
    {
        this.label = label;
    }
    
    /*
     * Public Methods
     */
    
    public void handleInput(int keycode)
    {
        if ((keycode > 64 && keycode < 91) || keycode == 32)
        {
            text += (char)keycode;
        }
        else if (keycode == KeyEvent.VK_BACK_SPACE &&
                 text.length() > 0)
        {
            text = text.substring(0, text.length() - 1);
        }
    }
    
    public String toString()
    {
        return label + text;
    }
}
