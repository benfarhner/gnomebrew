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
     * Private Methods
     */
    
    private void handleSelection()
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
    
    private void renderItems(Dimension size)
    {
        Graphics g = buffer.createGraphics();
        
        Font.draw("Inventory", g, 32, 32, 1);
        Font.draw("---------", g, 32, 42, 1);
        for (int i = 0; i < inventory.size(); i++)
        {
            int style = (currentColumn == 0 && i == currentItem ? 2 : 0);
            Font.draw(inventory.get(i).getDescription(), g,
                      32, 52 + i * 10, style);
        }
        
        Font.draw("Recipes", g, size.width / 2, 32, 1);
        Font.draw("-------", g, size.width / 2, 42, 1);
        for (int i = 0; i < recipes.size(); i++)
        {
            int style = (currentColumn == 1 && i == currentItem ? 2 : 0);
            Font.draw(recipes.get(i).getResult().getDescription(), g,
                      size.width / 2, 52 + i * 10, style);
        }
        
        g.dispose();
    }
    
    private void nextItem()
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
    
    private void previousItem()
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
    
    private void firstItem()
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
    
    private void nextList()
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
    
    private void previousList()
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
