package mmaioe.com.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by maoito on 12/19/15.
 */
public class Image {
    public BufferedImage image;
    public String fileName;


    public Image(String fileName){
        InputStream is = null;
        this.fileName = fileName;
        try {
            is = new FileInputStream(fileName);
            image = ImageIO.read(is);
        } catch (IOException e) {
            throw new RuntimeException("failed to read "+fileName);
        } finally {
            if (is != null) try { is.close(); } catch (IOException e) {
                throw  new RuntimeException("failed to close FileInputStream");
            }
        }
    }
    public Image(int width, int height){
        this.image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
    }

    public Color getRGBColor(int x,int y){
        return new Color(image.getRGB(x, y));
    }

    public void setColor(int x,int y, Color c){
        image.setRGB(x, y, c.getRGB());
    }

    public int width(){
        return image.getWidth();
    }
    public int height(){
        return image.getHeight();
    }

    public void writeImage(String fileName){
        try{
            ImageIO.write(image, "jpg", new File(fileName));
        }catch(Exception e){
            throw new RuntimeException("failed to write image to "+fileName);
        }
    }
}