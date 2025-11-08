package dataviewer2;

import java.util.SortedMap;
import java.util.TreeMap;

public class DataViewerData {
	private TreeMap<Integer, SortedMap<Integer, Double>> m_plotData;
	private TreeMap<Integer, Double> m_plotMonthlyMaxValue;
	private TreeMap<Integer, Double> m_plotMonthlyMinValue;

	// initialize
	public DataViewerData() {
		m_plotData = new TreeMap<>();
		m_plotMonthlyMaxValue = new TreeMap<>();
		m_plotMonthlyMinValue = new TreeMap<>();
	}

	// self explanatory
	public void clearPlotData() {
		m_plotData.clear();
		m_plotMonthlyMaxValue.clear();
		m_plotMonthlyMinValue.clear();
	}

	public TreeMap<Integer, SortedMap<Integer, Double>> getPlotData() {
		return m_plotData;
	}

	public TreeMap<Integer, Double> getPlotMonthlyMaxValue() {
		return m_plotMonthlyMaxValue;
	}

	public TreeMap<Integer, Double> getPlotMonthlyMinValue() {
		return m_plotMonthlyMinValue;
	}

	public void setPlotData(TreeMap<Integer, SortedMap<Integer, Double>> newPlot) {
		m_plotData = newPlot;
	}

	public void setPlotDataMax(TreeMap<Integer, Double> newPlot) {
		m_plotMonthlyMaxValue = newPlot;
	}

	public void setPlotDataMin(TreeMap<Integer, Double> newPlot) {
		m_plotMonthlyMinValue = newPlot;
	}
}
