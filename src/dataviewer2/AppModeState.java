package dataviewer2;

import edu.du.dudraw.Draw;

public interface AppModeState {
	public void update(ApplicationState appState, PlotRenderer plotRenderer, Draw window, DataViewerData plotData);
	public void handleInput(int key, DataViewerApp dataViewerApp, ApplicationState appState);
}
