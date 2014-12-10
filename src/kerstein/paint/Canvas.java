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
	private DrawListener listener;

	public Canvas() {
		image = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
		g = (Graphics2D) image.getGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 800, 600);
		g.setColor(Color.BLACK);
		setDrawListener(new PencilListener(this));
	}

	public void setDrawListener(DrawListener dl) {
		this.removeMouseMotionListener(listener);
		this.removeMouseListener(listener);
		this.addMouseMotionListener(dl);
		this.addMouseListener(dl);
		this.listener = dl;
	}

	public int getStrokeWidth() {
		return strokeWidth;
	}

	public Graphics2D getGraphicsPen() {
		return g;

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

	public void clear() {
		image = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
		g = (Graphics2D) image.getGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 800, 600);
		g.setColor(Color.BLACK);
		setDrawListener(new PencilListener(this));
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null);
		listener.drawPreview((Graphics2D) g);
	}
}
