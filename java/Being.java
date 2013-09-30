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
    
    private String name;
    private int sex;
    private String mood;
    private ArrayList<Entity> inventory;
    
    private ArrayList<String> maleNames;
    private ArrayList<String> femaleNames;
    private ArrayList<String> lastNameModifiers;
    private ArrayList<String> lastNameNouns;
    private ArrayList<String> lastNameVerbs;
    
    /*
     * Constructors
     */
    
    public Being()
    {
        loadNames();
        
        type = 0;
        fetchable = false;
        
        sex = generateSex();
        name = generateName();
        mood = generateMood();
        inventory = new ArrayList<Entity>();
    }
    
    public Being(Being copy)
    {
        super(copy);
        
        name = copy.name;
        sex = copy.sex;
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
    
    public int getSex()
    {
        return sex;
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
    }
    
    public void setSex(int sex)
    {
        this.sex = sex;
    }
    
    public void setMood(String mood)
    {
        this.mood = mood;
    }
    
    public void addInventory(Entity entity)
    {
        if (entity != null)
        {
            inventory.add(entity);
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
        ArrayList<Entity> ingredients;
        ingredients = new ArrayList<Entity>(recipe.getIngredients());
        
        for (int i = 0; i < inventory.size(); i++)
        {
            ingredients.remove(inventory.get(i));  
        }
    
        return ingredients.size() == 0;
    }
    
    // brew()
    // Brews the given recipe and returns the newly created Entity
    public void brew(Recipe recipe)
    {
        ArrayList<Entity> ingredients = recipe.getIngredients();
        
        for (int i = 0; i < ingredients.size(); i++)
        {
            inventory.remove(ingredients.get(i));
        }
        
        inventory.add(recipe.getResult());
    }
    
    /*
     * Private Methods
     */
    
    public String generateName()
    {
        String name = "";
        
        Random rand = new Random((new Date()).getTime());
        
        if (sex == Sex.Female)
        {
            name = femaleNames.get(rand.nextInt(femaleNames.size())) + " ";
        }
        else
        {
            name = maleNames.get(rand.nextInt(maleNames.size())) + " ";
        }
        
        String lastName = rand.nextInt(2) == 1 ?
            (lastNameNouns.get(rand.nextInt(lastNameNouns.size())) +
             lastNameVerbs.get(rand.nextInt(lastNameVerbs.size()))) :
            (lastNameModifiers.get(rand.nextInt(lastNameModifiers.size())) +
             lastNameNouns.get(rand.nextInt(lastNameNouns.size())));
        
        name += Character.toUpperCase(lastName.charAt(0)) + lastName.substring(1);
        
        return name;
    }
    
    public int generateSex()
    {
        Random rand = new Random((new Date()).getTime());
        return rand.nextInt(2);
    }
    
    public String generateMood()
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
                maleNames = new ArrayList<String>(Arrays.asList(line.split(delimiter)));
            }
            
            if ((line = reader.readLine()) != null)
            {
                femaleNames = new ArrayList<String>(Arrays.asList(line.split(delimiter)));
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
