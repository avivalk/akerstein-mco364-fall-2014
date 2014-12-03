package kerstein.paint;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public class PencilListener implements  DrawListener {

	private Canvas canvas;
	private int sx;
	private int sy;
	private int x;
	private int y;
	private boolean onDrag;

	public PencilListener(Canvas canvas) {
		this.canvas = canvas;
		onDrag = false;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (onDrag) {
			x = e.getX();
			y = e.getY();
			canvas.getGraphicsPen().drawLine(sx, sy, x, y);
			canvas.repaint();
		}
		onDrag = true;
		sx = e.getX();
		sy = e.getY();

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (onDrag) {
			onDrag = false;
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void drawPreview(Graphics2D g) {
		canvas.getGraphicsPen().drawLine(sx, sy, x, y);

	}

}
