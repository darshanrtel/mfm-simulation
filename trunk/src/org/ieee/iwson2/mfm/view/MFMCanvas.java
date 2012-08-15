package org.ieee.iwson2.mfm.view;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

import javax.swing.JLabel;

import org.ieee.iwson2.mfm.model.networkblueprint.NetworkBluePrintImpl;
import org.ieee.iwson2.mfm.model.networkelements.Cell;
import org.ieee.iwson2.mfm.model.networkelements.Site;
import org.ieee.iwson2.mfm.view.model.MFMCanvasModel;

/**
 * 
 * @author prem
 * 
 */
public class MFMCanvas extends Canvas {
	private static final short LENGTH_OF_CELL = 35;

	private static final short CELL_LINE_WIDTH = 5;

	private static MFMCanvas myMFMCanvas = null;
	
	float dashes[] = { 5f, 5f };

	BasicStroke stroke;

	private JLabel coords;

	public synchronized static MFMCanvas getMFMCanvas(final JLabel coords) {
		if (null == myMFMCanvas) {
			myMFMCanvas = new MFMCanvas(coords);
			myMFMCanvas.setBackground(new Color(255, 240, 255, 0));
		}
		return myMFMCanvas;
	}

	public synchronized static MFMCanvas getMFMCanvas() throws Exception {
		if (null == myMFMCanvas) {
			throw new Exception("Canvas not ready!!!");
		}
		return myMFMCanvas;
	}

	private MFMCanvas(final JLabel coords) {
		this.coords = coords;
		setBackground(Color.white);
		addMouseListener(new MFMCanvasMouseListener(this, this.coords));
		addMouseMotionListener(new MFMCanvasMouseListener(this, this.coords));
		setSize(400, 400);
		stroke = new BasicStroke(1f, BasicStroke.CAP_BUTT,
				BasicStroke.JOIN_BEVEL, 10f, dashes, 0f);
	}

	public void update(Graphics g) {
		paint(g);
	}

	public void paint(Graphics g) {
	}

	private static final long serialVersionUID = 7512161346350158746L;

	public void drawSite(Site mfmSites) throws Exception {
		if (null == myMFMCanvas) {
			throw new Exception("Canvas not ready!!!");
		}
		Shape circle = new Ellipse2D.Float(mfmSites.getX1(), mfmSites.getY1(),
				10.0f, 8.0f);
		paint(myMFMCanvas.getGraphics(), circle, Color.DARK_GRAY, NetworkElementType.SITE);
		myMFMCanvas.repaint();
	}

	public void paint(final Graphics g, final Shape requestedShape,
			final Color paintColor, final NetworkElementType drawType) {
		Graphics2D ga = (Graphics2D) g;
		ga.setPaint(paintColor);
		if (drawType == NetworkElementType.SITE) {
			ga.draw(requestedShape);
			ga.fill(requestedShape);
			MFMCanvasModel.getMFMCanvas().setMySiteCircles(
					(Ellipse2D) requestedShape);
		} else if (requestedShape instanceof Line2D) {
			if(drawType == NetworkElementType.CELL) {
				ga.setStroke(new BasicStroke(CELL_LINE_WIDTH)); // line thickness
			} else if (drawType == NetworkElementType.ECR) {
				ga.setStroke(new BasicStroke(2)); // line thickness
			}
			ga.drawLine((int) ((Line2D) requestedShape).getX1(),
					(int) ((Line2D) requestedShape).getY1(),
					(int) ((Line2D) requestedShape).getX2(),
					(int) ((Line2D) requestedShape).getY2());
			MFMCanvasModel.getMFMCanvas().setMyCellLines(
					(Line2D) requestedShape, drawType);
		}
	}

	public void drawCell(Cell mfmCell) throws Exception {
		if (null == myMFMCanvas) {
			throw new Exception("Canvas not ready!!!");
		}
		Site site = NetworkBluePrintImpl.getInstance().searchSite(
				mfmCell.getSiteId());
		if (null == site) {
			throw new Exception("Site not found!!!");
		}
		final float siteX1 = site.getX1() + 5; // +5 is to take to the centre of
												// eclipse (site circle)
		final float siteY1 = site.getY1() + 2; // +2 is to take to the centre of
												// eclipse (site circle)
		final short bearing = mfmCell.getMyBearing();
		float siteX2 = 0;
		float siteY2 = 0;
		short r = LENGTH_OF_CELL;
		// Finding the point on the circle
		siteX2 = (int) Math.round(siteX1 + r * Math.cos(bearing));
		siteY2 = (int) Math.round(siteY1 + r * Math.sin(bearing));
		Shape line = new Line2D.Double(siteX1, siteY1, siteX2, siteY2);
		paint(myMFMCanvas.getGraphics(), line, Color.ORANGE, NetworkElementType.CELL);
		if(mfmCell.getExpectedCellRange() > 0) {
			short r1 = (short) (mfmCell.getExpectedCellRange() * 30);
			siteX2 = (int) Math.round(siteX1 + r1 * Math.cos(bearing));
			siteY2 = (int) Math.round(siteY1 + r1 * Math.sin(bearing));			
			Shape ecrLine = new Line2D.Double(siteX1, siteY1, siteX2, siteY2);
			paint(myMFMCanvas.getGraphics(), ecrLine, Color.BLACK, NetworkElementType.ECR);
		}
		myMFMCanvas.repaint();
	}

	public void selectCell(Line2D line) {
		paint(myMFMCanvas.getGraphics(), line, Color.GRAY, NetworkElementType.CELL);
	}

	public void clearCanvas() {
		Graphics2D ga = (Graphics2D) myMFMCanvas.getGraphics();
		ga.setColor(this.getBackground());
		ga.clearRect(0, 0, this.getWidth(), this.getHeight());
		myMFMCanvas.repaint();
	}

/*	public void drawPoint(Point2D cp, Color color) {
		Graphics2D ga = (Graphics2D) myMFMCanvas.getGraphics();
		ga.setColor(this.getBackground());
		Shape circle = new Ellipse2D.Float((float)cp.getX(), (float)cp.getY(),
				10.0f, 8.0f);
		paint(myMFMCanvas.getGraphics(), circle, color, NetworkElementType.INTERSECTION);
		myMFMCanvas.repaint();
	}*/
}