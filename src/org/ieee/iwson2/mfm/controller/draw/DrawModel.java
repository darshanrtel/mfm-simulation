package org.ieee.iwson2.mfm.controller.draw;

/**
 * 
 * @author prem
 * 
 */
public interface DrawModel {
	public void drawSites() throws Exception;

	public void drawCells() throws Exception;

	public void clearCells() throws Exception;

	public void selectCells(final float x1, final float y1);
}