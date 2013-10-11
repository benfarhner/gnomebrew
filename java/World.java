/*

World.java

Represents a game World composed of Tiles containing Entities.

Worlds are square, so only one dimension (size) is necessary.

*/

import java.awt.event.*;
import java.util.*;

public class World implements Updateable
{
    /*
     * Properties
     */
    
    private int size;
    private List<List<Tile>> tiles;
    private GameTime clock;
    
    /*
     * Constructors
     */
    
    public World()
    {
        size = 9;
        clock = new GameTime();
        
        generateWorld();
    }
    
    public World(int size)
    {
        this.size = 9;
        clock = new GameTime();
        
        if (size > 0)
        {
            this.size = size;
        }
        
        generateWorld();
    }
    
    /*
     * Accessors
     */
    
    public int getSize()
    {
        return size;
    }
    
    public Tile getTile(int x, int y)
    {
        if (x >= 0 && x < tiles.size() &&
            y >= 0 && y < tiles.get(x).size())
        {
            return tiles.get(x).get(y);
        }
        
        return null;
    }
    
    public Tile getTile(Location location)
    {
        return getTile(location.getX(), location.getY());
    }
    
    public GameTime getClock()
    {
        return clock;
    }
    
    public String getTimestamp()
    {
        return clock.toString();
    }
    
    /*
     * Updateable Methods
     */
    
    public void update(long ms)
    {
        // Calculate in-game time passage
        long gameElapsed = (long)(ms * Config.getClockSpeed());
        
        // Update the game clock
        clock.addMilliseconds(gameElapsed);
        
        // Update tiles
        for (int x = 0; x < size; x++)
        {
            for (int y = 0; y < size; y++)
            {
                tiles.get(x).get(y).update(gameElapsed);
            }
        }
    }
    
    /*
     * Private Functions
     */
    
    private void generateWorld()
    {        
        WorldGenerator generator = new WorldGenerator();
        tiles = generator.generate(size);        
    }
}
