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
    
    private static String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ-";
    private static int charWidth = 8;
    private static int charHeight = 8;
    private static int charPaddingX = 0;
    private static int charPaddingY = 2;
    
    /*
     * Public Methods
     */
    
    public static int getLineHeight()
    {
        return charHeight + charPaddingY;
    }
    
    public static int getWidth(String text)
    {
        return text.length() * (charWidth + charPaddingX);
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
            int charIndex = characters.indexOf(text.charAt(i));
            
            if (charIndex >= 0)
            {
                g.drawImage(Skin.getFont(),
                            x + i * charWidth,
                            y,
                            x + i * charWidth + charWidth,
                            y + charHeight,
                            charIndex * charWidth,
                            charHeight * style,
                            charIndex * charWidth + charWidth,
                            charHeight * style + charHeight,
                            null);
            }
            else if (text.charAt(i) == '_')
            {
                charIndex = characters.indexOf('-');
                
                g.drawImage(Skin.getFont(),
                            x + i * charWidth,
                            y + charHeight / 2,
                            x + i * charWidth + charWidth,
                            y + charHeight,
                            charIndex * charWidth,
                            charHeight * style,
                            charIndex * charWidth + charWidth,
                            charHeight * style + charHeight / 2,
                            null);
            }
        }
    }
}
