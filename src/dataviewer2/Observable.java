package dataviewer2;

import dataviewer2.ApplicationState.DataType;

public interface Observable {
	public void addObserver(Observer observer);
	public void removeObserver(Observer observer);
	public void notifyObservers(DataType dataType);
}
