package org.ieee.iwson2.mfm.controller.draw;

import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.ieee.iwson2.mfm.model.networkblueprint.NetworkBluePrint;
import org.ieee.iwson2.mfm.model.networkblueprint.NetworkBluePrintImpl;
import org.ieee.iwson2.mfm.model.networkelements.Cell;
import org.ieee.iwson2.mfm.model.networkelements.Site;
import org.ieee.iwson2.mfm.view.MFMCanvas;
import org.ieee.iwson2.mfm.view.NetworkElementType;
import org.ieee.iwson2.mfm.view.model.MFMCanvasModel;

/**
 * 
 * @author prem
 * 
 */
public class DrawModelImpl implements DrawModel {
	NetworkBluePrint myNetworkBluePrint = NetworkBluePrintImpl
			.getNetWorkBluePrint();
	private MFMCanvas myCanvas;

	public DrawModelImpl() {
		super();
	}

	@Override
	public void clearCells() throws Exception {
		getCanvas().clearCanvas();
	}

	@Override
	public void drawSites() throws Exception {
		logger.debug("Drawing Sites!!!");
		final List<Site> mfmSites = myNetworkBluePrint
				.getSitesWithCoOrdinates();
		for (Site oneSite : mfmSites) {
			getCanvas().drawSite(oneSite);
		}
		logger.debug("Done Drawing Sites!!!");
	}

	@Override
	public void drawCells() throws Exception {
		logger.debug("Drawing Cells!!!");
		final List<Cell> mfmCells = myNetworkBluePrint.getCellsOfSites();
		for (Cell oneCell : mfmCells) {
			getCanvas().drawCell(oneCell);
		}
		logger.debug("Done Drawing Cells!!!");
	}

	@Override
	public void drawCellIntersection() {
		logger.debug("Logging cell intersection!!!");
		final List<Line2D> lines = MFMCanvasModel.getMFMCanvas()
				.getMyCellLines(NetworkElementType.ECR);
		if (lines == null) {
			return;
		}
		List<List<Integer>> alreadyCheckedPairs = new ArrayList<List<Integer>>();
		for (Line2D line : lines) {
			for (Line2D secondline : lines) {
				if (lineIntersection(line, secondline, alreadyCheckedPairs)) {
					// Do something
				}
			}
		}
	}

	private boolean lineIntersection(final Line2D line1, final Line2D line2,
			final List<List<Integer>> alreadyCheckedPairs) {
		if (isLineChecked(line1, line2, alreadyCheckedPairs)) {
			return false;
		}
		boolean interSectionStatus = false;
		// Check for lines whether they were already checked
		interSectionStatus = Line2D.linesIntersect(line1.getX1(),
				line1.getY1(), line1.getX2(), line1.getY2(), line2.getX1(),
				line2.getY1(), line2.getX2(), line2.getY2());
		if (interSectionStatus) {
			System.out
					.println("Co-ordinate of line1 : " + line1.getX1() + ","
							+ line1.getY1() + ":" + line1.getX2() + ","
							+ line1.getY2());
			System.out
					.println("Co-ordinate of line2 : " + line2.getX1() + ","
							+ line2.getY1() + ":" + line2.getX2() + ","
							+ line2.getY2());
			System.out.println("Intersection of line : "
					+ line1.intersectsLine(line2));
			System.out.println("Lines Intersect : " + interSectionStatus);
		}
		return interSectionStatus;
	}

	private boolean isLineChecked(final Line2D line1, final Line2D line2,
			final List<List<Integer>> alreadyCheckedPairs) {
		boolean isPairAlreadyChecked = false;
		// check for existence of the pairs
		for (List<Integer> linePair : alreadyCheckedPairs) {
			if (linePair.contains(line1.hashCode())
					&& linePair.contains(line2.hashCode())) {
				isPairAlreadyChecked = true;
				break;
			}
		}
		if (!isPairAlreadyChecked) {
			List<Integer> tempObjList = new ArrayList<Integer>();
			tempObjList.add(line1.hashCode());
			tempObjList.add(line2.hashCode());
			alreadyCheckedPairs.add(tempObjList);
		}
		return isPairAlreadyChecked;
	}

	@Override
	public void selectCells(final float x1, final float y1) {
		int HIT_BOX_SIZE = 3;
		logger.debug("Select Cells!!!");
		List<Line2D> lines = new ArrayList<Line2D>(MFMCanvasModel
				.getMFMCanvas().getMyCellLines(NetworkElementType.CELL));
		for (Line2D line : lines) {
			// float boxX = (int) ((x1 - HIT_BOX_SIZE) / 2);
			// float boxY = (int) ((y1 - HIT_BOX_SIZE) / 2);
			int width = HIT_BOX_SIZE;
			int height = HIT_BOX_SIZE + 7;
			if (line.intersects(x1, y1, width, height)) {
				// if (line.intersectsLine(boxX , boxY, width, height)) {
				getCanvas().selectCell(line);
			}
		}
	}

	@SuppressWarnings("finally")
	private MFMCanvas getCanvas() {
		try {
			myCanvas = MFMCanvas.getMFMCanvas();
		} catch (Exception e) {
			e.printStackTrace();
			logger.fatal("Canvas not ready.  Mostly application initialization issue!!!");
		} finally {
			return myCanvas;
		}
	}

	static Logger logger = Logger.getLogger(DrawModelImpl.class.getName());
}