package triangulation;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PolygonGenerator {
   
    public PolygonGenerator(){

    }
    public static void generatePolygons(String filePath, int polygonCount) {
        // Create the output folder if it doesn't exist
        File folder = new File(filePath);
        if (!folder.exists()) {
            folder.mkdir();
        }

        Random random = new Random();

        for (int i = 0; i < polygonCount; i++) {
            // Generate a random number of vertices (between 4 and 8)
            int verticesCount = random.nextInt(5) + 4;

            // Generate random points
            List<Point> vertices = new ArrayList<>();
            for (int j = 0; j < verticesCount; j++) {
                double x = -10 + (20 * random.nextDouble()); // Random double between -10 and 10
                double y = -10 + (20 * random.nextDouble());
                vertices.add(new Point(x, y));
            }

            // Sort vertices in counterclockwise order to avoid intersecting edges
            vertices = sortVerticesCounterClockwise(vertices);

            // Save the polygon to a text file
            savePolygonToFile(filePath, "polygon_" + (i + 1) + ".txt", vertices);
        }
    }

    private static List<Point> sortVerticesCounterClockwise(List<Point> vertices) {
        // Calculate the centroid of the polygon
        final double centerX, centerY;
        {
            double tempX = 0, tempY = 0;
            for (Point vertex : vertices) {
                tempX += vertex.x;
                tempY += vertex.y;
            }
            tempX /= vertices.size();
            tempY /= vertices.size();
            centerX = tempX;
            centerY = tempY;
        }

        // Sort vertices based on their angle relative to the centroid
        vertices.sort((p1, p2) -> {
            double angle1 = Math.atan2(p1.y - centerY, p1.x - centerX);
            double angle2 = Math.atan2(p2.y - centerY, p2.x - centerX);
            return Double.compare(angle1, angle2);
        });

        return vertices;
    }

    private static void savePolygonToFile(String filePath, String fileName, List<Point> polygon) {
        File file = new File(filePath + File.separator + fileName);

        try (FileWriter writer = new FileWriter(file)) {
            for (Point vertex : polygon) {
                writer.write(vertex.x + " " + vertex.y + "\n");
            }
            System.out.println("Polygon saved to: " + file.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error saving polygon to file: " + e.getMessage());
        }
    }
}