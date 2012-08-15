/**
 * 
 */
package org.ieee.iwson2.mfm.algorithm;

import java.util.ArrayList;
import java.util.List;

import org.ieee.iwson2.mfm.model.networkelements.Cell;

/**
 * @author darr
 */
public class AvailablePciProvider {

    private static final List<Integer> allPcis = new ArrayList<Integer>();

    static {
        for (int i = 0; i < 504; i++) {
            allPcis.add(Integer.valueOf(i));
        }
    }

    public static int getBestPossiblePci(final Cell cell, final List<Cell> cells, final List<Integer> siblingPcis) {
        final List<Integer> pcis = new ArrayList<Integer>(allPcis);
        removeCollidingPcis(cell, pcis);
        removeConfusingPcis(cell, pcis);
        removeReuseDistanceViolatingPcis(cell, cells, pcis);
        return bestPciFollowingMod3Rule(cell, siblingPcis, pcis);
    }

    private static int bestPciFollowingMod3Rule(final Cell cell, final List<Integer> siblingPcis,
            final List<Integer> pcis) {
        for (final Integer siblingPci : siblingPcis) {
            if (siblingPci != cell.getPci()) {
                final int available = getAvailableSiblingPci(siblingPci, pcis);
                if (available != -1) {
                    return available;
                }
            }
        }
        final int suitablePci = getSuitablePci(pcis);
        if (suitablePci != -1) {
            return suitablePci;
        }

        if (pcis.contains(cell.getPci())) {
            return cell.getPci();
        }
        return pcis.isEmpty() ? -1 : pcis.get(0);
    }

    private static int getSuitablePci(final List<Integer> pcis) {
        int count = 0;
        for (final Integer integer : pcis) {
            if (integer % 3 == 0) {
                count = 0;
            }
            count++;
            if (count == 3) {
                return (integer / 3) * 3;
            }
        }
        return -1;
    }

    private static int getAvailableSiblingPci(final int siblingPci, final List<Integer> pcis) {
        final int startingPci = (siblingPci / 3) * 3;
        for (int i = startingPci; i < startingPci + 3; i++) {
            if (siblingPci != i && pcis.contains(Integer.valueOf(i))) {
                return i;
            }
        }
        return -1;
    }

    private static void removeReuseDistanceViolatingPcis(final Cell cell, final List<Cell> cells,
            final List<Integer> pcis) {
        for (final Cell otherCell : cells) {
            if (!otherCell.equals(cell)
                    && Double.compare(cell.computeDistanceBetween(otherCell), cell.getExpectedCellRange()) <= 0) {
                pcis.remove(Integer.valueOf(otherCell.getPci()));
            }
        }
    }

    private static void removeConfusingPcis(final Cell cell, final List<Integer> pcis) {
        final List<Cell> neighbors = cell.getNeighbors();
        for (final Cell neighbor : neighbors) {
            final List<Cell> secondNeighbors = neighbor.getNeighbors();
            for (final Cell secondNeighbor : secondNeighbors) {
                if (!secondNeighbor.equals(cell)) {
                    pcis.remove(Integer.valueOf(secondNeighbor.getPci()));
                }
            }
        }
    }

    private static void removeCollidingPcis(final Cell cell, final List<Integer> pcis) {
        final List<Cell> neighbors = cell.getNeighbors();
        for (final Cell neighbor : neighbors) {
            pcis.remove(Integer.valueOf(neighbor.getPci()));
        }
    }

}
