package Utils.Binarize;

import Init.LoadImage;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Equalization {


    static public String GRAY = "Gray";
    static public String COLOR = "Color";

    public static void equalize(String typeOfHistogram) {

        int grayTmp[] = new int[256];
        int colorTmp[][]= new int[256][3];

        int width = LoadImage.getImage().getWidth();
        int height = LoadImage.getImage().getHeight();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color color = new Color(LoadImage.getImage().getRGB(i, j));
                if (typeOfHistogram.equals(COLOR)){
                    colorTmp[color.getRed()][0]++;
                    colorTmp[color.getGreen()][1]++;
                    colorTmp[color.getBlue()][2]++;
                }
                else if (typeOfHistogram.equals(GRAY)) {
                    grayTmp[(color.getRed() + color.getGreen() + color.getBlue()) / 3]++;
                }
            }
        }

        double graySum = 0;
        double redSum = 0;
        double greenSum = 0;
        double blueSum = 0;
        double grayD[] = new double[256];
        double colorD[][] = new double[256][3];

        for (int i = 0; i < 256; i++) {
            if (typeOfHistogram.equals(COLOR)){
                redSum+=colorTmp[i][0];
                greenSum+=colorTmp[i][1];
                blueSum+=colorTmp[i][2];
                colorD[i][0] = (redSum ) / (width*1.0 * height*1.0);
                colorD[i][1] = (greenSum ) / (width*1.0 * height*1.0);
                colorD[i][2] = (blueSum ) / (width*1.0 * height*1.0);
            }
            else if (typeOfHistogram.equals(GRAY)){
                graySum += grayTmp[i];
                grayD[i] = (graySum ) / (width*1.0 * height*1.0);
            }
        }

        double minGrayD=2;
        double minRedD=2;
        double minGreenD=2;
        double minBlueD=2;

        for (int i = 0; i <256 ; i++) {
            if (typeOfHistogram.equals(COLOR)){
                if (colorD[i][0] != 0 && colorD[i][0] < minRedD) {
                    minRedD = colorD[i][0];
                }
                if (colorD[i][1]!= 0 && colorD[i][1] < minGreenD) {
                    minGreenD = colorD[i][1];
                }
                if (colorD[i][2] != 0 && colorD[i][2] < minBlueD) {
                    minBlueD = colorD[i][2] ;
                }
            }
            else if (typeOfHistogram.equals(GRAY)) {
                if (grayD[i] != 0 && grayD[i] < minGrayD) {
                    minGrayD = grayD[i];
                }
            }
        }
        
        int lut[][]= new int[256][3];
        for (int i = 0; i < 256; i++) {
            if (typeOfHistogram.equals(COLOR)) {
                lut[i][0] = (int) (((colorD[i][0]- minRedD) / (1 - minRedD)) * 255);
                lut[i][1] = (int) (((colorD[i][1]- minGreenD) / (1 - minGreenD)) * 255);
                lut[i][2] = (int) (((colorD[i][2]- minBlueD) / (1 - minBlueD)) * 255);
            }
            else if (typeOfHistogram.equals(GRAY)) {
                lut[i][0] = (int) (((grayD[i] - minGrayD) / (1 - minGrayD)) * 255);
            }
        }

        BufferedImage tmpImage= new BufferedImage(LoadImage.getImage().getWidth(),LoadImage.getImage().getHeight(),LoadImage.getImage().getType());
        int pixelCountContainer[] = new int[256];
        int out []=new int[width*height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j <height ; j++) {
                Color color = new Color(LoadImage.getImage().getRGB(i, j));
                if (typeOfHistogram.equals(COLOR)){
                    tmpImage.setRGB(i,j ,new Color(lut[color.getRed()][0] ,lut[color.getGreen()][1],lut[color.getBlue()][2]).getRGB());
                }
                if (typeOfHistogram.equals(GRAY)) {
                    out[i * j] = lut[(color.getRed() + color.getGreen() + color.getBlue()) / 3][0];
                    tmpImage.setRGB(i,j,new Color(out[i*j],out[i*j],out[i*j]).getRGB());
                    pixelCountContainer[out[i*j]]++;
                }

            }
        }

        LoadImage.setImage(tmpImage);


    }

//    public static void showEqualizationResult(String typeOfHistogram){
//        includeImageToProject.printLoadedImageOnWindow();
//        if (typeOfHistogram.equals(COLOR)) {
//            Histogram redHistogram = new Histogram(Histogram.RED);
//            Histogram greenHistogram = new Histogram(Histogram.GREEN);
//            Histogram blueHistogram = new Histogram(Histogram.BLUE);
//        }else if (typeOfHistogram.equals(GRAY)){
//            Histogram grayHistogram = new Histogram(Histogram.GRAY);
//        }
//    }
}
