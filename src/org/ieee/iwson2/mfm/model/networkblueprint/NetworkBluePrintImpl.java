package org.ieee.iwson2.mfm.model.networkblueprint;

import java.util.ArrayList;
import java.util.List;

import org.ieee.iwson2.mfm.model.networkelements.Cell;
import org.ieee.iwson2.mfm.model.networkelements.Coordinate;
import org.ieee.iwson2.mfm.model.networkelements.Site;
import org.ieee.iwson2.mfm.view.model.MFMCanvasModel;

/**
 * @author prem
 */
public class NetworkBluePrintImpl implements NetworkBluePrint {
    private static NetworkBluePrint mfmNetworkBluePrint = new NetworkBluePrintImpl();
    private List<Site> mySites = new ArrayList<Site>();
    private List<Cell> myCells = new ArrayList<Cell>();

    private NetworkBluePrintImpl() {}

    public synchronized static NetworkBluePrint getInstance() {
        return mfmNetworkBluePrint;
    }

    @Override
    public List<Site> getSitesWithCoOrdinates() {
        return mySites;
    }

    @Override
    public List<Cell> getCellsOfSites() {
        return myCells;
    }

    @Override
    public double getRepulstionFactor(Cell cell) {
        // TODO Auto-generated method stub
        return -1;
    }

    @Override
    public void addSite(final int x1, final int y1) {
        mySites.add(new Site(x1, y1));
    }

    @Override
    public synchronized void addCell(final int siteID, final int cellID, final short bearing,
            final Coordinate coordinate) {
        myCells.add(new Cell(siteID, cellID, bearing, coordinate));
    }

    @Override
    public Site searchSite(int siteId) {
        for (Site singleSite : mySites) {
            if (siteId == singleSite.getSiteId()) {
                return singleSite;
            }
        }
        return null;
    }

    @Override
    public void clearNetwork() {
        myCells.clear();
        mySites.clear();
        MFMCanvasModel.getMFMCanvas().clearCells();
        MFMCanvasModel.getMFMCanvas().clearSites();
    }

    @Override
    public void clearNetwork(Class<?> classDetails) {
        if (classDetails.isAssignableFrom(Cell.class)) {
            myCells.clear();
            MFMCanvasModel.getMFMCanvas().clearCells();
        } else if (classDetails.isAssignableFrom(Site.class)) {
            mySites.clear();
            MFMCanvasModel.getMFMCanvas().clearSites();
        }
    }
}
