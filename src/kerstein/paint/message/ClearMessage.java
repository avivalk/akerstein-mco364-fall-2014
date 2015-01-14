package kerstein.paint.message;

import java.awt.Graphics2D;

import kerstein.paint.Canvas;

public class ClearMessage implements PaintMessage {
	private Canvas canvas;

	public ClearMessage(Canvas canvas) {
		this.canvas = canvas;
	}

	public String toString() {
		return "CLEAR" + "\n";
	}

	@Override
	public void apply(Graphics2D g) {
		canvas.reset();
	}

}
