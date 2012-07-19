package org.ieee.iwson2.mfm.model.networkelements;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author prem
 * 
 */
public class Site implements NetworkElement {
	float myXCoOrdinate;
	float myYCoOrdinate;
	int mySiteid;
	List<Integer> myCells = new ArrayList<Integer>();

	public List<Integer> getMyCells() {
		return myCells;
	}

	public void setMyCells(final Integer myCells) {
		this.myCells.add(myCells);
	}

	public int getSiteId() {
		return mySiteid;
	}

	public float getX1() {
		return myXCoOrdinate;
	}

	public void setX1(float x1) {
		this.myXCoOrdinate = x1;
	}

	public float getY1() {
		return myYCoOrdinate;
	}

	public void setY1(float y1) {
		this.myYCoOrdinate = y1;
	}

	public Site(float x1, float y1) {
		super();
		this.mySiteid = SequenceGenerator.getSideIDGenerator().getNextSiteID();
		this.myXCoOrdinate = x1;
		this.myYCoOrdinate = y1;
	}
}
