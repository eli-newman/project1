package dataviewer2;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.swing.JOptionPane;

import edu.du.dudraw.Draw;
import edu.du.dudraw.DrawListener;

/**
 * Main application coordinating all components.
 * Manages data, state, views, and user interactions.
 */
public class DataViewerApp implements DrawListener {
    // Components
    private DataViewerData plotData;
    private ApplicationState appState;
    private PlotRenderer plotRenderer;
    private FileLoader fileLoader;
    private Draw window;
    
    // Raw data storage
    private List<TemperatureRecord> dataRecords;
    private SortedSet<String> dataStates;
    private SortedSet<String> dataCountries;
    private SortedSet<Integer> dataYears;
    
    // Data file
    private String dataFilePath;
    
    /**
     * Create and initialize the application.
     */
    public DataViewerApp(String dataFile) throws FileNotFoundException {
        this.dataFilePath = dataFile;
        
        // Initialize components
        plotData = new DataViewerData();
        appState = new ApplicationState();
        plotRenderer = new PlotRenderer();
        fileLoader = new FileLoader();
        
        // Initialize data storage
        dataRecords = new ArrayList<>();
        dataStates = new TreeSet<>();
        dataCountries = new TreeSet<>();
        dataYears = new TreeSet<>();
        
        // Setup window
        window = new Draw(StaticReferences.WINDOW_TITLE);
        window.setCanvasSize(StaticReferences.WINDOW_WIDTH, StaticReferences.WINDOW_HEIGHT);
        window.enableDoubleBuffering();
        
        // Add listener
        window.addListener(this);
        
        // Load initial data
        loadData();
        
        // Draw initial screen
        update();
    }
    
    /**
     * Load data from file for currently selected country.
     */
    public void loadData() throws FileNotFoundException {
        // Clear existing data
        dataRecords.clear();
        dataStates.clear();
        dataCountries.clear();
        dataYears.clear();
        plotData.clearPlotData();
        
        // Load data for selected country
      List<List<Object>> rawData = fileLoader.loadData("Data/GlobalLandTemperaturesByState.csv");

		List<TemperatureRecord> records = new ArrayList<>();

		for (List<Object> row : rawData) {
		    try {
		        if (row.size() < 5) continue;

		        String dateString = row.get(0).toString().trim();
		        String tempStr = row.get(1).toString().trim();
		        String state = row.get(3).toString().trim();
		        String country = row.get(4).toString().trim();

		        // Skip header and blanks
		        if (dateString.equalsIgnoreCase("dt") || dateString.isEmpty() || tempStr.isEmpty())
		            continue;

		        // Parse date like "2000-01-01"
		        String[] parts = dateString.split("-");
		        if (parts.length < 2) continue;

		        int year = Integer.parseInt(parts[0]);
		        int month = Integer.parseInt(parts[1]);
		        double temperature = Double.parseDouble(tempStr);
		        records.add(new TemperatureRecord(year, month, temperature, state, country));


		    } catch (Exception e) {
		        // Ignore malformed or partial lines
		        System.err.println("Skipping malformed row: " + row);
		    }
		}

		System.out.println("Loaded " + records.size() + " temperature records.");
        // Add all records and extract metadata
        for(TemperatureRecord record : records) {
            dataRecords.add(record);
            dataStates.add(record.getState());
			dataCountries.add(record.getCountry());
            dataYears.add(record.getYear());
        }
        
        // Set default selections from loaded data
        if(!dataStates.isEmpty()) {
            appState.setSelectedState(dataStates.first());
        }
        if(!dataYears.isEmpty()) {
            appState.setSelectedStartYear(dataYears.first());
            appState.setSelectedEndYear(dataYears.last());
        }
        
        System.out.printf("INFO: loaded %d records\n", dataRecords.size());
        System.out.printf("INFO: loaded data for %d states\n", dataStates.size());
        System.out.printf("INFO: loaded data for %d years [%d, %d]\n", 
            dataYears.size(), 
            appState.getSelectedStartYear(), 
            appState.getSelectedEndYear());
    }
    
    /**
     * Process raw data into plot data.
     */
    private void updatePlotData() {
        // Initialize plot data structures
        TreeMap<Integer, SortedMap<Integer,Double>> newPlotData = new TreeMap<>();
        for(int month = 1; month <= 12; month++) {
            newPlotData.put(month, new TreeMap<Integer,Double>());
        }
        
        TreeMap<Integer,Double> monthlyMaxValue = new TreeMap<>();
        TreeMap<Integer,Double> monthlyMinValue = new TreeMap<>();
        
        // Initialize min/max
        for(int i = 1; i <= 12; i++) {
            monthlyMaxValue.put(i, Double.MIN_VALUE);
            monthlyMinValue.put(i, Double.MAX_VALUE);
        }
        
        // Filter raw data based on selections
        for(TemperatureRecord rec : dataRecords) {
            String recState = rec.getState();
            Integer recYear = rec.getYear();
            
            // Check if record matches selected state and year range
            if (recState.equals(appState.getSelectedState()) && 
               recYear >= appState.getSelectedStartYear() && 
               recYear <= appState.getSelectedEndYear()) {
                
                Integer month = rec.getMonth();
                Double temperature = rec.getTemperature();
                
                // Update min/max
                if(temperature < monthlyMinValue.get(month)) {
                    monthlyMinValue.put(month, temperature);
                }
                if(temperature > monthlyMaxValue.get(month)) {
                    monthlyMaxValue.put(month, temperature);
                }
                
                // Add to plot data
                newPlotData.get(month).put(recYear, temperature);
            }
        }
        
        // Update plot data using commands
        plotData.setPlotData(newPlotData);
        plotData.setPlotDataMax(monthlyMaxValue);
        plotData.setPlotDataMin(monthlyMinValue);
    }
    
