package convolutionfilter;

import java.io.File;
import javax.swing.JFrame;

public class ConvolutionFilter extends JFrame{

    public ConvolutionFilter () {
        this.setSize(1100,600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        
        //sailorMoon.png
        //ciudad.png
        //paisaje.jpg
        //obito.jpg
        //pinguino.jpeg
        //edificios.jpg
        File imageFile = new File("src/edificios.jpg");
        Viewer viewer = new Viewer(imageFile);
        
        this.add(viewer);
        
        Thread viewerThread = new Thread(viewer);
        viewerThread.start();

        this.setVisible(true);
    }
    
    
    public static void main(String[] args) {
        
        new ConvolutionFilter();
        
        
        
    }
    
}
