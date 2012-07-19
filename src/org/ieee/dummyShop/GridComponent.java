package org.ieee.dummyShop;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JComponent;

public class GridComponent extends JComponent {

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		int count = 10;
		int size = 40;

		for (int i = 0; i < count; i++) {
			for (int j = 0; j < count; j++) {
				Rectangle grid = new Rectangle(100 + i * size, 100 + j * size,
						size, size);
				g2.draw(grid);

			}
		}

	}

}
