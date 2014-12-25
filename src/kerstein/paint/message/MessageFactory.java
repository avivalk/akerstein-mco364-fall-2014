package kerstein.paint.message;

import kerstein.paint.Canvas;

public class MessageFactory {

	private PaintMessage paintMessage;
	private Canvas canvas;

	public MessageFactory(Canvas canvas) {
		this.canvas = canvas;
	}

	public PaintMessage getMessage(String message) {
		PaintMessage paintMessage = null;
		String[] action = message.split(" ");
		switch (action[0]) {
		case "LINE":
			paintMessage = new LineMessage(Integer.parseInt(action[1]), Integer.parseInt(action[2]),
					Integer.parseInt(action[3]), Integer.parseInt(action[4]), Integer.parseInt(action[5]),
					Integer.parseInt(action[6]));
			break;
		case "SHAPE":
			paintMessage = new ShapeMessage(action[1], Integer.parseInt(action[2]), Integer.parseInt(action[3]),
					Integer.parseInt(action[4]), Integer.parseInt(action[5]), Integer.parseInt(action[6]),
					Integer.parseInt(action[7]), Boolean.parseBoolean(action[8]));
			break;
		case "CLEAR":
			paintMessage = new ClearMessage();
			break;
		case "BUCKET_FILL":
			paintMessage = new BucketFillMessage(Integer.parseInt(action[1]), Integer.parseInt(action[2]),
					Integer.parseInt(action[3]), canvas);
			break;
		}
		return paintMessage;
	}
}
