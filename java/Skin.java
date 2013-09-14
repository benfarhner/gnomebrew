/*

Skin.java

Loads a Skin for the game.

*/

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.nio.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;
import javax.imageio.*;

public class Skin
{
    /*
     * Properties
     */
    
    private static Dimension _tileSize;
    private static Color _backgroundColor;
    private static Color _foregroundColor;
    private static Color _highlightColor;
    private static HashMap<Integer, BufferedImage> _entityImages;
    
    /*
     * Accessors
     */
    
    public static Dimension getTileSize()
    {
        return _tileSize;
    }
    
    public static Color getBackgroundColor()
    {
        return _backgroundColor;
    }
    
    public static Color getForegroundColor()
    {
        return _foregroundColor;
    }
    
    public static Color getHighlightColor()
    {
        return _highlightColor;
    }
    
    public static BufferedImage getEntityImage(int type)
    {
        return _entityImages.get(type);
    }
    
    /*
     * Functions
     */
    
    public static void load()
    {
        Path file = Paths.get("default.skin");
        String delimiter = ",", partDelimiter = ";";
        Charset charset = Charset.forName("UTF-8");
        
        try (BufferedReader reader = Files.newBufferedReader(file, charset))
        {
            String line = null;
            
            // Read first line to get tile size
            if ((line = reader.readLine()) != null)
            {
                String[] parts = line.split(delimiter);
                
                if (parts.length == 2)
                {
                    _tileSize = new Dimension(Integer.valueOf(parts[0]), 
                                              Integer.valueOf(parts[1]));
                }
                else
                {
                    _tileSize = new Dimension(16, 16);
                }
            }
            
            // Read second line to get background and foreground colors
            if ((line = reader.readLine()) != null)
            {
                String[] parts = line.split(partDelimiter);
                
                if (parts.length == 3)
                {
                    _backgroundColor = createColorFromString(parts[0]);
                    
                    if (_backgroundColor == null)
                    {
                        _backgroundColor = Color.black;
                    }
                    
                    _foregroundColor = createColorFromString(parts[1]);
                    
                    if (_foregroundColor == null)
                    {
                        _foregroundColor = Color.white;
                    }
                    
                    _highlightColor = createColorFromString(parts[2]);
                    
                    if (_highlightColor == null)
                    {
                        _highlightColor = Color.yellow;
                    }
                    
                }
            }
            
            _entityImages = new HashMap<Integer, BufferedImage>();
            
            // Read each following line as an Entity skin
            while ((line = reader.readLine()) != null)
            {
                String[] parts = line.split(partDelimiter);
                
                if (parts.length > 1)
                {
                    Integer type = Integer.valueOf(parts[0]);
                    
                    int index = 1;
                    BufferedImage image = null;
                    
                    while (image == null && index < parts.length)
                    {
                        image = loadImage(parts[index]);
                        index++;
                    }
                    
                    if (image != null)
                    {
                        _entityImages.put(type, image);
                    }
                }
            }
        }
        catch (IOException e)
        {
            System.err.format("IOException: %s%n", e);
        }
    }
    
    /*
     * Private Functions
     */
    
    private static BufferedImage loadImage(String def)
    {
        BufferedImage image = null;
        
        if (def.length() > 0)
        {
            int defEnd = def.length() - 1;
            
            // Determine if it is a character, RGB, or a path
            if (def.charAt(0) == '\'' &&
                def.charAt(defEnd) == '\'')
            {
                String character = def.substring(1, defEnd);
                image = createImageFromCharacter(character);
            }
            else if (def.charAt(0) == '(' &&
                def.charAt(defEnd) == ')')
            {
                Color color = createColorFromString(def);
                image = createImageFromColor(color);
            }
            else
            {
                try
                {
                    image = ImageIO.read(new File(def));
                }
                catch (IOException e)
                {
                    System.err.println("Oops, can't load image '" + def + "'! " + e.toString());
                }
            }
        }
        
        return image;
    }
    
    private static BufferedImage createImageFromColor(Color color)
    {
        BufferedImage image = null;
        
        if (color != null)
        {
            image = new BufferedImage(_tileSize.width,
                                      _tileSize.height,
                                      BufferedImage.TYPE_INT_RGB);
            Graphics g = image.createGraphics();
        
            g.setColor(color);
            g.fillRect(0, 0, _tileSize.width, _tileSize.height);
        }
        
        return image;
    }
    
    private static BufferedImage createImageFromCharacter(String character)
    {
        int leftPadding = _tileSize.width / 4;
        int bottomPadding = _tileSize.height / 4;
        BufferedImage image = new BufferedImage(_tileSize.width,
                                                _tileSize.height,
                                                BufferedImage.TYPE_INT_RGB);
        Graphics g = image.createGraphics();
        
        g.setColor(_backgroundColor);
        g.fillRect(0, 0, _tileSize.width, _tileSize.height);
        
        g.setColor(_foregroundColor);
        g.drawString(character.substring(0, 1),
                     leftPadding,
                     _tileSize.height - 1 - bottomPadding);
        
        return image;
    }
    
    // createColorFromString()
    // Parses an RGB color from the given string and returns a Color
    // Expects string in the format "(#,#,#)"
    private static Color createColorFromString(String rgb)
    {
        Color color = null;
        String[] parts = rgb.substring(1, rgb.length() - 1).split(",");
        
        if (parts.length == 3)
        {
            int r = Integer.parseInt(parts[0]);
            int g = Integer.parseInt(parts[1]);
            int b = Integer.parseInt(parts[2]);
            
            color = new Color(r, g, b);
        }
        
        return color;
    }
}
