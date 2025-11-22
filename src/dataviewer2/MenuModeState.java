package dataviewer2;

import edu.du.dudraw.Draw;

public class MenuModeState implements AppModeState {
	public void update(ApplicationState appState, PlotRenderer plotRenderer, Draw window, DataViewerData plotData)
	{
		plotRenderer.drawMainMenu(window, 
                appState.getSelectedCountry(),
                appState.getSelectedState(),
                appState.getSelectedStartYear(),
                appState.getSelectedEndYear(),
                appState.getSelectedVisualization());
	}
	
	public void handleInput(int key, DataViewerApp dataViewerApp, ApplicationState appState)
	{
		dataViewerApp.handleMenuInput(key);
	}
}
