/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package find;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import static org.bytedeco.javacpp.helper.opencv_objdetect.cvHaarDetectObjects;
import org.bytedeco.javacpp.opencv_core.CvMemStorage;
import org.bytedeco.javacpp.opencv_core.CvRect;
import org.bytedeco.javacpp.opencv_core.CvScalar;
import org.bytedeco.javacpp.opencv_core.CvSeq;
import org.bytedeco.javacpp.opencv_core.IplImage;
import static org.bytedeco.javacpp.opencv_core.cvGetSeqElem;
import static org.bytedeco.javacpp.opencv_core.cvLoad;
import static org.bytedeco.javacpp.opencv_core.cvPoint;
import static org.bytedeco.javacpp.opencv_core.cvRectangle;
import org.bytedeco.javacpp.opencv_objdetect.CvHaarClassifierCascade;
import visual.Scroll;

/**
 *
 * @author Shamricov
 */
public final class FindFace
{
    private final String path;
    private static IplImage outRBG;
    private static IplImage out;
    private final int numberFace;
    
    /**
     * 
     * @param image входное изображение.
     */
    public FindFace(IplImage image)
        {
            this.path = "C:\\Snimok\\haarcascade_frontalface_alt.xml";
            this.outRBG = image.clone();
            out = image.clone();
            IplImage gray = toGray(outRBG);
            CvSeq faces = haarCascsdeDetector(gray, path);
            this.numberFace = faces.total();
            if(numberFace>0&&numberFace<60)
                {
                    for (int i = 0; i < numberFace; i++)
                        {
                            CvRect face;
                            face = new CvRect(cvGetSeqElem(faces, i));
                            int x = face.x();
                            int y = face.y();
                            int w = face.width();
                            int h = face.height();
                            if((h>1)&&(w>1))
                                {
                                    Scroll.setIcon(getimage(outRBG, x, y, w, h));
                                }
                            
                            cvRectangle
                                        (
                                            out,                                          //Изображение
                                            cvPoint(x, y),                  //Верхний левый угол
                                            cvPoint(                                        
                                                    x + w,              //Нижний правый угол
                                                    y + h              //
                                                    ), 
                                            CvScalar.GREEN,                     //Цвет
                                            4,                                              //Толщина
                                            8,                                              //Канал
                                            0                                               //Сглаживание
                                        );
                        }
                }
        }
    
    /**
     * 
     * @param image входное изображение в градациях серого.
     * @param path путь к файлу наборов векторов Хаара.
     * @return массив координат найденых лиц.
     */
    private  CvSeq haarCascsdeDetector(IplImage image, String path)
        {
            CvMemStorage storage = CvMemStorage.create();
            CvHaarClassifierCascade cascade = new CvHaarClassifierCascade(cvLoad(path));
            CvSeq list = cvHaarDetectObjects
                        (
                            image,
                            cascade,
                            storage, 
                            1.9, 
                            1, 
                            100
                        );
            storage.release();
            cascade.release();
            return list;
        }
    
    /**
     * 
     * @param in входное изображение.
     * @return обезцвеченное входное изображение.
     */
    private IplImage toGray(IplImage in)
        {
            BufferedImage image = new BufferedImage(in.width(), in.height(), BufferedImage.TYPE_BYTE_GRAY);
            Graphics g = image.getGraphics();
                    g.drawImage(in.getBufferedImage(), 0, 0, null);  
                    g.dispose(); 
            return IplImage.createFrom(image);
        }
    
    /**
     * 
     * @return изображение с выделенными в рамках лицами.
     */
    public IplImage getImage()
        {
            return out;
        }
    
    private static BufferedImage getimage(IplImage in, int x, int y, int w, int h)
        {
            int xx = x+w/4;
            int yy = y+h/4;
            int ww = w/2;
            int hh = h*4/5;
            if(ww==0)
                {
                    ww =15;
                }
            if(hh==0)
                {
                    hh=15;
                }
            Image im = in.getBufferedImage().getSubimage(xx, yy, ww, hh);
            BufferedImage scaled = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = scaled.createGraphics();
                        g.drawImage(im, 0, 0, 100, 100, null);
                        g.dispose();
//                        im = null;
            return scaled; 
        }

    @Override
    protected void finalize() throws Throwable
    {
        super.finalize(); //To change body of generated methods, choose Tools | Templates.
    }
    
}
