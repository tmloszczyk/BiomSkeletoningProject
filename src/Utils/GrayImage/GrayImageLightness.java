package Utils.GrayImage;

import Init.LoadImage;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GrayImageLightness {

    public static void createGrayImage() {
        BufferedImage resultImage = new BufferedImage(LoadImage.getImage().getWidth(), LoadImage.getImage().getHeight(), LoadImage.getImage().getType());
        BufferedImage tmpImage = LoadImage.getImage();
        int maxRGB = 0;
        int minRGB = 255;

        for (int i = 0; i < tmpImage.getWidth(); i++) {
            for (int j = 0; j < tmpImage.getHeight(); j++) {
                Color color = new Color(tmpImage.getRGB(i, j));
                if (color.getRed() > maxRGB) {
                    maxRGB = color.getRed();
                }
                if (color.getGreen() > maxRGB) {
                    maxRGB = color.getGreen();
                }
                if (color.getBlue() > maxRGB) {
                    maxRGB = color.getBlue();
                }

                if (minRGB > color.getRed()) {
                    minRGB = color.getRed();
                }
                if (minRGB > color.getGreen()) {
                    minRGB = color.getGreen();
                }
                if (minRGB > color.getBlue()) {
                    minRGB = color.getBlue();
                }
            }
        }

        int resultPixelValue = (maxRGB + minRGB) / 2;

        for (int i = 0; i < tmpImage.getWidth(); i++) {
            for (int j = 0; j < tmpImage.getHeight(); j++) {
                tmpImage.setRGB(i, j, new Color(resultPixelValue, resultPixelValue, resultPixelValue).getRGB());
            }
        }
        LoadImage.setImage(resultImage);

    }

}
