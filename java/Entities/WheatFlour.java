/*

WheatFlour.java

Defines flour ground from wheat.

*/

public class WheatFlour extends Entity
{
    public WheatFlour()
    {
        super();
        type = Entity.Type.WHEATFLOUR;
        description = "Wheat Flour";
        
        attributes.clear();
        
        states.clear();
        EntityState state = new EntityState(0, "Wheat Flour");
        states.put(0, state);
        currentState = states.get(0);
    }
}
