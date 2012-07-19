package org.ieee.iwson2.mfm.controller;

/**
 * Operation change event is notified via OperationChange interface
 * 
 * @author prem
 *
 */
public interface OperationChangeListener extends OperationStates {
	public void notifyOperationChange(final Operation_States operationChange);
}
