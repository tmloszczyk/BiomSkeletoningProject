package Init;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LoadImage {

    static private BufferedImage image;
    static private BufferedImage originalImageContent;
    static private BufferedImage previousImage;


    static private String dynamicImageFilePath;
    static private final String constImageFilePath= "C;/Users/Tomek/Desktop/biom/Fingerprint/01.png";


    public static void constLoadImage(){

        File imageFile;
        try {
            imageFile = new File(constImageFilePath);
            image= ImageIO.read(imageFile);
        } catch (IOException readException) {
            System.out.println(" constLoadImage function error : "+readException);
        }
        image = new BufferedImage(image.getWidth(null),image.getHeight(null),BufferedImage.TYPE_INT_RGB);
        image.createGraphics().drawImage(image,0,0,Color.WHITE,null);

    }


    public static BufferedImage getImage (){
        return image;
    }

    public static void setImage(BufferedImage newImage){
        previousImage=image;
        image= newImage;
    }



}
