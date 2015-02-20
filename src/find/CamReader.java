/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package find;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.bytedeco.javacpp.opencv_core.IplImage;
import org.bytedeco.javacv.OpenCVFrameGrabber;

/**
 *
 * @author Shamricov
 */
public class CamReader extends OpenCVFrameGrabber
{

    public CamReader(int deviceNumber)
        {
            super(deviceNumber);
        }

    @Override
    public void start() 
        {
            try
                {
                    super.start(); //To change body of generated methods, choose Tools | Templates.
                } 
            catch (Exception ex)
                {
                    Logger.getLogger(CamReader.class.getName()).log(Level.SEVERE, null, ex);
                }
        }

    /**
     *
     * @return
     */
    @Override
    public IplImage grab() 
        {
            
            IplImage in = null;
            
            try
                {
                     in = super.grab(); //To change body of generated methods, choose Tools | Templates.
                } 
            catch (Exception ex)
                {
                    Logger.getLogger(CamReader.class.getName()).log(Level.SEVERE, null, ex);
                }
            
            if(in==null)
                {
                    return grab();
                }
            
            return in;
        }

    @Override
    public ImageMode getImageMode()
        {
            return super.getImageMode(); //To change body of generated methods, choose Tools | Templates.
        }

    @Override
    public void release() 
        {
            try
                {
                    super.release(); //To change body of generated methods, choose Tools | Templates.
                } 
            catch (Exception ex)
                {
                    Logger.getLogger(CamReader.class.getName()).log(Level.SEVERE, null, ex);
                }
        }

    @Override
    public void restart() 
        {
            try
                {
                    super.restart(); //To change body of generated methods, choose Tools | Templates.
                } 
            catch (Exception ex)
                {
                    Logger.getLogger(CamReader.class.getName()).log(Level.SEVERE, null, ex);
                }
        }

    @Override
    public void stop()
        {
            try
                {
                    super.stop(); //To change body of generated methods, choose Tools | Templates.
                } 
            catch (Exception ex)
                {
                    Logger.getLogger(CamReader.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
    
    
    
    
     
}
