package convolutionfilter;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Viewer extends Canvas implements Runnable {

    BufferedImage original;
    Convolution convolutioned;

    public Viewer(File img) {
        setBackground(Color.gray);

        try {
            original = ImageIO.read(img);
        } catch (IOException e) {
            System.out.println(e);
        }
        
        Convolution convolutioned = new Convolution(original);
        this.convolutioned = convolutioned;

        
    }

    public void paint(Graphics g) {
        g.drawImage(original, 0, 0, null);
        g.drawImage(convolutioned, 700, 0, null);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(30);
                paint( this.getGraphics() );
            } catch (Exception e) {
                System.out.println("a");
                System.out.println(e);
            }

        }
    }

}
