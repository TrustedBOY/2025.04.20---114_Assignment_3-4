package triangulation;

import java.util.Collections;
import java.util.List;

public class Polygon extends Geometry {

    public Polygon(List<Point> vertices) {
        super(vertices);
        if (!isClockwise(vertices)) {
            // If the vertices are not in clockwise order, reverse the order.
            toClockwise();
        }
    }

    private boolean isClockwise(List<Point> vertices) {
        // This method checks if the polygon is in clockwise order.
        // The algorithm is based on the signed area of the polygon.

        double sum = 0.0;
        for (int i = 0; i < vertices.size(); i++) {
            try {
                Point p1 = vertices.get(i);
                Point p2 = vertices.get((i + 1) % vertices.size());
                sum += (p1.x * p2.y) - (p2.x * p1.y);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Index out of bounds in: "+ getClass() + ".isClockwise() | " + e.getMessage());
            }
        }
        return sum < 0;
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
                System.out.println("Index out of bounds in: "+ getClass() + ".area() | " + e.getMessage());
            }

        }
        return Math.abs(area) / 2.0;
    }

    private void toClockwise() {
        // This method reverses the order of the vertices to make them clockwise.

        if (!isClockwise(vertices)) {
            Collections.reverse(vertices);
        }
    }

}
