package kerstein.paint.message;

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
		return x + " " + y + " " + strokeWidth + " " + color + " " + type + " " + width + " " + height + " " + fill
				+ " " + "\n";
	}

	@Override
	public void apply(Graphics2D g) {
		switch (type) {
		case "rectangle":
			if (fill) {
				g.fillRect(x, y, width, height);
			} else {
				g.drawRect(x, y, width, height);
			}
		case "oval":
			if (fill) {
				g.fillOval(x, y, width, height);
			} else {
				g.drawOval(x, y, width, height);
			}
		}
	}

}
