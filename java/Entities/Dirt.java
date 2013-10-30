/*

Dirt.java

Defines basic Dirt Entity, used for the ground. Boring!

*/

public class Dirt extends Entity
{
    public Dirt()
    {
        super();
        type = Entity.Type.DIRT;
        description = "Dirt";
        
        attributes.clear();
        
        states.clear();
        EntityState state = new EntityState(0, "Dirt");
        states.put(0, state);
        currentState = states.get(0);
    }
}
