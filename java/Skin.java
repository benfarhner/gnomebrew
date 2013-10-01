/*

Skin.java

Loads a Skin for the game.

*/

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import javax.imageio.ImageIO;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Skin
{
    /*
     * Constants
     */
    
    private final static String DIRECTORY = "skins";
    private final static String FILENAME = "skin.xml";
    
    /*
     * Properties
     */
    
    private static String skinName;
    private static Dimension _tileSize;
    private static Color _backgroundColor;
    private static Color _foregroundColor;
    private static Color _highlightColor;
    private static HashMap<Integer, BufferedImage> _entityImages;
    private static BufferedImage font = null;
    private static BufferedImage menuBackground = null;
    
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
    
    public static BufferedImage getFont()
    {
        return font;
    }
    
    public static BufferedImage getMenuBackground()
    {
        return menuBackground;
    }
    
    /*
     * Public Methods
     */
    
    public static void load(String skinName)
    {
        Skin.skinName = skinName;
        Element root = Parser.parseFile(DIRECTORY, skinName, FILENAME);
        
        if (root != null)
        {
            loadTile(Parser.getLastElement(root, "tile"));
            loadBackground(Parser.getLastElement(root, "background"));
            loadForeground(Parser.getLastElement(root, "foreground"));
            loadHighlight(Parser.getLastElement(root, "highlight"));
            loadFont(Parser.getLastElement(root, "font"));
            loadDialog(Parser.getLastElement(root, "dialog"));
            loadEntities(Parser.getLastElement(root, "entities"));
        }
    }
    
    /*
     * Private Methods
     */
    
    private static void loadTile(Element element)
    {
        if (element == null)
        {
            return;
        }
        
        if (element.hasAttribute("width") &&
            element.hasAttribute("height"))
        {
            int width = Integer.parseInt(element.getAttribute("width"));
            int height = Integer.parseInt(element.getAttribute("height"));
            _tileSize = new Dimension(width, height);
        }
        else
        {
            // TODO: Store default size elsewhere
            _tileSize = new Dimension(16, 16);
        }
    }
    
    private static void loadBackground(Element element)
    {
        if (element == null)
        {
            return;
        }
        
        String rgb = element.getTextContent();
        _backgroundColor = createColorFromString(rgb);
    }
    
    private static void loadForeground(Element element)
    {
        if (element == null)
        {
            return;
        }
        
        String rgb = element.getTextContent();
        _foregroundColor = createColorFromString(rgb);
    }
    
    private static void loadHighlight(Element element)
    {
        if (element == null)
        {
            return;
        }
        
        String rgb = element.getTextContent();
        _highlightColor = createColorFromString(rgb);
    }
    
    private static void loadFont(Element element)
    {
        if (element == null)
        {
            return;
        }
        
        // Load characters
        Element characters = Parser.getLastElement(element, "characters");
        if (characters != null)
        {
            Font.setCharacters(characters.getTextContent());
        }
        
        // Load character properties
        Element character = Parser.getLastElement(element, "character");
        if (character != null)
        {
            Dimension size = new Dimension(0, 0);
            
            if (character.hasAttribute("width"))
            {
                size.width = Integer.parseInt(character.getAttribute("width"));
            }
            if (character.hasAttribute("height"))
            {
                size.height = Integer.parseInt(character.getAttribute("height"));
            }
            
            Font.setSize(size);
            
            Dimension padding = new Dimension(0, 0);
            
            if (character.hasAttribute("padding-x"))
            {
                padding.width = Integer.parseInt(character.getAttribute("padding-x"));
            }
            if (character.hasAttribute("padding-y"))
            {
                padding.height = Integer.parseInt(character.getAttribute("padding-y"));
            }
            
            Font.setPadding(padding);
        }
        
        // Load font image
        Element image = Parser.getLastElement(element, "image");
        if (image != null)
        {
            font = loadImage(image.getTextContent());
        }
    }
    
    private static void loadDialog(Element element)
    {
        if (element == null)
        {
            return;
        }
        
        Element image = Parser.getLastElement(element, "image");
        if (image != null)
        {
            menuBackground = loadImage(image.getTextContent());
        }
    }
    
    private static void loadEntities(Element element)
    {
        if (element == null)
        {
            return;
        }
        
        _entityImages = new HashMap<Integer, BufferedImage>();
        
        NodeList nodes = element.getElementsByTagName("entity");
        for (int i = 0; i < nodes.getLength(); i++)
        {
            loadEntity((Element)(nodes.item(i)));
        }
    }
    
    private static void loadEntity(Element entity)
    {
        if (entity.hasAttribute("id"))
        {
            int type = Integer.parseInt(entity.getAttribute("id"));
            BufferedImage image = null;
            
            Element node = Parser.getLastElement(entity, "image");
            
            if (node != null)
            {
                image = loadImage(node.getTextContent());
            }
            else
            {
                node = Parser.getLastElement(entity, "color");
                
                if (node != null)
                {
                    String rgb = node.getTextContent();
                    Color color = createColorFromString(rgb);
                    image = createImageFromColor(color);
                }
                else
                {
                    node = Parser.getLastElement(entity, "character");
                    
                    if (node != null)
                    {
                        String character = node.getTextContent();
                        image = createImageFromCharacter(character);
                    }
                }
            }
            
            if (image != null)
            {
                _entityImages.put(type, image);
            }
        }
    }
    
    private static BufferedImage loadImage(String fileName)
    {
        String path = Parser.getFullPath(DIRECTORY, Skin.skinName, fileName);
        BufferedImage image = null;
        
        try
        {
            image = ImageIO.read(new File(path));
        }
        catch (IOException e)
        {
            System.err.format("Can't load image file: %s%n", e);
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
        BufferedImage image = new BufferedImage(_tileSize.width,
                                                _tileSize.height,
                                                BufferedImage.TYPE_INT_RGB);
        Graphics g = image.createGraphics();
        
        g.setColor(_backgroundColor);
        g.fillRect(0, 0, _tileSize.width, _tileSize.height);
        
        Font.draw(character.substring(0, 1), g,
                  (_tileSize.width - Font.getSize().width) / 2,
                  (_tileSize.height - Font.getSize().height) / 2,
                  FontStyle.Normal);
        
        return image;
    }
    
    // createColorFromString()
    // Parses an RGB color from the given string and returns a Color
    // Expects string in the format "(#,#,#)"
    private static Color createColorFromString(String rgb)
    {
        Color color = null;
        String[] parts = rgb.split(",");
        
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
