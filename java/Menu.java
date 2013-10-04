/*

Menu.js

High-level class to render a menu with selectable options.

*/

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.*;

public class Menu extends Dialog
{
    /*
     * Properties
     */
    
    protected ArrayList<MenuItem> items;
    protected ListIterator<MenuItem> iterator;
    
    protected ArrayList<MenuListener> listeners;
    
    /*
     * Constructors
     */
    
    public Menu()
    {
        super();
        this.items = new ArrayList<MenuItem>();
        this.listeners = new ArrayList<MenuListener>();
    }
    
    /*
     * Mutators
     */
    
    public void add(MenuItem item)
    {
        if (item != null)
        {
            items.add(item);
            firstItem();
        }
    }
    
    public void clear()
    {
        if (items != null)
        {
            items.clear();
        }
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
    
    public void update()
    {
    }
    
    public BufferedImage render(Dimension size)
    {
        super.render(size);
        renderItems(size, new Dimension(0, 0));
        
        return buffer;
    }
    
    public boolean handleInput(int keycode)
    {
        boolean repaint = true;
        
        switch(keycode)
		{
		    case KeyEvent.VK_UP:
		    case KeyEvent.VK_LEFT:
		        previousItem();
		        break;
			case KeyEvent.VK_DOWN:
			case KeyEvent.VK_RIGHT:
			    nextItem();
			    break;
			case KeyEvent.VK_ENTER:
			    select();
			    break;
			default:
                items.get(iterator.nextIndex()).handleInput(keycode);
			    break;
		}
		
		return repaint;
    }
    
    /*
     * Protected Methods
     */
    
    protected void select()
    {
        MenuItem selection = items.get(iterator.nextIndex());
        
        for (MenuListener listener : listeners)
        {
            if (listener != null)
            {
                listener.handleSelection(selection);
            }
        }
    }
    
    protected void renderItems(Dimension size, Dimension offset)
    {
        Graphics g = buffer.createGraphics();
        int style;
        boolean isCurrentItem;
        MenuItem item;
        String text;
        int x, y;
        
        for (int i = 0; i < items.size(); i++)
        {
            item = items.get(i);
            isCurrentItem = (i == iterator.nextIndex());
            
            if (isCurrentItem)
            {
                style = FontStyle.Highlight;
            }
            else if (items.get(i).isEnabled())
            {
                style = FontStyle.Normal;
            }
            else
            {
                style = FontStyle.Disabled;
            }
            
            switch (item.getAlignment())
            {
                case MenuItem.LeftAligned:
                default:
                    x = padding;
                    break;
                case MenuItem.Centered:
                    x = (size.width - Font.getWidth(item.toString())) / 2;
                    break;
                case MenuItem.RightAligned:
                    x = size.width - padding - Font.getWidth(item.toString());
                    break;
            }
            
            x += item.getPadding().width + offset.width;
            y = padding + i * Font.getLineHeight() +
                item.getPadding().height + offset.height;
            
            if (item instanceof EditableMenuItem)
            {
                EditableMenuItem eItem = (EditableMenuItem)item;
                Font.draw(eItem.getLabel(), g, x, y, style);
                
                x += Font.getWidth(eItem.getLabel());
                text = eItem.getText() + (isCurrentItem ? "_" : "");
                Font.draw(text, g, x, y, FontStyle.Normal);
            }
            else if (item instanceof SwitchableMenuItem)
            {
                SwitchableMenuItem sItem = (SwitchableMenuItem)item;
                Font.draw(sItem.getLabel(), g, x, y, style);
                
                x += Font.getWidth(sItem.getLabel());
                ArrayList<MenuItem> options = sItem.getOptions();
                MenuItem currentOption = sItem.getCurrentOption();
                
                for (MenuItem option : options)
                {
                    text = option.toString();
                    
                    if (option == currentOption)
                    {
                        style = FontStyle.Bold;
                    }
                    else
                    {
                        style = FontStyle.Normal;
                    }
                    
                    Font.draw(text, g, x, y, style);
                    x += Font.getWidth(sItem.getLabel() + "  ");
                }
            }
            else
            {
                text = item.toString();
                Font.draw(text, g, x, y, style);
            }
        }
        
        g.dispose();
    }
    
    protected void nextItem()
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
                iterator = items.listIterator(0);
            }
            
            count++;
        }
        while (!(iterator.hasNext() && 
                 items.get(iterator.nextIndex()).isEnabled()) &&
               count < items.size());
    }
    
    protected void previousItem()
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
                iterator = items.listIterator(items.size() - 1);
            }
            
            count++;
        }
        while (!(iterator.hasNext() &&
                 items.get(iterator.nextIndex()).isEnabled()) &&
               count < items.size());
    }
    
    protected void firstItem()
    {
        iterator = items.listIterator(items.size() - 1);
        nextItem();
    }
}
