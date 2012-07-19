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
	public void selectCells(final float x1, final float y1) {
		int HIT_BOX_SIZE = 3;
		logger.debug("Select Cells!!!");
		List<Line2D> lines = new ArrayList<Line2D>(MFMCanvasModel.getMFMCanvas().getMyCellLines());
		for (Line2D line : lines) {
			//float boxX = (int) ((x1 - HIT_BOX_SIZE) / 2);
			//float boxY = (int) ((y1 - HIT_BOX_SIZE) / 2);
			int width = HIT_BOX_SIZE;
			int height = HIT_BOX_SIZE+7;
			if (line.intersects(x1 , y1, width, height)) {
			//if (line.intersectsLine(boxX , boxY, width, height)) {
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