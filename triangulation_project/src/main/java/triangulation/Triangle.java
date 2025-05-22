package triangulation;

import java.util.List;

public class Triangle extends Polygon {

    public static final double THIN_THRESHOLD_RATIO = 0.2;

    public Triangle(List<Point> vertices) {
        super(vertices);
        if (vertices.size() != 3) {
            throw new IllegalArgumentException("A triangle must have exactly 3 vertices.");
        }
        if (!isValidTriangle(vertices)) {
            throw new IllegalArgumentException("The provided points do not form a valid triangle.");
        }
    }

    private static double calculateDegree(Point pTarget, Point p1, Point p2) {
        double c = distance(p1, p2);
        double a = distance(pTarget, p2);
        double b = distance(pTarget, p1);

        if (a == 0 || b == 0) return 0;

        return Math.round((Math.toDegrees(Math.acos((a * a + b * b - c * c) / (2 * a * b)))) * 1e3) * 1e-3;
    }

    public double getMinAngle(){
        Point[] localVertices = {

            this.vertices.get(0),
            this.vertices.get(1),
            this.vertices.get(2)

        };

        double [] angles = new double[3];

        for(int i = 0 ; i < 3 ; i ++){
            angles[i] = calculateDegree(localVertices[i], localVertices[(i+1)%3], localVertices[(i+2)%3]);
        }

        return Math.min(angles[0],Math.min(angles[1] , angles[2]));
    
    }

    public double getMaxAngle(){
        Point[] localVertices = {

            this.vertices.get(0),
            this.vertices.get(1),
            this.vertices.get(2)

        };

        double [] angles = new double[3];

        for(int i = 0 ; i < 3 ; i ++){
            angles[i] = calculateDegree(localVertices[i], localVertices[(i+1)%3], localVertices[(i+2)%3]);
        }

        return Math.max(angles[0],Math.max(angles[1] , angles[2]));
    
    }

    public static boolean isValidTriangle(List<Point> vertices) {
        double a = distance(vertices.get(0), vertices.get(1));
        double b = distance(vertices.get(1), vertices.get(2));
        double c = distance(vertices.get(2), vertices.get(0));

        return (a + b > c) && (a + c > b) && (b + c > a);
    }

    @Override
    public double area() {
        // Using Heron's formula to calculate the area of the triangle

        double a = distance(vertices.get(0), vertices.get(1));
        double b = distance(vertices.get(1), vertices.get(2));
        double c = distance(vertices.get(2), vertices.get(0));

        double s = (a + b + c) / 2.0;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Triangle))
            return false;
        Polygon other = (Polygon) o;
        return vertices.equals(other.vertices);
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public String toString() {
        return "Triangle: " + vertices.get(0) + ", " + vertices.get(1) + ", " + vertices.get(2);
    }

    // This is our previous method t determine if the triangle is thin or not
    // This method is not used in the triangulation algorithm
    // Out new algorithm determine thinness based on the value of the angles
    public boolean isThin() {
        double a = distance(vertices.get(0), vertices.get(1));
        double b = distance(vertices.get(1), vertices.get(2));
        double c = distance(vertices.get(2), vertices.get(0));

        double minSide = Math.min(a, Math.min(b, c));
        double maxSide = Math.max(a, Math.max(b, c));

        return (minSide / maxSide) < Triangle.THIN_THRESHOLD_RATIO;
    }
}
