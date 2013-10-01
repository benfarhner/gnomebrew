/*

NewGnomeDialog.java

Dialog allowing user to define new Gnome properties.

*/

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

public class NewGnomeDialog extends Menu implements MenuListener
{
    /*
     * Constants
     */
    
    private final static int MENU_SEX = 0;
    private final static int MENU_NAME = 1;
    private final static int MENU_GENERATE = 2;
    private final static int MENU_STARTGAME = 30;
    
    /*
     * Properties
     */
    
    private Being gnome;
    private SwitchableMenuItem sex;
    private EditableMenuItem name;
    
    /*
     * Constructors
     */
    
    public NewGnomeDialog()
    {
        gnome = new Being();
        
        sex = new SwitchableMenuItem(MENU_SEX,
                                     "Sex: ",
                                     true,
                                     MenuItem.LeftAligned);
        sex.add(new MenuItem(Sex.Male, "Male"));
        sex.add(new MenuItem(Sex.Female, "Female"));
        sex.addMenuListener(this);
        add(sex);
        
        name = new EditableMenuItem(MENU_NAME,
                                    "Name: ",
                                    gnome.getName(),
                                    true,
                                    MenuItem.LeftAligned);
        add(name);
        
        add(new MenuItem(MENU_GENERATE,
                         "Generate Random Name",
                         true,
                         MenuItem.LeftAligned));
        
        MenuItem startGame = new MenuItem(MENU_STARTGAME,
                                          "Start Game",
                                          true,
                                          MenuItem.Centered);
        startGame.setPadding(new Dimension(0, Font.getLineHeight() * 2));
        add(startGame);
    }
    
    /*
     * Public Methods
     */
    
    public BufferedImage render(Dimension size)
    {
        buffer = new BufferedImage(size.width,
                                   size.height,
                                   BufferedImage.TYPE_INT_ARGB);
        
        renderBackground(size);
        renderUI(size);
        
        return buffer;
    }
    
    public boolean handleInput(int keycode)
    {
        boolean repaint = true;
        
        switch(keycode)
		{
		    case KeyEvent.VK_UP:
		        previousItem();
		        break;
			case KeyEvent.VK_DOWN:
			    nextItem();
			    break;
			case KeyEvent.VK_ENTER:
			    select();
			    break;
			default:
                items.get(iterator.nextIndex()).handleInput(keycode);
			    break;
		}
		
		return repaint;
    }
    
    /*
     * MenuListener Methods
     */
    
    public void handleSelection(MenuItem item)
    {
        gnome.setSex(item.getID());
    }
    
    /*
     * Protected Methods
     */
    
    protected void select()
    {
        MenuItem selection = items.get(iterator.nextIndex());
        
        switch (selection.getID())
        {
            case MENU_GENERATE:
                gnome.setName(NameGenerator.generateName(gnome.getSex()));
                name.setText(gnome.getName());
                break;
            case MENU_STARTGAME:
                super.select();
                break;
        }
    }
    
    /*
     * Private Methods
     */
    
    private void renderUI(Dimension size)
    {
        Graphics g = buffer.createGraphics();
        int x = padding, y = padding;
        int lh = Font.getLineHeight();
        
        Font.draw("New Gnome", g, x, y, FontStyle.Bold);
        Font.draw("---------", g, x, y += lh, FontStyle.Bold);
        
        renderItems(size, new Dimension(0, y - padding + lh));
        
        g.dispose();
    }
}
