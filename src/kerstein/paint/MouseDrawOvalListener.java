package kerstein.paint;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseDrawOvalListener implements MouseMotionListener, MouseListener {
	private Canvas canvas;
	private int x1, y1, x2, y2, width, height;

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

		System.out.println("press");
		System.out.println("x1: " + x1 + " y1:" + y1);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		x2 = e.getX();
		y2 = e.getY();
		width = Math.abs(x2 - x1);
		height = Math.abs(y2 - y1);
		canvas.drawAnOval(x1, y1, width, height);
		System.out.println("released");
		System.out.println("x1: " + x1 + " y1:" + y1);
		System.out.println("x2: " + x2 + " y2:" + y2);

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		x2 = e.getX();
		y2 = e.getY();
		width = x2 - x1;
		height = y2 - y1;
		System.out.println("drag");
		System.out.println("x1: " + x1 + " y1:" + y1);
		System.out.println("x2: " + x2 + " y2:" + y2);

	}

}
