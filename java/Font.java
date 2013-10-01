/*

Font.java

Handles the rendering of text in an image font, loaded from an image file.

Pass one of the style constants to draw() to affect the rendering.

*/

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class Font
{    
    /*
     * Properties
     */
    
    private static String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static Dimension size = new Dimension(8, 8);
    private static Dimension padding = new Dimension(0, 2);
    
    /*
     * Accessors
     */
    
    public static Dimension getSize()
    {
        return size;
    }
    
    public static Dimension getPadding()
    {
        return padding;
    }
    
    /*
     * Mutators
     */
    
    public static void setCharacters(String characters)
    {
        Font.characters = new String(characters);
    }
    
    public static void setSize(Dimension size)
    {
        Font.size = new Dimension(size);
    }
    
    public static void setPadding(Dimension padding)
    {
        Font.padding = new Dimension(padding);
    }
    
    /*
     * Public Methods
     */
    
    public static int getLineHeight()
    {
        return size.height + padding.height;
    }
    
    public static int getWidth(String text)
    {
        return text.length() * (size.width + padding.width);
    }
    
    public static void draw(String text,
                            Graphics g,
                            int x,
                            int y,
                            int style)
    {
        // Make sure font is available
        if (Skin.getFont() == null)
        {
            return;
        }
        
        // Make sure style is valid so we don't go out of bounds
        if (!FontStyle.isValid(style))
        {
            return;
        }
        
        text = text.toUpperCase();
        
        for (int i = 0; i < text.length(); i++)
        {
            int index = characters.indexOf(text.charAt(i));
            
            if (index >= 0)
            {
                g.drawImage(Skin.getFont(),
                            x + i * (size.width + padding.width),
                            y,
                            x + i * (size.width + padding.width) + size.width,
                            y + size.height,
                            index * size.width,
                            size.height * style,
                            index * size.width + size.width,
                            size.height * style + size.height,
                            null);
            }
            else if (text.charAt(i) == '_')
            {
                index = characters.indexOf('-');
                
                g.drawImage(Skin.getFont(),
                            x + i * (size.width + padding.width),
                            y + size.height / 2,
                            x + i * (size.width + padding.width) + size.width,
                            y + size.height,
                            index * size.width,
                            size.height * style,
                            index * size.width + size.width,
                            size.height * style + size.height / 2,
                            null);
            }
        }
    }
}
