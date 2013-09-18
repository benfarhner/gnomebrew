/*

GameManager.java

*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameManager implements MenuListener, KeyListener
{
    /*
     * Properties
     */
    
    private boolean _running;
    
    private World _world;
    private Being _player;
    
    private JFrame _window;
    private RenderingPanel panel;
    
    private WorldView gameView;
    private MenuView mainMenuView;
    private MenuView pauseMenuView;
    
    // Menu item IDs
    private final static int MENU_QUIT = 0;
    private final static int MAINMENU_LOADGAME = 10;
    private final static int MAINMENU_NEWGAME = 11;
    private final static int PAUSEMENU_RESUME = 20;
    
    private final static int FPS = 30;
    
    /*
     * Constructors
     */
    
    public GameManager()
    {
        _running = true;
        
        // Load game skin
        Skin.load();
        
        _world = new World(257);
        _player = new Being();
        
        int width = 80 * Skin.getTileSize().width;
        int height = 60 * Skin.getTileSize().height;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        
        _window = new JFrame();
        _window.setLocation((int)((screen.getWidth() - width) / 2),
                            (int)((screen.getHeight() - height) / 2));
        _window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        panel = new RenderingPanel();
        panel.setPreferredSize(new Dimension(width, height));
        panel.addKeyListener(this);
        _window.add(panel);
        
        Menu mainMenu = new Menu();
        mainMenu.addMenuListener(this);
        mainMenu.add(new MenuItem(MAINMENU_LOADGAME, "Load Game", false));
        mainMenu.add(new MenuItem(MAINMENU_NEWGAME, "New Game"));
        mainMenu.add(new MenuItem(MENU_QUIT, "Quit"));
        mainMenuView = new MenuView(mainMenu);
        panel.setView(mainMenuView);
        
        Menu pauseMenu = new Menu();
        pauseMenu.addMenuListener(this);
        pauseMenu.add(new MenuItem(PAUSEMENU_RESUME, "Resume"));
        pauseMenu.add(new MenuItem(MENU_QUIT, "Quit"));
        pauseMenuView = new MenuView(pauseMenu);
        
        gameView = new WorldView(_world, _player);
        
        _window.pack();
        _window.setVisible(true);
        _window.setResizable(false);
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
    
    public void keyTyped(KeyEvent e) { }
    
    public void keyPressed(KeyEvent e)
    {
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
            }
        }
        
        panel.render();
    }
    
    /*
     * Private Methods
     */
    
    private void quit()
    {
        _window.dispose();
        System.exit(0);
    }
}
