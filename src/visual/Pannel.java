/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visual;

import find.CamReader;
import find.FindFace;
import find.Training;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JSlider;
import org.bytedeco.javacpp.opencv_core.IplImage;

/**
 *
 * @author Shamricov
 */
public class Pannel extends JPanel
{
    private final Button start;
    private final Button pause;
    private final Button stop;
    private final Button train;


    private static JSlider slider1;
    private static JSlider slider2;

    private static CamReader camReader;
    private static FindFace face;
    private static Thread tWork;
    private static Thread tTrain;
    private static boolean flag;
    private static boolean fTrain;
    private static int key;
    
    private static String name;

    public Pannel(int w, int h)
        {
            setVisible(true);
            setLayout(null);
            setLocation(w-150, 0);
            setSize(150, h);
            setBackground(new Color(0, 0, 0, 0));
            flag = false;
            fTrain = false;
            key=0;
            this.start = new Button("Начать", 10 , 10);
            this.start.addActionListener((ActionEvent e) ->
                {
                    tWork = new Thread(() ->
                        {
                            flag = true;
                            if(camReader!=null)
                                {
                                    camReader.start();
                                    while (flag!=false)
                                        {
                                            if(key!=100)
                                                {
                                                    setToFrame(camReader.grab());
                                                    key++;
                                                }
                                            else
                                                {
                                                    camReader.release();
                                                    camReader.restart();
                                                    key=0;
                                                }
                                        }
                                }
                            else
                                {
                                    camReader = new CamReader(0);
                                    camReader.start();
                                    while (flag!=false)
                                        {
                                            if(key!=100)
                                                {
                                                    setToFrame(camReader.grab());
                                                    key++;
                                                }
                                            else
                                                {
                                                    camReader.release();
                                                    camReader.restart();
                                                    key=0;
                                                }
                                        }
                                }
                            
                        });
                    tWork.start();
                });
            
            
            this.pause = new Button("Пауза", 10 , 35);
            this.pause.addActionListener(new ActionListener()
                {

                    @Override
                    public void actionPerformed(ActionEvent e)
                        {
                            flag = false;
                            fTrain = false;
                            camReader.start();
                        }
                });
            
            this.stop = new Button("Остановить", 10, 60);
            this.stop.addActionListener(new ActionListener()
                {

                    @Override
                    public void actionPerformed(ActionEvent e)
                        {
                            flag = false;
                            fTrain = false;
                            camReader.stop();
                            camReader.release();
                            if(tWork!=null)
                                {
                                    tWork.stop();
                                }
                            if(tTrain!=null) tTrain.stop();
                        }
                });
            
            this.train = new Button("Train", 10, 85);
            this.train.addActionListener((ActionEvent e) ->
            {
                tTrain = new Thread(() ->
                {
                    camReader = new CamReader(0);
                    camReader.start();
                    ArrayList<IplImage> listFace = new ArrayList<>();
                    fTrain = true;
                    if(camReader!=null)
                                {
                                    camReader.start();
                                    while (fTrain!=false)
                                        {
                                            if(key!=100)
                                                {
                                                    setToFrame(camReader.grab());
                                                    if (listFace.size()<50)
                                                        {
//                                                        listFace.addAll(face.train());
                                                        }
                                                    else
                                                        {
                                                            Training training = new Training(listFace);
                                                            listFace = new ArrayList<>();
                                                            training = null;
                                                        }
                                                    key++;
                                                }
                                            else
                                                {
                                                    camReader.release();
                                                    camReader.restart();
                                                    key=0;
                                                }
                                        }
                                }
                });
               tTrain.start();
            });

            slider1 = new JSlider(JSlider.HORIZONTAL, 0, 100, 10);
            slider1.setPaintLabels(true);
            slider1.setLocation(10, 110);
            slider1.setSize(120, 20);
            
            slider2 = new JSlider(JSlider.HORIZONTAL, 1, 200, 100);
            slider2.setPaintLabels(true);
            slider2.setLocation(10, 135);
            slider2.setSize(120, 20);
            
            add(this.start);
            add(this.pause);
            add(this.stop);
            add(this.train);
            
            add(slider1);
            add(slider2);
        }
    
    
    public void exit()
        {
            flag = false;
            
            if(camReader!=null)
                {
                    camReader.stop();
                    camReader.release();
                }
            if(tWork!=null)
                {
                    tWork.stop();
                }
            
        }
    
    private static void setToFrame(IplImage image)
        {
            face = new FindFace(image);
            ExecutorService pool = Executors.newFixedThreadPool(12);
            List<Future<ImageIcon>> list = new ArrayList<>();
            for (int i = 0; i < 3; i++)
                {
                    list.add(pool.submit(new SetImage(image, 0, 0, 320, 240, i)));
                }
            list.add(pool.submit(new SetImage(face.getImage(), 0, 0, 320, 240, 0)));
            try
                {

                    Frame.setIN(list.get(0).get());
                    Frame.setGRAY(list.get(1).get());
                    Frame.setOut(list.get(2).get());
                    Frame.setFace(list.get(3).get());
                } 
            catch (InterruptedException | ExecutionException  e)
                {
                    Logger.getLogger(Pannel.class.getName()).log(Level.SEVERE, null, e);
                }
            pool.shutdown();
//            list = null;
            System.gc();
        }

}
