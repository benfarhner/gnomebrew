/*

Location.java

*/

public class Location
{
    /*
     * Enumerations
     */
    
    public enum Direction
    {
        North,
        South,
        East,
        West
    }
    
    /*
     * Properties
     */
    
    private int _x;
    private int _y;
    
    /*
     * Constructors
     */
    
    public Location()
    {
        _x = 0;
        _y = 0;
    }
    
    public Location(int x, int y)
    {
        setX(x);
        setY(y);
    }
    
    public Location(Location copy)
    {
        setX(copy._x);
        setY(copy._y);
    }
    
    /*
     * Accessors
     */
    
    public int getX()
    {
        return _x;
    }
    
    public int getY()
    {
        return _y;
    }
    
    /*
     * Mutators
     */
    
    public void setX(int x)
    {
        if (x >= 0)
        {
            _x = x;
        }
        else
        {
            _x = 0;
        }
    }
    
    public void setY(int y)
    {
        if (y >= 0)
        {
            _y = y;
        }
        else
        {
            _y = 0;
        }
    }
    
    /*
     * Functions
     */
    
    public boolean equals(Location other)
    {
        return (_x == other._x && _y == other._y);
    }
    
    public int hashCode()
    {
        return _x * _y * (_x + _y);
    }
    
    public void move(Direction direction)
    {
        if (direction == Direction.North &&
            _y > 0)
        {
            _y--;
        }
        else if (direction == Direction.South)
        {
            _y++;
        }
        else if (direction == Direction.East)
        {
            _x++;
        }
        else if (direction == Direction.West &&
                 _x > 0)
        {
            _x--;
        }
    }
}
