package Utils.GrayImage;

import Init.LoadImage;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GrayImageLuminosity {

    public static void createGrayImage() {
        BufferedImage resultImage = new BufferedImage(LoadImage.getImage().getWidth(), LoadImage.getImage().getHeight(), LoadImage.getImage().getType());
        BufferedImage tmpImage = LoadImage.getImage();

        for (int i = 0; i < tmpImage.getWidth(); i++) {
            for (int j = 0; j < tmpImage.getHeight(); j++) {
                Color color = new Color(tmpImage.getRGB(i, j));
                int grayColor = (color.getRed() + color.getGreen() + color.getBlue()) / 3;
                resultImage.setRGB(i, j, new Color(grayColor, grayColor, grayColor).getRGB());
            }
        }
        LoadImage.setImage(resultImage);

    }


}
