package convolutionfilter;

import java.io.File;
import javax.swing.JFrame;

public class ConvolutionFilter extends JFrame{

    public ConvolutionFilter () {
        this.setVisible(true);
        this.setSize(700,400);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        
        //sailorMoon
        //ciudad
        File imageFile = new File("src/ciudad.png");
        Viewer viewer = new Viewer(imageFile);
        
        this.add(viewer);
        
        Thread viewerThread = new Thread(viewer);
        viewerThread.start();

    }
    
    
    public static void main(String[] args) {
        
        new ConvolutionFilter();
        
        
        
    }
    
}
