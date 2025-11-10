package dataviewer2;

import java.io.FileNotFoundException;

/**
 * Main entry point for the DataViewer application.
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        String dataFile = "Data/GlobalLandTemperaturesByState.csv";
        // String dataFile = "Data/sample.csv";  // Use for testing
        new DataViewerApp(dataFile);
    }
}
