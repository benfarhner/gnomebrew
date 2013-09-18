/*

Tile.java

*/

import java.util.*;

public class Tile
{
    /*
     * Properties
     */
    
    private ArrayList<Entity> entities;
    
    /*
     * Constructors
     */
    
    public Tile()
    {
        entities = new ArrayList<Entity>();
    }
    
    /*
     * Public Methods
     */
    
    public void pushEntity(Entity entity)
    {
        entities.add(0, entity);
    }
    
    public void popEntity()
    {
        if (entities.size() > 0)
        {
            entities.remove(0);
        }
    }
    
    public Entity getTopEntity()
    {
        if (entities.size() > 0)
        {
            return entities.get(0);
        }
        
        return null;
    }
    
    public ArrayList<Entity> getEntities()
    {
        return entities;
    }
}
