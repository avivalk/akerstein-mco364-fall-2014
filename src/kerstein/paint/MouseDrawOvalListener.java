package kerstein.paint;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import kerstein.paint.message.SendPaintMessage;
import kerstein.paint.message.ShapeMessage;

public class MouseDrawOvalListener implements DrawListener {
	private Canvas canvas;
	private int x1, y1, x2, y2, width, height;
	private boolean fillShape;

	public MouseDrawOvalListener(Canvas canvas) {
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
			ShapeMessage fillOval = new ShapeMessage("SHAPE OVAL", x1, y1, width, height, 
					canvas.getColor().getRGB(),canvas.getStrokeWidth(), true);
			SendPaintMessage paintMessage = new SendPaintMessage(fillOval.toString(), canvas.getSocket());
			paintMessage.sendMessage();
			// canvas.getGraphicsPen().fillOval((Math.min(x1,x2)),(Math.min(y1,y2)),
			// width, height);
		} else {
			ShapeMessage drawOval = new ShapeMessage("SHAPE OVAL", x1, y1, width, height,
					canvas.getColor().getRGB(), canvas.getStrokeWidth(), false);
			SendPaintMessage paintMessage = new SendPaintMessage(drawOval.toString(), canvas.getSocket());
			paintMessage.sendMessage();
			//canvas.getGraphicsPen().drawOval((Math.min(x1, x2)), (Math.min(y1, y2)), width, height);
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

	public void setFillShape(boolean fillShape) {
		this.fillShape = fillShape;
	}

	@Override
	public void drawPreview(Graphics2D g) {
		if (fillShape) {
			g.fillOval((Math.min(x1, x2)), (Math.min(y1, y2)), width, height);
		} else {
			g.drawOval((Math.min(x1, x2)), (Math.min(y1, y2)), width, height);
		}
	}

}
