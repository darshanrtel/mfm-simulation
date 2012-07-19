package org.ieee.iwson2.mfm.view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JLabel;

import org.ieee.iwson2.mfm.controller.MFMDrawController;

public class MFMCanvasMouseListener extends MouseAdapter implements
		MouseMotionListener {
	public MFMCanvas canvas;
	private JLabel coords;
	private float x1, y1;

	public MFMCanvasMouseListener(final MFMCanvas canvas, final JLabel coords) {
		super();
		this.coords = coords;
		this.canvas = canvas;
	}

	public void mousePressed(MouseEvent e) {
		x1 = e.getX();
		y1 = e.getY();
		MFMDrawController myMFMDrawController = MFMDrawController
				.getDrawController();
		myMFMDrawController.addSites(x1, y1);
		myMFMDrawController.selectNetwork(x1, y1);
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseDragged(MouseEvent e) {
	}

	public void mouseMoved(MouseEvent e) {
		String string = "(" + Integer.toString(e.getX()) + ", "
				+ Integer.toString(e.getY()) + "), In KM(" + (e.getX() / 30)
				+ "," + (e.getY() / 30) + ")";
		coords.setText(string);
	}
}