package kerstein.paint;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;

public class BucketFillListener implements DrawListener {

	private int x;
	private int y;
	private Canvas canvas;	
	public BucketFillListener(Canvas canvas){
		this.canvas=canvas;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		x = e.getX();
		y=e.getY();
		int currentRGB=canvas.getImage().getRGB(x, y);
		Color replacementColor=canvas.getColor();
	    Color targetColor=new Color(currentRGB, true);
	    floodFill(x,y,targetColor, replacementColor);
	    canvas.repaint();
	    System.out.println("click");
}

	@Override
	public void mouseEntered(MouseEvent arg0) {

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
	
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void drawPreview(Graphics2D g) {
		
	}
	public void floodFill(int x, int y, Color targetColor,Color replacementColor) {

	    if (canvas.getImage().getRGB(x, y) != targetColor.getRGB()) return;
         if(canvas.getImage().getRGB(x, y)!=replacementColor.getRGB()){
	    canvas.getImage().setRGB(x, y, replacementColor.getRGB());
	    canvas.getGraphicsPen().fillOval(x,y,1,1);
	    floodFill(x - 1, y, targetColor, replacementColor);
	    floodFill(x + 1, y, targetColor, replacementColor);
	    floodFill(x, y - 1, targetColor, replacementColor);
	    floodFill(x, y + 1, targetColor, replacementColor);
	    return;
         }
	}
	}


