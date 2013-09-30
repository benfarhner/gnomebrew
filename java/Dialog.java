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
    
    protected BufferedImage buffer;
    protected int padding = 32;
    
    /*
     * Constructors
     */
    
    public Dialog()
    {
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
        
        return buffer;
    }
    
    public boolean handleInput(int keycode)
    {
        return false;
    }
    
    /*
     * Protected Methods
     */
    
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
}
