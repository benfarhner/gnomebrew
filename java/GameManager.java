/*

GameManager.java

*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameManager implements KeyListener
{
    /*
     * Properties
     */
    
    private boolean _running;
    
    private World _world;
    private Being _player;
    
    private JFrame _window;
    private JPanel _panels;
    private JPanel _mainMenuPanel;
    private JPanel _gamePanel;
    private JPanel _pauseMenuPanel;
    
    private final static String MAINMENUPANEL = "_mainMenuPanel";
    private final static String GAMEPANEL = "_gamePanel";
    private final static String PAUSEMENUPANEL = "_pauseMenuPanel";
    
    private final static int FPS = 30;
    
    /*
     * Constructors
     */
    
    public GameManager()
    {
        _running = true;
        
        // Load game font, skin, and other resources
        Skin.load();
        Font.load();
        
        _world = new World(257);
        _player = new Being();
        
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int width = 640;
        int height = 480;
        
        _window = new JFrame();
        //_window.addKeyListener(this);
        _window.setLocation((int)((screen.getWidth() - width) / 2),
                            (int)((screen.getHeight() - height) / 2));
        _window.setPreferredSize(new Dimension(width, height));
        _window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        _mainMenuPanel = new Menu();
        _mainMenuPanel.setPreferredSize(new Dimension(width, height));
        
        _gamePanel = new WorldView(_world, _player);
        _gamePanel.setPreferredSize(new Dimension(width, height));
        
        _panels = new JPanel(new CardLayout());
        _panels.add(_mainMenuPanel);
        _panels.add(_gamePanel);
        //_panels.add(_pauseMenuPanel);
        
        _window.add(_panels, BorderLayout.CENTER);
        _window.pack();
        _window.setVisible(true);
        _window.setResizable(false);
        _mainMenuPanel.requestFocus();
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
		    case KeyEvent.VK_ESCAPE:
		        // Show pause menu
		        break;
			case KeyEvent.VK_Q:
			    quit();
			    break;
		}
		
		_window.repaint();
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
