package triangulation;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class PolygonFileGenerator {
    private int NUM_FILES = 10;
    private final int MIN_POINTS = 3;
    private final int MAX_POINTS = 10;
    private final double RADIUS = 10.0;
    private final String directoryPath ;

    public PolygonFileGenerator(int numFiles , String directoryPath) {
        this.directoryPath = directoryPath;
        this.NUM_FILES = numFiles;
    }

    public void generate() {
        String directoryPath = "triangulation_project\\\\src\\\\inputData";

        for (int i = 1; i <= NUM_FILES; i++) {
            int numPoints = getRandomInt(MIN_POINTS, MAX_POINTS);
            ArrayList<double[]> points = generatePolygonPoints(numPoints);

            if (new Random().nextBoolean()) {
                Collections.reverse(points);
            }

            String filename = directoryPath + "/" + i + ".txt";
            writePointsToFile(points, filename);
        }

        System.out.printf("%d polygon files generated in: %s " , NUM_FILES , directoryPath);
    }

    private ArrayList<double[]> generatePolygonPoints(int numPoints) {
        Random rand = new Random();
        ArrayList<Double> angles = new ArrayList<>();
        for (int i = 0; i < numPoints; i++) {
            angles.add(rand.nextDouble() * 2 * Math.PI);
        }
        Collections.sort(angles);

        ArrayList<double[]> points = new ArrayList<>();
        double angleOffset = rand.nextDouble() * 2 * Math.PI;
        for (double angle : angles) {
            double radius = RADIUS * (0.8 + 0.4 * rand.nextDouble());
            double x = Math.cos(angle + angleOffset) * radius;
            double y = Math.sin(angle + angleOffset) * radius;
            points.add(new double[]{x, y});
        }

        return points;
    }

    private static void writePointsToFile(ArrayList<double[]> points, String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            for (double[] point : points) {
                writer.write(String.format("%.4f %.4f%n", point[0], point[1]));
            }
        } catch (IOException e) {
            System.err.println("Error writing to " + filename + ": " + e.getMessage());
        }
    }

    private static int getRandomInt(int min, int max) {
        return new Random().nextInt(max - min + 1) + min;
    }
}
