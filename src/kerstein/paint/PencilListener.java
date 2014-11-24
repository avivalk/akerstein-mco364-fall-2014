package kerstein.paint;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class PencilListener implements MouseMotionListener {

	private Canvas canvas;
	private int sx;
	private int sy;
	private boolean onDrag;

	public PencilListener(Canvas canvas) {
		this.canvas = canvas;
		onDrag = false;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (onDrag) {
			int x = e.getX();
			int y = e.getY();
			canvas.setLine(sx, sy, x, y);
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

}
