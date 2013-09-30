/*

FontStyle.java

Defines font styles.

*/

public class FontStyle
{
    /*
     * Constants
     */
    
    public final static int Normal = 0;
    public final static int Bold = 1;
    public final static int Highlight = 2;
    public final static int Disabled = 3;
    
    /*
     * Public Methods
     */
    
    public static boolean isValid(int style)
    {
        return (style >= 0 && style <= 3);
    }
}
