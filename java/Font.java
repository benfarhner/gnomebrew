/*

Font.java

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
    
    private static BufferedImage font;
    private static String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    
    public static void load()
    {
        font = null;
        
        try
        {
            font = ImageIO.read(new File("font.png"));
        }
        catch (IOException e)
        {
            System.err.println("Oops, can't load font! " + e.toString());
        }
    }
    
    public static void draw(String text,
                            Graphics g,
                            int x,
                            int y,
                            boolean highlight)
    {
        text = text.toUpperCase();
        
        for (int i = 0; i < text.length(); i++)
        {
            int charIndex = characters.indexOf(text.charAt(i));
            
            if (charIndex >= 0)
            {
                g.drawImage(font,
                            x + i * 16,
                            y,
                            x + i * 16 + 16,
                            y + 16,
                            charIndex * 8,
                            highlight ? 8 : 0,
                            charIndex * 8 + 8,
                            highlight ? 16 : 8,
                            null);
            }
        }
    }
}
