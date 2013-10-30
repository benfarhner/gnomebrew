/*

PropertyListener.java

Interface for listening for custom property change events.

*/

public interface PropertyListener
{
    // Called when a property's value is changed
    public void handlePropertyChanged(String propertyName);
}
