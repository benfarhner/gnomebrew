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
    
    Entity result;
    ArrayList<Entity> ingredients;
    
    /*
     * Constructors
     */
    
    public Recipe()
    {
        result = null;
        ingredients = new ArrayList<Entity>();
    }
    
    public Recipe(Entity result)
    {
        this();
        this.result = result;
    }
    
    public Recipe(Entity result, ArrayList<Entity> ingredients)
    {
        this.result = result;
        
        if (ingredients != null)
        {
            this.ingredients = ingredients;
        }
        else
        {
            this.ingredients = new ArrayList<Entity>();
        }
    }
    
    /*
     * Accessors
     */
    
    public Entity getResult()
    {
        return new Entity(result);
    }
    
    public ArrayList<Entity> getIngredients()
    {
        return ingredients;
    }
    
    /*
     * Mutators
     */
    
    public void addIngredient(Entity ingredient)
    {
        if (ingredient != null)
        {
            ingredients.add(ingredient);
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
            ArrayList<Entity> list = new ArrayList<Entity>(ingredients);
        
            for (int i = 0; i < otherRecipe.ingredients.size(); i++)
            {
                if (!list.remove(otherRecipe.ingredients.get(i)))
                {
                    return false;
                }            
            }
        
            return list.size() == 0;
        }
        
        return false;
    }
}
