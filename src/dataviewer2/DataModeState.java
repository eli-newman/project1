package dataviewer2;

import edu.du.dudraw.Draw;

public class DataModeState implements AppModeState {
	public void update(ApplicationState appState, PlotRenderer plotRenderer, Draw window, DataViewerData plotData)
	{
		plotRenderer.drawData(window, plotData,
                appState.getSelectedCountry(),
                appState.getSelectedState(),
                appState.getSelectedStartYear(),
                appState.getSelectedEndYear(),
                appState.getSelectedVisualization());
	}
	
	public void handleInput(int key, DataViewerApp dataViewerApp, ApplicationState appState)
	{
		if(key == 'M') {
        	appState.setModeState(new MenuModeState());
            dataViewerApp.update();
        }
	}
}
