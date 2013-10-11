/*

Location.java

*/

public class Location
{    
    /*
     * Properties
     */
    
    private double x;
    private double y;
    
    /*
     * Constructors
     */
    
    public Location()
    {
        x = 0;
        y = 0;
    }
    
    public Location(double x, double y)
    {
        setX(x);
        setY(y);
    }
    
    public Location(Location copy)
    {
        setX(copy.x);
        setY(copy.y);
    }
    
    /*
     * Accessors
     */
    
    public int getX()
    {
        return (int)x;
    }
    
    public int getY()
    {
        return (int)y;
    }
    
    public double getRealX()
    {
        return x;
    }
    
    public double getRealY()
    {
        return y;
    }
    
    /*
     * Mutators
     */
    
    public void setX(double x)
    {
        if (x >= 0)
        {
            this.x = x;
        }
        else
        {
            this.x = 0;
        }
    }
    
    public void setY(double y)
    {
        if (y >= 0)
        {
            this.y = y;
        }
        else
        {
            this.y = 0;
        }
    }
    
    /*
     * Functions
     */
    
    public boolean equals(Location other)
    {
        return (x == other.x && y == other.y);
    }
    
    public int hashCode()
    {
        return (int)(x * y * (x + y));
    }
    
    public void move(Direction direction)
    {
        move(direction, 1);
    }
    
    public void move(Direction direction, double distance)
    {
        if (direction.equals(Direction.North) &&
            y >= distance)
        {
            y -= distance;
        }
        else if (direction.equals(Direction.South))
        {
            y += distance;
        }
        else if (direction.equals(Direction.East))
        {
            x += distance;
        }
        else if (direction.equals(Direction.West) &&
                 x >= distance)
        {
            x -= distance;
        }
    }
}
