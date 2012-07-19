package org.ieee.dummyShop;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CreateContainer {
	public static void main(String[] args) {
		Panel panel = new Panel();
		panel.add(new Button("Button 1"));
		panel.add(new Button("Button 2"));
		panel.add(new Button("Button 3"));
		Frame frame = new Frame("Container Frame");
		TextArea txtArea = new TextArea();
		frame.add(txtArea, BorderLayout.CENTER);
		frame.add(panel, BorderLayout.SOUTH);
		frame.setSize(400, 400);
		frame.setVisible(true);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});
	}
}
