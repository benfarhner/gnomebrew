/*

Entity.java

*/

public class Entity
{
    private Location _location;
    private String _description;
    //private GameTime _age;
    
    public Entity()
    {
        _location = new Location(0, 0);
        _description = "";
        //_age = new GameTime;
    }
    
    public void update()
    {
    }
}
