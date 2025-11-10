package dataviewer2;

/**
 * Static constants for the DataViewer application.
 * Centralized location for unchanging configuration values.
 */
public class StaticReferences {
    // Window dimensions
    public static final int WINDOW_WIDTH = 1320;  // Multiple of 12 for months
    public static final int WINDOW_HEIGHT = 720;
    public static final String WINDOW_TITLE = "DataViewer Application";
    public static final double DATA_WINDOW_BORDER = 50.0;
    
    // Menu layout
    public static final double MENU_STARTING_X = 40.0;
    public static final double MENU_STARTING_Y = 90.0;
    public static final double MENU_ITEM_SPACING = 5.0;
    
    // Temperature range for color mapping
    public static final double TEMPERATURE_MIN_C = -10.0;
    public static final double TEMPERATURE_MAX_C = 30.0;
    public static final double TEMPERATURE_RANGE = TEMPERATURE_MAX_C - TEMPERATURE_MIN_C;
    
    // Extrema visualization
    public static final double EXTREMA_PCT = 0.1;  // 10% threshold
    
    // Visualization modes
    public static final String[] VISUALIZATION_MODES = {
        "Raw",
        "Extrema (within 10% of min/max)"
    };
    public static final int VISUALIZATION_RAW_IDX = 0;
    public static final int VISUALIZATION_EXTREMA_IDX = 1;
    
    // Month names (1-based index)
    public static final String[] MONTH_NAMES = {
        "",  // Index 0 unused
        "Jan", "Feb", "Mar", "Apr", "May", "Jun",
        "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
    };
    
    // CSV file structure
    public static final int FILE_DATE_IDX = 0;
    public static final int FILE_TEMPERATURE_IDX = 1;
    public static final int FILE_UNCERTAINTY_IDX = 2;
    public static final int FILE_STATE_IDX = 3;
    public static final int FILE_COUNTRY_IDX = 4;
    public static final int FILE_NUM_COLUMNS = 5;
    
    // Default values
    public static final String DEFAULT_COUNTRY = "United States";
}


