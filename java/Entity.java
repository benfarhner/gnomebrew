/*

Entity.java

*/

public class Entity implements Comparable<Entity>
{    
    /*
     * Properties
     */
    
    protected int _type;
    protected Location _location;
    protected String _description;
    //private GameTime _age;
    protected boolean _fetchable;
    
    /*
     * Constructors
     */
    
    public Entity()
    {
        _type = 0;
        _location = new Location(0, 0);
        _description = "";
        //_age = new GameTime;
        _fetchable = false;
    }
    
    // DESTROY
    public Entity(int type)
    {
        this();
        
        _type = type;
    }
    
    public Entity(Entity copy)
    {
        _type = copy._type;
        _location = new Location(copy._location);
        _description = copy._description;
        _fetchable = copy._fetchable;
    }
    
    /*
     * Accessors
     */
    
    public int getType()
    {
        return _type;
    }
    
    public Location getLocation()
    {
        return _location;
    }
    
    public String getDescription()
    {
        return _description;
    }
    
    public boolean isFetchable()
    {
        return _fetchable;
    }
    
    /*
     * Public Methods
     */
    
    public boolean equals(Object other)
    {
        if (other instanceof Entity)
        {
            return _type == ((Entity) other)._type;
        }
        
        return false;
    }
    
    public int hashCode()
    {
        return _type;
    }
    
    public int compareTo(Entity other)
    {
        return _type - other._type;
    }
    
    public void update()
    {
    }
    
    public void move(Location.Direction direction)
    {
        _location.move(direction);
    }
}
