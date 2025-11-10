package dataviewer2;

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
