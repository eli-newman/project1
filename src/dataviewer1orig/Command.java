package dataviewer1orig;

public abstract class Command {
	protected DataViewerData dataViewerData;
	
	public Command(DataViewerData dvd)
	{
		dataViewerData = dvd;
	}
	
	public abstract void execute();
}
