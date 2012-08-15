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
    private final AlgorithmLogContainer myAlgorithmLogContainer;

    public MfmBasedAlgorithm(final MfmCellCollection cellController,
            final AlgorithmLogContainer algorithmLogContainer) {
        myCellController = cellController;
        myAlgorithmLogContainer = algorithmLogContainer;
        myRandom = new Random();
    }

    public void execute() {
        int iterationId = 0;
        int rSystem = RepulsionComputer.computeRepulsionInTheSystem(myCellController.getCells(),
                myAlgorithmLogContainer);
        myAlgorithmLogContainer.logIterationRepulsion(iterationId, rSystem);
        final Map<Integer, List<Cell>> parentCellMap = myCellController.getParentCellMap();
        final List<Integer> parentList = new ArrayList<Integer>(parentCellMap.keySet());
        final TimerThread timer = startTimer();
        while (!timer.isStopped()) {
            if (rSystem <= 0) {
                break;
            }

            int rNew = modifyPciAndCalculateRSystem(parentList, parentCellMap, rSystem);
            if (rSystem != rNew) {
                iterationId++;
                myAlgorithmLogContainer.logIterationRepulsion(iterationId, rNew);
            } else {
                myAlgorithmLogContainer.clearCellLogging();
            }
            // TODO: If rnew is less than r system then revert modified PCIs
            rSystem = rNew;
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
            return RepulsionComputer.computeRepulsionInTheSystem(allCells, myAlgorithmLogContainer);
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
