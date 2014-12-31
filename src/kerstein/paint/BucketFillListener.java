package kerstein.paint;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.Queue;

import kerstein.paint.message.BucketFillMessage;
import kerstein.paint.message.LoopbackNetworkModule;
import kerstein.paint.message.NetworkModule;
import kerstein.paint.message.OnlineNetworkModule;

public class BucketFillListener implements DrawListener {

	private int x;
	private int y;
	private Canvas canvas;

	public BucketFillListener(Canvas canvas) {
		this.canvas = canvas;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		x = e.getX();
		y = e.getY();
		BucketFillMessage bucket = new BucketFillMessage(x, y, canvas.getColor().getRGB(), canvas);
		NetworkModule network = new OnlineNetworkModule(canvas.getSocket());
		network.sendMessage(bucket);
		//LoopbackNetworkModule loop = new LoopbackNetworkModule(canvas);
		//loop.sendMessage(bucket);
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {

	}

	@Override
	public void mouseExited(MouseEvent arg0) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {

	}

	@Override
	public void mouseMoved(MouseEvent arg0) {

	}

	@Override
	public void drawPreview(Graphics2D g) {

	}

}
