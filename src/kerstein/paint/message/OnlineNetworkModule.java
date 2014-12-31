package kerstein.paint.message;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class OnlineNetworkModule implements NetworkModule {

	private Socket socket;

	public OnlineNetworkModule(Socket socket) {
		this.socket = socket;
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
