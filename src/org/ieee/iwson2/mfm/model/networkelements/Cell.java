package org.ieee.iwson2.mfm.model.networkelements;

/**
 * 
 * @author prem
 * 
 */
public class Cell implements NetworkElement {
	int mySiteid, myCellid;
	short myBearing;

	short myExpectedCellRange;

	public Cell(final int siteId, final int cellID, final short bearingID) {
		super();
		this.mySiteid = siteId;
		this.myBearing = bearingID;
		this.myCellid = cellID;
	}

	public void setExpectedCellRange(final short ECR) {
		this.myExpectedCellRange = ECR;
	}

	public short getExpectedCellRange() {
		return myExpectedCellRange;
	}

	public int getSiteId() {
		return mySiteid;
	}
	
	public short getMyBearing() {
		return myBearing;
	}	
}
