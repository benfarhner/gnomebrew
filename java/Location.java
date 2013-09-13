/*

Location.java

*/

public class Location
{
    private int _row;
    private int _column;
    
    public Location()
    {
        _row = 0;
        _column = 0;
    }
    
    public Location(int row, int column)
    {
        setRow(row);
        setColumn(column);
    }
    
    public int getRow()
    {
        return _row;
    }
    
    public int getColumn()
    {
        return _column;
    }
    
    public int getX()
    {
        return _column;
    }
    
    public int getY()
    {
        return _row;
    }
    
    public void setRow(int row)
    {
        if (row >= 0)
        {
            _row = row;
        }
        else
        {
            _row = 0;
        }
    }
    
    public void setColumn(int column)
    {
        if (column >= 0)
        {
            _column = column;
        }
        else
        {
            _column = 0;
        }
    }
}
