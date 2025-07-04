    package triangulation;

    import java.awt.BasicStroke;
    import java.awt.Color;
    import java.awt.Graphics2D;
    import java.awt.image.BufferedImage;
    import java.io.File;
    import java.io.IOException;
    import java.util.List;

    import javax.imageio.ImageIO;

    public class PNGWriter extends ModelWriter {

        private int savedImages = 0;

        private static final int[] randomColors = {
                (int) (Math.random() * (255 - 20)) + 20,
                (int) (Math.random() * (255 - 20)) + 20,
                (int) (Math.random() * (255 - 20)) + 20 
        };

        public PNGWriter() {
            super();
        }

        @Override
        public void writePolygons(String path, List<List<Point>> polygons) {
            int width  = 1500;
            int height = 1500;

            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphic = (Graphics2D) image.getGraphics();

            graphic.setColor(Color.white);
            graphic.fillRect(0, 0, width, height);

            // Find bounds
            double minX = getMin(polygons , true);
            double maxX = getMax(polygons , true);
            double minY = getMin(polygons , false);
            double maxY = getMax(polygons , false);

            double scaleX = (width - 40) / (maxX - minX);
            double scaleY = (height - 40) / (maxY - minY);
            double scale = Math.min(scaleX, scaleY);


            for (List<Point> polygon : polygons) {
                int[] xPoints = new int[polygon.size()];
                int[] yPoints = new int[polygon.size()];

                for (int i = 0; i < polygon.size(); i++) {
                    xPoints[i] = (int) ((polygon.get(i).x - minX) * scale + 30);
                    yPoints[i] = (int) ((maxY - polygon.get(i).y) * scale + 30);
                }

                int r = randomColors[0];
                int g = randomColors[1];
                int b = randomColors[2];

                randomColors[0] = (randomColors[0] += 53) % 255;
                randomColors[1] = (randomColors[1] += 73) % 255;    
                randomColors[2] = (randomColors[2] += 93) % 255;

                graphic.setColor(Color.black);
                graphic.setStroke(new BasicStroke(3));
                graphic.drawPolygon(xPoints, yPoints, Math.min(xPoints.length , yPoints.length));

                graphic.setColor(new Color(r , g, b));
                graphic.fillPolygon(xPoints, yPoints, Math.min(xPoints.length , yPoints.length));

                
                
            }

            try {
                ImageIO.write(image, "png", new File(path));
                // System.out.println("Image saved to: " + path);
                savedImages++;
            } catch (IOException e) {
                e.getMessage();
            }
        }

        @Override
        public void writePolygon(String path, List<Point> vertices) {
            int width = 1000;
            int height = 1000;

            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphic = (Graphics2D) image.getGraphics();

            graphic.setColor(Color.white);
            graphic.fillRect(0, 0, width, height);

            // Find bounds
            double minX = getMin(vertices , true);
            double maxX = getMax(vertices , true);
            double minY = getMin(vertices , false);
            double maxY = getMax(vertices , false);

            double scaleX = (width - 30) / (maxX - minX);
            double scaleY = (height - 30) / (maxY - minY);
            double scale = Math.min(scaleX, scaleY);

            int[] xPoints = new int[vertices.size()];
            int[] yPoints = new int[vertices.size()];

            for (int i = 0; i < vertices.size(); i++) {
                xPoints[i] = (int) ((vertices.get(i).x - minX) * scale + 15);
                yPoints[i] = (int) ((maxY - vertices.get(i).y) * scale + 15);
            }

            graphic.setColor(Color.black);
            graphic.setStroke(new BasicStroke(5));
            graphic.drawPolygon(xPoints, yPoints, xPoints.length);

            graphic.setColor(new Color(102, 102, 102));
            graphic.fillPolygon(xPoints, yPoints, xPoints.length);

            try {
                ImageIO.write(image, "png", new File(path));
                // System.out.println("Image saved to: " + path);
                savedImages++;
            } catch (IOException e) {
                e.getMessage();
            }
        }

        public int getSavedImages() {
            return savedImages;
        }
    }
