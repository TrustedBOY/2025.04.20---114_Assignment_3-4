package triangulation;

import java.util.ArrayList;
import java.util.List;

public class Main {
    static final String READ_FILE_DIRECTORY = "triangulation_project\\src\\inputData";
    static final String WRITE_FILE_DIRECTORY = "triangulation_project\\src\\outputImages";
    static final int POLYGONS_COUNT = 10 ;

    public static void main(String[] args) {

        List<Point> vertices = new ArrayList<>();
        vertices.add(new Point(0,0));
        vertices.add(new Point(1,0));
        vertices.add(new Point(0,3));

        Triangle triangle = new Triangle(vertices);

        System.out.println(triangle.getMinAngle());
        System.out.println(triangle.getMaxAngle());
        double mid =180.0 - (triangle.getMaxAngle() + triangle.getMinAngle());
        System.out.println(mid);

        // Generate polygons for testing
        // writeCoordinatesToFile();

        // Process the polygons and generate triangulations
        // process();

    }

    private static void writeCoordinatesToFile() {
        PolygonFileGenerator polygonFileGenerator = new PolygonFileGenerator(POLYGONS_COUNT, READ_FILE_DIRECTORY);
        polygonFileGenerator.generate();
    }

    private static void process() {
        JavaTriangulationApplication app = new JavaTriangulationApplication(READ_FILE_DIRECTORY, WRITE_FILE_DIRECTORY);
        app.run();
    }

}
