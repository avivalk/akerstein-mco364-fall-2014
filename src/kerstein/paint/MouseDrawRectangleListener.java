package kerstein.paint;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseDrawRectangleListener implements MouseMotionListener, MouseListener{
	private Canvas canvas;
	int currentX, currentY, startX, startY;
	boolean dragMode = false;
public MouseDrawRectangleListener(Canvas canvas){
		this.canvas=canvas;
	}
	public void mouseClicked( MouseEvent event ) {
		dragMode = true;
   		startX = event.getX();
   		startY = event.getY();
    }


// handle event when mouse enters area 
    public void mouseEntered( MouseEvent event ) {
    	
    }

// handle event when mouse exits area 
    public void mouseExited(  MouseEvent event ) {


    }	  

// handle event when user moves mouse 
    public void mouseMoved(  MouseEvent event ) {
    	
	}
    public void mousePressed( MouseEvent event )
    
       {
    	dragMode = true;
   		startX = event.getX();
   		startY = event.getY();
     
    
       
    
     
    
 
    
       }
     
    
       // handle event when mouse released after dragging
    @Override
       public void mouseReleased( MouseEvent e )
    
       {
   		dragMode = false;
   	}
   	
	@Override
	public void mouseDragged(MouseEvent e) {
		if (dragMode == true) {
   			currentX = e.getX();
   			currentY = e.getY();
   		canvas.drawRectangle(startX,startY,currentX,currentY,dragMode);
   		}		
	}

}
