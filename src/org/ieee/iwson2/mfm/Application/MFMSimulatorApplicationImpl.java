package org.ieee.iwson2.mfm.Application;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;

import org.ieee.iwson2.mfm.controller.MFMDrawController;
import org.ieee.iwson2.mfm.view.MFMBottomPanel;
import org.ieee.iwson2.mfm.view.MFMCanvas;
import org.ieee.iwson2.mfm.view.MFMSidePanel;
import org.ieee.iwson2.mfm.view.MFMTitlePanel;

/**
 * 
 * @author prem
 *
 */
public class MFMSimulatorApplicationImpl implements MFMSimulatorApplication {
	private static MFMSimulatorApplication myMFMApplication = new MFMSimulatorApplicationImpl();
	
	JFrame myMFMFrame = new JFrame("Magnetic Field Model");
	MFMCanvas myCanvas;
	JLabel coords = new JLabel("");
	JLabel myDefinition = new JLabel("<none>");

	private MFMSimulatorApplicationImpl() {
		initFrame();
	}
	
	public static synchronized MFMSimulatorApplication getMFMApplication() {
		return myMFMApplication;
	}

	/*
	 * 
	 * Initialize panels and canvas
	 */
	private void initFrame() {
		Container container = myMFMFrame.getContentPane();
		drawPanes(container);
	}

	private void drawPanes(Container container) {
		container.add(new MFMTitlePanel(), BorderLayout.NORTH);
		myCanvas = MFMCanvas.getMFMCanvas(coords);
		container.add(myCanvas);
		container.add(new MFMSidePanel(myDefinition), BorderLayout.EAST);
		container.add(new MFMBottomPanel(coords, myDefinition), BorderLayout.SOUTH);
		init_FrameListeners();
	}

	private void init_FrameListeners() {
		// FIXME: Move the listener classes out
		myMFMFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				// FIXME: Make exit generic
				System.exit(0);
			}
		});
	}

	@Override
	public void start() {
		//Initialize MFMDrawController
		//FIXME:  This would disapper once moved to spring, current invoked
		//so would become a observer
		MFMDrawController.getDrawController();
		// Show messages & enable buttons
		myMFMFrame.setSize(850, 550);
		myMFMFrame.setVisible(true);
	}
	
	public JFrame getMFMFrame() {
		return myMFMFrame;
	}
}
