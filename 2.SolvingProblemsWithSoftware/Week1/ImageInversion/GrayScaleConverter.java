/**
 * Create a gray scale version of an image by setting all color components of each pixel to the same value.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import java.io.*;

public class GrayScaleConverter {
    //I started with the image I wanted (inImage)
    public ImageResource makeGray(ImageResource inImage) {
        //I made a blank image of the same size
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        //for each pixel in outImage
        for(Pixel inPixel : outImage.pixels()){
            //look at the corresponding pixel in inImage
            Pixel temp = inImage.getPixel(inPixel.getX(), inPixel.getY());
            //compute inPixel's red + inPixel's blue + inPixel's green
            //divide that sum by 3 (call it average)

            //set pixel's red to average
            inPixel.setRed(255 - temp.getRed());
            //set pixel's green to average
            inPixel.setGreen(255 - temp.getGreen());
            //set pixel's blue to average
            inPixel.setBlue(255 - temp.getBlue());
        };
        //outImage is your answer
        return outImage;
    }

    public void testGray() {
        ImageResource ir = new ImageResource();
        ImageResource gray = makeGray(ir);
        gray.draw();
    }
    
    public void saveNew() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            ImageResource image = new ImageResource(f);
            ImageResource gray = makeGray(image);
            String fname = image.getFileName();
            String newName = "inverted-" + fname;
            gray.setFileName(newName);
            gray.draw();
            gray.save();
        }
    }
}
