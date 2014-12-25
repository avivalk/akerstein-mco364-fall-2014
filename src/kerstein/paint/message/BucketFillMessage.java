package kerstein.paint.message;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.LinkedList;
import java.util.Queue;

import kerstein.paint.Canvas;

public class BucketFillMessage implements PaintMessage {
	private int x;
	private int y;
	private int color;
	private Canvas canvas;

	public BucketFillMessage(int x, int y, int color, Canvas canvas) {
		this.x = x;
		this.y = y;
		this.color = color;
		this.canvas = canvas;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "BUCKET_FILL" + " " + x + " " + y + " " + color + "\n";

	}

	public void floodFill(int x, int y, int currentColor, int replacementColor) {
		Queue<Point> queue = new LinkedList<Point>();
		boolean[][] filled = new boolean[800][600];
		queue.add(new Point(x, y));

		while (!queue.isEmpty()) {
			Point p = queue.remove();
			int pixelColor = canvas.getImage().getRGB(p.x, p.y);
			if (pixelColor != replacementColor && pixelColor == currentColor) {
				if (x >= 0 && x < 800 && y >= 0 && y < 600){
				canvas.getImage().setRGB(p.x, p.y, replacementColor);
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

			}
			}
		}
	}

	@Override
	public void apply(Graphics2D g) {
		int currentRGB = canvas.getImage().getRGB(x, y);
		int replacementRGB = color;
		floodFill(x, y, currentRGB, replacementRGB);
	}
}
