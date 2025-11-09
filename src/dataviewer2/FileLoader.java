package dataviewer2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Utility class responsible for loading and parsing CSV files.
 * Returns the data as a list of records, each record represented as a List<Object>.
 */
public class FileLoader {

    /**
     * Reads a CSV file and parses its contents into a list of records.
     * Each record is a List<Object> containing the parsed fields.
     * 
     * @param fileName The path to the CSV file.
     * @return A List of rows, where each row is a List<Object> with parsed values. yes, you will have to do some stupid stuff to actually read it. sorry.
     * @throws FileNotFoundException if the file cannot be opened.
     */
	public static List<List<Object>> loadData(String fileName) throws FileNotFoundException {
	    List<List<Object>> data = new ArrayList<>();
	    File file = new File(fileName);

	    try (Scanner scanner = new Scanner(file)) {
	        boolean hasHeader = false;
	        if (scanner.hasNextLine()) {
	            scanner.useDelimiter("\\n");
	            scanner.reset();
	            String header = scanner.nextLine();
	            if (header.toLowerCase().contains("year")) {
	                hasHeader = true;
	            } else {
	                // No header likely means its a piece of data, so treat it accordingly
	                List<Object> record = parseCSVLine(header);
	                data.add(record);
	            }
	        }

	        while (scanner.hasNextLine()) {
	            String line = scanner.nextLine().trim();
	            if (!line.isEmpty()) {
	                List<Object> record = parseCSVLine(line);
	                data.add(record);
	            }
	        }
	    }

	    return data;
	}


    /**
     * Parses a single CSV line into a list of typed objects.
     * Automatically converts numeric fields to Integer or Double.
     * 
     * @param line A single CSV record line.
     * @return A list of objects, each parsed and typed appropriately.
     */
    public static List<Object> parseCSVLine(String line) {
        List<Object> record = new ArrayList<>();

        String[] tokens = line.split(",");

        for (String token : tokens) {
            token = token.trim();

            // Try parsing numeric values
            try {
                if (token.contains(".")) {
                    record.add(Double.parseDouble(token));
                } else {
                    record.add(Integer.parseInt(token));
                }
            }
            catch (NumberFormatException e) {
                // Not a number — treat as string
                record.add(token);
            }
        }

        return record;
    }
}
