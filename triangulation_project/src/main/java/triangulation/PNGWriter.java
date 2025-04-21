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
        int width = 600;
        int height = 600;

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
                xPoints[i] = (int) ((polygon.get(i).x - minX) * scale + 10);
                yPoints[i] = (int) ((maxY - polygon.get(i).y) * scale + 10);
            }

            int r  = Math.max((int) (Math.random() * 255) - 22,22);
            int g  = Math.max((int) (Math.random() * 255) - 22,22);
            int b  = Math.max((int) (Math.random() * 255) - 22,22);

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
    public void write(String path, List<Point> vertices) {
        int width = 600;
        int height = 600;

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics graphic = image.getGraphics();

        graphic.setColor(Color.white);
        graphic.fillRect(0, 0, width, height);

        // Find bounds
        double minX = getMinX(vertices);
        double maxX = getMaxX(vertices);
        double minY = getMinY(vertices);
        double maxY = getMaxY(vertices);
        System.out.println("minX: " + minX + ", maxX: " + maxX);
        System.out.println("minY: " + minY + ", maxY: " + maxY);

        double scaleX = (width - 40) / (maxX - minX);
        double scaleY = (height - 40) / (maxY - minY);
        double scale = Math.min(scaleX, scaleY);

        System.out.println("scaleX: " + scaleX + ", scaleY: " + scaleY);

        int[] xPoints = new int[vertices.size()];
        int[] yPoints = new int[vertices.size()];

        for (int i = 0; i < vertices.size(); i++) {
            xPoints[i] = (int) ((vertices.get(i).x - minX) * scale + 10);
            yPoints[i] = (int) ((maxY - vertices.get(i).y) * scale + 10);
        }

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
