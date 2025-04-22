package triangulation;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

public class PNGWriter extends ModelWriter {


    @Override
    public void write(String path , List<Point>[] polygons) {
        int width = 1500;
        int height = 1500;

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics graphic = image.getGraphics();

        graphic.setColor(Color.white);
        graphic.fillRect(0, 0, width, height);

        // Find bounds
        double minX = getMinX(polygons);
        double maxX = getMaxX(polygons);
        double minY = getMinY(polygons);
        double maxY = getMaxY(polygons);
        System.out.println("minX: " + minX + ", maxX: " + maxX);
        System.out.println("minY: " + minY + ", maxY: " + maxY);

        double scaleX = (width - 40) / (maxX - minX);
        double scaleY = (height - 40) / (maxY - minY);
        double scale = Math.min(scaleX, scaleY);
        System.out.println("scaleX: " + scaleX + ", scaleY: " + scaleY);

        for (List<Point> polygon : polygons) {
            int[] xPoints = new int[polygon.size()];
            int[] yPoints = new int[polygon.size()];

            for (int i = 0; i < polygon.size(); i++) {
                xPoints[i] = (int) ((polygon.get(i).x - minX) * scale + 30);
                yPoints[i] = (int) ((maxY - polygon.get(i).y) * scale + 30);
            }

            int r = (int) (Math.random() * (255-20)) + 20;
            int g = (int) (Math.random() * (255-20)) + 20;
            int b = (int) (Math.random() * (255-20)) + 20;

            graphic.setColor(new Color(r, g, b));
            graphic.fillPolygon(xPoints, yPoints, xPoints.length);
        }

        try {
            ImageIO.write(image, "png", new File(path));
            System.out.println("Image saved to: " + path);
        } catch (IOException e) {
            e.getMessage();
        }
    }

    @Override
    public void write(String path , List<Point> vertices) {
        int width = 1000;
        int height = 1000;

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphic = (Graphics2D) image.getGraphics();

        graphic.setColor(Color.white);
        graphic.fillRect(0, 0, width, height);

        // Find bounds
        double minX = getMinX(vertices);
        double maxX = getMaxX(vertices);
        double minY = getMinY(vertices);
        double maxY = getMaxY(vertices);
        System.out.println("minX: " + minX + ", maxX: " + maxX);
        System.out.println("minY: " + minY + ", maxY: " + maxY);

        double scaleX = (width - 30) / (maxX - minX);
        double scaleY = (height - 30) / (maxY - minY);
        double scale = Math.min(scaleX, scaleY);

        System.out.println("scaleX: " + scaleX + ", scaleY: " + scaleY);

        int[] xPoints = new int[vertices.size()];
        int[] yPoints = new int[vertices.size()];

        for (int i = 0; i < vertices.size(); i++) {
            xPoints[i] = (int) ((vertices.get(i).x - minX) * scale + 15);
            yPoints[i] = (int) ((maxY - vertices.get(i).y) * scale + 15);
        }

        graphic.setColor(Color.black);
        graphic.setStroke(new BasicStroke(10)); 
        graphic.drawPolygon(xPoints, yPoints, xPoints.length);

        graphic.setColor(new Color(102, 102, 102));
        graphic.fillPolygon(xPoints, yPoints, xPoints.length);

        try {
            ImageIO.write(image, "png", new File(path));
            System.out.println("Image saved to: " + path);
        } catch (IOException e) {
            e.getMessage();
        }
    }

}
