/*

EntityState.java

Represents a single state of an Entity with unique properties.

*/

import java.util.HashSet;

public class EntityState
{
    /*
     * Properties
     */
    
    private int id;
    protected String description;
    protected HashSet<Entity.Attribute> attributes;
    
    /*
     * Constructors
     */
    
    public EntityState(int id)
    {
        this(id, "");
    }
    
    public EntityState(int id, String description)
    {
        this.id = id;
        this.description = description;
        attributes = new HashSet<Entity.Attribute>();
    }
    
    public EntityState(EntityState copy)
    {
        id = copy.id;
        description = copy.description;
        attributes = new HashSet<Entity.Attribute>(copy.attributes);
    }
    
    /*
     * Accessors
     */
    
    public int getID()
    {
        return id;
    }
    
    public String getDescription()
    {
        return description;
    }
    
    public boolean hasAttribute(Entity.Attribute attribute)
    {
        return attributes.contains(attribute);
    }
    
    /*
     * Mutators
     */
    
    public void addAttribute(Entity.Attribute attribute)
    {
        attributes.add(attribute);
    }
    
    /*
     * Public Methods
     */
    
    public boolean equals(Object other)
    {
        if (other == this)
        {
            return true;
        }
        
        if (!(other instanceof EntityState))
        {
            return false;
        }
        
        return id == ((EntityState)other).id;
    }
    
    public int hashCode()
    {
        return id;
    }
    
    public int compareTo(EntityState other)
    {
        return id - other.id;
    }
}
