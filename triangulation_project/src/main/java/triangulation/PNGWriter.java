package triangulation;

import java.awt.Font; 
import java.awt.Graphics; 
import java.awt.image.BufferedImage; 

import javax.imageio.ImageIO; 

import java.io.File;
import java.util.List;

public class PNGWriter extends ModelWriter {

    @Override
    public abstract void write(String path , List<Poits> vertices) {
    
        int width = 800;
        int height = 600;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        g.drawPolygon(p);
    }

}
