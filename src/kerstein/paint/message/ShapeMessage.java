package kerstein.paint.message;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class ShapeMessage implements PaintMessage {
	private int x;
	private int y;
	private int strokeWidth;
	private int color;
	private String type;
	private int width;
	private int height;
	private boolean fill;

	public ShapeMessage(String type, int x, int y, int width, int height, int color, int strokeWidth, boolean fill) {
		this.x = x;
		this.y = y;
		this.strokeWidth = strokeWidth;
		this.color = color;
		this.type = type;
		this.width = width;
		this.height = height;
		this.fill = fill;
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

	public int getStrokeWidth() {
		return strokeWidth;
	}

	public void setStrokeWidth(int strokeWidth) {
		this.strokeWidth = strokeWidth;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public boolean isFill() {
		return fill;
	}

	public void setFill(boolean fill) {
		this.fill = fill;
	}

	@Override
	public String toString() {
		return "SHAPE" + " " + type + " " + x + " " + y + " " + width + " " + height + " " + color + " " + strokeWidth
				+ " " + fill + "\n";
	}

	@Override
	public void apply(Graphics2D g) {
		if (strokeWidth < 1) {
			strokeWidth = 1;
		}
		BasicStroke basic = new BasicStroke(strokeWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
		g.setStroke(basic);
		g.setColor(new Color(color));
		switch (type) {
		case "RECT":
			if (fill) {
				g.fillRect(x, y, width, height);
			} else {
				g.drawRect(x, y, width, height);
			}
			break;
		case "OVAL":
			if (fill) {
				g.fillOval(x, y, width, height);
			} else {
				g.drawOval(x, y, width, height);
			}
		}
	}

}
