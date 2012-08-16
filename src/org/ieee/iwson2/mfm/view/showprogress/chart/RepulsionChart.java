/**
 * 
 */
package org.ieee.iwson2.mfm.view.showprogress.chart;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JPanel;

import org.ieee.iwson2.mfm.showprogress.dataloader.XmlIterationDataLoader;
import org.ieee.iwson2.mfm.showprogress.model.Iteration;
import org.ieee.iwson2.mfm.showprogress.model.IterationItem;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataItem;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 * @author ppradhan
 * 
 */
public class RepulsionChart extends JDialog {
	private static final long serialVersionUID = 1L;
	private static String myXmlDataSourcePath;

	public RepulsionChart(String title, final String xmlDataSourcePath) {
		setTitle(title);
		setModal(true);
		myXmlDataSourcePath = xmlDataSourcePath;
		ChartFactory
				.setChartTheme(new StandardChartTheme("JFree/Shadow", true));

		ChartPanel chartPanel = (ChartPanel) createChartPanel();
		chartPanel.setPreferredSize(new Dimension(450, 270));
		setContentPane(chartPanel);
	}

	private static JFreeChart createChart(XYDataset dataset) {
		JFreeChart chart = ChartFactory.createXYLineChart(
				"Cells and Rsystem repulsion over Iterations",
				"Cells", "PCI Repulsion", dataset,
				PlotOrientation.VERTICAL, true, true, false);

		chart.setBackgroundPaint(Color.white);

		XYPlot plot = (XYPlot) chart.getPlot();
		plot.setBackgroundPaint(Color.lightGray);
		plot.setDomainGridlinePaint(Color.white);
		plot.setRangeGridlinePaint(Color.white);
		plot.setDomainCrosshairVisible(true);
		plot.setRangeCrosshairVisible(true);

		XYItemRenderer r = plot.getRenderer();
		if ((r instanceof XYLineAndShapeRenderer)) {
			XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) r;
			renderer.setBaseShapesVisible(true);
			renderer.setBaseShapesFilled(true);
			renderer.setDrawSeriesLineAsPath(true);
		}
		return chart;
	}

	private static XYDataset createDataset() {

		final List<Iteration> iterations = XmlIterationDataLoader
				.loadIterationsFromLocation(myXmlDataSourcePath);
		XYSeriesCollection dataset = new XYSeriesCollection();
		for (final Iteration iteration : iterations) {
			XYSeries series = new XYSeries(iteration.getName());
			final List<IterationItem> items = iteration.getIterationItems();
			final List<IterationItem> tempItems = new ArrayList<IterationItem>(
					items);
			Collections.sort(tempItems);
			for (int i = 0; i < tempItems.size(); i++) {
				final XYDataItem dataItem = new XYDataItem(Integer.valueOf(i),
						Double.valueOf(tempItems.get(i).getCost()));
				series.add(dataItem);
				System.out.println(tempItems.get(i));
			}

			dataset.addSeries(series);
		}
		return dataset;
	}

	public static JPanel createChartPanel() {
		JFreeChart chart = createChart(createDataset());
		ChartPanel panel = new ChartPanel(chart);
		panel.setFillZoomRectangle(true);
		panel.setMouseWheelEnabled(true);
		return panel;
	}
}
