/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package find;

import java.util.concurrent.Callable;
import org.bytedeco.javacpp.opencv_contrib.FaceRecognizer;
import static org.bytedeco.javacpp.opencv_contrib.createLBPHFaceRecognizer;
import org.bytedeco.javacpp.opencv_core.IplImage;
import org.bytedeco.javacpp.opencv_core.Mat;

/**
 *
 * @author Shamricov
 */
public class FF implements Callable<String>
{
    private final IplImage in;
    private final String path;

    
    public FF(IplImage in, String path)
        {
            this.in = in;
            this.path = path;
        }
    

    @Override
    public String call() throws Exception
        {
            FaceRecognizer recognizer = createLBPHFaceRecognizer();
            recognizer.load(path);
            Mat whats = new Mat(in);
            int  answer = recognizer.predict(whats);
            whats.release();
            String name  = null;
            int dot = path.lastIndexOf('.');
            int sep = path.lastIndexOf('_');
            if(answer>200&&answer<800)
                {
                    name = path.substring(sep+1, dot);
                }
            else
                {
                    name = "unknown";
                }

            return name;
        }
    
}
