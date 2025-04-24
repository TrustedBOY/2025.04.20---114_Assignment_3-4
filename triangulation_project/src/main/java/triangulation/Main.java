package triangulation;

public class Main {

    public static void main(String[] args) {

        String readFileDirectory = "triangulation_project\\src\\inputData";
        String writeFileDirectory = "triangulation_project\\src\\outputImages";
        
        // Generate polygons for testing
        PolygonGenerator.generatePolygons(readFileDirectory, 10);

        JavaTriangulationApplication app = new JavaTriangulationApplication(readFileDirectory, writeFileDirectory);

        app.run();


    }
}
