package kerstein.paint;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.Queue;

public class BucketFillListener implements DrawListener {

	private int x;
	private int y;
	private Canvas canvas;

	public BucketFillListener(Canvas canvas) {
		this.canvas = canvas;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		x = e.getX();
		y = e.getY();
		int currentRGB = canvas.getLayers()[canvas.getLayerSelected()].getRGB(x, y);
		int replacementRGB = canvas.getColor().getRGB();
		floodFill(x, y, currentRGB, replacementRGB);
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {

	}

	@Override
	public void mouseExited(MouseEvent arg0) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {

	}

	@Override
	public void mouseMoved(MouseEvent arg0) {

	}

	@Override
	public void drawPreview(Graphics2D g) {

	}

	public void floodFill(int x, int y, int targetColor, int replacementColor) {
		Queue<Point> queue = new LinkedList<Point>();
		boolean[][] filled = new boolean[800][600];
		queue.add(new Point(x, y));

		while (!queue.isEmpty()) {
			Point p = queue.remove();
			int pixelColor = canvas.getLayers()[canvas.getLayerSelected()].getRGB(p.x, p.y);
			if (pixelColor != replacementColor && pixelColor == targetColor) {
				if (x >= 0 && x < 800 && y >= 0 && y < 600){
				canvas.getLayers()[canvas.getLayerSelected()].setRGB(p.x, p.y, replacementColor);
				if (!filled[p.x - 1][p.y]) {
					queue.add(new Point(p.x - 1, p.y));
					filled[p.x - 1][p.y] = true;
				}
				if (!filled[p.x + 1][p.y]) {
					queue.add(new Point(p.x + 1, p.y));
					filled[p.x + 1][p.y] = true;
				}
				if (!filled[p.x][p.y - 1]) {
					queue.add(new Point(p.x, p.y - 1));
					filled[p.x][p.y - 1] = true;
				}
				if (!filled[p.x][p.y + 1]) {
					queue.add(new Point(p.x, p.y + 1));
					filled[p.x][p.y + 1] = true;
				}
				canvas.repaint();

			}

		}
	}
	}
}
