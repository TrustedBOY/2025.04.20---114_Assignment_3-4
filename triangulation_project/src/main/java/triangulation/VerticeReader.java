package triangulation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VerticeReader {

    public static List<Point> readVerticesFromFile(String filePath) {

        List<Point> vertices = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] coordinates = line.trim().split("\\s+");
                if (coordinates.length != 2) {
                    System.out.println("Invalid vertex format: " + line);
                    continue;
                }
                double x = Double.parseDouble(coordinates[0]);
                double y = Double.parseDouble(coordinates[1]);

                vertices.add(new Point(x, y));

            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format in file: " + e.getMessage());
        }

        return vertices;
    }
}
