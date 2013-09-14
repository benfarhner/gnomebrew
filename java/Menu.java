/*

Menu.java

*/

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.*;
import javax.swing.*;

public class Menu extends JPanel implements KeyListener
{
    /*
     * Properties
     */
    
    private ArrayList<String> items;
    private int selected;
    
    /*
     * Constructors
     */
    
    public Menu()
    {
        super();
        
        setOpaque(false);
        addKeyListener(this);
        
        items = new ArrayList<String>();
        items.add("New Game");
        items.add("Load Game");
        items.add("Quit");
        
        selected = 0;
    }
    
    /*
     * Public Methods
     */
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        g.setColor(Skin.getBackgroundColor());
        g.fillRect(0, 0, getPreferredSize().width, getPreferredSize().height);
        
        for (int i = 0; i < items.size(); i++)
        {
            int x = (getPreferredSize().width - items.get(i).length() * 16) / 2;
            Font.draw(items.get(i), g, x, 20 + i * 20, i == selected);
        }
    }
    
    /*
     * KeyListener Methods
     */
    
    public void keyTyped(KeyEvent e)
    {
    }
    
    public void keyPressed(KeyEvent e)
    {
    }
    
    public void keyReleased(KeyEvent e)
    {
        switch(e.getKeyCode())
		{
			case KeyEvent.VK_UP:
			case KeyEvent.VK_LEFT:
				if (selected == 0)
				{
				    selected = items.size() - 1;
				}
				else
				{
				    selected--;
				}
				break;
			case KeyEvent.VK_DOWN:
			case KeyEvent.VK_RIGHT:
                if (selected == items.size() - 1)
				{
				    selected = 0;
				}
				else
				{
				    selected++;
				}
				break;
			case KeyEvent.VK_ESCAPE:
			    break;
		}
		
		repaint();
    }
}
