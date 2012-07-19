package org.ieee.dummyShop;

import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class mainapplic extends JFrame {
	public mainapplic() {
		TestPanel tp = new TestPanel();
		Container c = getContentPane();
		c.add(tp);
		setSize(400, 400);
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				System.exit(-1);
			}
		});
	}

	public static void main(String args[]) {
		new mainapplic();
	}
}

class TestPanel extends JPanel {
	JTextField tf;
	JButton jb;

	public TestPanel() {
		tf = new JTextField(20);
		jb = new JButton("Clear");
		add(tf);
		add(jb);
	}
}
