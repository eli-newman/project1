package dataviewer2;

public interface Observer {
	public void onCountryChanged(String newValue);
	public void onStateChanged(String newValue);
	public void onStartYearChanged(int newValue);
	public void onEndYearChanged(int newValue);
	public void onVisualizationChanged(String newValue);
}
