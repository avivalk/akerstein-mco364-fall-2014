package kerstein.paint.message;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class SendPaintMessage {

	private String message;
	private Socket socket;

	public SendPaintMessage(String message, Socket socket) {
		this.message = message;
		this.socket = socket;
	}

	public void sendMessage() {
		try {
			OutputStream out = socket.getOutputStream();
			out.write((message).getBytes());
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
