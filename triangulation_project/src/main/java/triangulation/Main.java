package triangulation;

public class Main {
    public static void main(String[] args) {
        // Test the Polygon class
        testPolygonArea();

        
    }
    private static void testPolygonArea() {
        Point[] points = {
            new Point(0, 0),
            new Point(4, 0),
            new Point(4, 3),
            new Point(0, 3)
        };
        Polygon polygon = new Polygon(points);
        double area = polygon.area();
        System.out.println("Area of the polygon: " + area);
    }
}