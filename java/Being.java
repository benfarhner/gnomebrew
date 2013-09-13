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
    private String _name;
    private String _sex;
    private String _mood;
    
    private ArrayList<String> firstNames;
    private ArrayList<String> lastNameModifiers;
    private ArrayList<String> lastNameNouns;
    private ArrayList<String> lastNameVerbs;
    
    public Being()
    {
        loadNames();
        
        _name = generateName();
        _sex = generateSex();
        _mood = generateMood();
    }
    
    public String getName()
    {
        return _name;
    }
    
    public void update()
    {
    }
    
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
        return "";
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
