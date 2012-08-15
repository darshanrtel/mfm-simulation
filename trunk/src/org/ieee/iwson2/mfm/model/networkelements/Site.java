package org.ieee.iwson2.mfm.model.networkelements;

import java.util.ArrayList;
import java.util.List;

/**
 * @author prem
 */
public class Site implements NetworkElement {
    int myXCoOrdinate;
    int myYCoOrdinate;
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

    public int getX1() {
        return myXCoOrdinate;
    }

    public void setX1(int x1) {
        this.myXCoOrdinate = x1;
    }

    public int getY1() {
        return myYCoOrdinate;
    }

    public void setY1(int y1) {
        this.myYCoOrdinate = y1;
    }

    public Coordinate getCoordinate() {
        return new Coordinate(myXCoOrdinate, myYCoOrdinate);
    }

    public Site(int x1, int y1) {
        super();
        this.mySiteid = SequenceGenerator.getSideIDGenerator().getNextSiteID();
        this.myXCoOrdinate = x1;
        this.myYCoOrdinate = y1;
    }
}
