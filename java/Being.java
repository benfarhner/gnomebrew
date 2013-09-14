/*

Being.java

*/

import java.io.*;
import java.nio.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;

public class Being extends Entity
{
    /*
     * Properties
     */
    
    private String _name;
    private String _sex;
    private String _mood;
    private ArrayList<Entity> _inventory;
    
    private ArrayList<String> firstNames;
    private ArrayList<String> lastNameModifiers;
    private ArrayList<String> lastNameNouns;
    private ArrayList<String> lastNameVerbs;
    
    /*
     * Constructors
     */
    
    public Being()
    {
        loadNames();
        
        _type = 0;
        _fetchable = false;
        
        _name = generateName();
        _sex = generateSex();
        _mood = generateMood();
        _inventory = new ArrayList<Entity>();
    }
    
    public Being(Being copy)
    {
        super(copy);
        
        _name = copy._name;
        _sex = copy._sex;
        _mood = copy._mood;
        _inventory = new ArrayList<Entity>(copy._inventory);
    }
    
    /*
     * Accessors
     */
    
    public String getName()
    {
        return _name;
    }
    
    /*
     * Mutators
     */
    
    public void addInventory(Entity entity)
    {
        if (entity != null)
        {
            _inventory.add(entity);
        }
    }
    
    /*
     * Public Methods
     */
    
    public void update()
    {
    }
    
    // canBrew()
    // Determines if this Being has the items in inventory to brew the recipe
    public boolean canBrew(Recipe recipe)
    {
        ArrayList<Entity> ingredients = recipe.getIngredients();
        
        for (int i = 0; i < _inventory.size(); i++)
        {
            ingredients.remove(_inventory.get(i));  
        }
    
        return ingredients.size() == 0;
    }
    
    // brew()
    // Brews the given recipe and returns the newly created Entity
    public void brew(Recipe recipe)
    {
        _inventory.removeAll(recipe.getIngredients());
        _inventory.add(recipe.getResult());
    }
    
    /*
     * Private Methods
     */
    
    private String generateName()
    {
        Random rand = new Random((new Date()).getTime());
        
        String name = firstNames.get(rand.nextInt(firstNames.size())) + " ";
        
        String lastName = rand.nextInt(2) == 1 ?
            (lastNameNouns.get(rand.nextInt(lastNameNouns.size())) +
             lastNameVerbs.get(rand.nextInt(lastNameVerbs.size()))) :
            (lastNameModifiers.get(rand.nextInt(lastNameModifiers.size())) +
             lastNameNouns.get(rand.nextInt(lastNameNouns.size())));
        
        name += Character.toUpperCase(lastName.charAt(0)) + lastName.substring(1);
        
        return name;
    }
    
    private String generateSex()
    {
        Random rand = new Random((new Date()).getTime());
        return rand.nextInt() == 0 ? "M" : "F";
    }
    
    private String generateMood()
    {
        return "";
    }
    
    private void loadNames()
    {
        Path file = Paths.get("names.txt");
        String delimiter = ",";
        Charset charset = Charset.forName("UTF-8");
        
        try (BufferedReader reader = Files.newBufferedReader(file, charset))
        {
            String line = null;
            
            if ((line = reader.readLine()) != null)
            {
                firstNames = new ArrayList<String>(Arrays.asList(line.split(delimiter)));
            }
            
            if ((line = reader.readLine()) != null)
            {
                lastNameModifiers = new ArrayList<String>(Arrays.asList(line.split(delimiter)));
            }
            
            if ((line = reader.readLine()) != null)
            {
                lastNameNouns = new ArrayList<String>(Arrays.asList(line.split(delimiter)));
            }
            
            if ((line = reader.readLine()) != null)
            {
                lastNameVerbs = new ArrayList<String>(Arrays.asList(line.split(delimiter)));
            }
        }
        catch (IOException e)
        {
            System.err.format("IOException: %s%n", e);
        }
    }
}
