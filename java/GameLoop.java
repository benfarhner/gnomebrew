/*

GameLoop.java

Runs game loop at a set FPS.

*/

public class GameLoop implements Runnable
{
    /*
     * Constants
     */
    
    private final static int TARGETFPS = 30;
    
    /*
     * Properties
     */
    
    private GameManager manager;
    
    /*
     * Constructors
     */
    
    public GameLoop(GameManager manager)
    {
        this.manager = manager;
    }
    
    /*
     * Public Methods
     */
    
    public void run()
    {
        long gameStart = System.currentTimeMillis();
        long currentTime = System.currentTimeMillis();
        long lastTime = currentTime;
        long elapsedTime;
        long fpsTime = 0;
        
        while (manager.isRunning())
        {
            // Calculate elapsed time for updating
            currentTime = System.currentTimeMillis();
            elapsedTime = currentTime - lastTime;
            
            manager.update(elapsedTime);
            
            // Redraw at framerate
            fpsTime += elapsedTime;
            if (fpsTime >= 1000 / TARGETFPS)
            {
                //manager.update(fpsTime);
                manager.render();
                fpsTime = 0;
            }
            
            lastTime = currentTime;
        }
    }
}
