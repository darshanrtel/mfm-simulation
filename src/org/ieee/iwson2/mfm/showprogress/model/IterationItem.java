/**
 * 
 */
package org.ieee.iwson2.mfm.showprogress.model;

/**
 * @author ppradhan
 * 
 */
public class IterationItem implements Comparable<IterationItem> {

	private final String myName;
	private final double myRepulsionValue;

	public IterationItem(final String name, final double cost) {

		myName = name;
		myRepulsionValue = cost;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "IterationItem [name=" + myName + ", Repulsion="
				+ myRepulsionValue + "]";
	}

	public String getItemName() {
		return myName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object arg) {
		if (arg instanceof IterationItem) {
			IterationItem temp = (IterationItem) arg;
			return this.myName.equalsIgnoreCase(temp.myName);
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

	public double getCost() {
		return myRepulsionValue;
	}

	@Override
	public int compareTo(final IterationItem that) {
		return this.myName.compareTo(that.myName);
	}
}
