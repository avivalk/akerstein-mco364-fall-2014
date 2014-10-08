package kerstein.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.swing.JTextArea;

public class ChatSocketThread extends Thread {
	private Socket socket;
	private JTextArea messages;

	public ChatSocketThread(JTextArea messages, Socket socket) {
		this.messages = messages;
		this.socket = socket;
	}

	public void run() {

		int clientNo = 1;
		try {
			InputStream in = socket.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String line;
			while ((line = reader.readLine()) != null) {
				messages.append("\n" + line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		clientNo++;
	}

}
