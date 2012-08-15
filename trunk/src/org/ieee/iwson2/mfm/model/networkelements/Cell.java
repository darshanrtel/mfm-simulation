package org.ieee.iwson2.mfm.model.networkelements;

import java.util.Collections;
import java.util.List;

/**
 * @author prem
 */
public class Cell implements NetworkElement {
    int mySiteid, myCellid;
    short myBearing;
    int myPCI;
    private int myFrequency;

    short myExpectedCellRange;
    private String myName;
    private final Coordinate mySiteCoordinate;

    public Cell(final int siteId, final int cellID, final short bearingID, final Coordinate coordinate) {
        super();
        this.mySiteid = siteId;
        this.myBearing = bearingID;
        this.myCellid = cellID;
        this.mySiteCoordinate = coordinate;
        myName = "CELL-" + myCellid;
        myFrequency = 1; // TODO: Frequency to be a parameter
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

    public int getParentId() {
        return mySiteid;
    }

    public short getMyBearing() {
        return myBearing;
    }

    public int getPci() {
        return myPCI;
    }

    public void setPci(final int pciValue) {
        this.myPCI = pciValue;
    }

    /**
     * TODO: Neighbors currently is taken care by close by cells within Expected cell range. Hence returning empty
     * list.
     * @return
     */
    public List<Cell> getNeighbors() {
        return Collections.emptyList();
    }

    public boolean isSibling(final Cell otherCell) {
        return mySiteid == otherCell.mySiteid;
    }

    public String getName() {
        return myName;
    }

    public double computeDistanceBetween(final Cell cell) {
        return mySiteCoordinate.computeDistanceBetween(cell.mySiteCoordinate);
    }

    public int getFrequency() {
        return myFrequency;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(myName);
        builder.append("[");
        // builder.append(myParent);
        // builder.append("-");
        builder.append(mySiteCoordinate);
        builder.append("-");
        // builder.append(getExpectedCellRange());
        // builder.append("-");
        builder.append(getPci());
        builder.append("]");
        return builder.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + myCellid;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Cell other = (Cell) obj;
        if (myCellid != other.myCellid)
            return false;
        return true;
    }

}
