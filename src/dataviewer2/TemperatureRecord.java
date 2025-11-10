package dataviewer2;

/**
 * Type-safe model for temperature data record.
 * Replaces List<Object> with proper typed fields.
 */
public class TemperatureRecord {
    private final Integer year;
    private final Integer month;
    private final Double temperature;
    private final String state;
    
    /**
     * Create a new temperature record.
     * 
     * @param year Year of the record
     * @param month Month of the record (1-12)
     * @param temperature Temperature in Celsius
     * @param state State name
     */
    public TemperatureRecord(Integer year, Integer month, Double temperature, String state) {
        this.year = year;
        this.month = month;
        this.temperature = temperature;
        this.state = state;
    }
    
    public Integer getYear() {
        return year;
    }
    
    public Integer getMonth() {
        return month;
    }
    
    public Double getTemperature() {
        return temperature;
    }
    
    public String getState() {
        return state;
    }
    
    @Override
    public String toString() {
        return String.format("TemperatureRecord[%d-%02d, %s, %.2f°C]", 
            year, month, state, temperature);
    }
}

