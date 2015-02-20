/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visual;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.concurrent.Callable;
import javax.swing.ImageIcon;
import static org.bytedeco.javacpp.opencv_core.IPL_DEPTH_8U;
import org.bytedeco.javacpp.opencv_core.IplImage;
import static org.bytedeco.javacpp.opencv_core.cvSize;
import static org.bytedeco.javacpp.opencv_imgproc.CV_ADAPTIVE_THRESH_GAUSSIAN_C;
import static org.bytedeco.javacpp.opencv_imgproc.CV_THRESH_BINARY;
import static org.bytedeco.javacpp.opencv_imgproc.cvAdaptiveThreshold;

/**
 *
 * @author Shamricov
 */
public class SetImage implements Callable<ImageIcon>
{
    private final  IplImage in;
    private final  int x;
    private final  int y;
    private final  int w;
    private final  int h;
    private final  int flag;

    public SetImage(IplImage in, int x, int y, int w, int h, int flag)
        {
            this.in = in;
            this.x = x;
            this.y = y;
            this.w = w;
            this.h = h;
            this.flag = flag;
        }

    @Override
    public ImageIcon call() throws Exception
        {
            Image im;
            BufferedImage scaled = null;
            if(flag ==1)
                {
                    im = toGray(in).getBufferedImage().getSubimage(x, y, in.width(), in.height());
                    scaled = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
                    Graphics2D g = scaled.createGraphics();
                            g.drawImage(im, 0, 0, w, h, null);
                            g.dispose();
                            im = null;
                }
            if(flag ==0)
                {
                    im = in.getBufferedImage().getSubimage(x, y, in.width(), in.height());
                    scaled = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
                    Graphics2D g = scaled.createGraphics();
                            g.drawImage(im, 0, 0, w, h, null);
                            g.dispose();
                            im = null;
                }
            if(flag ==2)
                {
                    IplImage image = gaussian(in.clone());
                    im = image.getBufferedImage().getSubimage(x, y, in.width(), in.height());
                    scaled = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
                    Graphics2D g = scaled.createGraphics();
                            g.drawImage(im, 0, 0, w, h, null);
                            g.dispose();
                            im = null;
                }
                return new ImageIcon(scaled);   
        }
    
    private IplImage toGray(IplImage in)
        {
            BufferedImage image = new BufferedImage(in.width(), in.height(), BufferedImage.TYPE_BYTE_GRAY);
            Graphics g = image.getGraphics();
                     g.drawImage(in.getBufferedImage(), 0, 0, null);  
                     g.dispose(); 
            IplImage out = IplImage.createFrom(image);
            return out;
        }
    
    private IplImage gaussian(IplImage in)
        {
           IplImage out1 = IplImage.create(cvSize(in.width(), in.height()), IPL_DEPTH_8U, 1);
           cvAdaptiveThreshold
                                        (
                                                toGray(in), 
                                                out1, 
                                                250, 
                                                CV_ADAPTIVE_THRESH_GAUSSIAN_C, 
                                                CV_THRESH_BINARY, 
                                                7, 
                                                1
                                        );
           return out1;
        }
}
