package kerstein.paint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

public class Canvas extends JComponent {
	Color color=Color.BLACK;
	Graphics2D g;

	BufferedImage image;

	public Canvas() {
		image = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
	}

	public void setPenColor(Color color) {
		this.color = color;
	}

	public void setLine(int x, int y, int x2, int y2) {
		g = (Graphics2D) image.getGraphics();
		g.setColor(color);
		g.setStroke(new BasicStroke(7));
		g.drawLine(x, y, x2, y2);
	}
   
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null);
	}
}
