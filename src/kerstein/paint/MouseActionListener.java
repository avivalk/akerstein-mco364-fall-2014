package kerstein.paint;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseActionListener implements MouseMotionListener, MouseListener {

	private Canvas canvas;
	int sx;
	int sy;
	boolean onDrag;

	public MouseActionListener(Canvas canvas) {
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

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}

}
