package kerstein.paint;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

public class Canvas extends JComponent {
	int x;
	int y;
	int x2;
	int y2;
	Graphics g;

	BufferedImage image;

	public Canvas() {
		image = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
		g=image.getGraphics();
	}

	public void setPoint(int x, int y) {
		this.x = x;
		this.y = y;
		g.setColor(Color.BLACK);
		g.fillOval(x,y,10,10);
	}
	public void setPoint2(int x, int y){
		this.x2=x;
		this.y2=y;
		g.setColor(Color.BLACK);
		g.drawLine(x,y,x2,y2);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null);
	}
}
