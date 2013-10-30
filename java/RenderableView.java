/*

RenderableView.java

Base class for views that render as images

*/

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.ListIterator;

public class RenderableView implements Updateable, KeyListener
{
    /*
     * Properties
     */
    
    private ArrayList<ViewListener> listeners;
    
    /*
     * Constructors
     */
    
    public RenderableView()
    {
        listeners = new ArrayList<ViewListener>();
    }
    
    /*
     * Public Methods
     */
    
    public void addViewListener(ViewListener listener)
    {
        listeners.add(listener);
    }
    
    public void removeViewListener(ViewListener listener)
    {
        listeners.remove(listener);
    }
    
    public BufferedImage render(Dimension size)
    {
        return new BufferedImage(size.width,
                                 size.height,
                                 BufferedImage.TYPE_INT_ARGB);
    }
    
    /*
     * Updateable Methods
     */
    
    public void update(long ms) { }
    
    /*
     * KeyListener Methods
     */
    
    public void keyTyped(KeyEvent e) { }
    public void keyPressed(KeyEvent e) { }
    public void keyReleased(KeyEvent e) { }
    
    /*
     * Protected Methods
     */
    
    protected void notifyViewChanged()
    {
        ListIterator<ViewListener> iter = listeners.listIterator();
        while (iter.hasNext())
        {
            ViewListener listener = iter.next();
            if (listener != null)
            {
                listener.handleViewChanged();
            }
        }
    }
}
