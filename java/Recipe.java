/*

Recipe.java

Defines a Recipe of Entities used to brew a new Entity

*/

import java.util.*;

public class Recipe
{
    /*
     * Properties
     */
    
    Entity _result;
    ArrayList<Entity> _ingredients;
    
    /*
     * Constructors
     */
    
    public Recipe()
    {
        _result = null;
        _ingredients = new ArrayList<Entity>();
    }
    
    public Recipe(Entity result)
    {
        _result = result;
    }
    
    public Recipe(Entity result, ArrayList<Entity> ingredients)
    {
        _result = result;
        
        if (ingredients != null)
        {
            _ingredients = ingredients;
        }
        else
        {
            _ingredients = new ArrayList<Entity>();
        }
    }
    
    /*
     * Accessors
     */
    
    public Entity getResult()
    {
        return new Entity(_result);
    }
    
    public ArrayList<Entity> getIngredients()
    {
        return _ingredients;
    }
    
    /*
     * Mutators
     */
    
    public void addIngredient(Entity ingredient)
    {
        if (ingredient != null)
        {
            _ingredients.add(ingredient);
        }
    }
    
    /*
     * Public Methods
     */
    
    public boolean equals(Object other)
    {
        if (other instanceof Recipe)
        {
            Recipe otherRecipe = (Recipe) other;
            ArrayList<Entity> ingredients = new ArrayList<Entity>(_ingredients);
        
            for (int i = 0; i < otherRecipe._ingredients.size(); i++)
            {
                if (!ingredients.remove(otherRecipe._ingredients.get(i)))
                {
                    return false;
                }            
            }
        
            return ingredients.size() == 0;
        }
        
        return false;
    }
}
