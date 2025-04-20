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


        String str = "hi     hell";

        String[] words = str.split(" ");
        for (String word : words) {
            System.out.println(word);
        }

        
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
    private static void testTriangle(){
        List<Point> trianglePoints = Arrays.asList(
            new Point(0, 0),
            new Point(4, 3),
            new Point(8, 0)  // This will fail: all on the same line
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

}