/*

World.java

Represents a game World composed of Tiles containing Entities.

Worlds are square, so only one dimension (size) is necessary.

*/

import java.util.*;

public class World
{
    /*
     * Properties
     */
    
    private int _size;
    private List<List<Tile>> _tiles;
    
    /*
     * Constructors
     */
    
    public World()
    {
        _size = 9;
        
        generateWorld();
    }
    
    public World(int size)
    {
        _size = 9;
        
        if (size > 0)
        {
            _size = size;
        }
        
        generateWorld();
    }
    
    /*
     * Accessors
     */
    
    public int getSize()
    {
        return _size;
    }
    
    public Tile getTile(int x, int y)
    {
        if (x >= 0 && x < _tiles.size() &&
            y >= 0 && y < _tiles.get(x).size())
        {
            return _tiles.get(x).get(y);
        }
        
        return null;
    }
    
    public Tile getTile(Location location)
    {
        return getTile(location.getX(), location.getY());
    }
    
    /*
     * Private Functions
     */
    
    private void generateWorld()
    {        
        WorldGenerator generator = new WorldGenerator();
        _tiles = generator.generate(_size);        
    }
}
