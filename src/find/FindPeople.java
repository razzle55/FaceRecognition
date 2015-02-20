/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package find;

import java.io.File;
import org.bytedeco.javacpp.opencv_contrib.FaceRecognizer;
import static org.bytedeco.javacpp.opencv_contrib.createLBPHFaceRecognizer;
import org.bytedeco.javacpp.opencv_core.IplImage;
import org.bytedeco.javacpp.opencv_core.Mat;

/**
 *
 * @author Shamricov
 */
public class FindPeople
{
    
    private static String name;
    private final IplImage in;
    private static String AbsolutePath;
    
//    private static int good;
//    private static int bad;
    
//    private static FaceRecognizer recognizer;
    
    public FindPeople(IplImage in, String dir)
    {
        this.in = in;
        
        File [] flist = new File(dir).listFiles();
        int g=0;
            for (File flist1 : flist)
            {
                int good = 0;
                AbsolutePath = flist1.getAbsolutePath();
                FaceRecognizer recognizer = createLBPHFaceRecognizer();
                recognizer.load(AbsolutePath);
                Mat whats = new Mat(in);
                int  answer = recognizer.predict(whats);
                recognizer = null;
                whats = null;
                System.out.println(answer);
                if(answer>220&&answer<800)
                    {
                        good++;
                    } 
              
                g += good;        
            }
            
            if (g>0)
                {
                    name = "Vitaly";
                    System.out.println("Vitaly");
                }
             else
                {
                    name = "unknown";
                    System.out.println("unknown");
                }
    }
    
    public String getName()
    {
        return name;
    }
    
}
