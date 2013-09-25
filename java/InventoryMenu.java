/*

InventoryMenu.java

Displays player's inventory

*/

import java.awt.*;
import java.awt.image.*;
import java.util.*;

public class InventoryMenu extends Menu
{
    /*
     * Properties
     */
    
    private Being player;
    
    /*
     * Constructors
     */
    
    public InventoryMenu(Being player)
    {
        super();
        
        this.player = player;
    }
    
    /*
     * Public Methods
     */
    
    public BufferedImage render(Dimension size)
    {
        clear();
        
        ArrayList<Entity> entities = player.getInventory();
        
        for (int i = 0; i < entities.size(); i++)
        {
            add(new MenuItem(i, entities.get(i).getDescription()));
        }
        
        return super.render(size);
    }
    
    /*
}
