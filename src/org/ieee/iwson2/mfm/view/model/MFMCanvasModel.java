package org.ieee.iwson2.mfm.view.model;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author prem
 * 
 */
// FIXME: Move to spring
public class MFMCanvasModel {
	private static MFMCanvasModel mfmCanvasModel = new MFMCanvasModel();
	private Set<Line2D> myCellLines = new HashSet<Line2D>();
	private Set<Ellipse2D> mySiteCircles = new HashSet<Ellipse2D>();

	private MFMCanvasModel() {

	}

	public synchronized static MFMCanvasModel getMFMCanvas() {
		return mfmCanvasModel;
	}

	public Set<Line2D> getMyCellLines() {
		return myCellLines;
	}

	public Set<Ellipse2D> getMySiteCircles() {
		return mySiteCircles;
	}

	public void setMyCellLines(final Line2D line) {
		myCellLines.add(line);
	}

	public void setMySiteCircles(final Ellipse2D circle) {
		mySiteCircles.add(circle);
	}

	public void clearCells() {
		myCellLines.clear();
	}

	public void clearSites() {
		mySiteCircles.clear();
	}
}