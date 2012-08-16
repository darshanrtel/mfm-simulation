/**
 * 
 */
package org.ieee.iwson2.mfm.showprogress.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author ppradhan
 * 
 */
public class Iteration implements Comparable<Iteration> {
	private final String myName;
	private final double myRepulsionValue;
	private final List<IterationItem> myItems = new ArrayList<IterationItem>();

	/**
	 * @return the myName
	 */
	public String getName() {
		return myName;
	}

	public List<IterationItem> getIterationItems() {
		return Collections.unmodifiableList(myItems);
	}

	public boolean addIterationItem(final IterationItem item) {
		if (!myItems.contains(item)) {
			return myItems.add(item);
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object arg) {
		if (arg instanceof Iteration) {
			return this.myName.equalsIgnoreCase(((Iteration) arg).myName);
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return myName.hashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Iteration [name=" + myName + " repulsion =" + myRepulsionValue
				+ " items =" + myItems.toString() + "]";
	}

	public Iteration(final String name, final double repulsionValue) {
		myName = name;
		myRepulsionValue = repulsionValue;
	}

	@Override
	public int compareTo(Iteration that) {
		return this.myName.compareTo(that.myName);
	}
}
