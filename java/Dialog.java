/*

Dialog.js

High-level class to render a dialog window.

*/

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.*;

public class Dialog
{
    /*
     * Properties
     */
    
    protected BufferedImage background;
    protected BufferedImage buffer;
    protected int padding = 32;
    
    /*
     * Constructors
     */
    
    public Dialog()
    {
        renderBackground(Config.getScreenSize());
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
        
        Graphics g = buffer.createGraphics();
        g.drawImage(background, 0, 0, null);
        g.dispose();
        
        return buffer;
    }
    
    public boolean handleInput(int keycode)
    {
        return false;
    }
    
    /*
     * Protected Methods
     */
    
    protected void renderBackground(Dimension size)
    {
        renderBackground(size, false);
    }
    
    protected void renderBackground(Dimension size, boolean drawBorder)
    {
        BufferedImage bg = Skin.getMenuBackground();
        
        // Make sure we have a background image to work with
        if (bg == null)
        {
            return;
        }
        
        background = new BufferedImage(Config.getScreenSize().width,
                                       Config.getScreenSize().height,
                                       BufferedImage.TYPE_INT_ARGB);
        
        Graphics2D g = background.createGraphics();
        g.setComposite(AlphaComposite.Clear);
        g.setBackground(new Color(255, 255, 255, 0));
        g.fillRect(0, 0, size.width, size.height);
        g.setComposite(AlphaComposite.SrcOver);
        
        // Calculate the width of the borders in the background image
        int width = bg.getWidth() / 3;
        
        // Clear the view so we don't have overlaid transparent layers
        //g.clearRect(0, 0, size.width, size.height);
        
        int startX = 0;
        int startY = 0;
        int endX = size.width;
        int endY = size.height;
        
        if (drawBorder)
        {
            startX = width;
            startY = width;
            endX = size.width - width;
            endY = size.height - width;
        }
        
        // Fill in dialog background
        for (int x = startX; x < endX; x++)
        {
            for (int y = startY; y < endY; y++)
            {
                g.drawImage(bg.getSubimage(width, width, 1, 1), null,
                            x, y);
            }
        }
        
        if (drawBorder)
        {
            renderBorder(size, g);
        }
        
        g.dispose();
    }
    
    protected void renderBorder(Dimension size, Graphics2D g)
    {
        BufferedImage bg = Skin.getMenuBackground();
        
        // Calculate the width of the borders in the background image
        int width = bg.getWidth() / 3;
        
        // Draw the border corners first
        g.drawImage(bg.getSubimage(0, 0, width, width), null, 0, 0);
        g.drawImage(bg.getSubimage(width * 2, 0, width, width), null,
                    size.width - width, 0);
        g.drawImage(bg.getSubimage(0, width * 2, width, width), null,
                    0, size.height - width);
        g.drawImage(bg.getSubimage(width * 2, width * 2, width, width), null,
                    size.width - width, size.height - width);
        
        // Draw border sides
        int endX = size.width - width;
        for (int x = width; x < endX; x++)
        {
            // Top border
            g.drawImage(bg.getSubimage(width, 0, 1, width), null,
                        x, 0);
            
            // Bottom border
            g.drawImage(bg.getSubimage(width, width * 2, 1, width), null,
                        x, size.height - width);
        }
        
        int endY = size.height - width;
        for (int y = width; y < endY; y++)
        {
            // Left border
            g.drawImage(bg.getSubimage(0, width, width, 1), null,
                        0, y);
            
            // Right border
            g.drawImage(bg.getSubimage(width * 2, width, width, 1), null,
                        size.width - width, y);
        }
        
        g.dispose();
    }
}
