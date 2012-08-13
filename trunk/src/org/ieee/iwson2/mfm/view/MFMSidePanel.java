package org.ieee.iwson2.mfm.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.ieee.iwson2.mfm.controller.OperationChangeController;
import org.ieee.iwson2.mfm.controller.OperationStates;

public class MFMSidePanel extends JPanel implements OperationStates {
	private JLabel myOperation;

	public MFMSidePanel(final JLabel definition) {
		super();
		myOperation = definition;
		init_SidePanel();
	}

	private void init_SidePanel() {
		this.setLayout(new GridLayout(7, 1));
		JButton siteButton = new JButton("Define Sites");
		JButton cellButton = new JButton("Define Cells");
		JButton showCellECR = new JButton("Show Cell ECR");
		JButton pauseProgressButton = new JButton("Forbid PCI");
		JButton exitButton = new JButton("Forbid PRACH");
		JButton showRepelFactor = new JButton("Show Repel factor");
		JButton clearNetwork = new JButton("Clear Network");
		this.add(siteButton);
		this.add(cellButton);
		this.add(showCellECR);
		this.add(pauseProgressButton);
		this.add(exitButton);
		this.add(showRepelFactor);
		this.add(clearNetwork);
		init_ActionListeners(siteButton, cellButton, showCellECR, clearNetwork);
	}

	private void init_ActionListeners(final JButton siteButton,
			final JButton cellButton, final JButton showCellECR,
			final JButton clearNetwork) {
		siteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				OperationChangeController.getOperationChangeNotifier()
						.notifyOperationChange(Operation_States.SITES);
				myOperation.setText("Site definition");
			}
		});
		cellButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				OperationChangeController.getOperationChangeNotifier()
						.notifyOperationChange(Operation_States.CELLS);
				myOperation.setText("Cell definition");
			}
		});
		clearNetwork.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				OperationChangeController.getOperationChangeNotifier()
						.notifyOperationChange(Operation_States.CLEAR);
				myOperation.setText("Clear Network");
			}
		});
		showCellECR.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				OperationChangeController.getOperationChangeNotifier()
						.notifyOperationChange(Operation_States.ECR);
				myOperation.setText("Show ECR of Network");
			}
		});
	}

	private static final long serialVersionUID = -1139545974062417313L;
}
