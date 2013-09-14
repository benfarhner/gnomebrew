/*

Tile.java

*/

import java.util.*;

public class Tile
{
    /*
     * Properties
     */
    
    private ArrayList<Entity> _entities;
    
    /*
     * Constructors
     */
    
    public Tile()
    {
        _entities = new ArrayList<Entity>();
    }
    
    /*
     * Functions
     */
    
    public void pushEntity(Entity entity)
    {
        _entities.add(0, entity);
    }
    
    public void popEntity()
    {
        if (_entities.size() > 0)
        {
            _entities.remove(0);
        }
    }
    
    public Entity getTopEntity()
    {
        if (_entities.size() > 0)
        {
            return _entities.get(0);
        }
        
        return null;
    }
}
