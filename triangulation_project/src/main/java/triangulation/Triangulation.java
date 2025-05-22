package triangulation;

import java.util.ArrayList;
import java.util.List;

public class Triangulation {

    private final Polygon polygon;
    private final List<Triangle> triangles = new ArrayList<>();

    public Triangulation(Polygon polygon) {
        this.polygon = polygon;
    }

    public void triangulate() {
        List<Point> verts = new ArrayList<>(polygon.getVertices());
        triangulateRecursive(verts);
    }

    private void triangulateRecursive(List<Point> verts) {
        List<Point> vertsCopy = new ArrayList<>(verts);

        if (vertsCopy.size() < 3)
            return;

        if (vertsCopy.size() == 3) {
            triangles.add(new Triangle(List.of(vertsCopy.get(0), vertsCopy.get(1), vertsCopy.get(2))));
            return;
        }

        try {
            Triangle bestTriangle = null;
            double bestMinAngle = -1;

            for (int i = 0; i < vertsCopy.size(); i++) {
                Point a = vertsCopy.get(i);
                Point b = vertsCopy.get((i + 1) % vertsCopy.size());
                Point c = vertsCopy.get((i + 2) % vertsCopy.size());

                Triangle testTriangle = new Triangle(List.of(a, b, c));

                if (isConvex(a, b, c) && isEar(a, b, c, vertsCopy)) {
                    double minAngle = testTriangle.getMinAngle();

                    if (minAngle > bestMinAngle) {
                        bestMinAngle = minAngle;
                        bestTriangle = testTriangle;
                    }
                }
            }

            if (bestTriangle != null) {
                Point b = bestTriangle.getVertices().get(1);
                triangles.add(bestTriangle);
                vertsCopy.remove(vertsCopy.indexOf(b));
                triangulateRecursive(vertsCopy);
            }

        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index out of bounds: " + e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("Null pointer exception: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    private boolean isConvex(Point a, Point b, Point c) {
        double cross = (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);
        return cross < 0;
    }

    public List<Triangle> getTriangles() {
        return triangles;
    }

    public List<List<Point>> getVertices() {
        List<List<Point>> verts = new ArrayList<>();
        for (Triangle triangle : triangles) {
            verts.add(triangle.getVertices());
        }
        return verts;
    }

    private boolean isEar(Point a, Point b, Point c, List<Point> verts) {
        for (Point p : verts) {
            if (p == a || p == b || p == c) {
                continue;
            }
            if (pointInTriangle(p, a, b, c)) {
                return false;
            }
        }
        return true;
    }

    private boolean pointInTriangle(Point p, Point a, Point b, Point c) {
        double areaOrig = Math.abs(crossProduct(a, b, c));
        double area1 = Math.abs(crossProduct(p, b, c));
        double area2 = Math.abs(crossProduct(a, p, c));
        double area3 = Math.abs(crossProduct(a, b, p));

        return Math.abs(areaOrig - (area1 + area2 + area3)) < 0.000001;
    }

    private double crossProduct(Point a, Point b, Point c) {
        return (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);
    }

}
