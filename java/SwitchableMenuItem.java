/*

SwitchableMenuItem.java

Menu item whose state can be altered.

*/

import java.awt.event.*;
import java.util.*;

public class SwitchableMenuItem extends MenuItem
{
    /*
     * Properties
     */
    
    protected String label;
    protected ArrayList<MenuItem> options;
    protected ListIterator<MenuItem> iterator;
    
    protected ArrayList<MenuListener> listeners;
    
    /*
     * Constructors
     */
    
    public SwitchableMenuItem()
    {
        this(0, "", true, Centered);
    }
    
    public SwitchableMenuItem(int id, String label)
    {
        this(id, label, true, Centered);
    }
    
    public SwitchableMenuItem(int id,
                              String label,
                              boolean enabled,
                              int alignment)
    {
        super(id, "", enabled, alignment);
        this.label = label;
        this.options = new ArrayList<MenuItem>();
        
        this.listeners = new ArrayList<MenuListener>();
    }
    
    /*
     * Accessors
     */
     
    public String getLabel()
    {
        return label;
    }
    
    public String getText()
    {
        String text = "";
        
        for (MenuItem option : options)
        {
            text += "  " + option.toString();
        }
        
        return text;
    }
    
    public ArrayList<MenuItem> getOptions()
    {
        return options;
    }
    
    public MenuItem getCurrentOption()
    {
        return options.get(iterator.nextIndex());
    }
    
    /*
     * Mutators
     */
    
    public void add(MenuItem option)
    {
        if (option != null)
        {
            options.add(option);
            firstOption();
        }
    }
    
    public void clear()
    {
        if (options != null)
        {
            options.clear();
        }
    }
    
    public void setLabel(String label)
    {
        this.label = label;
    }
    
    public void addMenuListener(MenuListener listener)
    {
        if (listener != null)
        {
            listeners.add(listener);
        }
    }
    
    /*
     * Public Methods
     */
    
    public void handleInput(int keycode)
    {
        switch (keycode)
        {
            case KeyEvent.VK_LEFT:
                previousOption();
                select();
                break;
            case KeyEvent.VK_RIGHT:
                nextOption();
                select();
                break;
        }
    }
    
    public String toString()
    {
        String output = label;
        
        for (MenuItem option : options)
        {
            output += "  " + option.toString();
        }
        
        return output;
    }
    
    /*
     * Private Methods
     */
    
    private void select()
    {
        for (MenuListener listener : listeners)
        {
            if (listener != null)
            {
                listener.handleSelection(options.get(iterator.nextIndex()));
            }
        }
    }
    
    private void nextOption()
    {
        int count = 0;
        
        do
        {
            if (iterator.hasNext())
            {
                iterator.next();
            }
            else
            {
                iterator = options.listIterator(0);
            }
            
            count++;
        }
        while (!(iterator.hasNext() && 
                 options.get(iterator.nextIndex()).isEnabled()) &&
               count < options.size());
    }
    
    private void previousOption()
    {
        int count = 0;
        
        do
        {
            if (iterator.hasPrevious())
            {
                iterator.previous();
            }
            else
            {
                iterator = options.listIterator(options.size() - 1);
            }
            
            count++;
        }
        while (!(iterator.hasNext() &&
                 options.get(iterator.nextIndex()).isEnabled()) &&
               count < options.size());
    }
    
    private void firstOption()
    {
        iterator = options.listIterator(options.size() - 1);
        nextOption();
    }
}
