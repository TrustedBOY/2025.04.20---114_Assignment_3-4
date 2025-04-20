package triangulation;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Test the Polygon class
        testPolygonArea();

        
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
}