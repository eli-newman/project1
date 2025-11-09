package dataviewer2;

import java.io.FileNotFoundException;
import java.util.List;

public class FileLoaderTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		testFileLoading();
		testParseCSVLine();
		System.out.println("\n");
		//i won't lie, this system is quite convoluted and probably really, really bad.
		TestBoth();
	}


	 private static void testFileLoading() {
	        String[] testFiles = {
	            "Data/sample.csv",
	            "Data/GlobalLandTemperaturesByState.csv",
	            "Data/test.csv" // should throw FileNotFoundException
	        };

	        for (String fileName : testFiles) {
	            System.out.println("\n");
	            System.out.println("Attempting to load file: " + fileName);

	            try {
	                List<List<Object>> data = FileLoader.loadData(fileName);
	                System.out.println("Successfully loaded " + data.size() + " records.");
	                
	                // Preview the first few rows for confirmation
	                int previewLimit = Math.min(3, data.size());
	                for (int i = 0; i < previewLimit; i++) {
	                    System.out.println("Row " + (i + 1) + ": " + data.get(i));
	                }
	            } 
	            catch (FileNotFoundException e) {
	                System.out.println("File not found: " + fileName);
	                System.out.println("   => " + e.getMessage());
	            } 
	            catch (Exception e) {
	                System.out.println("Unexpected error while reading " + fileName);
	                e.printStackTrace();
	            }

	            System.out.println("\n");
	        }
	    }
	 private static void testParseCSVLine() {
	        System.out.println("Testing parseCSVLine()");

	        String[] lines = {
	            "2023, 7, 15.2, Colorado, United States",
	            "1999, 12, -5, Alaska, United States",
	            "Year, Month, Temp, State, Country",
	            "foo, 123, 45.67, bar"               
	        };

	        for (String line : lines) {
	            System.out.println("Input line: \"" + line + "\"");
	            List<Object> parsed = FileLoader.parseCSVLine(line);
	            System.out.println("Parsed fields: " + parsed);
	            System.out.println("Field types: ");
	            for (Object obj : parsed) {
	                System.out.println(" => " + obj + " (" + obj.getClass().getSimpleName() + ")");
	            }
	            System.out.println();
	        }

	        System.out.println("-----\n");
	    }
	 private static void TestBoth() {
		  String fileName = "Data/sample.csv";

	        System.out.println("Running FileLoader integration test on: " + fileName);

	        try {
	            // Load entire file using FileLoader
	            List<List<Object>> loadedData = FileLoader.loadData(fileName);
	            System.out.println("loaded " + loadedData.size() + " rows successfully.");

	            // Verify first few rows are consistent with manual parsing
	            int rowsToTest = Math.min(3, loadedData.size());
	            System.out.println("\nRe-parsing first " + rowsToTest + " rows to verify consistency:\n");

	            for (int i = 0; i < rowsToTest; i++) {
	                List<Object> rowFromLoad = loadedData.get(i);

	                StringBuilder csvLineBuilder = new StringBuilder();
	                for (int j = 0; j < rowFromLoad.size(); j++) {
	                    csvLineBuilder.append(rowFromLoad.get(j));
	                    if (j < rowFromLoad.size() - 1)
	                        csvLineBuilder.append(", ");
	                }
	                String reconstructedLine = csvLineBuilder.toString();

	                // Now manually parse that same line
	                List<Object> rowFromParse = FileLoader.parseCSVLine(reconstructedLine);

	                // Compare and print both
	                System.out.println("Row " + (i + 1) + " reconstructed: " + reconstructedLine);
	                System.out.println("From loadData(): " + rowFromLoad);
	                System.out.println("From parseCSVLine(): " + rowFromParse);

	                boolean match = rowFromLoad.equals(rowFromParse);
	                System.out.println("=> Match: " + (match ? "true" : "false (potential type/spacing diff)") + "\n");
	            }
	        } 
	        catch (FileNotFoundException e) {
	            System.out.println("Could not find file: " + fileName);
	            System.out.println("  => " + e.getMessage());
	        } 
	        catch (Exception e) {
	            System.out.println("Unexpected error during integration test:");
	            e.printStackTrace();
	        }

	        System.out.println("\n");
	 }
}
