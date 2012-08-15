package org.ieee.iwson2.mfm.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.ieee.iwson2.mfm.Application.MFMSimulatorApplication;
import org.ieee.iwson2.mfm.Application.MFMSimulatorApplicationImpl;
import org.ieee.iwson2.mfm.controller.draw.DrawModel;
import org.ieee.iwson2.mfm.controller.draw.DrawModelImpl;
import org.ieee.iwson2.mfm.model.networkblueprint.NetworkBluePrint;
import org.ieee.iwson2.mfm.model.networkblueprint.NetworkBluePrintImpl;
import org.ieee.iwson2.mfm.model.networkelements.Cell;
import org.ieee.iwson2.mfm.model.networkelements.SequenceGenerator;
import org.ieee.iwson2.mfm.model.networkelements.Site;

/**
 * @author prem
 */
// FIXME: Move this to spring
public class MFMDrawController implements OperationChangeListener {
    private static MFMDrawController mfmDrawController = null;
    public Operation_States myCurrent_Operation;
    private DrawModel myNetworkBrush = new DrawModelImpl();
    private short NO_OF_SECTORS_PER_SITE = 4;

    public Operation_States getMyCurrent_Operation() {
        return myCurrent_Operation;
    }

    private MFMDrawController() {
        super();
    }

    public synchronized static MFMDrawController getDrawController() {
        if (null == mfmDrawController) {
            mfmDrawController = new MFMDrawController();
            ((OperationChangeController) OperationChangeController.getOperationChangeNotifier())
                    .subscribe(mfmDrawController);
        }
        return mfmDrawController;
    }

    public void drawSites() throws Exception {
        myNetworkBrush.drawSites();
    }

    public void drawCells() throws Exception {
        myNetworkBrush.drawCells();
    }

    private void drawCellIntersection() {
        myNetworkBrush.drawCellIntersection();
    }

    private void clearNetwork() throws Exception {
        myNetworkBrush.clearCells();
        NetworkBluePrintImpl.getInstance().clearNetwork();
    }

    public void showHelp() {
        MFMSimulatorApplication myMFM = MFMSimulatorApplicationImpl.getMFMApplication();
        JOptionPane.showMessageDialog(((MFMSimulatorApplicationImpl) myMFM).getMFMFrame(), "Version 0.0.6"
                + "\nAuthor(s) : Prem, Darshan, Pankaj & Christoph"
                + "\nIs MFM helpfull: Let's see.  All is well!!!");
    }

    public void addSites(final int x1, final int y1) {
        if (myCurrent_Operation == Operation_States.SITES) {
            NetworkBluePrint myNetworkBluePrint = NetworkBluePrintImpl.getInstance();
            myNetworkBluePrint.addSite(x1, y1);
        }
    }

    private synchronized void addCells() {
        if (myCurrent_Operation == Operation_States.CELLS) {
            NetworkBluePrint myNetworkBluePrint = NetworkBluePrintImpl.getInstance();
            if (myNetworkBluePrint.getCellsOfSites().size() > 0) {
                logger.debug("Cell already exists.  Not defining again!!!");
                return;
            }
            for (Site singleSite : myNetworkBluePrint.getSitesWithCoOrdinates()) {
                final int totalCells = 1 + (new Random().nextInt(NO_OF_SECTORS_PER_SITE));
                final List<Short> bearing = getBearings(totalCells);
                for (int cellNumber = 0; cellNumber < totalCells; cellNumber++) {
                    int cellID = SequenceGenerator.getSideIDGenerator().getNextCellID();
                    myNetworkBluePrint.addCell(singleSite.getSiteId(), cellID, bearing.get(cellNumber)
                            .shortValue(), singleSite.getCoordinate());
                    singleSite.setMyCells(new Integer(cellID));
                }
            }
            logger.debug("Cells added...");
        }
    }

    private synchronized void configureECR() {
        if (myCurrent_Operation == Operation_States.ECR) {
            NetworkBluePrint myNetworkBluePrint = NetworkBluePrintImpl.getInstance();
            for (Cell singleCell : myNetworkBluePrint.getCellsOfSites()) {
                short expectedCellRange = (short) (new Random().nextInt(3));
                expectedCellRange += 2; // To avoid zeros
                singleCell.setExpectedCellRange(expectedCellRange);
            }
            logger.debug("ECR Configured...");
        }
    }

    private List<Short> getBearings(int totalCells) {
        List<Short> bearings = new ArrayList<Short>();
        for (int cellNumber = 0; cellNumber < totalCells; cellNumber++) {
            if (NO_OF_SECTORS_PER_SITE < 5) {
                switch (cellNumber) {
                case 0:
                    bearings.add(new Short((short) (new Random().nextInt(90))));
                    break;
                case 1:
                    bearings.add(new Short((short) (90 + (short) (Math.random() * (new Random().nextInt(90))))));
                    break;
                case 2:
                    bearings.add(new Short((short) (180 + (short) (Math.random() * (new Random().nextInt(90))))));
                    break;
                case 3:
                    bearings.add(new Short((short) (270 + (short) (Math.random() * (new Random().nextInt(90))))));
                    break;
                default:
                    break;
                }
            } else {
                bearings.add(new Short((short) (new Random().nextInt(359))));
            }
        }
        return bearings;
    }

    @Override
    public void notifyOperationChange(Operation_States operationChange) {
        myCurrent_Operation = operationChange;
        switch (myCurrent_Operation) {
        case CELLS:
            logger.debug("CELLS");
            addCells();
            break;
        case SITES:
            logger.debug("SITES");
            break;
        case ECR:
            logger.debug("ECR");
            configureECR();
            break;
        case CLEAR:
            logger.debug("CLEAR");
            try {
                clearNetwork();
            } catch (Exception e) {
                logger.fatal("Unable to clear network & canvas!!!");
                e.printStackTrace();
            }
            break;
        default:
            break;
        }
    }

    static Logger logger = Logger.getLogger(MFMDrawController.class.getName());

    public void drawNetwork() throws Exception {
        if (null == myCurrent_Operation) {
            logger.debug("Nothing can be drawn.  Define Network type.");
            return;
        }
        switch (myCurrent_Operation) {
        case ECR:
            //
        case SITES:
            // logger.debug("Creating Sites");
            // drawSites();
            // break;//Since both can be drawn
        case CELLS:
            logger.debug("Drawing Sites & Cells with ECR");
            drawCells(); // With ECR
            drawSites();
            drawCellIntersection();
            break;
        case CLEAR:
            clearNetwork();
            break;
        default:
            break;
        }
    }

    public void selectNetwork(final float x1, final float y1) {
        if (myCurrent_Operation == Operation_States.CELLS) {
            myNetworkBrush.selectCells(x1, y1);
        }
    }
}
