package triangulation;

public class Main {
    public static void main(String[] args) {
        Point[] points = {
            new Point(1, 1),
            new Point(4, 1),
            new Point(3.5, 2),
            new Point(4, 3),
            new Point(3, 2),
            new Point(2, 3)
        };
        Polygon polygon = new Polygon(points);

        System.out.println(polygon.area());

        System.out.println(("check"));
    }
}