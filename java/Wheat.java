/*

Wheat.java

Defines Wheat Entity so we can watch grass grow.

*/

public class Wheat extends Entity
{
    public Wheat()
    {
        type = EntityType.Wheat;
        description = "Wheat";
        fetchable = true;
    }
}
