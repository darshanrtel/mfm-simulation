package org.ieee.iwson2.mfm.controller;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author prem
 * 
 */
// FIXME: Potential candidate for spring
public class OperationChangeController implements OperationChangeListener, OperationChangeNotifier {
	private static OperationChangeListener myOperationChange = new OperationChangeController();
	private List<OperationChangeListener> myObservers = new ArrayList<OperationChangeListener>();

	public synchronized static OperationChangeListener getOperationChangeNotifier() {
		return myOperationChange;
	}

	@Override
	public void notifyOperationChange(Operation_States operationChange) {
		for (OperationChangeListener observer : myObservers) {
			observer.notifyOperationChange(operationChange);
		}
	}

	@Override
	public void subscribe(OperationChangeListener observer) {
		myObservers.add(observer);
	}
}
