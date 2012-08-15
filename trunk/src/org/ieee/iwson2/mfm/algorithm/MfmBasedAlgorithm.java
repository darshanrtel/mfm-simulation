/**
 * 
 */
package org.ieee.iwson2.mfm.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.ieee.iwson2.mfm.model.networkelements.Cell;

/**
 * @author darr
 */
public class MfmBasedAlgorithm {

    private final MfmCellCollection myCellController;
    private final Random myRandom;

    public MfmBasedAlgorithm(final MfmCellCollection cellController) {
        myCellController = cellController;
        myRandom = new Random();
    }

    public void execute() {
        int rSystem = RepulsionComputer.computeRepulsionInTheSystem(myCellController.getCells());
        final Map<Integer, List<Cell>> parentCellMap = myCellController.getParentCellMap();
        final List<Integer> parentList = new ArrayList<Integer>(parentCellMap.keySet());
        final TimerThread timer = startTimer();
        while (!timer.isStopped()) {
            if (rSystem <= 0) {
                break;
            }
            rSystem = modifyPciAndCalculateRSystem(parentList, parentCellMap, rSystem);
        }
    }

    private int modifyPciAndCalculateRSystem(final List<Integer> parentList,
            final Map<Integer, List<Cell>> parentCellMap, final int rSystem) {
        final Integer randomParent = parentList.get(myRandom.nextInt(parentList.size()));
        final List<Cell> siblingCells = parentCellMap.get(randomParent);
        final List<Cell> allCells = myCellController.getCells();
        boolean isChanged = false;
        for (final Cell cell : siblingCells) {
            final List<Integer> siblingPcis = getSiblingPcis(parentCellMap.get(randomParent));
            final int bestPossiblePci = AvailablePciProvider.getBestPossiblePci(cell, allCells, siblingPcis);
            if (bestPossiblePci != -1 && bestPossiblePci != cell.getPci()) {
                cell.setPci(bestPossiblePci);
                isChanged = true;
            }
        }
        if (isChanged) {
            return RepulsionComputer.computeRepulsionInTheSystem(allCells);
        }
        return rSystem;
    }

    private List<Integer> getSiblingPcis(final List<Cell> siblingCells) {
        final List<Integer> pcis = new ArrayList<Integer>();
        for (final Cell cell : siblingCells) {
            pcis.add(Integer.valueOf(cell.getPci()));
        }
        return pcis;
    }

    private TimerThread startTimer() {
        final TimerThread timerThread = new TimerThread(myCellController.getCells().size());
        timerThread.start();
        return timerThread;
    }
}
