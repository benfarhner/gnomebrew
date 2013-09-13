/*

Gnomebrew.java

*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Gnomebrew extends javax.swing.JFrame
{
    public Gnomebrew()
    {
        //GameManager manager = new GameManager();
        
        // Main loop
        //while (manager.update());
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        Being a = new Being();
        JLabel test = new JLabel(a.getName());
        this.getContentPane().add(test);
        this.pack();
        this.setVisible(true);
    }
    
    public static void main(String args[])
    {
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new Gnomebrew();
            }
        });
    }
}
