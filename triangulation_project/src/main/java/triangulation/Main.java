package triangulation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Test the Polygon class
        testPolygonArea();
        System.out.println("---------------------");
        // Test the Triangle class
        testTriangle();
        System.out.println("---------------------");
        // Test the VerticeReader class
        testReader();
        System.out.println("---------------------");
        // Test the PNGWriter class
        testReaderWWriter();
        System.out.println("---------------------");

    }

    private static void testPolygonArea() {

        List<Point> vertices = new ArrayList<>();

        Point[] points = {
            new Point(0, 0),
            new Point(4, 0),
            new Point(4, 3),
            new Point(0, 3)
        };

        for (Point point : points) {
            vertices.add(point);
        }

        Polygon polygon = new Polygon(vertices);
        double area = polygon.area();
        System.out.println("Area of the polygon: " + area);
    }

    private static void testTriangle() {
        List<Point> trianglePoints = Arrays.asList(
                new Point(0, 0),
                new Point(4, 3),
                new Point(8, 0) // This will fail: all on the same line
        );

        try {
            Triangle triangle = new Triangle(trianglePoints);
            System.out.println("Triangle created successfully.");
            System.out.println("Area: " + triangle.area());
            System.out.println("Is thin? " + triangle.isThin());
        } catch (IllegalArgumentException e) {
            System.err.println("Triangle creation failed: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
    }

    private static void testReader() {
        String filePath = "triangulation_project\\src\\inputData\\1.txt";

        try {
            List<Point> points = VerticeReader.readVerticesFromFile(filePath);
            System.out.println("Points read from file:");
            for (Point point : points) {
                System.out.println(point);
            }
        } catch (Exception e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    private static void testReaderWWriter() {
        String readFilePath = "triangulation_project\\src\\inputData\\1.txt";
        String writeFilePath = "triangulation_project\\src\\outputImages\\1.png";

        ModelWriter pngWriter = new PNGWriter();
        try {
            List<Point> points = VerticeReader.readVerticesFromFile(readFilePath);
            System.out.println("Points read from file:");
            pngWriter.write(writeFilePath, points);

        } catch (Exception e) {
            System.err.println("Error reading file: " + e.getMessage());
        }




    }

}
