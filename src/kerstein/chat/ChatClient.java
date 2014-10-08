package kerstein.chat;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;


public class ChatClient {
	private String output;
	private Socket socket;

	public ChatClient(String output, Socket socket) throws UnknownHostException, IOException {
		this.output = output;
		this.socket = socket;
		OutputStream out = socket.getOutputStream();
		out.write(output.getBytes());
		out.flush();
	}
}
