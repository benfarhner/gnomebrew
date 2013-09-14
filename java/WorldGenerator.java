/*

WorldGenerator.java

Handles the generation of World terrain.

*/

import java.util.*;

public class WorldGenerator
{
    /*
     * Properties
     */
    
    private Random _random = new Random((new Date()).getTime());
    private List<List<Tile>> _tiles;
    private double[][] _values;
    private int _size;
    private double _min = 100.0, _max = -100.0;
    
    /*
     * Constructors
     */
    
    public WorldGenerator()
    {
    }
    
    /*
     * Public Methods
     */
    
    public List<List<Tile>> generate(int size)
    {
        _size = size;
        _tiles = new ArrayList<List<Tile>>();
        _values = new double[_size][_size];
        
        for (int rowIndex = 0; rowIndex < _size; rowIndex++)
        {
            ArrayList<Tile> row = new ArrayList<Tile>();
            
            for (int columnIndex = 0; columnIndex < _size; columnIndex++)
            {
                Tile tile = new Tile();
                tile.pushEntity(new Dirt());
                row.add(tile);
            }
            
            _tiles.add(row);
        }
        
        /* Diamond-Square Algorithm */
        
        double scale = 1.0;
        int featureSize = (_size - 1) / 4;
        
        // Seed initial values
        for (int x = 0; x < _size; x += featureSize)
        {
            for (int y = 0; y < _size; y += featureSize)
            {
                setValue(x, y, randomDouble());
            }
        }
        
        for (int squareSize = featureSize;
            squareSize > 1;
            squareSize /= 2, scale /= 2)
        {
            seedDiamondSquare(squareSize, scale);
        }
        
        populateTiles();
        
        return _tiles;
    }
    
    /*
     * Private Methods
     */
    
    private void populateTiles()
    {
        // Populate tiles with Entities based on height values
        double stoneThreshold = 0.75;
        double wheatThreshold = 0.5;
        
        double range = _max - _min;
        
        for (int x = 0; x < _size; x++)
        {
            for (int y = 0; y < _size; y++)
            {
                double normalized = (getValue(x, y) - _min) / range;
                setValue(x, y, normalized);
                
                if (normalized >= stoneThreshold)
                {
                    _tiles.get(x).get(y).pushEntity(new Stone());
                }
                else if (normalized >= wheatThreshold)
                {
                    _tiles.get(x).get(y).pushEntity(new Wheat());
                }
            }
        }
    }
    
    private void seedDiamondSquare(int size, double scale)
    {
        int halfSize = size / 2;
        
        for (int x = halfSize; x < _size + halfSize; x += size)
        {
            for (int y = halfSize; y < _size + halfSize; y += size)
            {
                seedSquare(x, y, halfSize, randomDouble() * scale);
            }
        }
        
        for (int x = 0; x < _size; x += size)
        {
            for (int y = 0; y < _size; y += size)
            {
                seedDiamond(x + halfSize, y, halfSize, randomDouble() * scale);
                seedDiamond(x, y + halfSize, halfSize, randomDouble() * scale);
            }
        }
    }
    
    private void seedSquare(int x, int y, int halfSize, double offset)
    {
        double tl = getValue(x - halfSize, y - halfSize);
        double tr = getValue(x + halfSize, y - halfSize);
        double bl = getValue(x - halfSize, y + halfSize);
        double br = getValue(x + halfSize, y + halfSize);
        
        double value = ((tl + tr + bl + br) / 4.0) + offset;
        setValue(x, y, value);
        
        _min = Math.min(_min, value);
        _max = Math.max(_max, value);
    }
    
    private void seedDiamond(int x, int y, int halfSize, double offset)
    {
        double t = getValue(x, y - halfSize);
        double b = getValue(x, y + halfSize);
        double l = getValue(x - halfSize, y);
        double r = getValue(x + halfSize, y);
        
        double value = ((t + b + l + r) / 4.0) + offset;
        setValue(x, y, value);
        
        _min = Math.min(_min, value);
        _max = Math.max(_max, value);
    }
     
    private double randomDouble()
    {
        return _random.nextDouble() * 2 - 1;
    }
    
    private double getValue(int x, int y)
    {
        int wrapX = (x + (_size - 1)) % (_size - 1);
        int wrapY = (y + (_size - 1)) % (_size - 1);
        return _values[wrapX][wrapY];
    }
    
    private void setValue(int x, int y, double value)
    {
        int wrapX = (x + _size) % _size;
        int wrapY = (y + _size) % _size;
        _values[wrapX][wrapY] = value;
    }
}
