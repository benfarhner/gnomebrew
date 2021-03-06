/*

StoneMill.java

*/

public class StoneMill extends Entity
{
    public StoneMill()
    {
        super();
        type = Entity.Type.STONEMILL;
        description = "Stone Mill";
        
        attributes.clear();
        attributes.add(Entity.Attribute.FETCHABLE);
        
        states.clear();
        EntityState state = new EntityState(0, "Stone Mill");
        state.addAttribute(Entity.Attribute.FETCHABLE);
        states.put(0, state);
        currentState = states.get(0);
    }
}