    /**
     * Update the display.
     */
    @Override
    public void update() {
        if(appState.isInMenuMode()) {
            plotRenderer.drawMainMenu(window, 
                appState.getSelectedCountry(),
                appState.getSelectedState(),
                appState.getSelectedStartYear(),
                appState.getSelectedEndYear(),
                appState.getSelectedVisualization());
        } else if(appState.isInDataMode()) {
            plotRenderer.drawData(window, plotData,
                appState.getSelectedCountry(),
                appState.getSelectedState(),
                appState.getSelectedStartYear(),
                appState.getSelectedEndYear(),
                appState.getSelectedVisualization());
        }
        window.show();
    }
    
    // Keyboard input handling
    @Override
    public void keyPressed(int key) {
        // Q always quits
        if(key == 'Q') {
            System.out.println("Bye");
            System.exit(0);
        }
        
        if(appState.isInMenuMode()) {
            handleMenuInput(key);
        } else if(appState.isInDataMode()) {
            handlePlotInput(key);
        }
    }
    
    /**
     * Handle input when in main menu mode.
     */
    private void handleMenuInput(int key) {
        switch(key) {
            case 'C': // Set country
                Object selectedCountry = JOptionPane.showInputDialog(null,
                    "Choose a Country", "Input",
                    JOptionPane.INFORMATION_MESSAGE, null,
                    dataCountries.toArray(), appState.getSelectedCountry());
                
                if(selectedCountry != null && !selectedCountry.equals(appState.getSelectedCountry())) {
                    System.out.printf("INFO: User selected country: '%s'\n", selectedCountry);
                    appState.setSelectedCountry((String)selectedCountry);
                    try {
                        loadData();
                        update();
                    } catch (FileNotFoundException e) {
                        System.err.println("ERROR: Could not reload data: " + e.getMessage());
                    }
                }
                break;
                
            case 'T': // Set state
                Object selectedState = JOptionPane.showInputDialog(null,
                    "Choose a State", "Input",
                    JOptionPane.INFORMATION_MESSAGE, null,
                    dataStates.toArray(), appState.getSelectedState());
                
                if(selectedState != null && !selectedState.equals(appState.getSelectedState())) {
                    System.out.printf("INFO: User selected state: '%s'\n", selectedState);
                    appState.setSelectedState((String)selectedState);
                    updatePlotData();
                    update();
                }
                break;
                
            case 'S': // Set start year
                Object selectedStartYear = JOptionPane.showInputDialog(null,
                    "Choose the start year", "Input",
                    JOptionPane.INFORMATION_MESSAGE, null,
                    dataYears.toArray(), appState.getSelectedStartYear());
                
                if(selectedStartYear != null) {
                    Integer year = (Integer)selectedStartYear;
                    if(year > appState.getSelectedEndYear()) {
                        System.err.printf("ERROR: Start year (%d) cannot be after end year (%d)\n", 
                            year, appState.getSelectedEndYear());
                    } else if(!year.equals(appState.getSelectedStartYear())) {
                        System.out.printf("INFO: User selected start year: '%s'\n", selectedStartYear);
                        appState.setSelectedStartYear(year);
                        updatePlotData();
                        update();
                    }
                }
                break;
                
            case 'E': // Set end year
                Object selectedEndYear = JOptionPane.showInputDialog(null,
                    "Choose the end year", "Input",
                    JOptionPane.INFORMATION_MESSAGE, null,
                    dataYears.toArray(), appState.getSelectedEndYear());
                
                if(selectedEndYear != null) {
                    Integer year = (Integer)selectedEndYear;
                    if(year < appState.getSelectedStartYear()) {
                        System.err.printf("ERROR: End year (%d) cannot be before start year (%d)\n", 
                            year, appState.getSelectedStartYear());
                    } else if(!year.equals(appState.getSelectedEndYear())) {
                        System.out.printf("INFO: User selected end year: '%s'\n", selectedEndYear);
                        appState.setSelectedEndYear(year);
                        updatePlotData();
                        update();
                    }
                }
                break;
                
            case 'V': // Set visualization
                Object selectedViz = JOptionPane.showInputDialog(null,
                    "Choose the visualization mode", "Input",
                    JOptionPane.INFORMATION_MESSAGE, null,
                    StaticReferences.VISUALIZATION_MODES, appState.getSelectedVisualization());
                
                if(selectedViz != null && !selectedViz.equals(appState.getSelectedVisualization())) {
                    System.out.printf("INFO: User selected visualization: '%s'\n", selectedViz);
                    appState.setSelectedVisualization((String)selectedViz);
                    update();
                }
                break;
                
            case 'P': // Plot data
                appState.setGuiMode(ApplicationState.GUI_MODE_DATA);
                if(plotData.getPlotData().isEmpty()) {
                    updatePlotData();
                }
                update();
                break;
        }
    }
    
    /**
     * Handle input when in plot/data mode.
     */
    private void handlePlotInput(int key) {
        if(key == 'M') {
            appState.setGuiMode(ApplicationState.GUI_MODE_MAIN_MENU);
            update();
        }
    }
    
    // Unused DrawListener methods
    @Override
    public void keyReleased(int key) {}
    
    @Override
    public void keyTyped(char key) {}
    
    @Override
    public void mouseClicked(double x, double y) {}
    
    @Override
    public void mouseDragged(double x, double y) {}
    
    @Override
    public void mousePressed(double x, double y) {}
    
    @Override
    public void mouseReleased(double x, double y) {}
}
