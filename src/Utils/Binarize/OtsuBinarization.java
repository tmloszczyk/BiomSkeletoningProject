package Utils.Binarize;

import Init.LoadImage;

import java.awt.*;
import java.awt.image.BufferedImage;

public class OtsuBinarization {

    public static void binarize() {
        Equalization.equalize(Equalization.GRAY);

        int histogram []= new int[256];

        int width= LoadImage.getImage().getWidth();
        int height= LoadImage.getImage().getHeight();

        for (int i = 0; i <width ; i++) {
            for (int j = 0; j <height ; j++) {
                Color color = new Color(LoadImage.getImage().getRGB(i,j));
                histogram[color.getRed()]++;
            }
        }

        long eta[]=new long[256];

        for (int i = 0; i <256 ; i++) {
            double p0=sumClasses(histogram,0,i,false);
            double p1=sumClasses(histogram,i,256,false);
            double u0=sumClasses(histogram,0,i,true);
            double u1=sumClasses(histogram,i,256,true);
            eta[i]=(long)((p0/(width*height*1.0))*(p1/(width*height*1.0))*Math.pow((u0/p0)-(u1/p1),2.0));
        }

        long maxEtaValue=Integer.MIN_VALUE;
        int etaTreshold=0;
        for (int i = 0; i <256 ; i++) {
            if (eta[i]>maxEtaValue){
                maxEtaValue=eta[i];
                etaTreshold=i;
            }
        }


        BufferedImage tmpImage = new BufferedImage(width,height,LoadImage.getImage().getType());
        for (int i = 0; i <width ; i++) {
            for (int j = 0; j <height; j++) {
                Color color = new Color(LoadImage.getImage().getRGB(i,j));
                if (color.getRed()<etaTreshold){
                    tmpImage.setRGB(i,j, new Color(0,0,0).getRGB());
                }
                else if (color.getRed()>=etaTreshold){
                    tmpImage.setRGB(i,j, Color.WHITE.getRGB() );
                }
            }
        }

        LoadImage.setImage(tmpImage);

    }

    public static int sumClasses(int [] histogram ,int indexStart,int t,boolean avgBrightness){

        int sum=0;
        for (int i = indexStart; i <t; i++) {
            if (avgBrightness) {
                sum += histogram[i]*i;
            }else{
                sum += histogram[i];
            }
        }

        return sum;
    }
}
