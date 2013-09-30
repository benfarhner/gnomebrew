/*

InventoryMenu.java

Displays player's inventory

*/

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.*;

public class InventoryMenu extends Menu
{
    /*
     * Properties
     */
    
    private Being player;
    private ArrayList<Entity> inventory;
    private ArrayList<Recipe> recipes;
    private int currentColumn = 0;
    private int currentItem = -1;
    
    /*
     * Constructors
     */
    
    public InventoryMenu(Being player, ArrayList<Recipe> recipes)
    {
        super();
        
        this.player = player;
        this.recipes = recipes;
        
        update();
        firstItem();
    }
    
    /*
     * Public Methods
     */
    
    public void update()
    {
        inventory = player.getInventory();
    }
    
    public BufferedImage render(Dimension size)
    {
        buffer = new BufferedImage(size.width,
                                   size.height,
                                   BufferedImage.TYPE_INT_ARGB);
        
        renderBorder(size);
        renderItems(size);
        
        return buffer;
    }
    
    public boolean handleInput(int keycode)
    {
        boolean repaint = true;
        
        switch(keycode)
		{
		    case KeyEvent.VK_UP:
		        previousItem();
		        break;
			case KeyEvent.VK_DOWN:
			    nextItem();
			    break;
		    case KeyEvent.VK_LEFT:
		        previousList();
		        break;
			case KeyEvent.VK_RIGHT:
			    nextList();
			    break;
			case KeyEvent.VK_ENTER:
			    handleSelection();
			    break;
			default:
			    repaint = false;
			    break;
		}
		
		return repaint;
    }
    
    /*
     * Protected Methods
     */
    
    protected void handleSelection()
    {
        if (currentColumn == 1)
        {
            if (player.canBrew(recipes.get(currentItem)))
            {
                player.brew(recipes.get(currentItem));
                update();
            }
        }
    }
    
    protected void renderItems(Dimension size)
    {
        // Create grouped list of inventory items
        Map<Entity, Integer> inventoryGroup = new HashMap<Entity, Integer>();
        
        for (Entity entity : inventory)
        {
            Integer count = inventoryGroup.get(entity);
            inventoryGroup.put(entity, (count == null ? 1 : count + 1));
        }
        
        Graphics g = buffer.createGraphics();
        int x = padding, y = padding;
        
        Font.draw("Inventory", g, x, y, FontStyle.Bold);
        y += Font.getLineHeight();
        Font.draw("---------", g, x, y, FontStyle.Bold);
        y += Font.getLineHeight();
        
        int index = 0;
        for (Map.Entry<Entity, Integer> cursor : inventoryGroup.entrySet())
        {
            int style = (currentColumn == 0 && index == currentItem ?
                         FontStyle.Highlight : FontStyle.Normal);
            
            String text = "(" + cursor.getValue().toString() + ") " +
                          cursor.getKey().getDescription();
            Font.draw(text, g, x, y, style);
            
            y += Font.getLineHeight();
            index++;
        }
        
        x = size.width / 2;
        y = padding;
        
        Font.draw("Recipes", g, x, y, FontStyle.Bold);
        y += Font.getLineHeight();
        Font.draw("-------", g, x, y, FontStyle.Bold);
        y += Font.getLineHeight();
        
        for (int i = 0; i < recipes.size(); i++)
        {
            int style = (currentColumn == 1 && i == currentItem ?
                         FontStyle.Highlight : FontStyle.Normal);
            Font.draw(recipes.get(i).getResult().getDescription(), g,
                      x, y, style);
            
            y += Font.getLineHeight();
        }
        
        g.dispose();
    }
    
    protected void nextItem()
    {        
        if (currentColumn == 0 && inventory != null && inventory.size() > 0)
        {
            if (currentItem < inventory.size() - 1)
            {
                currentItem++;
            }
            else
            {
                currentItem = 0;
            }
        }
        else if (currentColumn == 1 && recipes != null && recipes.size() > 0)
        {
            int count = 0;
            
            do
            {
                if (currentItem < recipes.size() - 1)
                {
                    currentItem++;
                }
                else
                {
                    currentItem = 0;
                }
        
                count++;
            }
            while (!player.canBrew(recipes.get(currentItem)) &&
                   count < recipes.size());
        }
    }
    
    protected void previousItem()
    {        
        if (currentColumn == 0 && inventory != null && inventory.size() > 0)
        {
            if (currentItem > 0)
            {
                currentItem--;
            }
            else
            {
                currentItem = inventory.size() - 1;
            }
        }
        else if (currentColumn == 1 && recipes != null && recipes.size() > 0)
        {
            int count = 0;
            
            do
            {
                if (currentItem > 0)
                {
                    currentItem--;
                }
                else
                {
                    currentItem = recipes.size() - 1;
                }
            
                count++;
            }
            while (!player.canBrew(recipes.get(currentItem)) &&
                   count < recipes.size());
        }
    }
    
    protected void firstItem()
    {
        if (currentColumn == 0)
        {
            if (inventory != null && inventory.size() > 0)
            {
                currentItem = inventory.size() - 1;
                nextItem();
            }
        }
        else if (currentColumn == 1)
        {
            if (recipes != null && recipes.size() > 0)
            {
                currentItem = recipes.size() - 1;
                nextItem();
            }
        }
    }
    
    protected void nextList()
    {
        if (currentColumn < 1)
        {
            currentColumn++;
        }
        else
        {
            currentColumn = 0;
        }
        
        firstItem();
    }
    
    protected void previousList()
    {
        if (currentColumn > 0)
        {
            currentColumn--;
        }
        else
        {
            currentColumn = 1;
        }
        
        firstItem();
    }
}
