package kerstein.paint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

public class Canvas extends JComponent {
	private Color color = Color.BLACK;
	private Graphics2D g;
	private BufferedImage image;
	private int strokeWidth;

	public Canvas() {
		image = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
		g = (Graphics2D) image.getGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 800, 600);
	}

	public int getStrokeWidth() {
		return strokeWidth;
	}

	public void setStrokeWidth(int width) {
		this.strokeWidth = width;
	}

	public Color getColor() {
		return color;
	}

	public void setPenColor(Color color) {
		this.color = color;
	}

	public void setLine(int x, int y, int x2, int y2) {
		g.setColor(color);
		BasicStroke basic=new BasicStroke(getStrokeWidth(),BasicStroke.CAP_ROUND,BasicStroke.JOIN_MITER);
		g.setStroke(basic);
		g.drawLine(x, y, x2, y2);
	}

	public void clear() {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 800, 600);
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null);
	}
}
