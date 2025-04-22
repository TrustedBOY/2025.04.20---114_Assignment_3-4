package triangulation;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<List<Point>> polygons = read();
        ModelWriter pngWriter = new PNGWriter();
        String writeFilePath = "triangulation_project\\src\\outputImages\\";

        for (int i = 0; i < polygons.size(); i++) {

            String currentWriteFilePath = writeFilePath + (i + 1) + "_Polygon.png";
            pngWriter.write(currentWriteFilePath, polygons.get(i));
        }

        System.out.println(polygons.size() + " polygons written to PNG files.");
    }
    private static List<List<Point>> read() {
        String filePath = "triangulation_project\\src\\inputData\\";
        List<List<Point>> polygons = new ArrayList<>();

        for (int i = 0; true ; i++) {
            String currentFilePath = filePath + (i + 1) + ".txt";
            File file = new File(currentFilePath);

            if (!file.exists()) {
                break;
            }

            try {
                polygons.add(TextReader.readVerticesFromFile(currentFilePath));
            } catch (Exception e) {
                System.err.println("Error reading file: " + e.getMessage());
                break;
            }
        }

        return polygons;
    }
}
