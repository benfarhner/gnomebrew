/*

Stone.java

*/

public class Stone extends Entity
{
    public Stone()
    {
        super();
        type = Entity.Type.STONE;
        description = "Stone";
        
        attributes.clear();
        attributes.add(Entity.Attribute.FETCHABLE);
        attributes.add(Entity.Attribute.CONSUMABLE);
        
        states.clear();
        EntityState state = new EntityState(0, "Stone");
        state.addAttribute(Entity.Attribute.FETCHABLE);
        state.addAttribute(Entity.Attribute.CONSUMABLE);
        states.put(0, state);
        currentState = states.get(0);
    }
}
