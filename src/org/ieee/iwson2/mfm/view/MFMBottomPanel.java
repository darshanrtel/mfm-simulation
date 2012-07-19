package org.ieee.iwson2.mfm.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.log4j.Logger;
import org.ieee.iwson2.mfm.controller.MFMDrawController;

/**
 * 
 * @author prem
 * 
 */
public class MFMBottomPanel extends JPanel {
	public MFMBottomPanel(final JLabel coords, final JLabel definition) {
		super();
		initButtonPanel(coords, definition);
	}

	private void initButtonPanel(final JLabel coords, final JLabel definition) {
		JLabel label = new JLabel("Mouse Location (x, y):  ");
		this.setLayout(new GridLayout(2, 6));
		this.add(label);
		this.add(coords);
		this.add(new JLabel(""));
		this.add(new JLabel(""));
		this.add(new JLabel("Current Operation : "));
		this.add(definition);

		JButton loadButton = new JButton("Load Network");
		JButton startButton = new JButton("Start MFM");
		JButton showProgressButton = new JButton("Show Progress");
		JButton pauseProgressButton = new JButton("Pause/Continue");
		JButton exitButton = new JButton("Exit");
		JButton about = new JButton("About");
		this.add(loadButton);
		this.add(startButton);
		this.add(showProgressButton);
		this.add(pauseProgressButton);
		this.add(exitButton);
		this.add(about);
		init_ActionListeners(exitButton, loadButton, about);
	}

	private void init_ActionListeners(final JButton exitButton,
			final JButton loadButton, final JButton about) {
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// FIXME: Make exit generic
				System.exit(0);
			}
		});
		loadButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MFMDrawController mfmController = MFMDrawController
						.getDrawController();
				try {
					mfmController.drawNetwork();
				} catch (Exception e1) {
					logger.fatal(e1.getMessage() + ":" + e1.getStackTrace());
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		about.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MFMDrawController mfmController = MFMDrawController
						.getDrawController();
				mfmController.showHelp();
			}
		});
	}

	private static final long serialVersionUID = -1641443646844790545L;
	static Logger logger = Logger.getLogger(MFMBottomPanel.class.getName());
}
