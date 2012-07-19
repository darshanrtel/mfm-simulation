package org.ieee.iwson2.mfm.view;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 * @author prem
 *
 */
public class MFMTitlePanel extends JPanel {
	public MFMTitlePanel() {
		super();
		JLabel appText = new JLabel(
				"Simulation for Magnetic Field Model proposed in IWSON2 (ISBN: 978-1-4673-0762-8/12)");
		this.add(appText);
	}

	private static final long serialVersionUID = -9039608746593693855L;
}