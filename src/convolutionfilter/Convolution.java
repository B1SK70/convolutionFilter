package convolutionfilter;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Convolution extends BufferedImage {

    BufferedImage original;
    
    public Convolution(BufferedImage original) {
        super(original.getWidth(), original.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
        this.original = original;

        convolutionate();

    }

    private void convolutionate() {
        for (int x = 1; x < this.getWidth() - 1; x++) {
            for (int y = 1; y < this.getHeight() - 1; y++) {
                kernel(x, y);
            }
        }
    }

    //255/8 -> 31,87 per adjacent pixel
    private void kernel(int x, int y) {

        int colorAlteration = 0;

        Color pixel = new Color(original.getRGB(x, y));

        colorAlteration += (int) (diferenceCalculator(pixel, x - 1, y - 1)
                + diferenceCalculator(pixel, x, y - 1)
                + diferenceCalculator(pixel, x + 1, y - 1)
                + diferenceCalculator(pixel, x - 1, y)
                + diferenceCalculator(pixel, x + 1, y)
                + diferenceCalculator(pixel, x - 1, y + 1)
                + diferenceCalculator(pixel, x, y + 1)
                + diferenceCalculator(pixel, x + 1, y + 1));

        try {
            this.setRGB(x, y, new Color(colorAlteration, colorAlteration, colorAlteration).getRGB());
        } catch (Exception e) {
            System.out.println(colorAlteration);
        }

    }

    private double diferenceCalculator(Color pixel, int x, int y) {
        Color compared = new Color(original.getRGB(x, y));

        // -------
        int AR = pixel.getRed();
        int BR = compared.getRed();
        int RDiff;
        if (AR != BR) {
            RDiff = Math.max(AR, BR) - Math.min(AR, BR);
        } else {
            RDiff = 0;
        }
        // -------
        int AG = pixel.getGreen();
        int BG = compared.getGreen();
        int GDiff;
        if (AG != BG) {
            GDiff = Math.max(AG, BG) - Math.min(AG, BG);
        } else {
            GDiff = 0;
        }
        // -------
        int AB = pixel.getBlue();
        int BB = compared.getBlue();
        int BDiff;
        if (AB != BB) {
            BDiff = Math.max(AB, BB) - Math.min(AB, BB);
        } else {
            BDiff = 0;
        }
        // -------
        // 31,87 max value per cell
        double diff = (0.3187 * (RDiff * 0.33 + GDiff * 0.33 + BDiff * 0.33));

        return diff;

    }

}
