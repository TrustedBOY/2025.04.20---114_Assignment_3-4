package triangulation;

public class Main {
    static final String readFileDirectory = "triangulation_project\\src\\inputData";
    static final String writeFileDirectory = "triangulation_project\\src\\outputImages";

    public static void main(String[] args) {

        // Generate polygons for testing
        writeCoordinatesToFile();

        // Process the polygons and generate triangulations
        process();

    }

    private static void writeCoordinatesToFile() {
        PolygonFileGenerator polygonFileGenerator = new PolygonFileGenerator(5, readFileDirectory);
        polygonFileGenerator.generate();
    }

    private static void process() {
        JavaTriangulationApplication app = new JavaTriangulationApplication(readFileDirectory, writeFileDirectory);
        app.run();
    }

}
