package dataviewer2;

/**
 * Stores user selections and GUI state.
 * Separates state management from application logic.
 */
public class ApplicationState {
    // GUI mode constants
    public static final int GUI_MODE_MAIN_MENU = 0;
    public static final int GUI_MODE_DATA = 1;
    
    // Default values
    private static final String DEFAULT_COUNTRY = "United States";
    private static final String DEFAULT_VISUALIZATION = "Raw";
    
    // User selections
    private String selectedCountry;
    private String selectedState;
    private Integer selectedStartYear;
    private Integer selectedEndYear;
    private String selectedVisualization;
    
    // GUI state
    private int guiMode;
    
    /**
     * Create new application state with default values.
     */
    public ApplicationState() {
        this.selectedCountry = DEFAULT_COUNTRY;
        this.selectedVisualization = DEFAULT_VISUALIZATION;
        this.guiMode = GUI_MODE_MAIN_MENU;
    }
    
    // Country
    public String getSelectedCountry() {
        return selectedCountry;
    }
    
    public void setSelectedCountry(String selectedCountry) {
        this.selectedCountry = selectedCountry;
    }
    
    // State
    public String getSelectedState() {
        return selectedState;
    }
    
    public void setSelectedState(String selectedState) {
        this.selectedState = selectedState;
    }
    
    // Start Year
    public Integer getSelectedStartYear() {
        return selectedStartYear;
    }
    
    public void setSelectedStartYear(Integer selectedStartYear) {
        this.selectedStartYear = selectedStartYear;
    }
    
    // End Year
    public Integer getSelectedEndYear() {
        return selectedEndYear;
    }
    
    public void setSelectedEndYear(Integer selectedEndYear) {
        this.selectedEndYear = selectedEndYear;
    }
    
    // Visualization
    public String getSelectedVisualization() {
        return selectedVisualization;
    }
    
    public void setSelectedVisualization(String selectedVisualization) {
        this.selectedVisualization = selectedVisualization;
    }
    
    // GUI Mode
    public int getGuiMode() {
        return guiMode;
    }
    
    public void setGuiMode(int guiMode) {
        this.guiMode = guiMode;
    }
    
    /**
     * Check if currently in main menu mode.
     */
    public boolean isInMenuMode() {
        return guiMode == GUI_MODE_MAIN_MENU;
    }
    
    /**
     * Check if currently in data/plot mode.
     */
    public boolean isInDataMode() {
        return guiMode == GUI_MODE_DATA;
    }
}

