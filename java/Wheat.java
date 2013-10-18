/*

Wheat.java

Defines Wheat Entity so we can watch grass grow.

*/

public class Wheat extends Entity
{
    /*
     * Enumerations
     */
    
    private enum State
    {
        UNKNOWN(-1),
        BEING(0),
        DIRT(1),
        STONE(2),
        WHEAT(3),
        STONEMILL(4);
        
        private int id;
        
        private State(int id)
        {
            this.id = id;
        }
        
        public int getID()
        {
            return id;
        }
    }
    
    public Wheat()
    {
        super();
        type = Entity.Type.WHEAT;
        description = "Wheat";
        
        attributes.clear();
        attributes.add(Entity.Attribute.FETCHABLE);
        attributes.add(Entity.Attribute.CONSUMABLE);
        
        states.clear();
        EntityState state = new EntityState(0, "Wheat Berries");
        state.addAttribute(Entity.Attribute.CONSUMABLE);
        states.add(state);
        state = new EntityState(1, "Young Wheat");
        state.addAttribute(Entity.Attribute.CONSUMABLE);
        states.add(state);
        state = new EntityState(2, "Ripe Wheat");
        state.addAttribute(Entity.Attribute.FETCHABLE);
        state.addAttribute(Entity.Attribute.CONSUMABLE);
        states.add(state);
        state = new EntityState(3, "Dead Wheat");
        state.addAttribute(Entity.Attribute.CONSUMABLE);
        states.add(state);
        stateCursor = states.listIterator();
        currentState = stateCursor.next();
    }
    
    public void update(long ms)
    {
        super.update(ms);
        
        if (age.getTotalHours() >= 3)
        {
            currentState = states.get(3);
        }
        else if (age.getTotalHours() >= 2)
        {
            currentState = states.get(2);
        }
        else if (age.getTotalHours() >= 1)
        {
            currentState = states.get(1);
        }
        else
        {
            currentState = states.get(0);
        }
    }
}
