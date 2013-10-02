/*

Dirt.java

Defines basic Dirt Entity, used for the ground. Boring!

*/

public class Dirt extends Entity
{
    public Dirt()
    {
        type = EntityType.Dirt;
        fetchable = false;
        consumable = true;
    }
}
