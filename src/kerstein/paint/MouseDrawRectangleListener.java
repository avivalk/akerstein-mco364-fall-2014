package kerstein.paint;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import kerstein.paint.message.SendPaintMessage;
import kerstein.paint.message.ShapeMessage;

public class MouseDrawRectangleListener implements DrawListener {
	private Canvas canvas;
	private int x1, y1, x2, y2, width, height;
	private boolean fillShape;

	public MouseDrawRectangleListener(Canvas canvas) {
		this.canvas = canvas;
	}

	@Override
	public void mouseClicked(MouseEvent event) {

	}

	@Override
	public void mouseEntered(MouseEvent event) {

	}

	@Override
	public void mouseExited(MouseEvent event) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent event) {
		x1 = event.getX();
		y1 = event.getY();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		x2 = e.getX();
		y2 = e.getY();
		width = Math.abs(x2 - x1);
		height = Math.abs(y2 - y1);
		if (fillShape) {
			ShapeMessage fillRect = new ShapeMessage("SHAPE RECT", x1, y1, width, height, 
					canvas.getColor().getRGB(), canvas.getStrokeWidth(), true);
			SendPaintMessage paintMessage = new SendPaintMessage(fillRect.toString(), canvas.getSocket());
			paintMessage.sendMessage();
			// canvas.getGraphicsPen().fillRect((Math.min(x1, x2)),
			// (Math.min(y1, y2)), width, height);
		} else {
			ShapeMessage rectangle = new ShapeMessage("SHAPE RECT", x1, y1, width, height,  canvas
					.getColor().getRGB(), canvas.getStrokeWidth(), false);
			SendPaintMessage paintMessage = new SendPaintMessage(rectangle.toString(), canvas.getSocket());
			paintMessage.sendMessage();
			//canvas.getGraphicsPen().drawRect((Math.min(x1, x2)), (Math.min(y1, y2)), width, height);
		}
		canvas.repaint();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		x2 = e.getX();
		y2 = e.getY();
		width = Math.abs(x2 - x1);
		height = Math.abs(y2 - y1);
		canvas.repaint();
	}

	@Override
	public void drawPreview(Graphics2D g) {
		if (fillShape) {
			g.fillRect((Math.min(x1, x2)), (Math.min(y1, y2)), width, height);
		} else {
			g.drawRect((Math.min(x1, x2)), (Math.min(y1, y2)), width, height);
		}

	}

	public void setFillShape(boolean fillShape) {
		this.fillShape = fillShape;
	}

}
