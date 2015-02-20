/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visual;

import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;

/**
 *
 * @author Shamricov
 */
public class Frame extends JFrame
{
    
    
    private static JLabel IN;
    private static JLabel GRAY;
    private static JLabel FACE;
    private static JLabel OUT;
    
    private static Pannel pannel;
    private static Scroll scroll;
    
    public Frame(String name) throws HeadlessException
        {
            setName(name);
            setResizable(false);
            setUndecorated(true);
            setExtendedState(JFrame.MAXIMIZED_BOTH);
            setLayout(null);
            setLocationRelativeTo(null);
            setBackground(new Color(0, 0, 100, 100));
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            addWindowListener
            (
                    new WindowAdapter()
                        {

                            @Override
                            public void windowClosing(WindowEvent e)
                            {
                                pannel.exit();
                                super.windowClosing(e); //To change body of generated methods, choose Tools | Templates.
                            }

                        }
            );
            java.awt.EventQueue.invokeLater(() ->
            {
                Border border = BorderFactory.createLineBorder(Color.BLACK,1);
                IN = new JLabel();
                IN.setLocation(10, 10);
                IN.setSize(320, 240);
                IN.setBorder(border);
                
                
                GRAY = new JLabel();
                GRAY.setLocation(IN.getX()+IN.getWidth()+1, IN.getY());
                GRAY.setSize(320, 240);
                GRAY.setBorder(border);
                
                FACE = new JLabel();
                FACE.setLocation(IN.getX(), IN.getY()+1+IN.getHeight());
                FACE.setSize(320, 240);
                FACE.setBorder(border);
                
                OUT = new JLabel();
                OUT.setLocation(GRAY.getX(), GRAY.getY()+GRAY.getHeight()+1);
                OUT.setSize(320, 240);
                OUT.setBorder(border);
                
                pannel = new Pannel(getWidth(), getHeight());
                pannel.setVisible(true);
                
                scroll = new Scroll(getWidth());
                scroll.setLocation(IN.getX(), FACE.getY()+1+FACE.getHeight());
                scroll.setVisible(true);
                
                add(IN);
                add(GRAY);
                add(FACE);
                add(OUT);
                add(pannel);
                add(scroll);
                pannel.repaint();
            });
            setVisible(true);
        }
    
    
    
    public static void setIN(ImageIcon in)
        {
//            IN.setIcon(new ImageIcon(in));
            IN.setIcon(in);
        }
    
    public static void setGRAY(ImageIcon in)
        {
            GRAY.setIcon(in);
        }    

    public static void setFace(ImageIcon in)
        {
            FACE.setIcon(in);
        }
    
    public static void setOut(ImageIcon in)
        {
            OUT.setIcon(in);
        }
}
