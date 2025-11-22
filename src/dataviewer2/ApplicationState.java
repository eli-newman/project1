package dataviewer2;

/**
 * Stores user selections and GUI state.
 * Separates state management from application logic.
 */
public class ApplicationState {
    
    // Default values
    private static final String DEFAULT_COUNTRY = "United States";
    private static final String DEFAULT_VISUALIZATION = "Raw";
    
    // User selections
    private String selectedCountry;
    private String selectedState;
    private Integer selectedStartYear;
    private Integer selectedEndYear;
    private String selectedVisualization;
    
    
    private AppModeState currentState;
    
    /**
     * Create new application state with default values.
     */
    public ApplicationState() {
        this.selectedCountry = DEFAULT_COUNTRY;
        this.selectedVisualization = DEFAULT_VISUALIZATION;
        currentState = new MenuModeState();
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
    public void setModeState(AppModeState newState)
    {
    	currentState = newState;
    }
    
    public AppModeState getAppModeState()
    {
    	return currentState;
    }
}

