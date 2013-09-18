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
     * Constants
     */
    
    // Font styles
    public final static int NORMAL = 0;
    public final static int BOLD = 1;
    public final static int HIGHLIGHT = 2;
    public final static int DISABLED = 3;
    
    /*
     * Properties
     */
    
    private static String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    
    /*
     * Public Methods
     */
    
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
        if (style < 0 || style > 3)
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
                            x + i * 8,
                            y,
                            x + i * 8 + 8,
                            y + 8,
                            charIndex * 8,
                            8 * style,
                            charIndex * 8 + 8,
                            8 * style + 8,
                            null);
            }
        }
    }
}
