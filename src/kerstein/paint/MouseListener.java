package kerstein.paint;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MouseListener implements MouseMotionListener {

	private Canvas canvas;

	public MouseListener(Canvas canvas) {
		this.canvas = canvas;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		int x2=e.getX();  
		    int     y2=e.getY();  
		      canvas.setPoint2(x2,y2);  
		   canvas.repaint();  
		     int  x1=x2;  
		    int  y1=y2;  
		      canvas. setPoint(x1,y1);  

	}

	public void mouseReleased(MouseEvent e) {
		canvas.setPoint2(e.getX(), e.getY());
		canvas.repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
