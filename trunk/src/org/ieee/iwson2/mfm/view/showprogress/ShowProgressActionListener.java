/**
 * 
 */
package org.ieee.iwson2.mfm.view.showprogress;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import org.ieee.iwson2.mfm.showprogress.dataloader.XYSeriesTest;
import org.ieee.iwson2.mfm.showprogress.dataloader.XmlIterationDataLoader;
import org.ieee.iwson2.mfm.showprogress.model.Iteration;
import org.ieee.iwson2.mfm.view.showprogress.chart.RepulsionChart;
import org.jfree.ui.RefineryUtilities;

/**
 * @author ppradhan
 * 
 */
public class ShowProgressActionListener implements ActionListener {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(final ActionEvent event) {
		// TODO: change the path to preferred location
		final String testDir = "D:/userdata/mfm-simulation/conf/iterationLog.xml";
		RepulsionChart chart = new RepulsionChart(
				"Cells and Rsystem repulsion over Iterations", testDir);
		chart.pack();
		RefineryUtilities.centerFrameOnScreen(chart);
		chart.setVisible(true);
	}

}
