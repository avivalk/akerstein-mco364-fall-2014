package kerstein.paint.message;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import kerstein.paint.Canvas;

public class OnlineNetworkModule implements NetworkModule {

	private Socket socket;
	private MessageFactory factory;
	private Canvas canvas;

	public OnlineNetworkModule(MessageFactory factory, Canvas canvas) {
		this.factory = factory;
		this.canvas = canvas;
		try {
			socket = new Socket("192.168.117.107", 3773);
		} catch (IOException e) {
			e.printStackTrace();
		}
		MessageReciever receiver = new MessageReciever(socket, factory, canvas);
		receiver.start();
	}

	public Socket getSocket() {
		return socket;
	}

	@Override
	public void sendMessage(PaintMessage message) {
		try {
			OutputStream out = socket.getOutputStream();
			out.write((message.toString()).getBytes());
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
