package dataviewer2;

import java.util.TreeMap;

public class SetPlotDataMinCommand extends Command {
	private TreeMap<Integer,Double> newData;
	
	public SetPlotDataMinCommand(DataViewerData dvd, TreeMap<Integer,Double> nd)
	{
		super(dvd);
		newData = nd;
	}
	
	public void execute()
	{
		dataViewerData.setPlotDataMin(newData);
	}
}