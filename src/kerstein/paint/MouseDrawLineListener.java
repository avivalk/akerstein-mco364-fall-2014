package kerstein.paint;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;


public class MouseDrawLineListener implements DrawListener {
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
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		x2 = e.getX();
		y2 = e.getY();
		canvas.getGraphicsPen().drawLine(x1, y1, x2, y2);
	}

	@Override
	public void mouseDragged(MouseEvent event) {
		x2 = event.getX();
		y2 = event.getY();
         canvas.repaint();		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawPreview(Graphics2D g) {
		g.drawLine(x1, y1, x2, y2);
		
	}

}
