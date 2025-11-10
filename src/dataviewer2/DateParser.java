package dataviewer2;

/**
 * Utility class for parsing dates from CSV file.
 * Supports M/D/Y and Y-M-D formats.
 */
public class DateParser {
    
    /**
     * Parse year from date string.
     * Supports formats: "1/20/1823" or "1823-01-20"
     * 
     * @param dateString Date string to parse
     * @return Year as Integer, or null if unable to parse
     */
    public Integer parseYear(String dateString) {
        // Determine delimiter
        String delimiter;
        int yearIndex;
        
        if(dateString.contains("/")) {
            delimiter = "/";
            yearIndex = 2;  // M/D/Y format - year is last
        } 
        else if(dateString.contains("-")) {
            delimiter = "-";
            yearIndex = 0;  // Y-M-D format - year is first
        } 
        else {
            throw new RuntimeException("Unexpected date format: " + dateString);
        }
        
        // Split and extract year
        String[] parts = dateString.split(delimiter);
        if(parts.length == 3) {
            return Integer.parseInt(parts[yearIndex]);
        }
        
        return null;
    }
    
    /**
     * Parse month from date string.
     * Supports formats: "1/20/1823" or "1823-01-20"
     * 
     * @param dateString Date string to parse
     * @return Month as Integer (1-12), or null if invalid
     */
    public Integer parseMonth(String dateString) {
        // Determine delimiter
        String delimiter;
        int monthIndex;
        
        if(dateString.contains("/")) {
            delimiter = "/";
            monthIndex = 0;  // M/D/Y format - month is first
        } 
        else if(dateString.contains("-")) {
            delimiter = "-";
            monthIndex = 1;  // Y-M-D format - month is second
        } 
        else {
            throw new RuntimeException("Unexpected date format: " + dateString);
        }
        
        // Split and extract month
        String[] parts = dateString.split(delimiter);
        if(parts.length != 3) {
            return null;
        }
        
        Integer month = Integer.parseInt(parts[monthIndex]);
        
        // Validate month is between 1-12
        if(month < 1 || month > 12) {
            return null;
        }
        
        return month;
    }
}

