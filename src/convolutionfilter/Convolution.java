package convolutionfilter;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Convolution extends BufferedImage {

    int kernel[][] = new int[][]{
        {0, 0, 0},
        {-1, 2, -1},
        {0, 0, 0}};

    int kernelSumVal;

    BufferedImage original;

    public Convolution(BufferedImage original) {
        super(original.getWidth(), original.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
        this.original = original;
        
        kernelSumVal = getKernelSumVal();
        
        convolutionate();
        
    }

    private void convolutionate() {
        for (int x = 1; x < this.getWidth() - 1; x++) {
            for (int y = 1; y < this.getHeight() - 1; y++) {
                kernel(x, y);
            }
        }
    }

    private void kernel(int x, int y) {
        
        int red = 0;
        int green = 0;
        int blue = 0;
        
        for (int itX = -1; itX <= 1; itX++) {
            for (int itY = -1; itY <= 1; itY++) {
                
                if (kernel[1 + itX][1 + itY] == 0) continue;
                
                Color pixel = new Color(original.getRGB(x + itX, y + itY));
                
                red += pixel.getRed() * kernel[itX + 1][itY + 1];
                green += pixel.getGreen() * kernel[itX + 1][itY + 1];
                blue += pixel.getBlue() * kernel[itX + 1][itY + 1];
                
            }
        }
        
        
        red = (red / kernelSumVal < 1) ? 0 : (red / kernelSumVal > 255) ? 255 : red / kernelSumVal;
        green = (green / kernelSumVal < 1) ? 0 : (green / kernelSumVal > 255) ? 255 : green / kernelSumVal;
        blue = (blue / kernelSumVal < 1) ? 0 : (blue / kernelSumVal > 255) ? 255 : blue / kernelSumVal;
        
        //This is only usefull if you want absolute values when looking for vertical/horizontals edges, otherwise you'll only get weird shapes
//        if (red + green + blue >= 130 ) {
//            red = 255;
//            green = 255;
//            blue = 255;
//        } else {
//            red = 0;
//            green = 0;
//            blue = 0;
//        }
        
        try {
            this.setRGB(x, y, new Color(red, green, blue).getRGB());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private int getKernelSumVal() {
        int m = 0;
        for (int[] row : kernel) {
            for (int n : row) {
                m += n;
            }
        }
        return (m < 1) ? 1 : m;
    }
}
