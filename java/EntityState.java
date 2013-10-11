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
}
