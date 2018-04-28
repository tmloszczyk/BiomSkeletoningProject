package Utils.Binarize;

import Init.LoadImage;

import java.awt.*;
import java.awt.image.BufferedImage;

public class NiblackBinarization {
    
    public static void binarize(int sizeWindowNiblack , double thresholdingParameter){

        int width = LoadImage.getImage().getWidth();
        int height= LoadImage.getImage().getHeight();


        BufferedImage tmpImage = new BufferedImage(width,height,LoadImage.getImage().getType());
        for (int i = 0; i <width; i++) {
            for (int j = 0; j <height; j++) {
                double sum=0;
                double avg=0;
                for (int k = i-(sizeWindowNiblack-1)/2; k <i-((sizeWindowNiblack-1)/2)+sizeWindowNiblack; k++) {
                    for (int l = j-(sizeWindowNiblack-1)/2; l <j-((sizeWindowNiblack-1)/2)+sizeWindowNiblack; l++) {
                        if (k>=0&&l>=0&&k<=width-1&&l<=height-1) {
                            Color color = new Color(LoadImage.getImage().getRGB(k, l));
                            sum+=color.getRed();
                        }
                    }
                }
                avg=sum/(sizeWindowNiblack*sizeWindowNiblack*1.0);
                sum=0;
                for (int k = i-(sizeWindowNiblack-1)/2; k <=i-((sizeWindowNiblack-1)/2)+sizeWindowNiblack; k++) {
                    for (int l = j-(sizeWindowNiblack-1)/2; l <=j-((sizeWindowNiblack-1)/2)+sizeWindowNiblack; l++) {
                        if (k>=0&&l>=0&&k<=width-1&&l<=height-1) {
                            Color color = new Color(LoadImage.getImage().getRGB(k, l));
                            sum+=Math.pow(color.getRed()-avg,2.0);
                        }
                    }
                }
                int threshold=(int)(avg+thresholdingParameter*(int)(Math.sqrt(sum/(1.0*(sizeWindowNiblack*sizeWindowNiblack)))));

                Color color=new Color(LoadImage.getImage().getRGB(i,j));
                if (color.getRed()>=threshold){
                    tmpImage.setRGB(i,j,new Color(255,255 ,255).getRGB());
                }else if (color.getRed()<threshold){

                    tmpImage.setRGB(i,j,new Color(0,0,0).getRGB());
                }


            }
        }

        LoadImage.setImage(tmpImage);
        
        
        
    }
    
}
