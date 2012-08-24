/**
 * 
 */
package org.ieee.iwson2.mfm.model.networkelements;

import java.awt.Point;

/**
 * @author Darshan
 */
public class Coordinate {
    public int x;
    public int y;

    public Coordinate(final int coordinateX, final int coordinateY) {
        x = coordinateX;
        y = coordinateY;
    }

    @Override
    public String toString() {
        return "[" + x + ", " + y + "]";
    }

    public double computeDistanceBetween(final Coordinate coordinate) {
        return Point.distance(x, y, coordinate.x, coordinate.y);
    }
}
