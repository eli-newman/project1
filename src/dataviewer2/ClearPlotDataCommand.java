package dataviewer2;

import java.util.SortedMap;
import java.util.TreeMap;

public class ClearPlotDataCommand extends Command {
	
	public ClearPlotDataCommand(DataViewerData dvd)
	{
		super(dvd);
	}
	
	public void execute()
	{
		dataViewerData.clearPlotData();
	}
}
