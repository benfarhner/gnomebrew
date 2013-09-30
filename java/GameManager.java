/*

GameManager.java

*/

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class GameManager implements MenuListener, KeyListener
{
    /*
     * Properties
     */
    
    private boolean _running;
    
    private World world;
    private Being player;
    
    private JFrame window;
    private RenderingPanel panel;
    
    private WorldView gameView = null;
    private MenuView mainMenuView;
    private MenuView newGnomeView;
    private MenuView pauseMenuView;
    private MenuView inventoryView;
    
    // Menu item IDs
    private final static int MENU_QUIT = 0;
    private final static int MAINMENU_LOADGAME = 10;
    private final static int MAINMENU_NEWGAME = 11;
    private final static int PAUSEMENU_RESUME = 20;
    private final static int GNOMEDIALOG_STARTGAME = 30;
    
    private final static int FPS = 30;
    
    /*
     * Constructors
     */
    
    public GameManager()
    {
        _running = true;
        
        // Load game skin
        Skin.load();
        
        world = new World(257);
        player = new Being();
        
        int width = 40 * Skin.getTileSize().width;
        int height = 30 * Skin.getTileSize().height;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        
        window = new JFrame();
        window.setLocation((int)((screen.getWidth() - width) / 2),
                            (int)((screen.getHeight() - height) / 2));
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        panel = new RenderingPanel();
        panel.setPreferredSize(new Dimension(width, height));
        panel.addKeyListener(this);
        window.add(panel);
        
        Menu mainMenu = new Menu();
        mainMenu.addMenuListener(this);
        mainMenu.add(new MenuItem(MAINMENU_LOADGAME, "Load Game", false, MenuItem.Centered));
        mainMenu.add(new MenuItem(MAINMENU_NEWGAME, "New Game"));
        mainMenu.add(new MenuItem(MENU_QUIT, "Quit"));
        mainMenuView = new MenuView(mainMenu);
        panel.setView(mainMenuView);
        
        NewGnomeDialog newGnomeDialog = new NewGnomeDialog();
        newGnomeDialog.addMenuListener(this);
        newGnomeView = new MenuView(newGnomeDialog);
        
        Menu pauseMenu = new Menu();
        pauseMenu.addMenuListener(this);
        pauseMenu.add(new MenuItem(PAUSEMENU_RESUME, "Resume"));
        pauseMenu.add(new MenuItem(MENU_QUIT, "Quit"));
        pauseMenuView = new MenuView(pauseMenu);
        
        Menu inventory = new InventoryMenu(player, genRecipes());
        inventory.addMenuListener(this);
        inventoryView = new MenuView(inventory);
        
        window.pack();
        window.setVisible(true);
        window.setResizable(false);
        panel.requestFocus();
    }
    
    public boolean update()
    {
        /*
        try
        {
            Thread.sleep(1000 / FPS);
        }
        catch (InterruptedException e) { }
        */
        return _running;
    }
    
    /*
     * MenuListener Methods
     */
    
    public void handleSelection(MenuItem selection)
    {
        if (selection.getID() == MENU_QUIT)
        {
            quit();
        }
        else if (selection.getID() == MAINMENU_NEWGAME)
        {
            panel.setView(newGnomeView);
        }
        else if (selection.getID() == GNOMEDIALOG_STARTGAME)
        {
            gameView = new WorldView(world, player);
            panel.setView(gameView);
        }
        else if (selection.getID() == PAUSEMENU_RESUME)
        {
            panel.setView(gameView);
        }
    }
    
    /*
     * KeyListener Methods
     */
    
    public void keyTyped(KeyEvent e)
    {
        panel.getView().keyTyped(e);
    }
    
    public void keyPressed(KeyEvent e)
    {
        panel.getView().keyPressed(e);
        panel.render();
    }
    
    public void keyReleased(KeyEvent e)
    {
        if (panel.getView() == gameView)
        {
            switch (e.getKeyCode())
            {
                case KeyEvent.VK_ESCAPE:
                    panel.setView(pauseMenuView);
                    break;
                case KeyEvent.VK_A:
                    panel.setView(inventoryView);
                    break;
            }
        }
        else if (gameView != null &&
                 panel.getView() instanceof MenuView &&
                 e.getKeyCode() == KeyEvent.VK_ESCAPE)
        {
            panel.setView(gameView);
        }
        
        panel.getView().keyReleased(e);
        panel.render();
    }
    
    /*
     * Private Methods
     */
    
    private void quit()
    {
        window.dispose();
        System.exit(0);
    }
    
    private ArrayList<Recipe> genRecipes()
    {
        ArrayList<Recipe> recipes = new ArrayList<Recipe>();
        
        ArrayList<Entity> ingredients = new ArrayList<Entity>();
        ingredients.add(new Stone());
        ingredients.add(new Stone());
        
        recipes.add(new Recipe(new StoneMill(), ingredients));
        
        return recipes;
    }
}
