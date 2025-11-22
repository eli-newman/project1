package dataviewer2;

import java.util.ArrayList;
import java.util.List;


/**
 * Stores user selections and GUI state.
 * Separates state management from application logic.
 */
public class ApplicationState implements Observable {
    
    // Default values
    private static final String DEFAULT_COUNTRY = "United States";
    private static final String DEFAULT_VISUALIZATION = "Raw";
    
    public enum DataType
    {
    	country,
    	state,
    	startYear,
    	endYear,
    	visualization
    }
    
    // User selections
    private String selectedCountry;
    private String selectedState;
    private Integer selectedStartYear;
    private Integer selectedEndYear;
    private String selectedVisualization;
    
    private AppModeState currentState;
    
    private List<Observer> observers = new ArrayList<Observer>();
    
    /**
     * Create new application state with default values.
     */
    public ApplicationState() {
        this.selectedCountry = DEFAULT_COUNTRY;
        this.selectedVisualization = DEFAULT_VISUALIZATION;
        setModeState(new MenuModeState());
        
    }
    
    // Country
    public String getSelectedCountry() {
        return selectedCountry;
    }
    
    public void setSelectedCountry(String selectedCountry) {
        this.selectedCountry = selectedCountry;
        notifyObservers(DataType.country);
    }
    
    // State
    public String getSelectedState() {
        return selectedState;
    }
    
    public void setSelectedState(String selectedState) {
        this.selectedState = selectedState;
        notifyObservers(DataType.state);
    }
    
    // Start Year
    public Integer getSelectedStartYear() {
        return selectedStartYear;
    }
    
    public void setSelectedStartYear(Integer selectedStartYear) {
        this.selectedStartYear = selectedStartYear;
        notifyObservers(DataType.startYear);
    }
    
    // End Year
    public Integer getSelectedEndYear() {
        return selectedEndYear;
    }
    
    public void setSelectedEndYear(Integer selectedEndYear) {
        this.selectedEndYear = selectedEndYear;
        notifyObservers(DataType.endYear);
    }
    
    // Visualization
    public String getSelectedVisualization() {
        return selectedVisualization;
    }
    
    public void setSelectedVisualization(String selectedVisualization) {
        this.selectedVisualization = selectedVisualization;
        notifyObservers(DataType.visualization);
    }
    

    // GUI Mode
    public void setModeState(AppModeState newState)
    {
    	if (currentState != null && currentState instanceof MenuModeState)
    	{
    		removeObserver((Observer) currentState);
    	}
    	currentState = newState;
    	System.out.println("state changed " + newState);
    	if (currentState instanceof MenuModeState)
    	{
    		addObserver((Observer) currentState);
    	}
    }
    
    public AppModeState getAppModeState()
    {
    	return currentState;
    }

	@Override
	public void addObserver(Observer observer) {
		observers.add(observer);
		System.out.println("observer found");
	}

	@Override
	public void removeObserver(Observer observer) {
		observers.remove(observer);
	}

	@Override
	public void notifyObservers(DataType dataType) {
		System.out.println(dataType);
		
		switch(dataType)
		{
			case country:
				for(Observer observer : observers)
				{
					observer.onCountryChanged(selectedCountry);
				}
			case endYear:
				for(Observer observer : observers)
				{
					observer.onEndYearChanged(selectedEndYear);
				}
			case startYear:
				for(Observer observer : observers)
				{
					observer.onStartYearChanged(selectedStartYear);
				}
			case state:
				for(Observer observer : observers)
				{
					observer.onStateChanged(selectedState);
				}
			case visualization:
				for(Observer observer : observers)
				{
					observer.onVisualizationChanged(selectedVisualization);
				}
			default:
				break;
			
		}
		
	}
}

