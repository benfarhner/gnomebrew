/*

RenderingPanel.java

Renders a RenderableView within a JPanel

*/

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;

public class RenderingPanel extends JPanel implements KeyListener, ViewListener, Updateable
{
    /*
     * Properties
     */
    
    private RenderableView view;
    private BufferedImage buffer = null;
    private BufferedImage oldBuffer = null;
    
    /*
     * Constructors
     */
    
    public RenderingPanel()
    {        
        this(null);
    }
    
    public RenderingPanel(RenderableView view)
    {
        super();
        setOpaque(false);
        
        setView(view);
    }
    
    /*
     * Accessors
     */
    
    public RenderableView getView()
    {
        return view;
    }
    
    /*
     * Mutators
     */
    
    public void setView(RenderableView view)
    {
        if (this.view != null)
        {
            this.view.removeViewListener(this);
        }
        
        this.view = view;
        
        if (this.view != null)
        {
            this.view.addViewListener(this);
        }
        
        // Store previous rendered view
        oldBuffer = copyImage(buffer);
        
        // Render new view initially
        renderView();
    }
    
    /*
     * Public Methods
     */
    
    public void update(long ms)
    {
        if (view != null)
        {
            view.update(ms);
        }
    }
    
    public void handleViewChanged()
    {
        renderView();
    }
    
    public void render()
    {
        new Thread()
        {
            public void run()
            {
                paintImmediately(0,
                                 0,
                                 getPreferredSize().width,
                                 getPreferredSize().height);
            }
        }.run();
    }
    
    public void paintComponent(Graphics g)
    {
        g.drawImage(buffer, 0, 0, null);
    }
    
    public void setPreferredSize(Dimension size)
    {
        super.setPreferredSize(size);
        
        buffer = new BufferedImage(getPreferredSize().width,
                                   getPreferredSize().height,
                                   BufferedImage.TYPE_INT_ARGB);
        
        oldBuffer = new BufferedImage(getPreferredSize().width,
                                      getPreferredSize().height,
                                      BufferedImage.TYPE_INT_ARGB);
    }
    
    /*
     * KeyListener Methods
     */
    
    public void keyTyped(KeyEvent e)
    {
        if (this.view != null)
        {
            this.view.keyTyped(e);
        }
    }
    
    public void keyPressed(KeyEvent e)
    {
        if (this.view != null)
        {
            this.view.keyPressed(e);
        }
    }
    
    public void keyReleased(KeyEvent e)
    {
        if (this.view != null)
        {
            this.view.keyReleased(e);
        }
    }
    
    /*
     * Private Methods
     */
    
    private void renderView()
    {
        if (view != null)
        {
            BufferedImage image;
            image = view.render(new Dimension(getPreferredSize().width / 2,
                                              getPreferredSize().height / 2));
            Image scaled;
            scaled = image.getScaledInstance(getPreferredSize().width,
                                             getPreferredSize().height,
                                             Image.SCALE_DEFAULT);
            
            Graphics2D bg = buffer.createGraphics();
            bg.setComposite(AlphaComposite.Clear);
            bg.setBackground(new Color(255, 255, 255, 0));
            bg.fillRect(0, 0,
                        getPreferredSize().width, getPreferredSize().height);
            bg.setComposite(AlphaComposite.SrcOver);
            
            if (oldBuffer != null)
            {
                bg.drawImage(oldBuffer, 0, 0, null);
            }
            
            bg.drawImage(scaled, 0, 0, null);
            bg.dispose();
        }
    }
    
    private BufferedImage copyImage(BufferedImage image)
    {
        if (image == null)
        {
            return null;
        }
        
        ColorModel cm = image.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = image.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }
}
