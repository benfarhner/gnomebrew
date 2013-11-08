/*

Config.java

Loads and stores configuration data from file (config.xml).

*/

import java.awt.Dimension;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Config
{
    /*
     * Constants
     */
    
    private final static String FILENAME = "config.xml";
    
    /*
     * Properties
     */
    
    private static Dimension screenSize = new Dimension(640, 480);
    private static Dimension screenTileSize = new Dimension(40, 30);
    private static double clockSpeed = 1;
    
    /*
     * Accessors
     */
    
    public static Dimension getScreenSize()
    {
        return screenSize;
    }
    
    public static Dimension getScreenTileSize()
    {
        return screenTileSize;
    }
    
    public static double getClockSpeed()
    {
        return clockSpeed;
    }
    
    /*
     * Public Methods
     */
    
    public static void load()
    {
        Element root = Parser.parseFile(FILENAME);
        
        if (root != null)
        {
            loadSkin(Parser.getLastElement(root, "skin"));
            loadScreen(Parser.getLastElement(root, "screen"));
            loadClock(Parser.getLastElement(root, "clock"));
            loadNames(Parser.getLastElement(root, "names"));
            loadRecipes(Parser.getLastElement(root, "recipes"));
        }
    }
    
    /*
     * Private Methods
     */
    
    private static void loadSkin(Element element)
    {
        if (element == null)
        {
            return;
        }
        
        Skin.load(element.getTextContent());
    }
    
    private static void loadScreen(Element element)
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
            screenTileSize = new Dimension(width, height);
            screenSize = new Dimension(width * Skin.getTileSize().width,
                                       height * Skin.getTileSize().height);
        }
    }
    
    private static void loadClock(Element element)
    {
        if (element == null)
        {
            return;
        }
        
        if (element.hasAttribute("speed"))
        {
            clockSpeed = Double.parseDouble(element.getAttribute("speed"));
        }
    }
    
    private static void loadNames(Element names)
    {
        if (names == null)
        {
            return;
        }
        
        NodeList nodes = names.getElementsByTagName("name");
        Element element;
        String name, position, sex, type;
        
        for (int i = 0; i < nodes.getLength(); i++)
        {
            element = (Element)(nodes.item(i));
            name = element.getTextContent(); 
            position = element.getAttribute("position");
            sex = element.getAttribute("sex");
            type = element.getAttribute("type");
            
            if (position.equals("first"))
            {
                if (sex.equals("female"))
                {
                    NameGenerator.addFemaleName(name);
                }
                else
                {
                    NameGenerator.addMaleName(name);
                }
            }
            else if (position.equals("last"))
            {
                if (type.equals("modifier"))
                {
                    NameGenerator.addLastNameModifier(name);
                }
                else if (type.equals("noun"))
                {
                    NameGenerator.addLastNameNoun(name);
                }
                else if (type.equals("verb"))
                {
                    NameGenerator.addLastNameVerb(name);
                }
            }
        }
    }
    
    private static void loadRecipes(Element recipes)
    {
        if (recipes == null)
        {
            return;
        }
        
        NodeList nodes = recipes.getElementsByTagName("recipe");
        Element element;
        
        for (int i = 0; i < nodes.getLength(); i++)
        {
            element = (Element)(nodes.item(i));
            loadRecipe(element);
        }
    }
    
    private static void loadRecipe(Element recipe)
    {
        int entity, state;
        
        Element result = Parser.getLastElement(recipe, "result");
        entity = Integer.parseInt(result.getAttribute("entity"));
        state = Integer.parseInt(result.getAttribute("state"));
        //Entity result = new Entity(entity, state);
        
        Element ingredients = Parser.getLastElement(recipe, "ingredients");
        NodeList nodes = ingredients.getElementsByTagName("ingredient");
        Element ingredient;
        
        for (int i = 0; i < nodes.getLength(); i++)
        {
            ingredient = (Element)(nodes.item(i));
            entity = Integer.parseInt(ingredient.getAttribute("entity"));
            state = Integer.parseInt(ingredient.getAttribute("state"));
            //Entity ingredient = new Entity(entity, state);
        }
    }
}
