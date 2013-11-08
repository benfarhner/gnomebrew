/*

RecipeBox.java

Holds list of all recipes.

*/

import java.util.ArrayList;

public class RecipeBox
{
    /*
     * Properties
     */
    
    private static ArrayList<Recipe> recipes = new ArrayList<Recipe>();
    
    /*
     * Accessors
     */
    
    public static void getRecipes()
    {
        return recipes;
    }
    
    /*
     * Mutators
     */
    
    public static void addRecipe(Recipe recipe)
    {
        if (recipe != null)
        {
            recipes.add(recipe);
        }
    }
    
    public static void removeRecipe(Recipe recipe)
    {
        recipes.remove(recipe);
    }
}
