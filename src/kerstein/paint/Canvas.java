package kerstein.paint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

public class Canvas extends JComponent {
	private Color color;
	private Graphics2D g;
	private BufferedImage image;
	private int strokeWidth;
	private boolean fillShape;

	public Canvas() {
		image = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
		g = (Graphics2D) image.getGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 800, 600);
		g.setColor(Color.BLACK);
		fillShape = false;
	}

	public int getStrokeWidth() {
		return strokeWidth;
	}

	public void setStrokeWidth(int width) {
		this.strokeWidth = width;
		BasicStroke basic = new BasicStroke(strokeWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
		g.setStroke(basic);
	}

	public Color getColor() {
		return color;

	}

	public void setPenColor(Color color) {
		g.setColor(color);
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setLine(int x, int y, int x2, int y2) {
		g.drawLine(x, y, x2, y2);
		repaint();
	}

	public void setFillShape(boolean fillShape) {
		this.fillShape = fillShape;
	}

	public void clear() {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 800, 600);
		repaint();
	}

	public void drawRectangle(int x1, int y1, int width, int height) {
		if (fillShape) {
			g.fillRect(x1, y1, width, width);
		} else {
			g.drawRect(x1, y1, width, height);
		}
		repaint();
	}

	public void drawAnOval(int x1, int y1, int width, int height) {
		if (fillShape) {
			g.fillOval(x1, y1, width, width);
		} else {
			g.drawOval(x1, y1, width, height);
		}
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null);
	}
}
