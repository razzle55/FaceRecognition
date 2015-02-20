/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facerecognition;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import visual.Frame;

/**
 *
 * @author Shamricov
 */
public class FaceRecognition
{
    private static Frame frame;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
       
       JFrame.setDefaultLookAndFeelDecorated(true);
       SwingUtilities.invokeLater(new Runnable()
       {

           @Override
           public void run()
           {
               frame = new Frame("FaceRecognition");
               frame.setIconImage(new ImageIcon(getClass().getResource("/resours/icon.png")).getImage());
               frame.setOpacity(0.95f);
           }
       });
    }
    public static void repaintFrame()
        {
            frame.repaint();
        }
    
}
