package dataviewer1orig;

import java.util.TreeMap;

public class SetPlotDataMaxCommand extends Command {
	private TreeMap<Integer,Double> newData;
	
	public SetPlotDataMaxCommand(DataViewerData dvd, TreeMap<Integer,Double> nd)
	{
		super(dvd);
		newData = nd;
	}
	
	public void execute()
	{
		dataViewerData.setPlotDataMax(newData);
	}
}