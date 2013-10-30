/*

Wheat.java

Defines Wheat Entity so we can watch grass grow.

*/

public class Wheat extends Entity
{
    /*
     * Enumerations
     */
    
    public enum State
    {
        WHEATBERRIES(0),
        YOUNGWHEAT(1),
        RIPEWHEAT(2),
        DEADWHEAT(3);
        
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
    
    /*
     * Constructors
     */
    
    public Wheat()
    {
        this(State.WHEATBERRIES);
    }
    
    public Wheat(State initialState)
    {
        super();
        type = Entity.Type.WHEAT;
        description = "Wheat";
        
        attributes.clear();
        attributes.add(Entity.Attribute.FETCHABLE);
        attributes.add(Entity.Attribute.CONSUMABLE);
        
        states.clear();
        EntityState state = new EntityState(State.WHEATBERRIES.getID(), "Wheat Berries");
        state.addAttribute(Entity.Attribute.CONSUMABLE);
        states.put(State.WHEATBERRIES.getID(), state);
        
        state = new EntityState(State.YOUNGWHEAT.getID(), "Young Wheat");
        state.addAttribute(Entity.Attribute.CONSUMABLE);
        states.put(State.YOUNGWHEAT.getID(), state);
        
        state = new EntityState(State.RIPEWHEAT.getID(), "Ripe Wheat");
        state.addAttribute(Entity.Attribute.FETCHABLE);
        state.addAttribute(Entity.Attribute.CONSUMABLE);
        states.put(State.RIPEWHEAT.getID(), state);
        
        state = new EntityState(State.DEADWHEAT.getID(), "Dead Wheat");
        state.addAttribute(Entity.Attribute.CONSUMABLE);
        states.put(State.DEADWHEAT.getID(), state);
        
        currentState = states.get(initialState.getID());
    }
    
    /*
     * Public Methods
     */
    
    public void update(long ms)
    {
        super.update(ms);
        
        if (age.getTotalHours() >= 3)
        {
            currentState = states.get(State.DEADWHEAT.getID());
        }
        else if (age.getTotalHours() >= 2)
        {
            currentState = states.get(State.RIPEWHEAT.getID());
        }
        else if (age.getTotalHours() >= 1)
        {
            currentState = states.get(State.YOUNGWHEAT.getID());
        }
        else
        {
            currentState = states.get(State.WHEATBERRIES.getID());
        }
    }
}
