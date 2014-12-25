package kerstein.paint.message;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class OnlineNetworkModule implements NetworkModule {

	private String message;
	private Socket socket;

	public OnlineNetworkModule(String message, Socket socket) {
		this.message = message;
		this.socket = socket;
	}

	@Override
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
