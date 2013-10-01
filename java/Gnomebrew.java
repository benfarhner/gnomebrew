/*

Gnomebrew.java

*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Gnomebrew extends javax.swing.JFrame
{
    public static void main(String args[])
    {        
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                // Load configuration first
                Config.load();
                
                GameManager manager = new GameManager();
            }
        });
    }
}
