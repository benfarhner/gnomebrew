/*

Direction.java

Represents an ordinal direction and has the ability to change directions/rotate.

*/

public class Direction
{
    /*
     * Constants
     */
        
    private final static int NORTH = 0;
    private final static int EAST = 1;
    private final static int SOUTH = 2;
    private final static int WEST = 3;
    
    public final static Direction North = new Direction(NORTH);
    public final static Direction East = new Direction(EAST);
    public final static Direction South = new Direction(SOUTH);
    public final static Direction West = new Direction(WEST);
    
    /*
     * Properties
     */
    
    private int direction;
    
    /*
     * Constructors
     */
    
    public Direction()
    {
        direction = NORTH;
    }
    
    public Direction(int direction)
    {
        this.direction = normalize(direction);
    }
    
    public Direction(Direction other)
    {
        direction = other.direction;
    }
    
    /*
     * Public Methods
     */
    
    public void rotateCW()
    {
        direction = normalize(direction + 1);
    }
    
    public void rotateCCW()
    {
        direction = normalize(direction - 1);
    }
    
    public boolean equals(Object other)
    {
        if (other == this)
        {
            return true;
        }
        
        if (!(other instanceof Direction))
        {
            return false;
        }
        
        return direction == ((Direction)other).direction;
    }
    
    public int hashCode()
    {
        return direction;
    }
    
    public String toString()
    {
        return String.format("%d", direction);
    }
    
    /*
     * Private Methods
     */
    
    private int normalize(int direction)
    {
        while (direction < 0)
        {
            direction += 4;
        }
        
        return direction % 4;
    }
}
