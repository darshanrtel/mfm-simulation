package org.ieee.iwson2.mfm.algorithm;

import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.ieee.iwson2.mfm.model.networkblueprint.NetworkBluePrint;
import org.ieee.iwson2.mfm.model.networkblueprint.NetworkBluePrintImpl;
import org.ieee.iwson2.mfm.model.networkelements.Cell;
import org.ieee.iwson2.mfm.view.MFMBottomPanel;

/**
 * 
 * @author z000dgeo
 * 
 */
// TODO: Candidate for spring
public class MFMAlgorithmController {
	static MFMAlgorithmController mfmAlgorithmController = null;

	public synchronized static MFMAlgorithmController getMFMAlgorithmController() {
		if (null == mfmAlgorithmController) {
			mfmAlgorithmController = new MFMAlgorithmController();
		}
		return mfmAlgorithmController;
	}

	public void assignPCIs() {
		NetworkBluePrint NetworkBluePrint = (NetworkBluePrintImpl) NetworkBluePrintImpl
				.getNetWorkBluePrint();
		List<Cell> myCells = NetworkBluePrint.getCellsOfSites();
		if (null == myCells) {
			logger.info("No cells to assign PCIs.");
		}
		for (Cell singleCell : myCells) {
			short pciValue = (short) (new Random().nextInt(503));
			singleCell.setPCI(pciValue);
		}
	}

	static Logger logger = Logger.getLogger(MFMBottomPanel.class.getName());
}
