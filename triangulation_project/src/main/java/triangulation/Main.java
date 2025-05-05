package triangulation;

public class Main {
    static final String READ_FILE_DIRECTORY = "triangulation_project\\src\\inputData";
    static final String WRITE_FILE_DIRECTORY = "triangulation_project\\src\\outputImages";
    static final int POLYGONS_COUNT = 0 ;

    public static void main(String[] args) {

        // Generate polygons for testing
        writeCoordinatesToFile();

        // Process the polygons and generate triangulations
        process();

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
