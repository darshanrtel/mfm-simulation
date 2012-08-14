package org.ieee.iwson2.mfm.view.model;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.ieee.iwson2.mfm.view.NetworkElementType;

/**
 * 
 * @author prem
 * 
 */
// FIXME: Move to spring
public class MFMCanvasModel {
	private static MFMCanvasModel mfmCanvasModel = new MFMCanvasModel();
	private EnumMap<NetworkElementType, ArrayList<Line2D>> myCellLines = new EnumMap<NetworkElementType, ArrayList<Line2D>>(
			NetworkElementType.class);
	private Set<Ellipse2D> mySiteCircles = new HashSet<Ellipse2D>();

	private MFMCanvasModel() {

	}

	public synchronized static MFMCanvasModel getMFMCanvas() {
		return mfmCanvasModel;
	}

	public synchronized List<Line2D> getMyCellLines(final NetworkElementType drawType) {
		return myCellLines.get(drawType);
	}

	public synchronized Set<Ellipse2D> getMySiteCircles() {
		return mySiteCircles;
	}

	public synchronized void setMyCellLines(final Line2D line, NetworkElementType drawType) {
		ArrayList<Line2D> netWorkSet = myCellLines.get(drawType);
		if (netWorkSet == null) {
			netWorkSet = new ArrayList<Line2D>();
		}
		if(!netWorkSet.equals(line)) { //FIXME: Implement equals to avoid duplication
			netWorkSet.add(line);
			myCellLines.put(drawType, netWorkSet);			
		}
	}

	public synchronized void setMySiteCircles(final Ellipse2D circle) {
		mySiteCircles.add(circle);
	}

	public synchronized void clearCells() {
		myCellLines.clear(); //FIXME:clear is removing all the elements from the enum map!!!
		for(NetworkElementType neType:myCellLines.keySet()) { //get all the enums and clear
			myCellLines.remove(neType);
		}
	}

	public synchronized void clearSites() {
		mySiteCircles.clear();
	}
}