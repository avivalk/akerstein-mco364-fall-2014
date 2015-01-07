package kerstein.paint.message;

import java.awt.Graphics2D;

import kerstein.paint.Canvas;

public class LoopbackNetworkModule implements NetworkModule {

	private Canvas canvas;

	public LoopbackNetworkModule(Canvas canvas) {
		this.canvas = canvas;
	}

	@Override
	public void sendMessage(PaintMessage message) {
		message.apply((Graphics2D) canvas.getImage().getGraphics());
		canvas.repaint();
	}
}
