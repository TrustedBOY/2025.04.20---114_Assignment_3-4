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
        if (verts.size() < 3) {
            return;
        }

        //last triangle
        if (verts.size() == 3) {
            triangles.add(new Triangle(List.of(verts.get(0), verts.get(1), verts.get(2))));
            return;
        }

        try {
            for (int i = 0; i < verts.size(); i++) {
                Point a = verts.get(i);
                Point b = verts.get((i + 1) % verts.size());
                Point c = verts.get((i + 2) % verts.size());

                Triangle t = new Triangle(List.of(a, b, c));
                if (isConvex(a, b, c) && isEar(a, b, c, verts) && !t.isThin()) {
                    triangles.add(t);
                    verts.remove((i + 1) % verts.size());
                    triangulateRecursive(verts);
                    return;
                }

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

    public List<Point>[] getVertices() {
        List<Point>[] verts = new List[triangles.size()];
        for (int i = 0; i < triangles.size(); i++) {
            verts[i] = triangles.get(i).getVertices();
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

        return Math.abs(areaOrig - (area1 + area2 + area3)) < 1e-6;
    }

    private double crossProduct(Point a, Point b, Point c) {
        return (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);
    }

}
