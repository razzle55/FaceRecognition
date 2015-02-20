/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visual;

import facerecognition.FaceRecognition;
import find.FF;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import org.bytedeco.javacpp.opencv_core.IplImage;

/**
 *
 * @author Shamricov
 */
public final class Scroll extends JPanel
{
    private final int SIZE = 100;
    
    private static JLabel f1;
    private static JLabel f2;
    private static JLabel f3;
    private static JLabel f4;
    private static JLabel f5;
    private static JLabel f6;
    private static JLabel f7;
    private static JLabel f8;
    private static JLabel f9;
    private static JLabel f10;
    
    
    private static JLabel n1;
    private static JLabel n2;
    private static JLabel n3;
    private static JLabel n4;
    private static JLabel n5;
    private static JLabel n6;
    private static JLabel n7;
    private static JLabel n8;
    private static JLabel n9;
    private static JLabel n10;
    
            
    public Scroll(int width)
        {
            setLayout(null);
            setSize(width, 121);
            setBackground(new Color(0, 0, 0, 0));
            Border border = BorderFactory.createLineBorder(Color.BLACK,1);

            f1 = new JLabel();
            f1.setLocation(0, 0);
            f1.setSize(SIZE, SIZE);
            f1.setBorder(border);

            f2 = new JLabel();
            f2.setLocation(f1.getX()+f1.getWidth()+1, 0);
            f2.setSize(SIZE, SIZE);
            f2.setBorder(border);

            f3 = new JLabel();
            f3.setLocation(f2.getX()+f2.getWidth()+1, 0);
            f3.setSize(SIZE, SIZE);
            f3.setBorder(border);

            f4 = new JLabel();
            f4.setLocation(f3.getX()+f3.getWidth()+1, 0);
            f4.setSize(SIZE, SIZE);
            f4.setBorder(border);

            f5 = new JLabel();
            f5.setLocation(f4.getX()+f4.getWidth()+1, 0);
            f5.setSize(SIZE, SIZE);
            f5.setBorder(border);

            f6 = new JLabel();
            f6.setLocation(f5.getX()+f5.getWidth()+1, 0);
            f6.setSize(SIZE, SIZE);
            f6.setBorder(border);

            f7 = new JLabel();
            f7.setLocation(f6.getX()+f6.getWidth()+1, 0);
            f7.setSize(SIZE, SIZE);
            f7.setBorder(border);

            f8 = new JLabel();
            f8.setLocation(f7.getX()+f7.getWidth()+1, 0);
            f8.setSize(SIZE, SIZE);
            f8.setBorder(border);

            f9 = new JLabel();
            f9.setLocation(f8.getX()+f8.getWidth()+1, 0);
            f9.setSize(SIZE, SIZE);
            f9.setBorder(border);

            f10 = new JLabel();
            f10.setLocation(f9.getX()+f9.getWidth()+1, 0);
            f10.setSize(SIZE, SIZE);
            f10.setBorder(border);


            n1 = new JLabel();
            n1.setLocation(f1.getX(), 101);
            n1.setSize(f1.getWidth(), 20);
            n1.setBorder(border);

            n2 = new JLabel();
            n2.setLocation(f2.getX(), 101);
            n2.setSize(f2.getWidth(), 20);
            n2.setBorder(border);

            n3 = new JLabel();
            n3.setLocation(f3.getX(), 101);
            n3.setSize(f3.getWidth(), 20);
            n3.setBorder(border);

            n4 = new JLabel();
            n4.setLocation(f4.getX(), 101);
            n4.setSize(f4.getWidth(), 20);
            n4.setBorder(border);

            n5 = new JLabel();
            n5.setLocation(f5.getX(), 101);
            n5.setSize(f5.getWidth(), 20);
            n5.setBorder(border);

            n6 = new JLabel();
            n6.setLocation(f6.getX(), 101);
            n6.setSize(f6.getWidth(), 20);
            n6.setBorder(border);

            n7 = new JLabel();
            n7.setLocation(f7.getX(), 101);
            n7.setSize(f7.getWidth(), 20);
            n7.setBorder(border);

            n8 = new JLabel();
            n8.setLocation(f8.getX(), 101);
            n8.setSize(f8.getWidth(), 20);
            n8.setBorder(border);

            n9 = new JLabel();
            n9.setLocation(f9.getX(), 101);
            n9.setSize(f9.getWidth(), 20);
            n9.setBorder(border);

            n10 = new JLabel();
            n10.setLocation(f10.getX(), 101);
            n10.setSize(f10.getWidth(), 20);
            n10.setBorder(border);

                    add(f1);
                    add(f2);
                    add(f3);
                    add(f4);
                    add(f5);
                    add(f6);
                    add(f7);
                    add(f8);
                    add(f9);
                    add(f10);

                    add(n1);
                    add(n2);
                    add(n3);
                    add(n4);
                    add(n5);
                    add(n6);
                    add(n7);
                    add(n8);
                    add(n9);
                    add(n10);
            setVisible(true);
        }

    
    public static void setIcon(BufferedImage in)
        {
            if(f9!=null)
                {
                    f10.setIcon(f9.getIcon());
                    n10.setText(n9.getText());
                }
            if(f8!=null)
                {
                    f9.setIcon(f8.getIcon());
                    n9.setText(n8.getText());
                }
            if(f7!=null)
                {
                    f8.setIcon(f7.getIcon());
                    n8.setText(n7.getText());
                }
            if(f6!=null)
                {
                    f7.setIcon(f6.getIcon());
                    n7.setText(n6.getText());
                }
            if(f5!=null)
                {
                    f6.setIcon(f5.getIcon());
                    n6.setText(n5.getText());
                }
            if(f4!=null)
                {
                    f5.setIcon(f4.getIcon());
                    n5.setText(n4.getText());
                }
            if(f3!=null)
                {
                    f4.setIcon(f3.getIcon());
                    n4.setText(n3.getText());
                }
            if(f2!=null)
                {
                    f3.setIcon(f2.getIcon());
                    n3.setText(n2.getText());
                }
            if(f1!=null)
                {
                    f2.setIcon(f1.getIcon());
                    n2.setText(n1.getText());
                    n1.repaint();
                }
            f1.setIcon(new ImageIcon(in));
            BufferedImage image = toGray(in);
            IplImage w = IplImage.createFrom(image);
            f1.setText(".....?");
            FaceRecognition.repaintFrame();
            n1.setText(getPeopleName(w));
           FaceRecognition.repaintFrame();
        }
    
    private synchronized static String getPeopleName(IplImage in)
        {
            ExecutorService pool = Executors.newFixedThreadPool(12);
            List<Future<String>> list = new ArrayList<>();
            File [] flist = new File("C:\\Snimok\\XML\\").listFiles();
            for (File flist1 : flist)
            {
                list.add(pool.submit(new FF(in, flist1.getAbsolutePath())));
            }
            int good = 0;
            String name = "unknown";
            for (int i = 0; i < list.size(); i++)
            {
                try
                    {
                        String s = list.get(i).get();
                        if(!s.equals("unknown"))
                        {
                            name = s;
                         good++;   
                        }
                    } 
                catch (InterruptedException | ExecutionException ex)
                    {
                        Logger.getLogger(Scroll.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
            list.clear();
            pool.shutdown();
            return name;
        }
    
    private static BufferedImage toGray(BufferedImage in)
        {
            BufferedImage image = new BufferedImage(in.getWidth(), in.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
            Graphics g = image.getGraphics();
                    g.drawImage(image, 0, 0, null);  
                    g.dispose(); 
            return image;
        }

}
