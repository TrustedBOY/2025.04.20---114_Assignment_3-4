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

                if (isConvex(a, b, c)) {
                    triangles.add(new Triangle(List.of(a, b, c)));
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
}
