package org.ieee.iwson2.mfm.model.networkblueprint;

import java.util.ArrayList;
import java.util.List;

import org.ieee.iwson2.mfm.model.networkelements.Cell;
import org.ieee.iwson2.mfm.model.networkelements.Site;
import org.ieee.iwson2.mfm.view.model.MFMCanvasModel;

/**
 * 
 * @author prem
 * 
 */
public class NetworkBluePrintImpl implements NetworkBluePrint {
	private static NetworkBluePrint mfmNetworkBluePrint = null;
	private List<Site> mySites = new ArrayList<Site>();
	private List<Cell> myCells = new ArrayList<Cell>();

	private NetworkBluePrintImpl() {
	}

	public synchronized static NetworkBluePrint getNetWorkBluePrint() {
		if (null == mfmNetworkBluePrint) {
			mfmNetworkBluePrint = new NetworkBluePrintImpl();
		}
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
	public void addSite(final float x1, final float y1) {
		mySites.add(new Site(x1, y1));
	}

	@Override
	public synchronized void addCell(final int siteID, final int cellID,
			final short bearing) {
		myCells.add(new Cell(siteID, cellID, bearing));
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
