package org.ieee.iwson2.mfm.model.networkelements;

/**
 * 
 * @author prem
 *
 */
public class SequenceGenerator {
	private static SequenceGenerator mySideIDGenerator = new SequenceGenerator();
	private int myCurrentSiteId, myCurrentCellId = 0;	
	
	private SequenceGenerator() {
		super();
	}
	
	public synchronized static SequenceGenerator getSideIDGenerator() {
		return mySideIDGenerator;
	}
	
	public synchronized int getNextSiteID(){
		return (myCurrentSiteId++);
	}
	
	public synchronized int getNextCellID(){
		return (myCurrentCellId++);
	}
}
