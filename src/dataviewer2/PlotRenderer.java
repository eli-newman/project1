package dataviewer1orig;

import java.awt.Color;
import java.util.SortedMap;

import edu.du.dudraw.Draw;

public class PlotRenderer {
	public StaticReferences statref;
	public ColorUtil colorUtil;
	
	public void drawMainMenu(Draw window, String selectedCountry, String selectedState, int selectedStartYear, int selectedEndYear, String selectedVisualization) {
		window.clear(Color.WHITE);

    	String[] menuItems = {
    			"Type the menu number to select that option:",
    			"",
    			String.format("C     Set country: [%s]", selectedCountry),
    			String.format("T     Set state: [%s]", selectedState),
    			String.format("S     Set start year [%d]", selectedStartYear),
    			String.format("E     Set end year [%d]", selectedEndYear),
    			String.format("V     Set visualization [%s]", selectedVisualization),
    			String.format("P     Plot data"),
    			String.format("Q     Quit"),
    	};
    	
    	// enable drawing by "percentage" with the menu drawing
    	window.setXscale(0, 100);
    	window.setYscale(0, 100);
		
		// draw the menu
    	window.setPenColor(Color.BLACK);
		
		drawMenuItems(window, menuItems);
    }
	
	private void drawData(Draw window, DataViewerData data, String m_selectedCountry, String m_selectedState, int m_selectedStartYear, int m_selectedEndYear, String m_selectedVisualizationn) {
    	// Give a buffer around the plot window
		window.setXscale(-statref.DATA_WINDOW_BORDER, statref.WINDOW_WIDTH+statref.DATA_WINDOW_BORDER);
		window.setYscale(-statref.DATA_WINDOW_BORDER, statref.WINDOW_HEIGHT+statref.DATA_WINDOW_BORDER);

    	// gray background
		window.clear(Color.LIGHT_GRAY);

    	// white plot area
		window.setPenColor(Color.WHITE);
		window.filledRectangle(statref.WINDOW_WIDTH/2.0, statref.WINDOW_HEIGHT/2.0, statref.WINDOW_WIDTH/2.0, statref.WINDOW_HEIGHT/2.0);  

		window.setPenColor(Color.BLACK);
    	
    	double nCols = 12; // one for each month
    	double nRows = m_selectedEndYear - m_selectedStartYear + 1; // for the years
 		
        double cellWidth = statref.WINDOW_WIDTH / nCols;
        double cellHeight = statref.WINDOW_HEIGHT / nRows;
        
        boolean extremaVisualization = m_selectedVisualization.equals(statref.VISUALIZATION_MODES[statref.VISUALIZATION_EXTREMA_IDX]);
        info("visualization: %s (extrema == %b)", m_selectedVisualization, extremaVisualization);
        
        for(int month = 1; month <= 12; month++) {
            double fullRange = data.getPlotMonthlyMaxValue.get(month) - data.getPlotMonthlyMinValue.get(month);
            double extremaMinBound = data.getPlotMonthlyMinValue.get(month) + statref.EXTREMA_PCT * fullRange;
            double extremaMaxBound = data.getPlotMonthlyMaxValue.get(month) - statref.EXTREMA_PCT * fullRange;


            // draw the line separating the months and the month label
            window.setPenColor(Color.BLACK);
        	double lineX = (month-1.0)*cellWidth;
        	window.line(lineX, 0.0, lineX, statref.WINDOW_HEIGHT);
        	window.text(lineX+cellWidth/2.0, -statref.DATA_WINDOW_BORDER/2.0, statref.MONTH_NAMES[month]);
        	
        	// there should always be a map for the month
        	SortedMap<Integer,Double> monthData = data.getPlotData.get(month);
        	
        	for(int year = m_selectedStartYear; year <= m_selectedEndYear; year++) {

        		// month data structure might not have every year
        		if(monthData.containsKey(year)) {
        			Double value = monthData.get(year);
        			
        			double x = (month-1.0)*cellWidth + 0.5 * cellWidth;
        			double y = (year-m_selectedStartYear)*cellHeight + 0.5 * cellHeight;
        			
        			Color cellColor = null;
        			
        			// get either color or grayscale depending on visualization mode
        			if(extremaVisualization && value > extremaMinBound && value < extremaMaxBound) {
        				cellColor = colorUtil.getColorTemperature(value, true);
        			}
        			else if(extremaVisualization) {
        				// doing extrema visualization, show "high" values in red "low" values in blue.
        				if(value >= extremaMaxBound) {
        					cellColor = Color.RED;
        				}
        				else {
        					cellColor = Color.BLUE;
        				}
        			}
        			else {
        				cellColor = colorUtil.getColorTemperature(value, false);
        			}
        			
        			// draw the rectangle for this data point
        			window.setPenColor(cellColor);
        			window.filledRectangle(x, y, cellWidth/2.0, cellHeight/2.0);
        		}
        	}
        }
        
        // draw the labels for the y-axis
        window.setPenColor(Color.BLACK);

        double labelYearSpacing = (m_selectedEndYear - m_selectedStartYear) / 5.0;
        double labelYSpacing = statref.WINDOW_HEIGHT/5.0;
        // spaced out by 5, but need both the first and last label, so iterate 6
        for(int i=0; i<6; i++) {
        	int year = (int)Math.round(i * labelYearSpacing + m_selectedStartYear);
        	String text = String.format("%4d", year);
        	
        	window.textRight(0.0, i*labelYSpacing, text);
        	window.textLeft(statref.WINDOW_WIDTH, i*labelYSpacing, text);
        }
     
        // draw rectangle around the whole data plot window
        window.rectangle(statref.WINDOW_WIDTH/2.0, statref.WINDOW_HEIGHT/2.0, statref.WINDOW_WIDTH/2.0, statref.WINDOW_HEIGHT/2.0);
        
        // put in the title
        String title = String.format("%s, %s from %d to %d. Press 'M' for Main Menu.  Press 'Q' to Quit.",
        		m_selectedState, m_selectedCountry, m_selectedStartYear, m_selectedEndYear);
        window.text(statref.WINDOW_WIDTH/2.0, statref.WINDOW_HEIGHT + statref.DATA_WINDOW_BORDER/2.0, title);
	}
	
	public void drawMenuItems(Draw window, String[] menuItems) {
		double yCoord = statref.MENU_STARTING_Y;
		
		for(int i=0; i<menuItems.length; i++) {
			window.textLeft(statref.MENU_STARTING_X, yCoord, menuItems[i]);
			yCoord -= statref.MENU_ITEM_SPACING;
		}
	}
	
	
}
