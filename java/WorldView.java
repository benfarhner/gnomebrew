/*

WorldView.java

Handles the rendering of a World as a JPanel.

*/

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;

public class WorldView extends JPanel implements KeyListener
{
    /*
     * Properties
     */
    
    private World _world;
    private Being _player;
    
    /*
     * Constructors
     */
    
    public WorldView(World world, Being player)
    {
        super();
        
        setOpaque(false);
        addKeyListener(this);
        
        _world = world;
        _player = player;
    }
    
    /*
     * Public Methods
     */
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        render(g);
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
		
		repaint();
    }
    
    public void keyReleased(KeyEvent e)
    {
    }
    
    /*
     * Private Methods
     */
    
    private void render(Graphics g)
    {
        int width = getPreferredSize().width / Skin.getTileSize().width;
        int height = getPreferredSize().height / Skin.getTileSize().height;
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
        
        BufferedImage image = null;
        
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
    }
    
    private BufferedImage getTileImage(int x, int y)
    {
        Tile tile = _world.getTile(x, y);
        
        if (tile != null)
        {
            Entity entity = tile.getTopEntity();
        
            if (entity != null)
            {
                BufferedImage image;
                image = Skin.getEntityImage(entity.getType());
            
                if (image != null)
                {
                    return image;
                }
            }
        }
        
        return null;
    }
}
