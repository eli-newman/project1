package dataviewer1orig;

import java.util.SortedMap;
import java.util.TreeMap;

public class SetPlotDataCommand extends Command {
	private TreeMap<Integer, SortedMap<Integer,Double>> newData;
	
	public SetPlotDataCommand(DataViewerData dvd, TreeMap<Integer, SortedMap<Integer,Double>> nd)
	{
		super(dvd);
		newData = nd;
	}
	
	public void execute()
	{
		dataViewerData.setPlotData(newData);
	}
}
