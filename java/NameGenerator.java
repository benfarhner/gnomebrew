/*

NameGenerator.java

Generates random names for characters.

*/

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class NameGenerator
{
    /*
     * Properties
     */
    
    private static ArrayList<String> maleNames = new ArrayList<String>();
    private static ArrayList<String> femaleNames = new ArrayList<String>();
    private static ArrayList<String> lastNameModifiers = new ArrayList<String>();
    private static ArrayList<String> lastNameNouns = new ArrayList<String>();
    private static ArrayList<String> lastNameVerbs = new ArrayList<String>();
    
    /*
     * Mutators
     */
    
    public static void addMaleName(String name)
    {
        if (name != null && name.length() > 0)
        {
            maleNames.add(name);
        }
    }
    
    public static void addFemaleName(String name)
    {
        if (name != null && name.length() > 0)
        {
            femaleNames.add(name);
        }
    }
    
    public static void addLastNameModifier(String name)
    {
        if (name != null && name.length() > 0)
        {
            lastNameModifiers.add(name);
        }
    }
    
    public static void addLastNameNoun(String name)
    {
        if (name != null && name.length() > 0)
        {
            lastNameNouns.add(name);
        }
    }
    
    public static void addLastNameVerb(String name)
    {
        if (name != null && name.length() > 0)
        {
            lastNameVerbs.add(name);
        }
    }
    
    /*
     * Public Methods
     */
    
    public static String generateName()
    {
        return generateName(Sex.Male);
    }
    
    public static String generateName(int sex)
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
        
        // Generate either noun-verb or modifier-noun last name
        if (rand.nextInt(2) == 1)
        {
            name += lastNameNouns.get(rand.nextInt(lastNameNouns.size())) +
                    lastNameVerbs.get(rand.nextInt(lastNameVerbs.size()));
        }
        else
        {
            name += lastNameModifiers.get(rand.nextInt(lastNameModifiers.size())) +
                    lastNameNouns.get(rand.nextInt(lastNameNouns.size()));
        }
        
        return toStartCase(name);
    }
    
    /*
     * Private Methods
     */
    
    // Converts text into Start Case, capitalizing first letter of each word
    private static String toStartCase(String s)
    {
        String output = "";
        boolean wordStart = true;
        
        for (int i = 0; i < s.length(); i++)
        {
            if (wordStart)
            {
                // Capitalize first letter of word
                output += Character.toUpperCase(s.charAt(i));
                wordStart = false;
            }
            else
            {
                // Lowercase all other letters in a word
                output += Character.toLowerCase(s.charAt(i));
            }
            
            // Find word breaks
            if (s.charAt(i) == ' ')
            {
                wordStart = true;
            }
        }
        
        return output;
    }
}
