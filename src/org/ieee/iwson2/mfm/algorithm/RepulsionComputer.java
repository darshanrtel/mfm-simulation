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
public class RepulsionComputer {

    private static final int REPULSION_FACTOR_DUE_TO_COLLISION = 10000;
    private static final int REPULSION_FACTOR_DUE_TO_CONFUSION = 10000;
    private static final int REPUSLION_FACTOR_DUE_TO_REUSE_DISTANCE = 8000;
    private static final int REPUSLION_FACTOR_DUE_TO_MOD_THREE = 1000;

    public static int computeRepulsionInTheSystem(final List<Cell> cells) {
        int rSystem = 0;
        for (final Cell cell : cells) {
            final int repulsionDueToCollision = computeRepulsionDueToCollision(cell);
            final int replusionDueToConfusion = computeRepulsionDueToConfusion(cell);
            final int replusionDueToReuseDistance = computeRepulsionDueToReuseDistance(cell, cells);
            final int replusionDueToMod3 = computeRepulsionDueToModuloThreeRule(cell, cells);
            rSystem += (repulsionDueToCollision + replusionDueToConfusion + replusionDueToReuseDistance + replusionDueToMod3);
        }
        return rSystem;
    }

    private static int computeRepulsionDueToModuloThreeRule(final Cell cell, final List<Cell> cells) {
        for (final Cell otherCell : cells) {
            if (!cell.equals(otherCell) && cell.isSibling(otherCell)
                    && isCellMagnetizedForMod3Rule(cell, otherCell)) {
                return REPUSLION_FACTOR_DUE_TO_MOD_THREE;
            }
        }
        return 0;
    }

    private static boolean isCellMagnetizedForMod3Rule(final Cell cell, final Cell otherCell) {
        return cell.getFrequency() == otherCell.getFrequency() && cell.getPci() / 3 != otherCell.getPci() / 3;
    }

    private static int computeRepulsionDueToReuseDistance(final Cell cell, final List<Cell> cells) {
        int replusionDueToReuseDistance = 0;
        for (final Cell otherCell : cells) {
            if (!cell.equals(otherCell)) {
                final double distance = cell.computeDistanceBetween(otherCell);
                if (isCellsRepelling(cell, otherCell)
                        && Double.compare(distance, cell.getExpectedCellRange()) <= 0) {
                    replusionDueToReuseDistance += REPUSLION_FACTOR_DUE_TO_REUSE_DISTANCE;
                }
            }
        }
        return replusionDueToReuseDistance;
    }

    private static int computeRepulsionDueToCollision(final Cell cell) {
        final List<Cell> neighbors = cell.getNeighbors();
        int repulsionDueToCollision = 0;
        for (final Cell neighbor : neighbors) {
            if (isCellsRepelling(cell, neighbor)) {
                repulsionDueToCollision += REPULSION_FACTOR_DUE_TO_COLLISION;
            }
        }
        return repulsionDueToCollision;
    }

    private static int computeRepulsionDueToConfusion(final Cell cell) {
        final List<Cell> neighbors = cell.getNeighbors();
        int repulsionDueToConfusion = 0;
        List<Cell> consideredCells = new ArrayList<Cell>();
        for (final Cell neighbor : neighbors) {
            final List<Cell> secondLevelNeighbors = neighbor.getNeighbors();
            for (final Cell secondLevelNeighbor : secondLevelNeighbors) {
                if (!consideredCells.contains(secondLevelNeighbor) && !cell.equals(secondLevelNeighbor)
                        && isCellsRepelling(cell, secondLevelNeighbor)) {
                    repulsionDueToConfusion += REPULSION_FACTOR_DUE_TO_CONFUSION;
                    consideredCells.add(secondLevelNeighbor);
                }
            }
        }
        return repulsionDueToConfusion;
    }

    private static boolean isCellsRepelling(final Cell cell, final Cell secondLevelNeighbor) {
        return cell.getFrequency() == secondLevelNeighbor.getFrequency()
                && cell.getPci() == secondLevelNeighbor.getPci();
    }
}
