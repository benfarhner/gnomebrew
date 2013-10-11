/*

InventoryMenu.java

Displays player's inventory

*/

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InventoryMenu extends Menu
{
    /*
     * Properties
     */
    
    private Being player;
    private HashMap<Entity, Integer> inventory;
    private ArrayList<Recipe> recipes;
    private int currentColumn = 0;
    private int currentItem = -1;
    
    /*
     * Constructors
     */
    
    public InventoryMenu(Being player, ArrayList<Recipe> recipes)
    {
        super();
        renderBackground(Config.getScreenSize(), true);
        
        this.player = player;
        this.recipes = recipes;
        
        groupInventory(this.player.getInventory());
        firstItem();
    }
    
    /*
     * Public Methods
     */
    
    public void update(long ms)
    {
        groupInventory(player.getInventory());
    }
    
    public BufferedImage render(Dimension size)
    {
        super.render(size);
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
		        previousColumn();
		        break;
			case KeyEvent.VK_RIGHT:
			    nextColumn();
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
            }
        }
    }
    
    protected void renderItems(Dimension size)
    {
        Graphics g = buffer.createGraphics();
        int x = padding, y = padding;
        Font.Style style;
        
        if (currentColumn == 0)
        {
            style = Font.Style.HIGHLIGHT;
        }
        else
        {
            style = Font.Style.BOLD;
        }
        
        Font.draw("Inventory", g, x, y, style);
        y += Font.getLineHeight();
        Font.draw("---------", g, x, y, style);
        y += Font.getLineHeight();
        
        int index = 0;
        for (Map.Entry<Entity, Integer> cursor : inventory.entrySet())
        {
            if (currentColumn == 0 && index == currentItem)
            {
                style = Font.Style.HIGHLIGHT;
            }
            else
            {
                style = Font.Style.NORMAL;
            }
            
            String text = "(" + cursor.getValue().toString() + ") " +
                          cursor.getKey().getDescription();
            Font.draw(text, g, x, y, style);
            
            y += Font.getLineHeight();
            index++;
        }
        
        x = size.width / 2;
        y = padding;
        
        if (currentColumn == 1)
        {
            style = Font.Style.HIGHLIGHT;
        }
        else
        {
            style = Font.Style.BOLD;
        }
        
        Font.draw("Recipes", g, x, y, style);
        y += Font.getLineHeight();
        Font.draw("-------", g, x, y, style);
        y += Font.getLineHeight();
        
        for (int i = 0; i < recipes.size(); i++)
        {
            if (currentColumn == 1 && i == currentItem)
            {
                style = Font.Style.HIGHLIGHT;
            }
            else if (player.canBrew(recipes.get(i)))
            {
                style = Font.Style.NORMAL;
            }
            else
            {
                style = Font.Style.DISABLED;
            }
            
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
            
            if (count == recipes.size())
            {
                currentItem = -1;
            }
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
            
            if (count == recipes.size())
            {
                currentItem = -1;
            }
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
    
    protected void nextColumn()
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
    
    protected void previousColumn()
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
    
    protected void groupInventory(ArrayList<Entity> entities)
    {
        inventory = new HashMap<Entity, Integer>();
        
        for (Entity entity : entities)
        {
            Integer count = inventory.get(entity);
            inventory.put(entity, (count == null ? 1 : count + 1));
        }
    }
}
