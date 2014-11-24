package kerstein.paint;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseDrawLineListener implements MouseListener {
	private Canvas canvas;
	private int x1, y1, x2, y2;

	public MouseDrawLineListener(Canvas canvas) {
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
		canvas.setLine(x1, y1, x2, y2);
		System.out.println("released");
		System.out.println("x1: " + x1 + " y1:" + y1);
		System.out.println("x2: " + x2 + " y2:" + y2);

	}

}
