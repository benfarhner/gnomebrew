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
    private World _world;
    private Being _player;
    
    /*
     * Constructors
     */
    
    public WorldView(World world, Being player)
    {
        _world = world;
        _player = player;
    }
    
    /*
     * Public Methods
     */
    
    public void update()
    {
    }
    
    public BufferedImage render(Dimension size)
    {
        view = new BufferedImage(size.width,
                                 size.height,
                                 BufferedImage.TYPE_INT_ARGB);
        
        Graphics2D g = view.createGraphics();
        BufferedImage image = null;
        
        int width = size.width / Skin.getTileSize().width;
        int height = size.height / Skin.getTileSize().height;
        int topLeftX = _player.getLocation().getX() - (width / 2);
        int topLeftY = _player.getLocation().getY() - (height / 2);
        
        if (topLeftX < 0)
        {
            topLeftX = 0;
        }
        else if (topLeftX + width >= _world.getSize())
        {
            topLeftX = _world.getSize() - width - 1;
        }
        
        if (topLeftY < 0)
        {
            topLeftY = 0;
        }
        else if (topLeftY + height >= _world.getSize())
        {
            topLeftY = _world.getSize() - height - 1;
        }
        
        int bottomRightX = topLeftX + width;
        int bottomRightY = topLeftY + height;
        
        for (int x = topLeftX; x < bottomRightX; x++)
        {
            for (int y = topLeftY; y < bottomRightY; y++)
            {
                if (_player.getLocation().getX() == x &&
                    _player.getLocation().getY() == y)
                {
                    image = Skin.getEntityImage(_player.getType());
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
        
        g.dispose();
        
        return view;
    }
    
    /*
     * KeyListener Methods
     */
    
    public void keyTyped(KeyEvent e)
    {
    }
    
    public void keyPressed(KeyEvent e)
    {
        switch(e.getKeyCode())
		{
			case KeyEvent.VK_UP:
				_player.move(Location.Direction.North);
				break;
			case KeyEvent.VK_DOWN:
                if (_player.getLocation().getY() < _world.getSize() - 1)
                {
				    _player.move(Location.Direction.South);
				}
				break;
			case KeyEvent.VK_LEFT:
				_player.move(Location.Direction.West);
				break;
			case KeyEvent.VK_RIGHT:
                if (_player.getLocation().getX() < _world.getSize() - 1)
                {
				    _player.move(Location.Direction.East);
				}
				break;
			case KeyEvent.VK_B:
			    // Brew
			    break;
			case KeyEvent.VK_F:
			    // Fetch item
			    Tile currentTile = _world.getTile(_player.getLocation());
			    Entity entity = currentTile.getTopEntity();
			    
			    if (entity != null && entity.isFetchable())
			    {
                    currentTile.popEntity();
                    _player.addInventory(entity);
			    }
			    break;
		}
    }
    
    public void keyReleased(KeyEvent e)
    {
    }
    
    /*
     * Private Methods
     */
    
    private BufferedImage getTileImage(int x, int y)
    {
        Tile tile = _world.getTile(x, y);
        
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
                g.drawImage(Skin.getEntityImage(entities.get(i).getType()),
                            0, 0,
                            null);
            }
            
            return image;
        }
        
        return null;
    }
}
