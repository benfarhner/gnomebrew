/*

Input.java

Stores latest input data for polling.

*/

public class Input
{
    /*
     * Properties
     */
    
    private static int key = -1;
    
    /*
     * Public Methods
     */
    
    public static int getKey()
    {
        return key;
    }
    
    public static void setKey(int key)
    {
        Input.key = key;
    }
}
