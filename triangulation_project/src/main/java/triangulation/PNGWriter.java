package triangulation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

public class PNGWriter extends ModelWriter {

    @Override
    public void write(String path, List<Point>[] polygons) { 
        int width = 800;
        int height = 600;

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics graphic = image.getGraphics();

        graphic.setColor(Color.white);
        graphic.fillRect(0,0,width,height);


        
    }

    @Override
    public void write(String path , List<Point> vertices){
        int width = 10;
        int height = 10;

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics graphic = image.getGraphics();

        graphic.setColor(Color.white);
        graphic.fillRect(0,0,width,height);

        int[] xPoints = new int[vertices.size()];
        int[] yPoints = new int[vertices.size()];

        for (int i = 0; i < vertices.size(); i++) {
            xPoints[i] = (int) vertices.get(i).x;
            yPoints[i] = (int) vertices.get(i).y;
        }


        graphic.setColor(Color.gray);
        graphic.drawPolygon(xPoints, yPoints, xPoints.length);

        try {
            ImageIO.write(image,"png", new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
