package dataviewer2;

import edu.du.dudraw.Draw;

public class MenuModeState implements AppModeState, Observer {
	private boolean updated = true;
	
	public void update(ApplicationState appState, PlotRenderer plotRenderer, Draw window, DataViewerData plotData)
	{
		if (!updated)
		{
			return;
		}
		updated = false;
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

	//having 5 methods to recieve data is a lot more flexible than having one, because it means we can have different updates if specific values have changed.
	
	@Override
	public void onCountryChanged(String newValue) {
		updated = true;
		
	}

	@Override
	public void onStateChanged(String newValue) {
		updated = true;
	}

	@Override
	public void onStartYearChanged(int newValue) {
		updated = true;
	}

	@Override
	public void onEndYearChanged(int newValue) {
		updated = true;
	}

	@Override
	public void onVisualizationChanged(String newValue) {
		updated = true;
	}
}
