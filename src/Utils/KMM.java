package Utils;

import Init.LoadImage;

import java.awt.*;
import java.awt.image.BufferedImage;

public class KMM {

    public static void proccesing() {

        int width = LoadImage.getImage().getWidth();
        int height = LoadImage.getImage().getHeight();

        int imageMatrix[][] = considerPoints1();

        for (int i = 0; i <width ; i++) {
            for (int j = 0; j <height ; j++) {
                if(imageMatrix[i][j]==1&&i!=0&&j!=0){
                    if (considerPoint2(imageMatrix,i-1,j-1)){
                        imageMatrix[i][j]=2;
                    }
                }
            }
        }






    }

    private  static int [][] considerPoints1(){

        int width = LoadImage.getImage().getWidth();
        int height = LoadImage.getImage().getHeight();
        BufferedImage tmpImage = LoadImage.getImage();
        int matrix[][] = new int[width][height];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color color = new Color(tmpImage.getRGB(i, j));
                if (color.getRed() > 125) {
                    matrix[i][j] = 0;
                } else {
                    matrix[i][j] = 1;
                }

            }
        }

        return matrix;

    }


    private static boolean considerPoint2(int matrix[][], int start, int end) {


        for (int i = start; i <= end; i++) {
            for (int j = start; j <= end; j++) {
                if (i != start + 1 && j != start + 1) {
                    if(matrix[i][j]==0){
                        return true;
                    }
                }

            }
        }
        return false;


    }


}
