/*

Entity.java

*/

public class Entity implements Comparable<Entity>
{    
    /*
     * Properties
     */
    
    protected int type;
    protected Location location;
    protected String description;
    protected GameTime age;
    protected boolean fetchable;
    protected boolean consumable;
    
    /*
     * Constructors
     */
    
    public Entity()
    {
        type = 0;
        location = new Location(0, 0);
        description = "";
        age = new GameTime();
        fetchable = false;
        consumable = true;
    }
    
    // DESTROY
    public Entity(int type)
    {
        this();
        
        this.type = type;
    }
    
    public Entity(Entity copy)
    {
        type = copy.type;
        location = new Location(copy.location);
        description = copy.description;
        fetchable = copy.fetchable;
    }
    
    /*
     * Accessors
     */
    
    public int getType()
    {
        return type;
    }
    
    public Location getLocation()
    {
        return location;
    }
    
    public String getDescription()
    {
        return description;
    }
    
    public boolean isFetchable()
    {
        return fetchable;
    }
    
    public boolean isConsumable()
    {
        return consumable;
    }
    
    /*
     * Public Methods
     */
    
    public boolean equals(Object other)
    {
        if (other instanceof Entity)
        {
            return type == ((Entity) other).type;
        }
        
        return false;
    }
    
    public int hashCode()
    {
        return type;
    }
    
    public int compareTo(Entity other)
    {
        return type - other.type;
    }
    
    public void update()
    {
    }
    
    public void move(Location.Direction direction)
    {
        location.move(direction);
    }
}
