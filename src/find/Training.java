/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package find;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bytedeco.javacpp.opencv_contrib.FaceRecognizer;
import static org.bytedeco.javacpp.opencv_contrib.createLBPHFaceRecognizer;
import static org.bytedeco.javacpp.opencv_core.CV_32SC1;
import org.bytedeco.javacpp.opencv_core.IplImage;
import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacpp.opencv_core.MatVector;

/**
 *
 * @author Shamricov
 */
public class Training
{
    private static MatVector vector;
    
    public Training(ArrayList<IplImage> list)
        {
            File[] files = new File("C:\\Snimok\\XML\\LBPHF").listFiles();
            int k = files.length;
            files = null;
            File file = new File("C:\\Snimok\\XML\\LBPHF"+(k)+"_vitaly.xml");
            try
                {
                    file.createNewFile();
                } 
            catch (IOException ex)
                {
                    Logger.getLogger(Training.class.getName()).log(Level.SEVERE, null, ex);
                }
            FaceRecognizer recognition = createLBPHFaceRecognizer();
            vector = new MatVector(list.size());
            Mat mat = new Mat(list.size(), 1, CV_32SC1);
            for (int i = 0; i < list.size(); i++)
            {
                train(list.get(i), i);
            }
            System.out.println(list.size());
            recognition.train(vector, mat);
            recognition.save(file.getAbsolutePath());
            System.out.println("Save "+file.getAbsolutePath());
            /* */
            file = null;
            vector = null;
            mat = null;
            recognition = null;
            list = null;
        }
    
    private synchronized static void train(IplImage image, int number)
        {
                        Mat m = new Mat(image);
                        vector.put(number, m);
                        m=null;
        }  
}
