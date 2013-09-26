/*

Menu.js

High-level class to render a menu with selectable options.

*/

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.*;

public class Menu
{
    /*
     * Properties
     */
    
    protected BufferedImage buffer;
    
    protected ArrayList<MenuItem> items;
    protected ListIterator<MenuItem> iterator;
    
    protected ArrayList<MenuListener> listeners;
    
    /*
     * Constructors
     */
    
    public Menu()
    {        
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
        buffer = new BufferedImage(size.width,
                                   size.height,
                                   BufferedImage.TYPE_INT_ARGB);
        
        renderBorder(size);
        renderItems(size);
        
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
			    repaint = false;
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
    
    protected void renderBorder(Dimension size)
    {
        BufferedImage bg = Skin.getMenuBackground();
        
        // Make sure we have a background image to work with
        if (bg == null)
        {
            return;
        }
        
        Graphics2D g = buffer.createGraphics();
        g.setComposite(AlphaComposite.Clear);
        g.setBackground(new Color(255, 255, 255, 0));
        g.fillRect(0, 0, size.width, size.height);
        g.setComposite(AlphaComposite.SrcOver);
        
        // Calculate the width of each "tile" in the background image
        int width = bg.getWidth() / 3;
        
        // Clear the view so we don't have overlaid transparent layers
        g.clearRect(0, 0, size.width, size.height);
        //g.setColor(Skin.getBackgroundColor());
        //g.fillRect(0, 0, size.width, size.height);
        
        // Draw the border corners first
        g.drawImage(bg.getSubimage(0, 0, width, width), null, 0, 0);
        g.drawImage(bg.getSubimage(width * 2, 0, width, width), null,
                    size.width - width, 0);
        g.drawImage(bg.getSubimage(0, width * 2, width, width), null,
                    0, size.height - width);
        g.drawImage(bg.getSubimage(width * 2, width * 2, width, width), null,
                    size.width - width, size.height - width);
        
        // Draw border sides
        int sideWidth = size.width - width - width;
        int sideHeight = size.height - width - width;
        
        for (int y = width; y <= sideHeight; y += width)
        {
            // Left border
            g.drawImage(bg.getSubimage(0, width, width, width), null,
                        0, y);
            
            // Right border
            g.drawImage(bg.getSubimage(width * 2, width, width, width), null,
                        size.width - width, y);
        }
        
        for (int x = width; x <= sideWidth; x += width)
        {
            for (int y = width; y <= sideHeight; y += width)
            {
                // Fill center
                g.drawImage(bg.getSubimage(width, width, width, width), null,
                            x, y);
            }
            
            // Top border
            g.drawImage(bg.getSubimage(width, 0, width, width), null,
                        x, 0);
            
            // Bottom border
            g.drawImage(bg.getSubimage(width, width * 2, width, width), null,
                        x, size.height - width);
        }
        
        g.dispose();
    }
    
    /*
     * Private Methods
     */
    
    private void renderItems(Dimension size)
    {
        Graphics g = buffer.createGraphics();
        
        for (int i = 0; i < items.size(); i++)
        {
            int style = (i == iterator.nextIndex() ? 2 : 
                         (items.get(i).isEnabled() ? 0 : 3));
            int x = (size.width - items.get(i).getText().length() * 8) / 2;
            Font.draw(items.get(i).getText(), g, x, 32 + i * 10, style);
        }
        
        g.dispose();
    }
    
    private void nextItem()
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
    
    private void previousItem()
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
    
    private void firstItem()
    {
        iterator = items.listIterator(items.size() - 1);
        nextItem();
    }
}
