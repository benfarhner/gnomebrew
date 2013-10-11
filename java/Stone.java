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
    }
}
