package org.ieee.iwson2.mfm.view;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 * @author prem
 *
 */
public class MFMCanvasSupportingPanel extends JPanel {

	public MFMCanvasSupportingPanel(JLabel label, JLabel coords) {
		this.setLayout(new GridLayout(1, 2));
		this.add(label);
		this.add(coords);
	}

	private static final long serialVersionUID = 3245769001565948110L;
}
