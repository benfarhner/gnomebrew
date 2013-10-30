/*

WorldView.java

Handles the rendering of a World as a JPanel.

*/

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import java.util.*;

public class WorldView extends RenderableView
{
    /*
     * Properties
     */
    
    private BufferedImage view;
    private World world;
    private Being player;
    
    /*
     * Constructors
     */
    
    public WorldView(World world, Being player)
    {
        this.world = world;
        this.player = player;
    }
    
    /*
     * Public Methods
     */
    
    public BufferedImage render(Dimension size)
    {
        view = new BufferedImage(size.width,
                                 size.height,
                                 BufferedImage.TYPE_INT_ARGB);
        
        Graphics2D g = view.createGraphics();
        BufferedImage image = null;
        
        int width = size.width / Skin.getTileSize().width;
        int height = size.height / Skin.getTileSize().height;
        int topLeftX = player.getLocation().getX() - (width / 2);
        int topLeftY = player.getLocation().getY() - (height / 2);
        
        if (topLeftX < 0)
        {
            topLeftX = 0;
        }
        else if (topLeftX + width >= world.getSize())
        {
            topLeftX = world.getSize() - width - 1;
        }
        
        if (topLeftY < 0)
        {
            topLeftY = 0;
        }
        else if (topLeftY + height >= world.getSize())
        {
            topLeftY = world.getSize() - height - 1;
        }
        
        int bottomRightX = topLeftX + width;
        int bottomRightY = topLeftY + height;
        
        for (int x = topLeftX; x < bottomRightX; x++)
        {
            for (int y = topLeftY; y < bottomRightY; y++)
            {
                if (player.getLocation().getX() == x &&
                    player.getLocation().getY() == y)
                {
                    image = Skin.getEntityImage(player.getType().getID(),
                                                player.getState().getID());
                }
                else
                {
                    image = getTileImage(x, y);
                }
                
                if (image != null)
                {
                    g.drawImage(image,
                                (x - topLeftX) * Skin.getTileSize().width,
                                (y - topLeftY) * Skin.getTileSize().height,
                                null);
                }
            }
        }
        
        Font.drawString(world.getTimestamp(), g, 0, size.height - Skin.getTileSize().height);
        
        g.dispose();
        
        return view;
    }
    
    public void update(long ms)
    {
        world.update(ms);
        player.update(ms);
        
        // Poll input
        double distance = player.getSpeed() * ms / 1000;
        int key = Input.getKey();
        
        switch(key)
		{
			case KeyEvent.VK_UP:
				player.move(Direction.North, distance);
				break;
			case KeyEvent.VK_DOWN:
                if (player.getLocation().getY() < world.getSize() - 1)
                {
				    player.move(Direction.South, distance);
				}
				break;
			case KeyEvent.VK_LEFT:
				player.move(Direction.West, distance);
				break;
			case KeyEvent.VK_RIGHT:
                if (player.getLocation().getX() < world.getSize() - 1)
                {
				    player.move(Direction.East, distance);
				}
				break;
			case KeyEvent.VK_F:
			    // Fetch item
			    Tile currentTile = world.getTile(player.getLocation());
			    Entity entity = currentTile.getTopEntity();
			    
			    if (entity != null &&
                    entity.hasAttribute(Entity.Attribute.FETCHABLE))
			    {
                    currentTile.popEntity();
                    player.addInventory(entity);
			    }
			    break;
		}
        
        notifyViewChanged();
    }
    
    /*
     * KeyListener Methods
     */
    
    public void keyTyped(KeyEvent e)
    {
    }
    
    public void keyPressed(KeyEvent e)
    {
    }
    
    public void keyReleased(KeyEvent e)
    {
    }
    
    /*
     * Private Methods
     */
    
    private BufferedImage getTileImage(int x, int y)
    {
        Tile tile = world.getTile(x, y);
        
        if (tile != null)
        {
            BufferedImage image;
            image = new BufferedImage(Skin.getTileSize().width,
                                      Skin.getTileSize().height,
                                      BufferedImage.TYPE_INT_ARGB);
            
            Graphics2D g = image.createGraphics();
            g.setBackground(Skin.getBackgroundColor());
            g.fillRect(0, 0,
                       Skin.getTileSize().width, Skin.getTileSize().height);
            g.setComposite(AlphaComposite.SrcOver);
            
            ArrayList<Entity> entities = tile.getEntities();
            
            for (int i = entities.size() - 1; i >= 0; i--)
            {
                Entity entity = entities.get(i);
                BufferedImage entityImage;
                entityImage = Skin.getEntityImage(entity.getType().getID(),
                                                  entity.getState().getID());
                g.drawImage(entityImage,
                            0, 0,
                            null);
            }
            
            return image;
        }
        
        return null;
    }
}
