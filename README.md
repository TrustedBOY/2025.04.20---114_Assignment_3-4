Error hadling

Nice! You can draw a polygon using just the Graphics.drawPolygon() method, still keeping things minimal.

Here’s your updated example that draws a polygon and writes text on a newly created PNG:

import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

public class PNGWithPolygon {
    public static void main(String[] args) throws Exception {
        int width = 300, height = 200;

        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = img.getGraphics();

        // Background
        g.setColor(java.awt.Color.WHITE);
        g.fillRect(0, 0, width, height);

        // Text
        g.setFont(new Font("SansSerif", Font.BOLD, 20));
        g.setColor(java.awt.Color.BLACK);
        g.drawString("Polygon below:", 50, 30);

        // Polygon
        int[] xPoints = {50, 100, 150, 100}; // diamond shape
        int[] yPoints = {100, 70, 100, 130};
        int nPoints = 4;

        g.setColor(java.awt.Color.RED);
        g.drawPolygon(xPoints, yPoints, nPoints);

        ImageIO.write(img, "png", new File("polygon_image.png"));
    }
}

This will draw a red diamond shape and a label above it.

Let me know if you want to fill the polygon, use custom shapes, or draw curves!


