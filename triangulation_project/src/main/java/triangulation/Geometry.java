package triangulation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Geometry {

    protected final List<Point> vertices;

    public Geometry(List<Point> vertices) {
        if (vertices == null || vertices.size() < 3) {
            throw new IllegalArgumentException("A shape must have at least 3 vertices.");
        }
        this.vertices = new ArrayList<>(vertices);
        if (!isClockwise(vertices)) {
            // If the vertices are not in clockwise order, reverse the order.
            toClockwise();
        }
    }

    public List<Point> getVertices() {
        return vertices;
    }

    public double distance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p2.x - p1.x, 2) + Math.pow(p2.y - p1.y, 2));
    }

    protected boolean isClockwise(List<Point> vertices) {
        // This method checks if the polygon is in clockwise order.
        // The algorithm is based on the signed area of the polygon.

        double sum = 0.0;
        for (int i = 0; i < vertices.size(); i++) {
            try {
                Point p1 = vertices.get(i);
                Point p2 = vertices.get((i + 1) % vertices.size());
                sum += (p1.x * p2.y) - (p2.x * p1.y);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Index out of bounds in: " + getClass() + ".isClockwise() | " + e.getMessage());
            }
        }
        return sum < 0;
    }

    protected void toClockwise() {
        // This method reverses the order of the vertices to make them clockwise.

        if (!isClockwise(vertices)) {
            Collections.reverse(vertices);
        }
    }

    public double area() {
        // This method calculates the area of the polygon using the shoelace formula.

        double area = 0.0;
        for (int i = 0; i < vertices.size(); i++) {

            try {
                Point p1 = vertices.get(i);
                Point p2 = vertices.get((i + 1) % vertices.size());
                area += (p1.x * p2.y) - (p2.x * p1.y);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Index out of bounds in: " + getClass() + ".area() | " + e.getMessage());
            }

        }
        return Math.abs(area) / 2.0;
    }

}
