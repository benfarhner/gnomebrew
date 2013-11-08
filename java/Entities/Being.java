/*

Being.java

*/

import java.util.ArrayList;

public class Being extends Entity
{
    /*
     * Enumerations
     */
    
    public enum Gender
    {
        MALE,
        FEMALE
    }
    
    /*
     * Properties
     */
    
    protected String name;
    protected Gender gender;
    protected String mood;
    protected ArrayList<Entity> inventory;
    
    /*
     * Constructors
     */
    
    public Being()
    {
        super();
        type = Entity.Type.BEING;
        speed = 10;
        attributes.clear();
        
        gender = Gender.MALE;
        name = NameGenerator.generateName(gender);
        mood = generateMood();
        inventory = new ArrayList<Entity>();
    }
    
    public Being(Being copy)
    {
        super(copy);
        
        name = copy.name;
        gender = copy.gender;
        mood = copy.mood;
        inventory = new ArrayList<Entity>(copy.inventory);
    }
    
    /*
     * Accessors
     */
    
    public String getName()
    {
        return name;
    }
    
    public Gender getGender()
    {
        return gender;
    }
    
    public String getMood()
    {
        return mood;
    }
    
    public ArrayList<Entity> getInventory()
    {
        return inventory;
    }
    
    /*
     * Mutators
     */
    
    public void setName(String name)
    {
        this.name = name;
        notifyPropertyChanged("name");
    }
    
    public void setGender(Gender gender)
    {
        this.gender = gender;
        notifyPropertyChanged("gender");
    }
    
    public void setMood(String mood)
    {
        this.mood = mood;
        notifyPropertyChanged("mood");
    }
    
    public void addInventory(Entity entity)
    {
        if (entity != null)
        {
            inventory.add(entity);
            notifyPropertyChanged("inventory");
        }
    }
    
    /*
     * Public Methods
     */
    
    public void update(long ms)
    {
        super.update(ms);
    }
    
    // Determines if this Being has the right items in inventory to brew recipe
    public boolean canBrew(Recipe recipe)
    {
        ArrayList<Entity> ingredients;
        ingredients = new ArrayList<Entity>(recipe.getIngredients());
        
        for (int i = 0; i < inventory.size(); i++)
        {
            ingredients.remove(inventory.get(i));  
        }
        
        return ingredients.size() == 0;
    }
    
    // Brews recipe and adds the newly created Entity to inventory
    // WARNING: Assumes Being canBrew this Recipe!
    public void brew(Recipe recipe)
    {
        ArrayList<Entity> ingredients = recipe.getIngredients();
        
        for (int i = 0; i < ingredients.size(); i++)
        {
            if (ingredients.get(i).hasAttribute(Entity.Attribute.CONSUMABLE))
            {
                inventory.remove(ingredients.get(i));
            }
        }
        
        inventory.add(recipe.getResult());
        notifyPropertyChanged("inventory");
    }
    
    /*
     * Private Methods
     */
    
    public String generateMood()
    {
        return "";
    }
}
