package triangulation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Geometry {

    protected List<Point> vertices;

    public Geometry(List<Point> vertices) {
        if (vertices == null || vertices.size() < 3) {
            throw new IllegalArgumentException("A shape must have at least 3 vertices.");
        }
        this.vertices = ensureClockwise(vertices);
    }

    protected static List<Point> ensureClockwise(List<Point> vertices) {

        // This method ensures that the vertices are in clockwise order.
        // It checks the order of the vertices and reverses them if they are not in clockwise order.

        List<Point> result = new ArrayList<>(vertices);
        if (!isClockwise(result)) {
            Collections.reverse(result);
        }
        return result;
    }

    protected static boolean isClockwise(List<Point> vertices) {

        // This method checks if the polygon is in clockwise order.
        // The algorithm is based on the signed area of the polygon.

        double sum = 0.0;
        for (int i = 0; i < vertices.size(); i++) {
            Point p1 = vertices.get(i);
            Point p2 = vertices.get((i + 1) % vertices.size());
            sum += (p1.x * p2.y) - (p2.x * p1.y);
        }
        return sum < 0;
    }

    public List<Point> getVertices() {
        return vertices;
    }

    public static double distance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p2.x - p1.x, 2) + Math.pow(p2.y - p1.y, 2));
    }


    public abstract double area();

    @Override 
    public abstract String toString();

}
