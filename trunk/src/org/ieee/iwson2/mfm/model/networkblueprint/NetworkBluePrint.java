package org.ieee.iwson2.mfm.model.networkblueprint;

import java.util.List;

import org.ieee.iwson2.mfm.model.networkelements.Cell;
import org.ieee.iwson2.mfm.model.networkelements.Site;

/**
 * 
 * @author prem
 *
 */
public interface NetworkBluePrint {
	public List<Site> getSitesWithCoOrdinates();

	public List<Cell> getCellsOfSites();

	public double getRepulstionFactor(Cell cell);
	
	public void addSite(final float x1, final float y1);
	
	public void addCell(final int siteID, final int cellID, final short bearing);

	public Site searchSite(final int siteId);

	public void clearNetwork(Class<?> classDetails);

	public void clearNetwork();
}